package com.devindie.hieuvetraitim.ui.screens.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {

    protected var binding: VB? = null
    abstract val viewModelClass: Class<VM>
    protected val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateView(inflater, container, savedInstanceState)
        return binding?.root
    }

    abstract fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): VB?

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}