package com.devindie.hieuvetraitim.ui.screens.home

import android.content.Context
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.devindie.hieuvetraitim.R
import com.devindie.hieuvetraitim.VNCharacterUtils
import com.devindie.hieuvetraitim.colorIntToHex
import com.devindie.hieuvetraitim.datasource.FirebaseUtils
import com.devindie.hieuvetraitim.devutils.log
import com.devindie.hieuvetraitim.entities.Feeling
import com.devindie.hieuvetraitim.rawResourceIdByName
import com.devindie.hieuvetraitim.ui.screens.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel : BaseViewModel() {
    private val _feelings: MutableLiveData<ArrayList<Feeling>> = MutableLiveData()

    private var _emotionText = MutableLiveData("")
    private var _emotionIcon = MutableLiveData("")
    private var _emotionBackgroundResId = MutableLiveData("")
    private var _emotionBackgroundColors = MutableLiveData<Array<Int>>()

    val feelings: LiveData<ArrayList<Feeling>> get() = _feelings
    val emotionText: LiveData<String> get() = _emotionText
    val emotionIcon: LiveData<String> get() = _emotionIcon
    val emotionBackgroundImageUrl: LiveData<String> get() = _emotionBackgroundResId
    val emotionBackgroundColors: LiveData<Array<Int>> get() = _emotionBackgroundColors

    init {
        FirebaseUtils.getAllFeelings {
            _feelings.value = it
            initBackground()
        }
    }

    private fun getImageNameByEmotionTitle(emotionTitle: String): String {
        return VNCharacterUtils.removeAccent(emotionTitle).replace(" ", "_").lowercase()
    }

    private fun initBackground() {
        var firstEmotionTitle = _feelings.value?.firstOrNull()?.title.toString()
        firstEmotionTitle = getImageNameByEmotionTitle(firstEmotionTitle)
        _emotionBackgroundResId.postValue(firstEmotionTitle)
    }

    fun changeEmotion(p0: String, context: Context) {
        val feeling = _feelings.value?.firstOrNull { it.title?.lowercase() == p0.lowercase() }
        log("feeling: ${feeling?.title}")
        feeling?.let {
            val imageResourceId = getImageNameByEmotionTitle(feeling.title.toString())
            val imageRawResource = context.rawResourceIdByName(imageResourceId)
            viewModelScope.launch(Dispatchers.Default) {
                try {
                    val bitmap =
                        BitmapFactory.decodeResource(context.resources, imageRawResource)
                    Palette.from(bitmap).generate {
                        val lightVibrant = if (it?.lightVibrantSwatch?.rgb != null) {
                            it.lightVibrantSwatch?.rgb
                        } else {
                            it?.vibrantSwatch?.rgb
                        } ?: 0
                        val darkMute = if (it?.darkMutedSwatch?.rgb != null) {
                            it.darkMutedSwatch?.rgb
                        } else {
                            it?.mutedSwatch?.rgb
                        } ?: 0
                        _emotionBackgroundColors.postValue(arrayOf(lightVibrant, darkMute))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            _emotionBackgroundResId.postValue(imageResourceId)
        }
    }
}