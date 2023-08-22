plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.yassermovieapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.yassermovieapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable=true
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w500\"")
            buildConfigField("String", "SECRET_KEY", "\"c9856d0cb57c3f14bf75bdc6c063b8f3\"")

        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w500\"")
            buildConfigField("String", "SECRET_KEY", "\"c9856d0cb57c3f14bf75bdc6c063b8f3\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
kapt {
    correctErrorTypes = true
}
dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    kapt ("com.android.databinding:compiler:3.1.4")
    implementation ("androidx.activity:activity-ktx:1.7.2")
    implementation ("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    val lifecycle_version = "2.6.1"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")


    // Unit Test
    testImplementation( "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation( "io.mockk:mockk:1.13.5")
    testImplementation( "android.arch.core:core-testing:1.1.1")
    testImplementation ("org.mockito:mockito-inline:2.13.0")
    testImplementation( "org.robolectric:robolectric:4.7.3")
    testImplementation( "androidx.test:runner:1.5.2")
    testImplementation( "androidx.test.ext:junit:1.1.5")
    testImplementation( "androidx.test.espresso:espresso-core:3.5.1")
    testImplementation( "app.cash.turbine:turbine:0.13.0")
    testImplementation( "org.jetbrains.kotlin:kotlin-test:1.8.21")

    // Networking
    implementation( "com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation( "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation( "com.squareup.retrofit2:retrofit:2.9.0")
    implementation( "com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.4.0")

    //Glide
    implementation( "com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    //Facebook shimmer
    implementation( "com.facebook.shimmer:shimmer:0.5.0")

    //Lottie files
    implementation( "com.airbnb.android:lottie:3.4.0")

    //dependency injection
    val hiltVersion="2.47"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    //RxJava
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")

    //coil
    implementation ("com.google.accompanist:accompanist-coil:0.15.0")


    //Paging 3.0
    implementation ("androidx.paging:paging-compose:3.2.0")
}