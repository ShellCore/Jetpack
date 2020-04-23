package page.shellcore.tech.android.dogs.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import page.shellcore.tech.android.dogs.model.DogBreed
import page.shellcore.tech.android.dogs.model.DogDatabase
import page.shellcore.tech.android.dogs.model.DosgApiService
import page.shellcore.tech.android.dogs.util.SharedPreferencesHelper
import java.util.concurrent.TimeUnit

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val prefHelper = SharedPreferencesHelper(getApplication())

    private var refreshTime = TimeUnit.MINUTES.toNanos(5)

    private val dogService = DosgApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val updateTime = prefHelper.getUpdateTime()
        if (updateTime != null
            && updateTime != 0L
            && System.nanoTime() - updateTime < refreshTime
        ) {
            fetchFromDatabase()
        } else {
            fetchFromRemote()
        }
    }

    fun refreshBypassCache() {
        fetchFromRemote()
    }

    private fun fetchFromDatabase() {
        loading.value = true
        launch {
            val dogs = DogDatabase(getApplication()).dogDao()
                .getAllDogs()
            dogsRetrieved(dogs)
            Toast.makeText(getApplication(), "Dogs retrieved from database.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogService.getDogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dogList ->
                    storeDogsLocally(dogList)
                    Toast.makeText(getApplication(), "Dogs retrieved from endpoint.", Toast.LENGTH_SHORT)
                        .show()
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