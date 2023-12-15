package id.my.nutrikita.data.remote.retrofit

import id.my.nutrikita.data.remote.response.CustomFoodResponse
import id.my.nutrikita.data.remote.response.FoodDetectResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MLApiService {
    @Multipart
    @POST("prediction")
    suspend fun foodDetect(
        @Part image: MultipartBody.Part,
    ): FoodDetectResponse

    @FormUrlEncoded
    @POST("form")
    suspend fun customFood(
        @Field("Calories") calories: Int,
        @Field("FatContent") fatContent: Int,
        @Field("SaturatedFatContent") saturatedFatContent: Int,
        @Field("CholesterolContent") cholesterolContent: Int,
        @Field("SodiumContent") sodiumContent: Int,
        @Field("CarbohydrateContent") carbohydrateContent: Int,
        @Field("FiberContent") fiberContent: Int,
        @Field("SugarContent") sugarContent: Int,
        @Field("ProteinContent") proteinContent: Int
    ): CustomFoodResponse
}