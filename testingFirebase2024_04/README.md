# Task List Application

This is a simple Task List application built with Kotlin and Jetpack Compose, using Firebase Firestore as the backend.

## Prerequisites

- Android Studio Iguana | 2023.2.1 Patch 2 or later
- A Firebase project

## Setup

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Connect the app to your Firebase project:
    - Go to the Firebase console and create a new project.
    - Add an Android app to the project.
    - Download the `google-services.json` file and place it in the `app` directory of your project.
    - Apply the `com.google.gms:google-services` plugin in your `app/build.gradle.kts` file.
4. More Firebase instructions
    - Create new firestore implementation
    - In security selection - use "testing" (this allows firestore usage without authentication - not suitable for production)
    - There is no need for further configuration, because Android app will make automatic connection to Firestore
5. Add the "Firebase" dependencies to your both `build.gradle.kts` files
    - Instructions are in the Firebase console documentation
    - Depecies are related to "com.google.gms.google-services"
4. Sync the project with Gradle files.

## Running the App

1. Select a device or emulator running Android 33 or later.
2. Click `Run` (`Shift+F10`) to build and run the app.

## Testing
1. Logcat is your friend - check the firebase related messages
2. Use the Firebase console to check the data in Firestore

## Features

- Add tasks to the list.
- Tasks are stored in Firebase Firestore, so they persist across app launches.
- Visually appealing task list using Jetpack Compose.

## Contributing

Contributions are welcome. Please open an issue or submit a pull request.


## acknowledgements
This documentation is based on Github Copilot generated text.

## License

This project is licensed under the terms of the MIT license.