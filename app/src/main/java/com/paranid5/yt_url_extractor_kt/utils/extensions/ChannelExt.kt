package com.paranid5.yt_url_extractor_kt.utils.extensions

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration

internal suspend inline fun <T> Channel<T>.receiveTimeout(duration: Duration) = runCatching {
    withTimeout(duration) { receive() }
}

internal suspend inline fun <T> Channel<T>.receiveTimeout(millis: Long) = runCatching {
    withTimeout(millis) { receive() }
}