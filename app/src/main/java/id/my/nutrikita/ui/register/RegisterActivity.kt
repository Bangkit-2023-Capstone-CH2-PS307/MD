package id.my.nutrikita.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import id.my.nutrikita.R
import id.my.nutrikita.ViewModelFactory
import id.my.nutrikita.data.remote.Result
import id.my.nutrikita.databinding.ActivityRegisterBinding
import id.my.nutrikita.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = obtainViewModel(this@RegisterActivity)

        auth = Firebase.auth

        binding.switchToLogin.setOnClickListener {
            switchToLogin()
        }

        binding.registerButton.setOnClickListener {
            onRegisterButtonClicked()
        }
    }

    private fun obtainViewModel(registerActivity: RegisterActivity): RegisterViewModel {
        val factory = ViewModelFactory.getInstance(registerActivity.application)
        return ViewModelProvider(registerActivity, factory)[RegisterViewModel::class.java]
    }

    private fun onRegisterButtonClicked() {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val confirmPassword = binding.confirmPasswordEditText.text.toString()

        if (password == confirmPassword) {
            registerViewModel.postRegister(name, email, password).observe(this) { result ->
                when (result) {
                    is Result.Success -> {
                        showLoading(false)
                        Toast.makeText(
                            this@RegisterActivity,
                            result.data.message, Toast.LENGTH_SHORT
                        ).show()
                        switchToLogin()
                    }

                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(this@RegisterActivity, result.error, Toast.LENGTH_SHORT)
                            .show()
                    }

                    is Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Empty -> {
                        showLoading(false)
                        Toast.makeText(
                            this,
                            getString(R.string.response_data_is_empty),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else {
            binding.registerResult.text = getString(R.string.error_confirm_password)
        }
    }

    private fun switchToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}