package com.devindie.hieuvetraitim.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devindie.hieuvetraitim.VNCharacterUtils
import com.devindie.hieuvetraitim.datasource.FirebaseUtils
import com.devindie.hieuvetraitim.devutils.log
import com.devindie.hieuvetraitim.entities.Feeling
import com.devindie.hieuvetraitim.ui.screens.base.BaseViewModel
import java.util.Locale

class HomeViewModel : BaseViewModel() {
    private val _feelings: MutableLiveData<ArrayList<Feeling>> = MutableLiveData()

    private var _emotionText = MutableLiveData("")
    private var _emotionIcon = MutableLiveData("")
    private var _emotionBackgroundImageUrl = MutableLiveData("")
    private var _emotionBackgroundColors = MutableLiveData<Array<String>>()

    val feelings: LiveData<ArrayList<Feeling>> get() = _feelings
    val emotionText: LiveData<String> get() = _emotionText
    val emotionIcon: LiveData<String> get() = _emotionIcon
    val emotionBackgroundImageUrl: LiveData<String> get() = _emotionBackgroundImageUrl
    val emotionBackgroundColors: LiveData<Array<String>> get() = _emotionBackgroundColors

    init {
        FirebaseUtils.getAllFeelings {
            _feelings.value = it
            initBackground()
        }
    }

    private fun initBackground() {
        var firstEmotionTitle = _feelings.value?.first()?.title
        firstEmotionTitle =
            VNCharacterUtils.removeAccent(firstEmotionTitle).replace(" ", "_").lowercase()
        log("firstEmotionTitle: $firstEmotionTitle")
        _emotionBackgroundImageUrl.postValue(firstEmotionTitle)
    }

    /**
     * Trigger everytime user scroll and idle at a feeling
     */


}