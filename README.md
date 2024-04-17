# Key Cloud (Kloud)

## What is it about?

//uma descrição mais detalhada

## Objectives:

A simplified cloud monitoring app, designed to make monitoring more accessible to non-experts with just a few clicks.

## Main public:

The main audience that will benefit from the app comprises small and medium-sized business owners who cannot afford to hire multiple cloud experts.


This is a Kotlin Multiplatform project targeting Android, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…