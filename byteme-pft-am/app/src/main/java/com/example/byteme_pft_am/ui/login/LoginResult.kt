package com.example.byteme_pft_am.ui.login

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
