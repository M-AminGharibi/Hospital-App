plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.amingharibi.hospital"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.amingharibi.hospital"
        minSdk = 21
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding = true
    }
}
val parseVersion by extra("4.3.0")
dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.github.bumptech.glide:glide:4.11.0")


    implementation("com.github.parse-community.Parse-SDK-Android:parse:$parseVersion")
    // for Google login/signup support (optional)
    implementation("com.github.parse-community.Parse-SDK-Android:google:$parseVersion")
    // for Facebook login/signup support (optional)
    implementation("com.github.parse-community.Parse-SDK-Android:facebook:$parseVersion")
    // for Twitter login/signup support (optional)
    implementation("com.github.parse-community.Parse-SDK-Android:twitter:$parseVersion")
    // for FCM Push support (optional)
    implementation("com.github.parse-community.Parse-SDK-Android:fcm:$parseVersion")
    // for Kotlin extensions support (optional)
    implementation("com.github.parse-community.Parse-SDK-Android:ktx:$parseVersion")
    // for Kotlin coroutines support (optional)
    implementation("com.github.parse-community.Parse-SDK-Android:coroutines:$parseVersion")
    // for RxJava support (optional)
    implementation("com.github.parse-community.Parse-SDK-Android:rxjava:$parseVersion")

}