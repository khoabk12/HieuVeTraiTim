package com.devindie.hieuvetraitim.ui.screens.home

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.devindie.hieuvetraitim.databinding.FragmentHomeBinding
import com.devindie.hieuvetraitim.devutils.log
import com.devindie.hieuvetraitim.observeOnce
import com.devindie.hieuvetraitim.rawResourceIdByName
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
        viewModelObservers()
        viewBindingListeners()
    }

    private fun viewBindingListeners() {
        binding?.edtEmotion?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if ((p0?.length ?: 0) > 0) {
                    viewModel.changeEmotion(p0.toString(), requireContext())
                }
            }
        })
    }

    private fun viewModelObservers() {
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
            log("imageResId: $it")
            val imageRes = context?.rawResourceIdByName(it)
            binding?.imgBackground?.let { imageView ->
                Glide.with(this).load(imageRes).into(imageView)
            }
        }
        viewModel.emotionIcon.observe(viewLifecycleOwner) { emotionIcon ->
        }
        viewModel.emotionText.observe(viewLifecycleOwner) { emotionText ->
            binding?.edtEmotion?.setText(emotionText)
        }

        viewModel.emotionBackgroundColors.observe(viewLifecycleOwner) {
            val gd = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, it.toIntArray())
            binding?.imgBackground?.setBackgroundDrawable(gd)
        }
    }

}