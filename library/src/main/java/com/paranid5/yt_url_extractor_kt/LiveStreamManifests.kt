package com.paranid5.yt_url_extractor_kt

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Container of the URLs for the DASH and HLS live stream manifests
 * @property dashManifestUrl The URL of the DASH live stream manifest
 * @property hlsManifestUrl The URL of the HLS live stream manifest
 */

@Parcelize
data class LiveStreamManifests(
    @JvmField val dashManifestUrl: String? = null,
    @JvmField val hlsManifestUrl: String? = null
) : Parcelable