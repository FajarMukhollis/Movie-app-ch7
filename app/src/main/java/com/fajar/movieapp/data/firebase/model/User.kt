package com.fajar.movieapp.data.firebase.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    var id: String = "",
    var username: String = "",
    var email: String = "",
    var fullName: String = "",
    var dateOfBirth: String = "",
    var address: String = "",
    var profileImage: String = ""
): Parcelable
