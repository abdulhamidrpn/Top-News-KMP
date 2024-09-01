# Top-News-KMP 📱🖥️
![KMP NEWS BANNER (1)](https://github.com/user-attachments/assets/47a09e1d-f3b5-4499-8308-8a6bac8f205c)

## Overview
**Top-News-KMP** is a versatile news application built using Kotlin Multiplatform (KMP), designed to deliver the latest news across Android, iOS and Desktop platforms. This project showcases the power of shared code between multiple platforms while maintaining a native experience on each. Whether on a smartphone or tablet, desktop Top-News-KMP provides a seamless and consistent user experience.

## 🚀 Key Features
- **Cross-Platform Compatibility** (Android, iOS & Desktop Applications)
- **Live News Feed** with category filtering
- **Offline Reading** with saved articles
- **Elegant UI/UX Design** inspired by Material Design 3
- **Dark Mode Support** for eye comfort

## 🔥 Additional Features
- **Bookmarking Articles**
- **Search Functionality**
- **Advanced Article Sharing Options**

## 🛠️ Tech Stack & Plugins
Compose Libraries
- **Kotlin Multiplatform**: `2.0.20`
- **Compose**:
  - `compose.runtime`: Manages the lifecycle and state of your composables.
  - `compose.foundation`: Provides foundational components for building UI.
  - `compose.material3`: Implements Material Design 3 components.
  - `compose.ui`: Core UI components for Jetpack Compose.
  - `compose.animation`: Animation support in Jetpack Compose.
  - `compose.components.resources`: Access to compose-specific resources.
  - `compose.components.uiToolingPreview`: Tooling support for UI previews in Jetpack Compose.
- **Lifecycle Libraries**
  - `libs.androidx.lifecycle.viewmodel`: ViewModel support for managing UI-related data.
  - `libs.androidx.lifecycle.runtime.compose`: Integration of lifecycle-aware components with Jetpack Compose
- **Multiplatform Window Size**
  - `libs.window.size.multiplatform`: Helps handle different window sizes in a multiplatform environment.
- **Navigation**
  - `libs.navigation.compose`: Jetpack Compose Navigation for managing app navigation.
- **DataStore**
  - `libs.androidx.data.store.core`: For storing data asynchronously, using DataStore.
- **Coil** (Image Loading)
  - `libs.coil.compose.core`: Core library for image loading with Coil in Compose.
  - `libs.coil.compose`: Image loading integration for Jetpack Compose.
  - `libs.coil.mp`: Multiplatform support for Coil.
  - `libs.coil.network.ktor`: Integration of Coil with Ktor for network image loading.
- **Ktor** (Networking)
  - `libs.ktor.core`: Core library for building client and server applications with Ktor.
  - `libs.ktor.json`: JSON support in Ktor.
  - `libs.ktor.logging`: Logging for Ktor applications.
  - `libs.ktor.negotiation`: Content negotiation in Ktor.
  - `libs.kotlinx.serialization.json`: JSON serialization for Kotlin.
- **Logging with Kermit**
  - `libs.kermit`: Multiplatform logging library.
- **Dependency Injection with Koin**
  - `libs.koin.core`: Core library for dependency injection with Koin.
  - `libs.koin.compose`: Koin integration with Jetpack Compose.
- **Kotlinx DateTime**
  - `libs.kotlinx.datetime`: Kotlin Multiplatform library for working with dates and times.
- **Database Support**
  - `libs.androidx.room.runtime`: Provides a database layer on top of SQLite for Android.
  - `libs.sqlite.bundled`: SQLite database bundled with your application.

## 🚀 Future Plans
- **Update Room Database**.
- **Enable Web Site Platform**.

## 🌟 Contributions
Contributions are welcome! Please don't forget to fix this repository, submit a PR, or report any issues you encounter. Whether you're fixing a bug or adding a new feature, your contributions will make this project better for everyone!
[Inspired By](https://github.com/Coding-Meet/News-KMP-App)

## Getting Started

### Installation 🛠️

1. Clone this repository:
   ```bash
   git clone https://github.com/abdulhamidrpn/Top-News-KMP.git
   ```

2. Open in the latest version of Android Studio or intellij idea.
3. Before running the project, obtain an API key from [News Api](https://newsapi.org/) & [NewsData IO](https://newsdata.io/).
4. Add a `local.properties` file to the project root.
5. Place your News API key in `local.properties` file as `API_KEY` & `API_KEY_NEWS_DATA` property.

```properties
API_KEY = YOUR_API_KEY From News Api
API_KEY_NEWS_DATA = YOUR_API_KEY From News Data IO
```

### Run the app on your device or emulator:

- For Android, run the `composeApp` module by selecting the `app` configuration. If you need help with the
  configuration, follow this link
  for [android](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application-on-android)
- For iOS, run the `composeApp` module by selecting the `iosApp` configuration. If you need help with the configuration,
  follow this link
  for [Ios](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application-on-ios)
- For Desktop, run `./gradlew :composeApp:run`

### Application Demo:
<p>
  <a href="https://youtu.be/4dDDITxv4Ow"><img src="https://github.com/user-attachments/assets/8f115eda-3411-4c36-a2e1-1bb4430f124a" align="left" height="168.75" width="300" ></a>
</p>

## 🔗 Connect with me
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://linktr.ee/abdulhamidrpn)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/abdulhamidrpn/)
