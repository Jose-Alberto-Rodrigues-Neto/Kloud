# Key Cloud (Kloud)

![image](https://github.com/user-attachments/assets/825fef8d-2f4e-482e-8826-a6927a28eced)


## What is it about?

The **Key Cloud (Kloud)** is designed to provide real-time visibility and insights into cloud infrastructure performance. It aims to help businesses monitor their cloud environments efficiently, detect issues, and optimize resource utilization at Google Cloud Platform. The app will offer a comprehensive suite of monitoring tools, including performance metrics, alerts, and analytics dashboards, to ensure the health and security of cloud-based systems.

#### OBS:

This was a project created during my `Software Engineering` course in the `Computer Science` program at the ***"Universidade Federal do Ceará" (UFC)***. Unfortunately, we didn't continue with the app after the course ended. 

However, the development team was able to acquire essential skills in:

- **Mobile Development**, using `Kotlin Multiplatform`, `Jetpack Compose`, `Ktor`.
- **Mobile CI/CD**, with solid practices in `Continuous Integration` and `Continuous Delivery` in Mobile using Kotlin.
- **Cloud Monitoring**, using `Google Cloud Platform API's`.
- **Project management**, using `Agile Methodologies`, such as `Scrum` and `Kanban`, as well as learning more about requirements gathering.
- **Software Development Life Cycle (SDLC)**, as we had to simulate its application during the project's development.

## Objectives:

A simplified cloud monitoring app, designed to make monitoring more accessible to non-experts with just a few clicks.

## Main public:

The main audience that will benefit from the app comprises small and medium-sized business owners who cannot afford to hire multiple cloud experts.

# How to download apk

Follow the tutorial below to download the `Kloud.apk`

[![](https://markdown-videos-api.jorgenkh.no/youtube/_wL90vErURQ)](https://youtu.be/_wL90vErURQ)


# Repository Structure

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

# How to contribute

## Commit Convention

[Commit Convention](https://github.com/iuricode/padroes-de-commits)

## Pull Request Template

 The name of the PR's need to be `backend or frontend: (issue propose) name of the issue`, something like: `frontend: (feat) create login page`.

 The body of the PR's on the following [link](https://github.com/Jose-Alberto-Rodrigues-Neto/Kloud/blob/main/.github/PR_TEMPLATE.md).
