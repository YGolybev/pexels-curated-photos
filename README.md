# Pexels Curated Photos App

https://github.com/YGolybev/pexels-curated-photos/assets/14962060/6f43e1c7-3230-4e64-b31c-f5ddd6a99e02

[Prebuilt release-mode APK available here.](pexels.apk)

The app uses Pexels curated photos API to show a list of curated photos and a screen for viewing the whole photo.
Caching is done via Ktor and Coil file storage caches.

To build this app, you will need to put the Pexels API key into `app/secret.properties` as follows:
```
PexelsApiKey=YOUR_API_KEY
```

Also, as this app uses Jetpack Compose, expect it to be a bit slow in `debug` mode.
