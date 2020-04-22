package page.shellcore.tech.android.dogs.model


import com.google.gson.annotations.SerializedName

data class DogBreed(
    @SerializedName("bred_for")
    val bredFor: String?,
    @SerializedName("breed_group")
    val breedGroup: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("height")
    val height: Height?,
    @SerializedName("history")
    val history: String?,
    @SerializedName("id")
    val breedId: String?,
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("name")
    val dogBreed: String?,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("url")
    val imageUrl: String?,
    @SerializedName("weight")
    val weight: Weight?
)