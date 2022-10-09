![](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)
![GitHub repo size](https://img.shields.io/github/languages/code-size/aldomddev/tv-shows?style=for-the-badge&logo=github)

# tv-shows

An unofficial TV shows app based on [tvmaze api](https://www.tvmaze.com/api)

https://user-images.githubusercontent.com/47090245/192408322-4c570ace-3f96-437a-865e-b8b7906c74dd.mp4

### Main functionalities

The app has 3 main screens:

- All available TV shows
- Search TV Shows
- Favorite shows

The user is able to:

- See all available shows (paginated)
- Click on a show and see the details
- Add and remove a show from favorites (show details screen and favorites tab)
- See all episodes by season and see the details of an episode

### Architecture and Technologies

- Clean architecture + MVVM - 3 broad layers separation: data, domain and ui with object mappers for crossing boundaries
- [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) for background work
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
- [Room](https://developer.android.com/training/data-storage/room) for database
- [Navigation component](https://developer.android.com/jetpack/compose/navigation)
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for pagination
- [Coil](https://coil-kt.github.io/coil/) for image loading
- [Retrofit](https://square.github.io/retrofit/) for REST calls
- [Android Studio Dolphin | 2021.3.1](https://developer.android.com/studio)

### Adjustments and improvements

The project is not finished yet. The next updates will include the following main changes:

- [ ] Loading and error states for all shows screen
- [ ] Loading, error and empty states for show detail screen
- [ ] Loading, error and empty states for episode detail screen
- [ ] Loading, error and empty states for search shows screen
- [ ] Add success end error messages for adding and removing a show from favorites
- [ ] Add support to add and remove from favorites on all shows screen list (and sync state with db)
- [ ] Add pagination to favorites screen
- [ ] Search person functionality
