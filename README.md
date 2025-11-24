# MasterlyApp

Lightweight Android app built with Jetpack Compose that manages courses (add, view, progress). Uses Hilt for DI, Compose Navigation, and a Kotlin/Gradle setup.

## Table of contents
- `Overview`
- `Features`
- `Demo`
- `Tech stack`
- `Project structure`
- `Prerequisites`
- `Setup & run`
- `Testing`
- `Contributing`
- `License`

## Overview
MasterlyApp is an Android application demonstrating a simple course management UI using Jetpack Compose, Hilt, and modern Android tooling. The app shows a list of courses, allows adding courses via a dialog, and handles loading / error UI states.

## Features
- Add courses with title and description
- Compose-based UI with Material3 components
- Navigation between screens
- Hilt for dependency injection
- Simple local state handling in ViewModel

## Demo



https://github.com/user-attachments/assets/243f45b8-9cb1-416a-87e2-c14fcbb69be3


## Tech stack
- Kotlin
- Jetpack Compose (Material3)
- Hilt
- AndroidX Navigation Compose
- Gradle

## Project structure (high level)
- `app/src/main/java/com/example/masterlyapp/presentation` — UI layer, activities, composables, navigation
- `app/src/main/java/com/example/masterlyapp/domain` — domain models and use-cases
- `app/src/main/java/com/example/masterlyapp/ui` — theme and UI utilities
- `app/build.gradle` and root `build.gradle` — Gradle configuration

## Prerequisites
- macOS/windows (development machine)
- Android Studio Narwhal Feature Drop \| 2025.1.2 (recommended)
- Android SDK (API levels required by project)
- JDK 11 or later
- Internet access for Gradle dependencies

## Setup \& run
1. Clone the repository:
   ```bash
   git clone https://github.com/Saikrishna41/MasterlyApp.git
   cd MasterlyApp
