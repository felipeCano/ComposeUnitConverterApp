## Purpose and Scope
This document provides a high-level introduction to the ComposeUnitConverterApp, an Android unit conversion application built with Jetpack Compose and modern Android architecture components. This page covers the application's purpose, technology stack, architectural patterns, and key components.

For detailed information about specific topics, see:

Build and run instructions: Getting Started
Detailed architectural patterns: Architecture
UI component specifications: User Interface Components
Build system configuration: Build Configuration
Development environment setup: Development Environment
Sources:

app/build.gradle.kts
app/src/main/AndroidManifest.xml
## Application Purpose
ComposeUnitConverterApp is an Android application that provides unit conversion functionality with persistent history tracking. The application allows users to convert between different units of measurement (such as meters to feet, kilograms to pounds, etc.) and maintains a history of past conversions stored in a local SQLite database.

The application demonstrates modern Android development practices including:

Declarative UI with Jetpack Compose
Unidirectional data flow architecture
Dependency injection with Hilt
Local persistence with Room
Reactive state management with ViewModel
Sources:

app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
app/src/main/java/com/compose/unitconverterapp/ConverterApp.kt
## Technology Stack
The application is built using the following core technologies:
<img width="701" height="374" alt="image" src="https://github.com/user-attachments/assets/0ee61f87-820a-48cc-92ce-faa617b47134" />

Component	Technology	Version	Purpose
Language	Kotlin	-	Primary development language
UI Framework	Jetpack Compose	BOM-managed	Declarative UI toolkit
DI Framework	Hilt	2.57.2	Dependency injection
Database	Room	2.8.0	SQLite persistence layer
Build System	Gradle KTS	-	Build automation
Annotation Processing	KSP	-	Compile-time code generation
Architecture	MVVM	-	Separation of concerns pattern
Min SDK	Android 7.0	API 24	Minimum supported version
Target SDK	Android 14	API 36	Target platform version
Sources:

app/build.gradle.kts
9-41
app/build.gradle.kts
44-71
## High-Level Architecture
<img width="584" height="852" alt="image" src="https://github.com/user-attachments/assets/5067adf6-36f9-4ef8-8dca-960a289459bb" />

## Diagram: Application Architecture Layers

The application follows a layered architecture pattern with clear separation of concerns:

## Application Layer:
Entry points for the application, including ConverterApp 
app/src/main/java/com/compose/unitconverterapp/ConverterApp.kt
6-7
 and MainActivity 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
18-19

## UI Layer: 
Jetpack Compose components that render the user interface, with BaseScreen orchestrating the layout 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
28-30

## ViewModel Layer: 
ConverterViewModel manages UI state and business logic, created via ConverterViewModelFactory 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
20-21

## Data Layer: 
ConverterRepositoryImpl provides data access abstraction, backed by ConverterDatabase 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
12-13

## Domain Models: 
Data classes representing business entities (Conversion, ConversionResult)

## Sources:

app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
app/src/main/java/com/compose/unitconverterapp/ConverterApp.kt
app/src/main/AndroidManifest.xml
## Key Components and Initialization Flow
<img width="1434" height="848" alt="image" src="https://github.com/user-attachments/assets/87cc3616-4cc9-4f96-bffa-271e1d896487" />

## Diagram: Application Initialization Sequence

The initialization process follows this sequence:

## Application Start:
Android system creates ConverterApp instance 
app/src/main/AndroidManifest.xml
6
 which is annotated with @HiltAndroidApp 
app/src/main/java/com/compose/unitconverterapp/ConverterApp.kt
6

## DI Setup: 
Hilt initializes the dependency injection container at the application scope

## Activity Creation:
MainActivity is created with @AndroidEntryPoint annotation 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
18
 enabling field injection

## Factory Injection:
Hilt injects ConverterViewModelFactory into MainActivity 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
20-21

## Compose Setup:
MainActivity.onCreate() calls setContent to establish the Compose UI hierarchy 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
25-33

## Screen Rendering: 
BaseScreen composable receives the factory and renders the application UI 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
28-30

## Sources:

app/src/main/AndroidManifest.xml
5-14
app/src/main/java/com/compose/unitconverterapp/ConverterApp.kt
1-8
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
18-34
## Project Structure
The project follows the standard Android application structure with package-by-feature organization:

<img width="678" height="418" alt="image" src="https://github.com/user-attachments/assets/9f634fc4-f1d9-4ec9-baf1-9e3750fa5a36" />

## Key Package Responsibilities:

<img width="734" height="191" alt="image" src="https://github.com/user-attachments/assets/01eed776-5b8a-4d71-b752-c31b9902b9df" />

## Sources:

app/build.gradle.kts
1-7
app/src/main/AndroidManifest.xml
1-28
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
1-35
## Build Configuration Summary
The application uses Gradle Kotlin DSL for build configuration with the following key settings:

<img width="724" height="280" alt="image" src="https://github.com/user-attachments/assets/760957bc-d74a-448a-b608-ef8dc2af63dd" />

## Gradle Plugins Applied:
<img width="706" height="201" alt="image" src="https://github.com/user-attachments/assets/8ed0e9f6-c0a6-4c9f-8162-3268eb7f02e1" />

The application uses KSP (Kotlin Symbol Processing) instead of KAPT for annotation processing, providing faster compilation times for Room and Hilt code generation.

## Sources:

app/build.gradle.kts
1-7
app/build.gradle.kts
9-42
Dependency Injection Architecture

## Diagram: Hilt Dependency Injection Flow
<img width="1721" height="377" alt="image" src="https://github.com/user-attachments/assets/dcb65859-f2b6-4e12-a0b7-7039c48e4ad1" />

## The application uses Hilt for compile-time dependency injection:

## Compile-Time Generation: 
The Hilt Gradle plugin 
app/build.gradle.kts
6
 and KSP 
app/build.gradle.kts
5
 process annotations to generate DI components

## Application Scope: 
ConverterApp with @HiltAndroidApp 
app/src/main/java/com/compose/unitconverterapp/ConverterApp.kt
6
 initializes the application-level DI container

## Activity Scope:
MainActivity with @AndroidEntryPoint 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
18
 enables constructor and field injection

## Dependency Graph:
Hilt provides ConverterViewModelFactory 
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
20-21
 which creates ConverterViewModel with injected dependencies

## Sources:

app/build.gradle.kts
5-6
app/build.gradle.kts
69-70
app/src/main/java/com/compose/unitconverterapp/ConverterApp.kt
6-7
app/src/main/java/com/compose/unitconverterapp/MainActivity.kt
18-21
