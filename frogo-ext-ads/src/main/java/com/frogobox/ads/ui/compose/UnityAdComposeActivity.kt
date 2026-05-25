package com.frogobox.ads.ui.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.frogobox.ads.delegate.UnityAdDelegates
import com.frogobox.ads.delegate.UnityAdDelegatesImpl

/**
 * Created by Faisal Amir on 07/02/23
 * https://github.com/amirisback
 */

abstract class UnityAdComposeActivity : AppCompatActivity(),
    UnityAdDelegates by UnityAdDelegatesImpl() {

    companion object {
        val TAG: String = UnityAdComposeActivity::class.java.simpleName
    }

    open fun setupMonetized() {
        setupUnityAdDelegates(this)
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
