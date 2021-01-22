// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(Lib.ANDROID_GRADLE_PLUGIN)
        classpath(Lib.KOTLIN_PLUGIN)
        classpath(Lib.KOTLIN_ALLOPEN)
        classpath(Lib.NAVIGATION_SAFE_ARGS)
        classpath(Lib.HILT_GRADLE_PLUGIN)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.diffplug.gradle.spotless") version "3.27.1"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

subprojects {
    apply(plugin = "com.diffplug.gradle.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            ktlint("0.40.0").userData(mapOf("max_line_length" to "100", "disabled_rules" to "import-ordering"))
            licenseHeaderFile(project.rootProject.file("copyright.kt"))
        }
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-Xuse-experimental=" +
            "kotlin.Experimental," +
            "kotlinx.coroutines.ExperimentalCoroutinesApi," +
            "kotlinx.coroutines.InternalCoroutinesApi," +
            "kotlinx.coroutines.FlowPreview"
    }
}
