package com.paranid5.yt_url_extractor_kt

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * All possible formats, livestream URLs and metadata associated with a YouTube video
 * @property ytFiles Available video formats,
 * stored as a map of itag and corresponding [YtFile]
 * @property livestreamManifests The result of fetching the [LiveStreamManifests]
 * @property videoMeta The result of fetching [VideoMeta]
 */

@Parcelize
data class StreamData(
    @JvmField val ytFiles: Map<Int, YtFile>,
    @JvmField val livestreamManifests: Result<LiveStreamManifests>,
    @JvmField val videoMeta: Result<VideoMeta>
) : Parcelable