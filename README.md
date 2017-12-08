![logo](https://raw.githubusercontent.com/dev-labs-bg/transitioner/master/logo.png)

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT) [ ![Download](https://api.bintray.com/packages/sstoyanova/flyingfab/flying-fab/images/download.svg?version=0.0.1) ](https://bintray.com/radoslav/maven/transitioner/0.0.1/link)[![Kotlin](https://img.shields.io/badge/kotlin-1.2-blue.svg)](http://kotlinlang.org) [![Twitter URL](https://img.shields.io/badge/twitter-@devlabsbg-blue.svg)](http://twitter.com/devlabsbg)


## Download

### Gradle

```gradle
dependencies {
  compile 'bg.devlabs.transitioner:transitioner:<latest_version>'
}
 ```
 
### Maven
```xml
<dependency>
  <groupId>bg.devlabs.transitioner</groupId>
  <artifactId>transitioner</artifactId>
  <version>latest_version</version>
  <type>pom</type>
</dependency>
```

### Manually

You can also manually download [the library class](https://github.com/dev-labs-bg/transitioner/blob/master/transitioner/src/main/java/bg/devlabs/transitioner/Transitioner.kt) and use it in your application.

---
## Usage

First you need to create a Transitioner object containing your original and ending views:

```kotlin
val transition = Transitioner(original_view, ending_view)
```
  
The view pairs must have matching "transitionName" attributes so that they can be bound together:

```xml
<ConstraintLayout
        android:id="@+id/original_view"
        android:transitionName="constrView"
        android:layout_width="180dp"
        android:layout_height="180dp">

        <TextView
            android:id="@+id/text"
            android:transitionName="firstView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
</ConstraintLayout>

<ConstraintLayout
        android:id="@+id/ending_view"
        android:transitionName="constrView"
        android:layout_width="280dp"
        android:layout_height="280dp">

        <EditText
            android:id="@+id/text3"
            android:transitionName="firstView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
 </ConstraintLayout>
```

I recommend you hide the second view layout, since it's only used as a placeholder for the end destination.
The views can be of any type, you can mix and match them, the two layouts can have a different number of views and nested layouts are 100% supported. The only things to keep in mind are:

-  all views which you would want to match together must have the same transitionName attribute in both layouts

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

transition.animateTo(0f)

transition.onPercentChanged {
//triggered on every progress change of the transition
    seekBar.progress = (it * 100).toInt()
    }
    
val progress: Float = transition.currentProgress

```

Here is a preview of a simple application made using this library

![Preview](https://raw.githubusercontent.com/dev-labs-bg/transitioner/master/preview.gif)

Another one, implemented in a weather app

![Preview2](https://raw.githubusercontent.com/dev-labs-bg/transitioner/master/preview2.gif)

These effects can be reproduced by placing the "Transitioner.setProgress(Float)" function inside a onTouch or a onProgressChanged method.

---
## Compatibility

Minimum Android SDK: API level 21

---
## Author

Radoslav Yankov [@Radoslav_Y](https://twitter.com/Radoslav_Y)

---
## Getting help

If you spot a problem you can open an issue on the Github page, or alternatively, you can tweet us at [@devlabsbg](https://twitter.com/devlabsbg)

---
## License

Transitioner is released under the [MIT License](https://gitlab.com/SimonaStoyanova/flying-fab/blob/master/LICENSE).
