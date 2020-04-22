package page.shellcore.tech.android.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import page.shellcore.tech.android.dogs.model.DogBreed

class DetailViewModel: ViewModel() {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog = DogBreed(breedId = "1", dogBreed = "Corgi", lifeSpan = "15 years", breedGroup = "breadGroup", bredFor = "bredFor", temperament = "temperament", imageUrl = "", countryCode = "", description = "", history = "", origin = "", height = null, weight = null)
        dogLiveData.value = dog
    }
}