package com.henry.marvelmahle.presentation.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henry.marvelmahle.data.model.Results
import com.henry.marvelmahle.data.repository.AppRepository
import com.henry.marvelmahle.utils.NetworkHelper
import com.henry.marvelmahle.utils.Resource
import kotlinx.coroutines.launch

class CharactersMainVM (
    private val appRepository: AppRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    // region PROPERTIES ---------------------------------------------------------------------------

    private val _characterList = MutableLiveData<Resource<List<Results>>>()
    val characterList: LiveData<Resource<List<Results>>> = _characterList

    init {
        getCharacterList()
    }
    // endregion


    // region PRIVATE METHODS ----------------------------------------------------------------------

    private fun getCharacterList() {
        viewModelScope.launch {
            _characterList.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                appRepository.getCharacters().let {
                    if (it.isSuccessful) {
                        val characterList = it.body()?.data?.results as ArrayList<Results>
                        _characterList.postValue(Resource.success(characterList))
                    } else {
                        _characterList.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else _characterList.postValue(Resource.error("No internet connection", null))
        }
    }
    // endregion
}