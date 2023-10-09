package com.devindie.hieuvetraitim.ui.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devindie.hieuvetraitim.databinding.FragmentHomeBinding
import com.devindie.hieuvetraitim.datasource.FirebaseUtils
import com.devindie.hieuvetraitim.devutils.log
import com.devindie.hieuvetraitim.ui.screens.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        binding: FragmentHomeBinding?
    ): View {
        Log.e("binding?.root: ", binding?.root.toString())
        return FragmentHomeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Home", "SHOW")
        FirebaseUtils.getAllFeelings { feelings ->
            for (feel in feelings) {
                log(feel.title.toString())
                for (sub in feel.subtitle) {
                    log(sub.title.toString())
                }
            }
        }
    }

}