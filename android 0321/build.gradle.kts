buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle:8.1.4")
// Import the BoM for the Firebase platform

    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}


