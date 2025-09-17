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
