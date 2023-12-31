# YtUrlExtractorKt

[![Kotlin](https://img.shields.io/badge/kotlin-1.9.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![JitPack](https://jitpack.io/v/dinaraparanid/yt-url-extractor-kt.svg)](https://jitpack.io/#dinaraparanid/yt-url-extractor-kt)

## **Developer**
[Paranid5](https://github.com/dinaraparanid)

## **About Library**
Android library to extract media files from YouTube.
Library utilises Kotlin Coroutines and is built on top of [Ktor client library](https://ktor.io/) as an extension function.

## **Setup**

1. Add network permission to your manifest:

```
<uses-permission android:name="android.permission.INTERNET" />
```

2. Add this code snippet to your build.graddle or settings.gradle file:

**Kotlin DSL:**

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") } // <- add this
    }
}
```

**Groovy DSL:**

```groovy
dependencyResolutionManagement { 
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // <- add this
    }
}
```

3. Add the dependency to your app level build.gradle:

```kotlin
dependencies {
    implementation("com.github.dinaraparanid:yt-url-extractor-kt:x.y.z.w")
}
```

```groovy
dependencies {
    implementation 'com.github.dinaraparanid:yt-url-extractor-kt:x.y.z.w'
}
```

## **Example**

1. Create Ktor client:

```Kotlin
private const val USER_AGENT =
    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.98 Safari/537.36"

fun KtorClient() = HttpClient(OkHttp) {
    install(UserAgent) {
        agent = USER_AGENT
    }

    install(HttpRequestRetry)

    install(HttpTimeout) {
        connectTimeoutMillis = null
    }

    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
}
```

2. Extract files by URL with an extension function:

```Kotlin
val extractRes: Result<StreamData> = withTimeoutOrNull(timeMillis = 4500) {
    ktorClient.extractYtFilesWithMeta(
        context = context,
        ytUrl = ytUrl
    )
} ?: YtFailure(YtRequestTimeoutException())

val (
    ytFiles: Map<Int, YtFile>, // Itag to media file
    liveStreamManifestsRes: Result<LiveStreamManifests>, // DASH and HLS stream manifests
    videoMetaRes: Result<VideoMeta> // Video metadata
) = when (val res = extractRes.getOrNull()) {
    null -> onExtractionError(extractRes.exceptionOrNull()!!)
    else -> res
}

val videoMeta: VideoMeta = when (val res = videoMetaRes.getOrNull()) {
    null -> onExtractionError(videoMetaRes.exceptionOrNull()!!)
    else -> res
}

val liveStreamManifests = when (val res = liveStreamManifestsRes.getOrNull()) {
    null -> onExtractionError(liveStreamManifestsRes.exceptionOrNull()!!)
    else -> res
}

// Extract desired url (e.g. itag = 140 for audio files)
val audioUrl: String? = when (videoMeta?.isLiveStream) {
    true -> liveStreamManifests.hlsManifestUrl
    else -> ytFiles[DEFAULT_AUDIO_TAG]?.url
}

if (audioUrl == null)
    onExtractionError(YtFilesNotFoundException())
```

### Samples

1. [Streaming of audio and livestreaming with Exoplayer](https://github.com/dinaraparanid/Crescendo/blob/24ed03bc2d72006aa5637a9aade568746331778d/app/src/main/java/com/paranid5/crescendo/domain/services/stream_service/StreamService.kt#L694)
2. [Extract and cache audio files](https://github.com/dinaraparanid/Crescendo/blob/24ed03bc2d72006aa5637a9aade568746331778d/app/src/main/java/com/paranid5/crescendo/domain/services/video_cache_service/VideoCacheService.kt#L199)

## **System Requirements**
**Android 5.0** or higher

## **License**
*GNU Public License V 3.0*
