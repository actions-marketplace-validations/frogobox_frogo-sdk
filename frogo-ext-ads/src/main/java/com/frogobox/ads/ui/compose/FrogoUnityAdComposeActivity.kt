package com.frogobox.ads.ui.compose

import com.frogobox.ads.delegate.UnityAdDelegates
import com.frogobox.ads.delegate.UnityAdDelegatesImpl
import com.frogobox.compose.view.FrogoComposeActivity

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * ImplementationAdmob
 * Copyright (C) 31/10/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.admobhelper
 *
 */

abstract class FrogoUnityAdComposeActivity : FrogoComposeActivity(),
    UnityAdDelegates by UnityAdDelegatesImpl() {

    companion object {
        val TAG: String = FrogoUnityAdComposeActivity::class.java.simpleName
    }

    override fun setupDelegates() {
        super.setupDelegates()
        setupUnityAdDelegates(this)
    }

    override fun setupMonetized() {
        super.setupMonetized()
    }

}
