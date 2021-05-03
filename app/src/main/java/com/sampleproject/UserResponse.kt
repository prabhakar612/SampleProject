package com.sampleproject

data class UserResponse(
    val circles: List<Circle>,
    val loggedInUser: LoggedInUser
)