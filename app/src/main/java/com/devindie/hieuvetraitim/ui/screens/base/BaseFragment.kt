package com.devindie.hieuvetraitim.ui.screens.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.devindie.hieuvetraitim.R
import com.devindie.hieuvetraitim.ui.screens.MainActivity


abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {

    protected var binding: VB? = null
    abstract val viewModelClass: Class<VM>
    protected val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    protected val mainActivity by lazy {
        requireActivity() as MainActivity
    }


    protected val navController: NavController by lazy {
        val navHostFragment = mainActivity.supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateView(inflater, container, savedInstanceState)
        return binding?.root
    }

    /**
     * [ViewBinding].inflate(inflater, container, false)
     */
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

abstract class BaseAndroidViewModeFragment<AVM : AndroidViewModel, VB : ViewBinding> : Fragment() {

    protected var binding: VB? = null
    abstract val viewModelClass: Class<AVM>
    protected val viewModel: AVM by lazy {
        ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(viewModelClass)
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