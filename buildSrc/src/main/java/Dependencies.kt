import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "com.volokhinaleksey.androidcleanarchitecture"
    const val compileSdk = 33
    const val minSdk = 27
    const val targetSdk = 33
    val javaVersion = JavaVersion.VERSION_17
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    // Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val loggingOkHttp = "4.9.3"

    // Android Ktx
    const val ktxCore = "1.9.0"

    // Lifecycle
    const val lifecycleRuntimeKtx = "2.6.1"

    // Coroutines
    const val coroutinesCore = "1.6.4"
    const val coroutinesAndroid = "1.6.4"
    const val coroutinesTest = "1.6.4"

    // Compose
    const val activityCompose = "1.7.0"
    const val composeBom = "2022.12.00"
    const val composeViewModel = "2.5.1"
    const val composeMaterial3 = "1.1.0-alpha06"
    const val pagingCompose = "1.0.0-alpha18"
    const val composeNavigation = "2.5.3"

    // Koin
    const val koinCore = "3.3.3"
    const val androidKoin = "3.3.3"
    const val compatAndroidKoin = "3.3.3"
    const val koinTest = "3.3.3"
    const val koinCompose = "3.4.3"

    // Tests
    const val junit = "4.13.2"
    const val extJunit = "1.1.5"
    const val espresso = "3.5.1"
    const val googleTruth = "1.1.3"
    const val mockito = "5.2.0"
    const val mockitoInline = "5.2.0"
    const val kotlinMockito = "4.1.0"

    // Coil
    const val coilCompose = "2.2.2"

    // Paging 3
    const val pagingRuntime = "3.1.1"
    const val pagingCommon = "3.1.1"
}

object Paging {
    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.pagingRuntime}"
    const val pagingCommon = "androidx.paging:paging-common-ktx:${Versions.pagingCommon}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val loggingOkHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingOkHttp}"
}

object Android {
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"
}

object Coil {
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
}

object Compose {
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUI = "androidx.compose.ui:ui"
    const val composeUIUtil = "androidx.compose.ui:ui-util"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeUiTestJunit = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata"
    const val composePaging = "androidx.paging:paging-compose:${Versions.pagingCompose}"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
}

object Tests {
    const val junit = "junit:junit:${Versions.junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val googleTruth = "com.google.truth:truth:${Versions.googleTruth}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.kotlinMockito}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.androidKoin}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.compatAndroidKoin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koinTest}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}"
}
