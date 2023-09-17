plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.golnaz.home"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.golnaz.home"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        unitTests.isReturnDefaultValues = true
    }

    buildTypes {
        release {
            buildConfigField("String", "API_URL", "\"https://gsl-apps-technical-test.dignp.com/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "API_URL", "\"https://gsl-apps-technical-test.dignp.com/\"")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.navigation)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)

    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging)
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.gson)
    implementation(libs.retrofit.adapter)

    implementation(libs.koin.viewmodel)
    implementation(libs.koin.scope)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.core)
    implementation(libs.koin.navigation)

    implementation(libs.compose.ui)
    implementation(libs.compose.livedata)
    implementation(libs.compose.graphics)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    implementation(libs.compose.preview)
    implementation(libs.compose.viewModel)
    implementation(libs.coil)

    implementation(libs.android.xcore)
    implementation(libs.lifecycle.runtime)

    testImplementation(libs.junit)
    testImplementation(libs.junitJupiter)
    testImplementation(libs.junitJupiterParams)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.mockk)
    testImplementation(libs.kotest)
    testImplementation(libs.turbine)
}