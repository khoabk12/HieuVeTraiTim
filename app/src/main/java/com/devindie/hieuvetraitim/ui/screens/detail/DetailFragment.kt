package com.devindie.hieuvetraitim.ui.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.devindie.hieuvetraitim.databinding.FragmentDetailBinding
import com.devindie.hieuvetraitim.ui.screens.base.BaseFragment

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {
    override val viewModelClass: Class<DetailViewModel>
        get() = DetailViewModel::class.java

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    
}