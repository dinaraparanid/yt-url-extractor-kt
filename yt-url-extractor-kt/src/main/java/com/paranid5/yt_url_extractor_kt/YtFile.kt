package com.paranid5.yt_url_extractor_kt

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * YouTube file's data associated with a video's itag
 * @property format The format of the file, such as MP4 or HLS
 * @property url The URL of the file
 */

@Parcelize
data class YtFile(val format: Format?, val url: String?) : Parcelable