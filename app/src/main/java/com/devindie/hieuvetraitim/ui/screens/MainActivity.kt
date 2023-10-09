package com.devindie.hieuvetraitim.ui.screens

import android.os.Bundle
import androidx.fragment.app.commit
import com.devindie.hieuvetraitim.databinding.ActivityMainBinding
import com.devindie.hieuvetraitim.ui.screens.home.HomeFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeFragment by lazy { HomeFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        inflateHomeFragment()
    }

    private fun inflateHomeFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(binding.fragmentContainer.id, homeFragment)
        }
    }
}