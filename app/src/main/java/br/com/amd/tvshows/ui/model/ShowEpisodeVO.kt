package br.com.amd.tvshows.ui.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ShowEpisodeVO(
    val id: Long,
    val name: String,
    val summary: String,
    val season: Int,
    val number: Int,
    val mediumImageUrl: String,
    val originalImageUrl: String,
): Parcelable
