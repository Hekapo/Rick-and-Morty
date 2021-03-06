import io.gitlab.arturbosch.detekt.detekt
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

val localProps = Properties()
val localProperties = File(rootProject.rootDir, "local.properties")
if (localProperties.exists() && localProperties.isFile) {
    localProperties.inputStream().use { input ->
        localProps.load(input)
    }
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "ru.itis.rick_and_morty"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        val databaseUrl = checkNotNull(localProps.getProperty("api.url") ?: System.getenv("API_URL"))
        buildConfigField("String", "API_URL", "\"$databaseUrl\"")

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

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")

    implementation("com.github.bumptech.glide:okhttp3-integration:4.12.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    debugImplementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // region RxJava
    implementation("io.reactivex.rxjava3:rxjava:3.1.4")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")

    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-android-compiler:2.41")


    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

//detekt {
//    source = files(projectDir)
//    config = files("${project.rootDir}/config/detekt/detekt.yml")
//    parallel = true
//    reports {
//        txt.enabled = false
//        xml.enabled = false
//        sarif.enabled = false
//        html {
//            enabled = true
//            destination = file("build/reports/detekt.html")
//        }
//    }
//}