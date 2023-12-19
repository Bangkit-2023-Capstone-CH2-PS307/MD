package id.my.nutrikita.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.my.nutrikita.data.remote.model.RegisterModel
import id.my.nutrikita.data.remote.response.RegisterResponse
import id.my.nutrikita.data.repository.Repository
import id.my.nutrikita.data.remote.Result

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun postRegister(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> {
        val user = RegisterModel(email, password, name)
        return repository.postRegister(user)
    }
}