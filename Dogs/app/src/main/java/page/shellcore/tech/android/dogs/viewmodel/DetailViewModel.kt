package page.shellcore.tech.android.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import page.shellcore.tech.android.dogs.model.DogBreed

class DetailViewModel: ViewModel() {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog = DogBreed("1", "Corgi", "15 years", "breadGroup", "bredFor", "temperament", "")
        dogLiveData.value = dog
    }
}