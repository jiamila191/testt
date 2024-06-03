package com.example.byteme_pft_am.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val id: String,
    val displayName: String,
    val role: String
)
