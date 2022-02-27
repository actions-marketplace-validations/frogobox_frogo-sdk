![ScreenShoot Apps](docs/image/ss_banner.png?raw=true)

## About This Project (release-and-work-in-progress 👷🔧️👷‍♀️⛏)
- SDK for anything your problem to make easier developing android apps
- Privacy Policy [Click Here](https://github.com/amirisback/frogo-sdk/blob/master/PRIVACY-POLICY.md)
- License [Click Here](https://github.com/amirisback/frogo-sdk/blob/master/LICENSE)


## Version Release
This Is Latest Release

    ~ Beta Release
    $version_release = 0.0.1-beta02

What's New??

    * SDK Android and Desktop *
    * Beta Release *

## Download this project

### Step 1. Add the JitPack repository to your build file (build.gradle : Project)
    
#### <Option 1> Groovy Gradle

    // Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

#### <Option 2> Kotlin DSL Gradle

```kotlin
// Add it in your root build.gradle.kts at the end of repositories:

allprojects {
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
    }
}
```
      
### Step 2. Add the dependency (build.gradle : Module)

    #### <Option 1> Groovy Gradle

        dependencies {
            // library frogo-sdk
            implementation 'com.github.frogobox:frogo-sdk:0.0.1-beta02'
        }

    #### <Option 2> Kotlin DSL Gradle

        dependencies {
            // library frogo-sdk
            implementation("com.github.frogobox:frogo-sdk:0.0.1-beta02")
        }

### Step 3. Function from this SDK

#### All Class SDK (android)
```kotlin
FrogoActivity
FrogoApiClient
FrogoApplication
FrogoComposeActivity
FrogoDate
FrogoFragment
FrogoFunc
FrogoMusic
FrogoMutableLiveData
FrogoNavigation
FrogoPagerHelper
FrogoPreference
FrogoViewModel
```

### All Class SDK (desktop & android)
```kotlin
FrogoApiModel
FrogoApiObserver
FrogoConstant
FrogoCoreApiClient
FrogoDataResponse
FrogoLocalObserver
FrogoStateResponse
```

#### FrogoActivity
```kotlin
fun setupDetailActivity(title: String)

fun setupChildFragment(frameId: Int, fragment: Fragment)

fun showToast(message: String)

fun setupEventEmptyView(view: View, isEmpty: Boolean)

fun setupEventProgressView(view: View, progress: Boolean)

fun checkExtra(extraKey: String): Boolean

fun <Model> baseFragmentNewInstance(
    fragment: FrogoFragment<*>,
    argumentKey: String,
    extraDataResult: Model
)

fun verifySignature()

fun readSignature()

fun verifyInstallerId()

fun verifyUnauthorizedApps()

fun verifyStores()

fun verifyDebug()

fun verifyEmulator()

fun showApkSignatures()
```

#### FrogoFragment
```kotlin
fun setupChildFragment(frameId: Int, fragment: Fragment)

fun checkArgument(argsKey: String): Boolean

fun setupEventEmptyView(view: View, isEmpty: Boolean)

fun setupEventProgressView(view: View, progress: Boolean)

fun showToast(message: String)

fun <Model> baseNewInstance(argsKey: String, data: Model)
```
#### FrogoFunc

```kotlin
fun createFolderPictureVideo()

fun getVideoFilePath(): String

fun createDialogDefault(
    context: Context,
    title: String,
    message: String,
    listenerYes: () -> Unit,
    listenerNo: () -> Unit
)

fun noAction(): Boolean

fun randomNumber(start: Int, end: Int): Int

fun isNetworkAvailable(context: Context): Boolean?

fun <T> fetchRawData(mContext: Context, sourceRaw: Int): ArrayList<T>

fun <T> fetchRawData(mContext: Context, sourceRaw: Int, shuffle: Boolean): ArrayList<T>

fun getJsonFromAsset(context: Context, filename: String): String?

fun <T> getArrayFromJsonAsset(context: Context, filename: String): MutableList<T>

fun getDrawableString(context: Context, nameResource: String): Int

fun getRawString(context: Context, nameResource: String): Int
```

#### FrogoMusic

```kotlin
fun playMusic(context: Context, musicFile: Int)

fun stopMusic()

fun pauseMusic()
```

#### FrogoDate

```kotlin
fun getTimeStamp(): String

fun getTimeNow(): String

fun getCurrentDate(format: String): String

fun dateTimeToTimeStamp(date: String?): Long

fun getCurrentUTC(): String

fun timetoHour(date: String?): String

fun dateTimeTZtoHour(date: String?): String

fun DateTimeMonth(date: String?): String

fun dateTimeSet(date: String?): String

fun dateTimeProblem(date: String?): String

fun getTimeAgo(time: Long): String?

fun compareDate(newDate: String): String?

fun messageDate(newDate: String): String?

fun getDataChat(time: Long): String?

fun convertClassificationDate(string: String?): String

fun convertDateNewFormat(string: String?): String

fun convertLongDateNewFormat(string: String?): String

fun revertFromLongDateNewFormat(string: String?): String

fun convertTargetDate(string: String?): String

fun diffTime(timeStart: String, timeEnd: String): Long
```

## Colaborator
Very open to anyone, I'll write your name under this, please contribute by sending an email to me

- Mail To faisalamircs@gmail.com
- Subject : Github _ [Github-Username-Account] _ [Language] _ [Repository-Name]
- Example : Github_amirisback_kotlin_admob-helper-implementation

Name Of Contribute
- Muhammad Faisal Amir
- Waiting List
- Waiting List

Waiting for your contribute

## Attention !!!
- Please enjoy and don't forget fork and give a star
- Don't Forget Follow My Github Account


![ScreenShoot Apps](docs/image/mad_score.png?raw=true)
