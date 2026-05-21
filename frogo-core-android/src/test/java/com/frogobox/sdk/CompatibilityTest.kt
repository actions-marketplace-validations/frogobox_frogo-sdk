package com.frogobox.sdk

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.test.core.app.ApplicationProvider
import com.frogobox.sdk.util.FrogoFunc
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowNetworkInfo

@Suppress("DEPRECATION")
@RunWith(RobolectricTestRunner::class)
class CompatibilityTest {

    @Test
    @Config(sdk = [23]) // Compatibility check on API 23 (Android M - uses NetworkCapabilities branch)
    fun testIsNetworkConnected_API23_Disconnected() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // On API 23+ with no active network set, should return false
        val isConnected = FrogoFunc.isNetworkConnected(context)
        assertFalse("Should return false when no active network on API 23", isConnected)
    }

    @Test
    @Config(sdk = [28]) // Compatibility check on API 28 (Android Pie)
    fun testIsNetworkConnected_API28_Disconnected() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        val isConnected = FrogoFunc.isNetworkConnected(context)
        assertFalse("Should return false when no active network on API 28", isConnected)
    }

    @Test
    @Config(sdk = [33]) // Compatibility check on API 33 (Android Tiramisu)
    fun testIsNetworkConnected_API33_Disconnected() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        val isConnected = FrogoFunc.isNetworkConnected(context)
        assertFalse("Should return false when no active network on API 33", isConnected)
    }

    @Test
    @Config(sdk = [34]) // Compatibility check on API 34 (Android UpsideDownCake)
    fun testIsNetworkConnected_API34_Disconnected() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        val isConnected = FrogoFunc.isNetworkConnected(context)
        assertFalse("Should return false when no active network on API 34", isConnected)
    }

    @Test
    fun testRandomNumberUtility() {
        // Simple range test for utility function across default SDK
        val start = 10
        val end = 20
        val randomNum = FrogoFunc.randomNumber(start, end)
        assertTrue("Random number $randomNum should be in range [$start, $end]", randomNum in start..end)
    }
}
