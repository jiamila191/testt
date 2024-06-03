package com.example.byteme_pft_am.data

import com.example.byteme_pft_am.data.model.LoggedInUser
import java.io.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginDataSource {
    fun login(username: String, password: String, callback: (Result<LoggedInUser>) -> Unit) {
        val apiService = RetrofitClient.apiService
        val call = apiService.login(LoginRequest(username, password))

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.role != null) {
                        val user = LoggedInUser(loginResponse.id, loginResponse.username, loginResponse.role)
                        callback(Result.Success(user))
                    } else {
                        callback(Result.Error(IOException("Invalid credentials")))
                    }
                } else {
                    callback(Result.Error(IOException("Error logging in: ${response.code()} ${response.message()}")))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(Result.Error(IOException("Error logging in", t)))
            }
        })
    }

    fun logout() {
        // TODO: revoke authentication
    }
}
