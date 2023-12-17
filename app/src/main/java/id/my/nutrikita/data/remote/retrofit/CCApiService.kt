package id.my.nutrikita.data.remote.retrofit

import id.my.nutrikita.data.remote.model.RegisterModel
import id.my.nutrikita.data.remote.response.NewsResponse
import id.my.nutrikita.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CCApiService {

    @POST("auth/signup")
    suspend fun register(
        @Body request: RegisterModel
    ): RegisterResponse

    @GET("news")
    suspend fun getNews(): NewsResponse
}