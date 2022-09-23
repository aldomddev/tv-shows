import Dependencies.Deps

plugins {
    id(Dependencies.BuildPlugins.androidApplication)
    id(Dependencies.BuildPlugins.kotlinAndroid)
    id(Dependencies.BuildPlugins.kotlinKapt)
    id(Dependencies.BuildPlugins.kotlinParcelize)
    id(Dependencies.BuildPlugins.navigationSageArgs)
    id(Dependencies.BuildPlugins.daggerHiltAndroid)
}

android {
    namespace = "br.com.amd.tvshows"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.amd.tvshows"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Deps.androidxAppCompat)
    implementation(Deps.androidxCore)
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.androidMaterial)
    implementation(Deps.kotlinSerialization)
    dependOnHilt()
    dependOnLifecycle()
    dependOnNavigation()
    dependOnRetrofit()
    dependOnTests()
}