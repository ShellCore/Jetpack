package page.shellcore.tech.android.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import page.shellcore.tech.android.dogs.model.DogBreed

class ListViewModel: ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val dog1 = DogBreed("1", "Corgi", "15 years", "breadGroup", "bredFor", "temperament", "")
        val dog2 = DogBreed("2", "Labrador", "10 years", "breadGroup", "bredFor", "temperament", "")
        val dog3 = DogBreed("3", "Rotwailer", "20 years", "breadGroup", "bredFor", "temperament", "")
        val dog4 = DogBreed("4", "Rotwailer", "20 years", "breadGroup", "bredFor", "temperament", "")
        val dog5 = DogBreed("5", "Rotwailer", "20 years", "breadGroup", "bredFor", "temperament", "")

        val dogList = arrayListOf(dog1, dog2, dog3, dog4, dog5)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}