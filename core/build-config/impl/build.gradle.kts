plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "ru.itis.neuroteacher.buildconfig.impl"
    compileSdk = 36

    defaultConfig {
        minSdk = 29

        val propertiesFile = File(rootProject.rootDir, "properties")
        val apiKey = if (propertiesFile.exists()) {
            propertiesFile.readText().trim()
        } else {
            ""
        }

        buildConfigField("String", "OPENROUTER_API_KEY", "\"$apiKey\"")
    }

    buildFeatures {
        buildConfig = true
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(project(":core:build-config:api"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.javax.inject)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}