package id.my.nutrikita.data.remote.response

import com.google.gson.annotations.SerializedName

data class FoodDetectResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class Data(

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("prediction")
	val prediction: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("percentages")
	val percentages: List<String>,

	@field:SerializedName("nutritions")
	val nutritions: List<String>
)
