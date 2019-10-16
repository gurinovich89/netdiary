package com.netdiary.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    var id: Long,
    var name: String = "",
    var imageUrl: String = "",
    var highResolutionImageUrl: String = ""
) : Parcelable