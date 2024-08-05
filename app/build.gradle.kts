plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.quicknote"
    compileSdk = 34

    buildFeatures {
        compose =true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.quicknote"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //Dagger - Hilt

    implementation(libs.dagger.hilt.android)
    kapt(libs.hilt.android.compiler)

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.fragment.ktx)

    //Dagger - Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)

    // LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)

    // Compose
    implementation(platform(libs.androidx.compose.bom))



    implementation (libs.androidx.runtime)
    implementation (libs.androidx.ui)
    implementation (libs.androidx.foundation)
    implementation (libs.androidx.foundation.layout)
    implementation (libs.androidx.material3)
    implementation (libs.androidx.runtime.livedata)
    implementation (libs.androidx.ui.tooling)

    //intuit library ..sdp/ssp
    implementation (libs.sdp.android)
    implementation (libs.ssp.android)

    //splash screen
    implementation(libs.androidx.core.splashscreen)

    //Room
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation (libs.androidx.room.ktx)

    coreLibraryDesugaring(libs.desugar.jdk.libs)








}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}