package page.shellcore.tech.android.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import page.shellcore.tech.android.dogs.model.DogBreed
import page.shellcore.tech.android.dogs.model.DogDatabase
import page.shellcore.tech.android.dogs.model.DosgApiService
import page.shellcore.tech.android.dogs.util.SharedPreferencesHelper

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val prefHelper = SharedPreferencesHelper(getApplication())

    private val dogService = DosgApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogService.getDogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({dogList ->
                    storeDogsLocally(dogList)
                }, {
                    dogsLoadError.value = true
                    loading.value = false
                    it.printStackTrace()
                })
        )
    }

    private fun storeDogsLocally(dogList: List<DogBreed>) {
        launch {
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val result = dao.insertAll(*dogList.toTypedArray())
            var i = 0
            while (i < dogList.size) {
                dogList[i].uuid = result[i].toInt()
                ++i
            }
            dogsRetrieved(dogList)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    private fun dogsRetrieved(dogList: List<DogBreed>) {
        dogsLoadError.value = false
        loading.value = false
        dogs.value = dogList
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}