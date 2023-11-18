package com.paranid5.yt_url_extractor_kt

/** Base class for all YouTube-related exceptions */
open class YtException(message: String? = null) : RuntimeException(message)

class WrongYtUrlFormatException : YtException("Wrong YouTube URL format")

class YtPlayerResponseNotFoundException : YtException("ytPlayerResponse was not found")

class YtPlayerResponseStructureChangedException : YtException("ytPlayerResponse structure changed")

class YtFilesNotFoundException : YtException("Yt files not found")

class YtRequestTimeoutException : YtException("Timeout for the request has expired")

/**
 * Wraps an [YtException] in a [Result.failure] object
 * @param exception The [YtException] to wrap
 * @return A [Result.failure] object containing the [exception]
 */

@Suppress("FunctionName")
fun <T> YtFailure(exception: YtException) = Result.failure<T>(exception)