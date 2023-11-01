package com.devindie.hieuvetraitim.ui.screens.home

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.devindie.hieuvetraitim.databinding.FragmentHomeBinding
import com.devindie.hieuvetraitim.observeOnce
import com.devindie.hieuvetraitim.rawResourceIdByName
import com.devindie.hieuvetraitim.resIdByName
import com.devindie.hieuvetraitim.ui.screens.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewObservers()
    }

    private fun viewObservers() {
        viewModel.feelings.observeOnce(viewLifecycleOwner) { feelings ->
            val emotions = feelings.map { it.title ?: "" }.toTypedArray()
            if (emotions.isNotEmpty()) {
                binding?.edtEmotion?.apply {
                    setAutoCompleteArray(emotions)
                    filters = arrayOf(InputFilter.LengthFilter(40))
                    threshold = 1
                }
            }
        }

        viewModel.emotionBackgroundImageUrl.observe(viewLifecycleOwner) {
            val imageResId = context?.rawResourceIdByName(it)
            binding?.imgBackground?.let { imageView ->
                Glide.with(this).load(imageResId).into(imageView)
            }
        }
        viewModel.emotionIcon.observe(viewLifecycleOwner) { emotionIcon ->
        }
        viewModel.emotionText.observe(viewLifecycleOwner) { emotionText ->
            binding?.edtEmotion?.setText(emotionText)
        }
    }

}