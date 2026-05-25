package com.frogobox.compose.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import java.util.Calendar

abstract class FrogoComposeActivity : AppCompatActivity() {

    companion object {
        val TAG: String = FrogoComposeActivity::class.java.simpleName
    }

    protected val frogoActivity: FrogoComposeActivity by lazy { this }

    protected val textCopyright: String by lazy {
        "${getString(com.frogobox.sdk.R.string.about_all_right_reserved)} " +
                "${getString(com.frogobox.sdk.R.string.about_copyright)} ${Calendar.getInstance().get(Calendar.YEAR)}"
    }

    // ---------------------------------------------------------------------------------------------
    // Activity Result API
    // ---------------------------------------------------------------------------------------------
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            setupActivityResultExt(result)
        }

    // ---------------------------------------------------------------------------------------------
    // Lifecycle
    // ---------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupEnableEdgeToEdge()
        setupSetOnApplyWindowInsetsListener()
        setupDoOnBackPressedExt()
        setupDelegates()
        setupPiracyMode()
        setupMonetized()
        setupViewModel()
        onCreateExt(savedInstanceState)
        setContent {
            SetupCompose()
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Back Press Handling
    // ---------------------------------------------------------------------------------------------

    /** Called when back button pressed — default behavior is [finish] */
    open fun doOnBackPressedExt() {
        finish()
    }

    /** Allows manual trigger of back press */
    open fun onBackPressedExt() {
        onBackPressedDispatcher.onBackPressed()
    }

    /** Setup modern back press listener for all Android versions */
    open fun setupDoOnBackPressedExt() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                doOnBackPressedExt()
            }
        } else {
            onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() = doOnBackPressedExt()
                }
            )
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Enable Edge To Edge
    // ---------------------------------------------------------------------------------------------
    open fun setupEnableEdgeToEdge() {
        enableEdgeToEdge()
    }

    // ---------------------------------------------------------------------------------------------
    // Setup Hooks
    // ---------------------------------------------------------------------------------------------
    open fun setupDebugMode(): Boolean = true
    open fun setupSetOnApplyWindowInsetsListener() {}
    open fun setupPiracyMode() {}
    open fun setupDelegates() {}
    open fun setupViewModel() {}
    open fun setupMonetized() {}
    open fun setupActivityResultExt(result: ActivityResult) {}
    open fun onCreateExt(savedInstanceState: Bundle?) {}

    // ---------------------------------------------------------------------------------------------
    // Activity Result Helpers
    // ---------------------------------------------------------------------------------------------
    open fun startActivityResultExt(intent: Intent) {
        activityResultLauncher.launch(intent)
    }

    // ---------------------------------------------------------------------------------------------
    // System UI Controls
    // ---------------------------------------------------------------------------------------------
    /** Force fullscreen mode */
    open fun setupFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    /** Hide system bars (immersive mode) */
    open fun setupHideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    @Composable
    abstract fun SetupCompose()

}

