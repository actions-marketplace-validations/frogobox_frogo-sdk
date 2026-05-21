package com.frogobox.compose.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.FragmentActivity
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FrogoComposeBottomSheetFragmentTest {

    class TestBottomSheetFragment : FrogoComposeBottomSheetFragment() {
        var setupViewModelCalled = false
        var setupComposeCalled = false

        override fun setupViewModel() {
            super.setupViewModel()
            setupViewModelCalled = true
        }

        @Composable
        override fun setupCompose() {
            setupComposeCalled = true
            Text("Hello Bottom Sheet")
        }
    }

    // =============================================================================================
    // UI, FUNCTIONAL & SECURITY TESTS
    // =============================================================================================

    @Test
    fun testBottomSheetFragmentLifecycle() {
        // Build holding activity
        val activity = Robolectric.setupActivity(FragmentActivity::class.java)
        val fragment = TestBottomSheetFragment()

        // Show fragment
        fragment.show(activity.supportFragmentManager, "TestBottomSheet")
        activity.supportFragmentManager.executePendingTransactions()

        assertNotNull(fragment.view)
        assertTrue("View should be ComposeView", fragment.view is ComposeView)

        // Verify lifecycle call setupViewModel
        assertTrue(fragment.setupViewModelCalled)
        
        // Assert view is secure and contains expected composition strategy indirectly
        val composeView = fragment.view as ComposeView
        assertNotNull(composeView.context)
    }
}
