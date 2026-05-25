package com.frogobox.ads.ui.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.frogobox.ads.delegate.AdmobDelegates
import com.frogobox.ads.delegate.AdmobDelegatesImpl
import androidx.lifecycle.lifecycleScope

/**
 * Created by Faisal Amir on 07/02/23
 * https://github.com/amirisback
 */

abstract class AdmobComposeActivity : AppCompatActivity(),
    AdmobDelegates by AdmobDelegatesImpl() {

    companion object {
        val TAG: String = AdmobComposeActivity::class.java.simpleName
    }

    open fun setupMonetized() {
        setupAdmobDelegates(this)
        lifecycleScope.launchWhenCreated {
            // Initialize the Google Mobile Ads SDK on a background thread.
            setupAdmobApp()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupCompose()
        }
        setupMonetized()
    }

    @Composable
    abstract fun SetupCompose()

}
