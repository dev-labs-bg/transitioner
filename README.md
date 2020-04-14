![logo](https://raw.githubusercontent.com/dev-labs-bg/transitioner/master/logo.png)

[![License: MIT](https://img.shields.io/badge/license-MIT-a31f34.svg?style=flat-square)](https://opensource.org/licenses/MIT) [![Download](https://img.shields.io/badge/download-1.3-6db33f.svg?style=flat-square&label=version)](https://bintray.com/radoslav/maven/transitioner/1.3/link) [![Twitter URL](https://img.shields.io/badge/twitter-%40devlabsbg-1DA1F2.svg?style=flat-square&logo=twitter)](http://twitter.com/devlabsbg)

Transitioner provides easy, dynamic and adjustable animations between two views with nested children.


<img src="https://github.com/dev-labs-bg/transitioner/blob/master/preview1.gif" width="600">

App design feature [here](https://www.uplabs.com/posts/ios-weather-app-animation-interface).
## Usage

First you need to create a Transitioner object containing your original and ending views:

```kotlin
val transition = Transitioner(original_view, ending_view)
```
  
The view pairs must have matching "tag" attributes so that they can be bound together:

```xml
<ConstraintLayout
        android:id="@+id/original_view"
        android:tag="constrView"
        ...>

        <TextView
            android:id="@+id/text"
            android:tag="firstView"
            .../>
</ConstraintLayout>

<ConstraintLayout
        android:id="@+id/ending_view"
        android:tag="constrView"
        android:visibility="invisible"
        ...>

        <EditText
            android:id="@+id/text3"
            android:tag="firstView"
            .../>
 </ConstraintLayout>
```

I recommend you hide the second view layout, since it's only used as a placeholder for the end destination.
The views can be of any type, you can mix and match them, the two layouts can have a different number of views and nested layouts are 100% supported. The only things to keep in mind are:

-  all views which you would want to match together must have the same tag attribute in both layouts

-  all unmatched views will remain at their original place inside the original layout

-  the second layout is just a placeholder. It doesn't hold any logic, it only shows where the original layout should move to.

### Basic Usage

```kotlin
 transition.setProgress(0.5f)
 //or
 transition.setProgress(50)
```

### Additional methods and tweaks
```kotlin
transition.duration = 500

transition.interpolator = AccelerateDecelerateInterpolator()

transition.animateTo(percent = 0f)

transition.onProgressChanged {
//triggered on every progress change of the transition
    seekBar.progress = (it * 100).toInt()
    }
    
val progress: Float = transition.currentProgress

```

Here is a preview of a simple application made using this library

![Preview](https://raw.githubusercontent.com/dev-labs-bg/transitioner/master/preview.gif)

This effect can be reproduced by placing the "Transitioner.setProgress(Float)" function inside a onTouch or a onProgressChanged method.


## Download

### Manually

The recommended way to download is to copy [the single library class file](https://github.com/dev-labs-bg/transitioner/blob/master/transitioner/src/main/java/bg/devlabs/transitioner/Transitioner.kt) and use it in your application.

### Gradle

```gradle
dependencies {
  compile 'bg.devlabs.transitioner:transitioner:<latest_version>'
}
 ```

## Getting help

Dev Labs  [@devlabsbg](https://twitter.com/devlabsbg)

Radoslav Yankov [@rado__yankov](https://twitter.com/rado__yankov)





Under [MIT License](https://gitlab.com/SimonaStoyanova/flying-fab/blob/master/LICENSE).
