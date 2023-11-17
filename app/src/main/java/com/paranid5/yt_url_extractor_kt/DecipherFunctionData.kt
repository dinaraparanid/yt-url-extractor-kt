package com.paranid5.yt_url_extractor_kt

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class representing the information needed to decipher a url from the YouTube
 * @property decipherJsFileName The filename of the JavaScript file containing the decipher function
 * @property decipherFunctionName The name of the decipher function
 * @property decipherFunctions The source code of the decipher function
 */

@Parcelize
internal data class DecipherFunctionData(
    @JvmField val decipherJsFileName: String? = null,
    @JvmField val decipherFunctionName: String? = null,
    @JvmField val decipherFunctions: String? = null
) : Parcelable