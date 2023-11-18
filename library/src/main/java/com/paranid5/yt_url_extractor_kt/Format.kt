package com.paranid5.yt_url_extractor_kt

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Video format supported by YouTube
 * @property itag The unique identifier for the format
 * @property ext The file extension for the format
 * @property isDashContainer Whether or not the format is a DASH container
 * @property height The height of the video in pixels.
 * For audio-only formats height is always -1
 * @property fps The frames per second of the video.
 * For audio-only formats fps is always -1
 * @property audioBitrate The audio bitrate in bits per second.
 * For video-only formats audio bitrate is always -1
 * @property isHlsContent Whether or not the format is HLS content
 * @property videoCodec [VCodec] used for the format.
 * For audio-only formats video codec is always null
 * @property audioCodec [ACodec] used for the format.
 * For video-only formats audio codec is always null
 * @see <a href="https://gist.github.com/sidneys/7095afe4da4ae58694d128b1034e01e2">List of supported itags</a>
 */

@Parcelize
data class Format(
    @JvmField val itag: Int,
    @JvmField val ext: String,
    @JvmField val isDashContainer: Boolean,
    @JvmField val height: Int = -1,
    @JvmField val fps: Int = -1,
    @JvmField val audioBitrate: Int = -1,
    @JvmField val isHlsContent: Boolean = false,
    @JvmField var videoCodec: VCodec? = null,
    @JvmField var audioCodec: ACodec? = null
) : Parcelable {

    /** The video codec used for the format */

    enum class VCodec {
        H263,
        H264,
        MPEG4,
        VP8,
        VP9,
        NONE
    }

    /** The audio codec used for the format */

    enum class ACodec {
        MP3,
        AAC,
        VORBIS,
        OPUS,
        NONE
    }
}