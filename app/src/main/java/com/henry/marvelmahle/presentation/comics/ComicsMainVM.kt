package com.henry.marvelmahle.presentation.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.comic.ComicResult
import com.henry.marvelmahle.data.repository.AppRepository
import com.henry.marvelmahle.utils.NetworkHelper
import com.henry.marvelmahle.utils.Resource
import kotlinx.coroutines.launch

class ComicsMainVM (
    private val appRepository: AppRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    // region PROPERTIES ---------------------------------------------------------------------------

    private val _characterComicsList = MutableLiveData<Resource<List<ComicResult>>>()
    val characterComicsList: LiveData<Resource<List<ComicResult>>> = _characterComicsList

    // endregion


    // region PUBLIC METHODS ----------------------------------------------------------------------

    fun getCharacterComicsList(characterId: CharacterId) {
        viewModelScope.launch {
            _characterComicsList.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                appRepository.getCharacterComics(characterId).let {
                    if (it.isSuccessful) {
                        _characterComicsList.postValue(Resource.success(it.body()?.data?.results))
                    } else {
                        _characterComicsList.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else _characterComicsList.postValue(Resource.error("No internet connection", null))
        }
    }
    // endregion
}