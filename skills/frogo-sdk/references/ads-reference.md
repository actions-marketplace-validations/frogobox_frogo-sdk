# Frogo Ext Ads — Full API Reference

Package: `com.frogobox.ads`

## Application

### FrogoAdmobApplication
Base Application class for AdMob-enabled apps.

```kotlin
class MyApp : FrogoAdmobApplication() {
    override fun onCreateExt() {
        super.onCreateExt()
    }
}
```

---

## Delegate Pattern

The recommended approach uses Kotlin's **delegation pattern** for clean separation of ad logic.

### AdmobDelegates Interface

```kotlin
interface AdmobDelegates {
    fun setupAdmobDelegates(activity: AppCompatActivity)
    fun showAdConsent(callback: IFrogoAdConsent)
    fun setupAdmobApp()

    // Banner Ads (8 overloads)
    fun showAdBanner(mAdView: AdView)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int)
    fun showAdBanner(mAdView: AdView, keyword: List<String>)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, keyword: List<String>)
    fun showAdBanner(mAdView: AdView, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, keyword: List<String>, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobBannerCallback)

    // Banner Ads with Container (8 overloads)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, keyword: List<String>)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int, keyword: List<String>)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, callback: FrogoAdmobBannerCallback)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int, callback: FrogoAdmobBannerCallback)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, keyword: List<String>, callback: FrogoAdmobBannerCallback)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobBannerCallback)

    // Interstitial Ads (8 overloads)
    fun showAdInterstitial(interstitialAdUnitId: String)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int)
    fun showAdInterstitial(interstitialAdUnitId: String, keyword: List<String>)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int, keyword: List<String>)
    fun showAdInterstitial(interstitialAdUnitId: String, callback: FrogoAdmobInterstitialCallback)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int, callback: FrogoAdmobInterstitialCallback)
    fun showAdInterstitial(interstitialAdUnitId: String, keyword: List<String>, callback: FrogoAdmobInterstitialCallback)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobInterstitialCallback)

    // Rewarded Ads (4 overloads)
    fun showAdRewarded(mAdUnitIdRewarded: String, callback: FrogoAdmobRewardedCallback)
    fun showAdRewarded(mAdUnitIdRewarded: String, timeoutMilliSecond: Int, callback: FrogoAdmobRewardedCallback)
    fun showAdRewarded(mAdUnitIdRewarded: String, keyword: List<String>, callback: FrogoAdmobRewardedCallback)
    fun showAdRewarded(mAdUnitIdRewarded: String, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobRewardedCallback)

    // Rewarded Interstitial Ads (4 overloads)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, callback: FrogoAdmobRewardedCallback)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, timeoutMilliSecond: Int, callback: FrogoAdmobRewardedCallback)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, keyword: List<String>, callback: FrogoAdmobRewardedCallback)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobRewardedCallback)
}
```

### Implementation Class
```kotlin
class AdmobDelegatesImpl : AdmobDelegates { /* full implementation */ }
```

### Usage Example

```kotlin
class MyActivity : AppCompatActivity(), AdmobDelegates by AdmobDelegatesImpl() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdmobDelegates(this)

        // Show banner ad using XML AdView
        showAdBanner(binding.adView)

        // Show banner ad with timeout and keywords
        showAdBanner(
            mAdView = binding.adView,
            timeoutMilliSecond = 5000,
            keyword = listOf("games", "apps"),
            callback = object : FrogoAdmobBannerCallback {
                override fun onAdLoaded(tag: String, message: String) { }
                override fun onAdFailedToLoad(tag: String, errorCode: String, errorMessage: String) { }
                override fun onAdOpened(tag: String, message: String) { }
                override fun onAdClicked(tag: String, message: String) { }
                override fun onAdClosed(tag: String, message: String) { }
            }
        )

        // Show banner in a container programmatically
        showAdBannerContainer(
            bannerAdUnitId = "ca-app-pub-xxxxx/xxxxx",
            mAdsSize = AdSize.BANNER,
            container = binding.adContainer
        )

        // Show interstitial ad
        showAdInterstitial("ca-app-pub-xxxxx/xxxxx")

        // Show rewarded ad
        showAdRewarded("ca-app-pub-xxxxx/xxxxx", object : FrogoAdmobRewardedCallback {
            override fun onUserEarnedReward(tag: String, rewardItem: RewardItem) {
                // Grant reward
            }
            override fun onShowAdRequestProgress(tag: String, message: String) { }
            override fun onHideAdRequestProgress(tag: String, message: String) { }
            override fun onAdDismissed(tag: String, message: String) { }
            override fun onAdFailed(tag: String, errorMessage: String) { }
            override fun onAdLoaded(tag: String, message: String) { }
            override fun onAdShowed(tag: String, message: String) { }
        })
    }
}
```

---

## FrogoAdDelegates

Higher-level combined delegate that wraps both AdMob and Unity ad functionality.

```kotlin
interface FrogoAdDelegates {
    fun setupFrogoAdDelegates(activity: AppCompatActivity)

    fun showAdmobXUnityAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        callback: FrogoAdInterstitialCallback
    )

    fun showAdmobXUnityAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        timeout: Int,
        callback: FrogoAdInterstitialCallback
    )

    fun showUnityXAdmobAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        callback: FrogoAdInterstitialCallback
    )

    fun showUnityXAdmobAdInterstitial(
        admobInterstitialId: String,
        unityInterstitialId: String,
        timeout: Int,
        callback: FrogoAdInterstitialCallback
    )
}
```

---

## Unity Ads

### UnityAdDelegates Interface
```kotlin
interface UnityAdDelegates {
    fun setupUnityAdDelegates(activity: AppCompatActivity)

    fun setupUnityAdApp(
        testMode: Boolean,
        unityGameId: String,
        callback: FrogoUnityAdInitializationCallback? = null
    )

    fun showUnityAdInterstitial(
        adInterstitialUnitId: String,
        callback: FrogoUnityAdInterstitialCallback? = null
    )
}
```

### Usage
```kotlin
class MyActivity : AppCompatActivity(), UnityAdDelegates by UnityAdDelegatesImpl() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUnityAdDelegates(this)
        
        setupUnityAdApp(
            testMode = BuildConfig.DEBUG,
            unityGameId = "unity-game-id"
        )
    }
}
```

---

## Callbacks

### FrogoAdCoreInterstitialCallback
```kotlin
interface FrogoAdCoreInterstitialCallback {
    fun onShowAdRequestProgress(tag: String, message: String)
    fun onHideAdRequestProgress(tag: String, message: String)
    fun onAdDismissed(tag: String, message: String)
    fun onAdFailed(tag: String, errorMessage: String)
    fun onAdLoaded(tag: String, message: String)
    fun onAdShowed(tag: String, message: String)
}
```

### FrogoAdmobBannerCallback
```kotlin
interface FrogoAdmobBannerCallback {
    fun onAdLoaded(tag: String, message: String)
    fun onAdFailedToLoad(tag: String, errorCode: String, errorMessage: String)
    fun onAdOpened(tag: String, message: String)
    fun onAdClicked(tag: String, message: String)
    fun onAdClosed(tag: String, message: String)
}
```

### FrogoAdmobInterstitialCallback
```kotlin
interface FrogoAdmobInterstitialCallback : FrogoAdCoreInterstitialCallback
```

### FrogoAdmobRewardedCallback
```kotlin
interface FrogoAdmobRewardedCallback : FrogoAdCoreInterstitialCallback {
    fun onUserEarnedReward(tag: String, rewardItem: RewardItem)
}
```

### FrogoAdmobAppOpenAdCallback
```kotlin
interface FrogoAdmobAppOpenAdCallback : FrogoAdCoreInterstitialCallback
```

### FrogoUnityAdInitializationCallback
```kotlin
interface FrogoUnityAdInitializationCallback {
    fun onInitializationComplete(tag: String, message: String)
    fun onInitializationFailed(tag: String, message: String)
}
```

### FrogoUnityAdInterstitialCallback
```kotlin
interface FrogoUnityAdInterstitialCallback : FrogoAdCoreInterstitialCallback {
    fun onClicked(tag: String, message: String)
}
```

### FrogoAdInterstitialCallback
```kotlin
interface FrogoAdInterstitialCallback : FrogoUnityAdInterstitialCallback
```

### IFrogoAdConsent
```kotlin
interface IFrogoAdConsent {
    fun activity(): Activity
    fun isDebug(): Boolean
    fun isUnderAgeAd(): Boolean
    fun onNotUsingAdConsent()
    fun onConsentSuccess()
    fun onConsentError(formError: FormError)
}
```

---

## Jetpack Compose Ad Activities (NEW)

### Class Hierarchy
- **`FrogoAdComposeActivity`**: Implements hybrid AdMob & Unity ads, inherits from `FrogoComposeActivity`.
- **`FrogoAdmobComposeActivity`**: Implements AdMob delegates, inherits from `FrogoComposeActivity`.
- **`FrogoUnityAdComposeActivity`**: Implements Unity delegates, inherits from `FrogoComposeActivity`.
- **`AdComposeActivity`**, **`AdmobComposeActivity`**, **`UnityAdComposeActivity`**: Non-Frogo base classes inheriting directly from `AppCompatActivity` for projects not using `FrogoComposeActivity`'s lifecycle features.

### Compose Ad Activity Implementation Example
Here is a sample Compose implementation (`MainAdmobComposeActivity.kt`) utilizing hybrid FALLBACK ads (`showAdmobXUnityAdInterstitial`) and a banner displayed via Compose `AndroidView`:

```kotlin
class MainAdmobComposeActivity : FrogoAdComposeActivity(),
    FrogoAdmobInterstitialCallback, FrogoAdmobRewardedCallback, FrogoAdmobAppOpenAdCallback,
    FrogoAdInterstitialCallback {

    private val adStatus = mutableStateOf("Ready to request ads")
    private val isAdLoading = mutableStateOf(false)

    override fun setupMonetized() {
        super.setupMonetized()
        setupUnityAdApp(BuildConfig.DEBUG, getString(R.string.unity_ad_game_id))
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)

        showAdConsent(object : IFrogoAdConsent {
            override fun activity(): Activity = this@MainAdmobComposeActivity
            override fun isDebug(): Boolean = BuildConfig.DEBUG
            override fun isUnderAgeAd(): Boolean = false
            override fun onNotUsingAdConsent() {}
            override fun onConsentSuccess() {}
            override fun onConsentError(formError: FormError) {
                showLogDebug("Consent Error: ${formError.message}")
            }
        })
    }

    @Composable
    override fun SetupCompose() {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = adStatus.value)
            if (isAdLoading.value) {
                CircularProgressIndicator()
            }

            Button(onClick = {
                // Admob -> Fallback to Unity Interstitial
                showAdmobXUnityAdInterstitial(
                    admobInterstitialId = getString(R.string.admob_interstitial),
                    unityInterstitialId = getString(R.string.unity_ad_interstitial),
                    callback = this@MainAdmobComposeActivity
                )
            }) {
                Text("Show Hybrid Interstitial")
            }

            Button(onClick = {
                showAdRewarded(getString(R.string.admob_rewarded), this@MainAdmobComposeActivity)
            }) {
                Text("Show Rewarded Ad")
            }

            // Banner integration using AndroidView
            AndroidView(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = context.getString(R.string.admob_banner)
                        showAdBanner(this)
                    }
                }
            )
        }
    }

    // Callbacks
    override fun onShowAdRequestProgress(tag: String, message: String) {
        isAdLoading.value = true
        adStatus.value = "Loading: $message"
    }

    override fun onHideAdRequestProgress(tag: String, message: String) {
        isAdLoading.value = false
    }

    override fun onAdDismissed(tag: String, message: String) {
        adStatus.value = "Ad dismissed: $message"
    }

    override fun onAdFailed(tag: String, errorMessage: String) {
        adStatus.value = "Ad failed: $errorMessage"
    }

    override fun onAdLoaded(tag: String, message: String) {
        adStatus.value = "Ad loaded: $message"
    }

    override fun onAdShowed(tag: String, message: String) {
        adStatus.value = "Ad showed: $message"
    }

    override fun onUserEarnedReward(tag: String, rewardItem: RewardItem) {
        adStatus.value = "Earned Reward: ${rewardItem.amount} ${rewardItem.type}"
    }

    override fun onClicked(tag: String, message: String) {
        adStatus.value = "Ad clicked: $message"
    }
}
```

---

## Required Dependencies (via `libs.versions.toml`)

```toml
[versions]
googleAdmob = "25.2.0"
unityAd = "4.17.0"

[libraries]
ads-google-admob = { group = "com.google.android.gms", name = "play-services-ads", version.ref = "googleAdmob" }
ads-unityAd = { group = "com.unity3d.ads", name = "unity-ads", version.ref = "unityAd" }
```

## AndroidManifest.xml Setup

```xml
<manifest>
    <application>
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-xxxxx~xxxxx"/>
    </application>
</manifest>
```
