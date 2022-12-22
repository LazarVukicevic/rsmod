plugins {
    kotlin("jvm")
}

@Suppress("UnstableApiUsage")
dependencies {
    api(libs.nettyTransport)
    api(libs.nettyHandler)
    implementation(project(":crypto"))
    implementation(project(":protocol"))
}
