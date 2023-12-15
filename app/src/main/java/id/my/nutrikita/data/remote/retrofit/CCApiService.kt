package id.my.nutrikita.data.remote.retrofit

import id.my.nutrikita.data.remote.response.NewsResponse
import id.my.nutrikita.data.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CCApiService {
    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @GET("news")
    suspend fun getNews(): NewsResponse
}