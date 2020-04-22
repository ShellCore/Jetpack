package page.shellcore.tech.android.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import page.shellcore.tech.android.dogs.model.DogBreed

class ListViewModel: ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val dog1 = DogBreed(breedId = "1", dogBreed = "Corgi", lifeSpan = "15 years", breedGroup = "breadGroup", bredFor = "bredFor", temperament = "temperament", imageUrl = "", countryCode = "", description = "", history = "", origin = "", height = null, weight = null)
        val dog2 = DogBreed(breedId = "1", dogBreed = "Terrier", lifeSpan = "15 years", breedGroup = "breadGroup", bredFor = "bredFor", temperament = "temperament", imageUrl = "", countryCode = "", description = "", history = "", origin = "", height = null, weight = null)
        val dog3 = DogBreed(breedId = "1", dogBreed = "Labrador", lifeSpan = "15 years", breedGroup = "breadGroup", bredFor = "bredFor", temperament = "temperament", imageUrl = "", countryCode = "", description = "", history = "", origin = "", height = null, weight = null)
        val dog4 = DogBreed(breedId = "1", dogBreed = "Pomeranian", lifeSpan = "15 years", breedGroup = "breadGroup", bredFor = "bredFor", temperament = "temperament", imageUrl = "", countryCode = "", description = "", history = "", origin = "", height = null, weight = null)
        val dog5 = DogBreed(breedId = "1", dogBreed = "Chihuahua", lifeSpan = "15 years", breedGroup = "breadGroup", bredFor = "bredFor", temperament = "temperament", imageUrl = "", countryCode = "", description = "", history = "", origin = "", height = null, weight = null)

        val dogList = arrayListOf(dog1, dog2, dog3, dog4, dog5)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}