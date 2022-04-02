package com.henry.marvelmahle.presentation.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.series.SeriesResult
import com.henry.marvelmahle.data.repository.SeriesRepository
import com.henry.marvelmahle.utils.NetworkHelper
import com.henry.marvelmahle.utils.Resource
import kotlinx.coroutines.launch

class SeriesMainVM (
    private val repository: SeriesRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    // region PROPERTIES ---------------------------------------------------------------------------

    private val _characterSeriesList = MutableLiveData<Resource<List<SeriesResult>>>()
    val characterSeriesList: LiveData<Resource<List<SeriesResult>>> = _characterSeriesList

    // endregion


    // region PUBLIC METHODS ----------------------------------------------------------------------

    fun getCharacterSeriesList(characterId: CharacterId) {
        viewModelScope.launch {
            _characterSeriesList.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                repository.getCharacterSeries(characterId).let {
                    if (it.isSuccessful) {
                        _characterSeriesList.postValue(Resource.success(it.body()?.data?.results))
                    } else {
                        _characterSeriesList.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else _characterSeriesList.postValue(Resource.error("No internet connection", null))
        }
    }
    // endregion
}