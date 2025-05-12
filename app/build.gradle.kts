plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.vetpet"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    namespace = "com.example.vetpet"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions{
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Firebase dependencies
    implementation(libs.firebase.auth)
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation(libs.firebase.database)
    implementation(libs.firebase.core)

    // UI dependencies
    implementation(libs.constraintlayout)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation ("com.google.firebase:firebase-firestore:25.1.4")
    implementation ("com.google.firebase:firebase-firestore")
}


apply(plugin = "com.google.gms.google-services")

