package com.paranid5.yt_url_extractor_kt

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Metadata of a YouTube video
 * @property videoId The unique identifier for the video
 * @property title The title of the video
 * @property author The name of the video's author
 * @property channelId The unique identifier for the video's channel
 * @property videoLengthSecs The duration of the video in seconds
 * @property viewCount The number of times the video has been viewed
 * @property isLiveStream Whether or not the video is a live stream
 * @property shortDescription A brief description of the video
 */

@Parcelize
data class VideoMeta(
    val videoId: String,
    val title: String,
    val author: String,
    val channelId: String,
    val videoLengthSecs: Long,
    val viewCount: Long,
    val isLiveStream: Boolean,
    val shortDescription: String
) : Parcelable {
    private companion object {
        private const val IMAGE_BASE_URL = "http://i.ytimg.com/vi/"
    }

    /** 120 x 90 */
    val thumbnailUrl
        get() = "$IMAGE_BASE_URL$videoId/default.jpg"

    /** 320 x 180 */
    val mqImageUrl
        get() = "$IMAGE_BASE_URL$videoId/mqdefault.jpg"

    /** 480 x 360 */
    val hqImageUrl
        get() = "$IMAGE_BASE_URL$videoId/hqdefault.jpg"

    /** 640 x 480 */
    val sdImageUrl
        get() = "$IMAGE_BASE_URL$videoId/sddefault.jpg"

    /** Max Res */
    val maxResImageUrl
        get() = "$IMAGE_BASE_URL$videoId/maxresdefault.jpg"
}