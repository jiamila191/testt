package com.example.byteme_pft_am.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}

data class LoginRequest(val username: String, val password: String)

data class LoginResponse(val id: String, val username: String, val role: String)
