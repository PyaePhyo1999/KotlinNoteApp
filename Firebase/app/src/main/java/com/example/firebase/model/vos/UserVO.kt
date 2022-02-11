package com.example.firebase.model.vos

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserVO(
    var id: String,
    var name: String,
    var email: String
)


