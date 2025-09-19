🏙️ CityGuide App

CityGuide App is an Android application built with Jetpack Compose that allows users to explore, save, categorize, review, and share interesting places in a city. The app uses modern Android development practices like Room Database and Hilt dependency injection to provide a smooth and interactive experience.

✨ Features

 🏷️ Add Places: Users can add a place with details such as name, description, category, optional image, and rating.

 📋 View Places: Browse a list of saved places displayed in cards with images, ratings, and basic info.

 ❌ Delete Places: Easily remove unwanted places from the list.

 🗂️ Category Filters: Filter places by category, e.g., restaurants, parks, museums, etc.

 📤 Share Place Details: Share information about a place with others via intents.

 ⭐ Rating: Users can add ratings to places.

 💾 Offline Storage: All data is stored locally using Room Database.

 🎨 Clean UI: Built entirely with Jetpack Compose for a modern, responsive interface.

📸 Screenshots
<p align="center">
<img src="https://github.com/engineerbuddyy/City_Guide/blob/ae6f4789430189fa2b60391ef1689e781caebabe/Homescreen.jpeg" width="400">
<img src="https://github.com/engineerbuddyy/City_Guide/blob/ae6f4789430189fa2b60391ef1689e781caebabe/Addscreen.jpeg" width="400">
<img src="https://github.com/engineerbuddyy/City_Guide/blob/ae6f4789430189fa2b60391ef1689e781caebabe/Bookmark.jpeg" width="400">
<img src="https://github.com/engineerbuddyy/City_Guide/blob/ae6f4789430189fa2b60391ef1689e781caebabe/Category.jpeg" width="400">
<img src="https://github.com/engineerbuddyy/City_Guide/blob/ae6f4789430189fa2b60391ef1689e781caebabe/PlaceDetail.jpeg" width="400">
</p>


🏗️ Architecture

The app follows MVVM (Model-View-ViewModel) architecture:

🧩 Model: Data classes such as PlaceEntity represent the app’s data.

🧠 ViewModel: Handles business logic, data retrieval, and state management using StateFlow.

🖥️ View (UI): Composable screens for listing, adding, filtering, reviewing, and sharing places.



🛠️ Tech Stack


 💻 Language: Kotlin

 🎨 UI Framework: Jetpack Compose

 🗄️ Database: Room Database

 🔧 Dependency Injection: Hilt

 ⏱️ Coroutines: For asynchronous operations
 


🚀 Future Improvements


 ☁️ Cloud Backup: Sync places across devices.

 🗺️ Map Integration: Show places and directions (optional future update).

 ❤️ Already wrote code for animation , integrate while clicking heart i.e Bookmark.


🧠 Some of the most common and interesting Errors-

  1.Can we use suspend and Flow together in a function signature?

  Sol-No, you cannot use suspend and Flow together.

   suspend → a function that runs once and returns a single value.

   Flow → a cold asynchronous stream that can emit multiple values over time. 
   So make sure not make a function suspend if you are using Flow

  2.What happens when you change the schema version in Room?

  Sol-
    Write a Migration (best practice)

   Use destructive migration (testing only) : .fallbackToDestructiveMigration()

  3.What causes the error Row too big to fit into CursorWindow?

   Its a Room/SQLite error .It happens when one Column usually a string or ByteArray is too large for SQLite Cursor Window (2 MB limit per row).Storing images (BITMAP)   
   directly in the databases as ByteArray(too Big).

  4.Why does the keyboard overlap (cover) a Row or TextField in Jetpack Compose?

   Sol- Use .imePadding() 

  5.What causes the error Source must not be null?

   Sol- Match the version of Serialization dependency with the coroutine dependency.

