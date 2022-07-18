package com.frogobox.appsdk.core

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.frogobox.appsdk.BuildConfig
import com.frogobox.sdk.view.FrogoBindActivity

/*
 * Created by faisalamir on 02/08/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
abstract class BaseActivity<VB : ViewBinding> : FrogoBindActivity<VB>() {

    override fun setupDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }

}