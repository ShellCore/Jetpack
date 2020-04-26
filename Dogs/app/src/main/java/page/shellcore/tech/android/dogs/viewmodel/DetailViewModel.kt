package page.shellcore.tech.android.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import page.shellcore.tech.android.dogs.model.DogBreed
import page.shellcore.tech.android.dogs.model.DogDatabase

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao()
                .getDog(uuid)

            dogLiveData.value = dog
        }
    }
}