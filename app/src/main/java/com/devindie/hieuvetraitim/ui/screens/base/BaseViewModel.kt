package com.devindie.hieuvetraitim.ui.screens.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.devindie.hieuvetraitim.MyApplication

open class BaseViewModel : ViewModel()
open class BaseAndroidViewModel(application: MyApplication) :AndroidViewModel(application)