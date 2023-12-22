<div align="center">
  <img src="https://raw.githubusercontent.com/Bangkit-2023-Capstone-CH2-PS307/.github/main/profile/assets/logo.png" alt="Logo NutriKita" style="width: 20%;">
  <h1>Mobile Application</h1>
  <p>NutriKita Apps was created by mobile development member using CameraX, Retrofit, Room, and Firebase to build features such as authentication feature, custom food feature, check food nutrition feature, favorite food feature, and profile edit feature. Beside that, mobile development member also build another features such as splash screen, localization, etc.
</p>
</div>

<div align="center">
  <img src="https://raw.githubusercontent.com/Bangkit-2023-Capstone-CH2-PS307/.github/main/profile/assets/nutrikita%20overview.png" alt="Result App" style="width: 100%;">
</div>

## Tools & Libraries
- [Android Studio](https://developer.android.com/studio)
- Retrofit
- [Room](https://developer.android.com/jetpack/androidx/releases/room)
- [CameraX](https://developer.android.com/training/camerax)
- [Figma](https://www.figma.com/)
- [Firebase](https://firebase.google.com/)

## Demo Apps
[NutriKita Demo Apps](https://drive.google.com/file/d/1ZUX8kb0fgL67HkWwY1MDMahEqdRhZg8Y/view)

## Download APK
Download the APK here:<br>
[NutriKita APK](https://drive.google.com/file/d/1WnLHmuKmRjj20Ed3vNd-Wnq2w83Yr88y/view?usp=drive_link)

## Installation
#### Prerequisites
- Android Studio
#### Steps
- Clone project
 ```bash
 $ git clone https://github.com/Bangkit-2023-Capstone-CH2-PS307/MD.git
 ```
- Run the Application


## Mobile Development Architecture [Model-View-ViewModel Architecture]
<div align="center">
  <p>We use model-view-viewmodel architecture for building the apps. Here, we divide parts into the ui layer and data layer. In the ui layer, we used a ViewModel that incorporates LiveData to supply observed data to the activity. To get the data, view models are connected to the repository through a view model factory. The repository itself is located in the data layer and used to manage data sources both from local data source and remote data source. For the local data source, we implement Room to establish the database. As for the remote data source, Retrofit is used to interact with web services.
</p>
  <img src="https://raw.githubusercontent.com/Bangkit-2023-Capstone-CH2-PS307/.github/main/profile/assets/MD%20Architecture.png" alt="Model-View-ViewModel Architecture" style="width: 100%;">
</div>
