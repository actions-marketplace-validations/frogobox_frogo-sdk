# Implementation Plan - Migrate View to Jetpack Compose

This plan outlines the steps to migrate legacy Android View XML layouts, resources, and custom view Java/Kotlin classes from `frogo-ui-base` into modern Jetpack Compose equivalents in `frogo-compose-ui`.

## User Review Required

> [!IMPORTANT]
> The target module `frogo-compose-ui` currently does not have a `src/main/res` directory. However, to support the migrated classes (like `FrogoLoadingIndicatorView` which relies on custom attributes/styles, and `FrogoSingleAnimation` which overrides activity transitions using legacy XML animations), we will create `src/main/res` inside `frogo-compose-ui` and copy the required XML resources there.

> [!TIP]
> Since the loading indicators package contains 28 highly customized Java drawables with manual canvas animation calculations, rewrite-migrating all of them into pure Compose canvas logic would result in thousands of lines of code. We will migrate these Java files into `frogo-compose-ui` and write a clean Compose interoperability wrapper (`FrogoLoadingIndicator`) using `AndroidView` so they can be easily used in Jetpack Compose code.

## Proposed Changes

We will copy and adapt all required resources and Java/Kotlin source code.

### 1. Resources Migration (`frogo-ui-base` to `frogo-compose-ui`)

#### [NEW] [attrs.xml](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values/attrs.xml)
#### [NEW] [colors.xml](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values/colors.xml)
#### [NEW] [global_colors.xml](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values/global_colors.xml)
#### [NEW] [dimens.xml](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values/dimens.xml)
#### [NEW] [strings.xml](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values/strings.xml)
#### [NEW] [styles.xml](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values/styles.xml)
#### [NEW] [themes.xml](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values/themes.xml)
#### [NEW] [colors.xml (night)](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values-night/colors.xml)
#### [NEW] [themes.xml (night)](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/values-night/themes.xml)
#### [NEW] [frogo_ic_empty_view.xml](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/res/drawable/frogo_ic_empty_view.xml)
- Copy all `frogo_bg_*.xml` shape drawables to `frogo-compose-ui/src/main/res/drawable/` to ensure full XML compatibility.
- Copy all 47 animation XMLs from `frogo-ui-base/src/main/res/anim` to `frogo-compose-ui/src/main/res/anim`.

---

### 2. Kotlin / Java Sources Migration

#### [NEW] [Animation Package](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/java/com/frogobox/composeui/animation)
- `FrogoAnimation.kt`, `FrogoSingleAnimation.kt`, and `IFrogoAnimation.kt`
- Core animators: `Attention.kt`, `Bounce.kt`, `Fade.kt`, `Flip.kt`, `Rotate.kt`, `Slide.kt`, `Zoom.kt` and their interfaces.
- Update R imports to `com.frogobox.composeui.R`.

#### [NEW] [Extensions Package](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/java/com/frogobox/composeui/ext)
- Port `ViewExt.kt` to `com.frogobox.composeui.ext` so that it references the correct target R assets.

#### [NEW] [Fireworks Package](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/java/com/frogobox/composeui/fireworks)
- `ParticleSystem.kt`, `Particle.kt`, `AnimatedParticle.kt`, `ParticleField.kt`
- Subdirectories: `initializers` and `modifiers` contents.

#### [NEW] [Loading Indicator Package](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/java/com/frogobox/composeui/loadingindicator)
- Port `FrogoLoadingIndicatorView.java`, `Indicator.java`, and the 28 custom Java indicators.
- Create a modern Composable wrapper `FrogoLoadingIndicator` inside `com.frogobox.composeui.widget`:
```kotlin
@Composable
fun FrogoLoadingIndicator(
    modifier: Modifier = Modifier,
    indicatorName: String = "BallPulseIndicator",
    indicatorColor: Color = Color.White,
    size: Dp = 48.dp
) {
    AndroidView(
        factory = { context ->
            FrogoLoadingIndicatorView(context).apply {
                setIndicator(indicatorName)
                setIndicatorColor(indicatorColor.toArgb())
            }
        },
        modifier = modifier.size(size),
        update = { view ->
            view.setIndicator(indicatorName)
            view.setIndicatorColor(indicatorColor.toArgb())
        }
    )
}
```

---

### 3. Layouts Migration to Jetpack Compose

#### [NEW] [FrogoEmptyView.kt](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/java/com/frogobox/composeui/template/empty/FrogoEmptyView.kt)
- Compose equivalent of `frogo_empty_view.xml` with poster image (`frogo_ic_empty_view`) and title/subtitle text.

#### [NEW] [FrogoRvListItems.kt](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/java/com/frogobox/composeui/list/basic/FrogoRvListItems.kt)
- Standardize all 12 list item templates as high-quality Composables (`FrogoRvListType1` to `FrogoRvListType12`). Example layout styling matching parent attributes/colors/paddings from XML.
- Example structure for `FrogoRvListType4`:
```kotlin
@Composable
fun FrogoRvListType4(
    title: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
)
```

#### [NEW] [FrogoRvGridItems.kt](file:///d:/AndroidProject/frogo-sdk/frogo-compose-ui/src/main/java/com/frogobox/composeui/list/basic/FrogoRvGridItems.kt)
- Standardize all 7 grid item templates as Composables (`FrogoRvGridType1` to `FrogoRvGridType7`).

---

## Verification Plan

### Automated Tests
- Build project using `./gradlew :frogo-compose-ui:assembleDebug` to verify that there are no compilation or layout definition errors.

### Manual Verification
- Review Compose previews for migrated list/grid items and empty views.
- Confirm compilation and module package boundaries.
