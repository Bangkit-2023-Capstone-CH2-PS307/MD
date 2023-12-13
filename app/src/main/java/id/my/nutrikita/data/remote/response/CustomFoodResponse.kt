package id.my.nutrikita.data.remote.response

import com.google.gson.annotations.SerializedName

data class CustomFoodResponse(

    @field:SerializedName("CustomFoodResponse")
    val customFoodResponse: List<CustomFoodResponseItem>
)

data class CustomFoodResponseItem(

    @field:SerializedName("RecipeId")
    val recipeId: Int? = null,

    @field:SerializedName("Name")
    val name: String? = null,

    @field:SerializedName("Description")
    val description: String? = null,

    @field:SerializedName("Images")
    val images: String? = null,

    @field:SerializedName("RecipeIngredientQuantities")
    val recipeIngredientQuantities: List<String?>,

    @field:SerializedName("RecipeIngredientParts")
    val recipeIngredientParts: List<String?>? = null,

    @field:SerializedName("RecipeInstructions")
    val recipeInstructions: List<String?>? = null,

    @field:SerializedName("RecipeServings")
    val recipeServings: Double? = null,

    @field:SerializedName("RecipeCategory")
    val recipeCategory: String? = null,

    @field:SerializedName("PrepTime")
    val prepTime: String? = null,

    @field:SerializedName("TotalTime")
    val totalTime: String? = null,

    @field:SerializedName("CookTime")
    val cookTime: String? = null,

    @field:SerializedName("SugarContent")
    val sugarContent: Double? = null,

    @field:SerializedName("CholesterolContent")
    val cholesterolContent: Double? = null,

    @field:SerializedName("SaturatedFatContent")
    val saturatedFatContent: Double? = null,

    @field:SerializedName("ProteinContent")
    val proteinContent: Double? = null,

    @field:SerializedName("SodiumContent")
    val sodiumContent: Double? = null,

    @field:SerializedName("Calories")
    val calories: Double? = null,

    @field:SerializedName("CarbohydrateContent")
    val carbohydrateContent: Double? = null,

    @field:SerializedName("FatContent")
    val fatContent: Double? = null,

    @field:SerializedName("FiberContent")
    val fiberContent: Double? = null
)
