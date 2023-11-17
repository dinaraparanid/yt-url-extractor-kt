# YtUrlExtractorKt

[![Kotlin](https://img.shields.io/badge/kotlin-1.9.0-blue.svg?logo=kotlin)](http://kotlinlang.org)

## **Developer**
[Paranid5](https://github.com/dinaraparanid)

## **About Library**
Android library to extract media files from YouTube.
Library utilises Kotlin Coroutines and is built on top of [Ktor client library](https://ktor.io/) as an extension function.

### Example:

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

## **System Requirements**
**Android 5.0** or higher