# BaseMVVM
The MVVM Core library

## Description:
The library consists of the set of base classes and interfaces to simplify developing of the client-server mobile apps. </br>
It has been divided to three different layers: domain, data and presenation to follows the Clean Architecture. </br>

## Installation:
Add following repositories to the <i>settings.gradle</i> file.
```groovy
  dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}
```

Add any or all of these dependencies to your <i>build.gradle</i> file of Android module(e.g. app).
P.s. The version below is same as release tag. You can find the most actual version in the release section.
```groovy
  dependencies {
    def version = "1.0.0"
    implementation "com.github.Alexander1245.BaseMVVM:presentation:version"
    implementation "com.github.Alexander1245.BaseMVVM:domain:version"
    implementation "com.github.Alexander1245.BaseMVVM:data:version"
  }
```

Enable the view binding feature in a module which will contain the <i>presenation</i> dependency.
```groovy
  android {
    ...
    viewBinding {
      enable true
    }
  }
```
