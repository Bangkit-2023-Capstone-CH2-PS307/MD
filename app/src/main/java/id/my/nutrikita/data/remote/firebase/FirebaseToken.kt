package id.my.nutrikita.data.remote.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

object FirebaseToken {
    fun getToken(): String {
        val auth: FirebaseAuth = Firebase.auth
        val user = auth.currentUser
        var token = ""
        user?.getIdToken(true)
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    token = it.result.token.toString()
                }
            }
        return token
    }
}