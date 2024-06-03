package com.example.byteme_pft_am.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.byteme_pft_am.R
import com.example.byteme_pft_am.data.LoginRepository
import com.example.byteme_pft_am.data.Result
import com.example.byteme_pft_am.data.model.LoggedInUser

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        loginRepository.login(username, password) { result ->
            if (result is Result.Success) {
                val user = result.data
                _loginResult.postValue(LoginResult(success = LoggedInUserView(user.userId, user.displayName, user.role)))
            } else {
                if (result is Result.Error) {
                    _loginResult.postValue(LoginResult(error = R.string.login_failed))
                } else {
                    _loginResult.postValue(LoginResult(error = R.string.login_failed))
                }
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
