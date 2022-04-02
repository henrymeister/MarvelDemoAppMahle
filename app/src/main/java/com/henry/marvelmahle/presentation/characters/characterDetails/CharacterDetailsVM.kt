package com.henry.marvelmahle.presentation.characters.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResult
import com.henry.marvelmahle.data.repository.CharactersRepository
import com.henry.marvelmahle.utils.NetworkHelper
import com.henry.marvelmahle.utils.Resource
import kotlinx.coroutines.launch

class CharacterDetailsVM (
    private val repository: CharactersRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    // region PROPERTIES ---------------------------------------------------------------------------

    private val _character = MutableLiveData<Resource<List<CharacterResult>>>()
    val character: LiveData<Resource<List<CharacterResult>>> = _character
    // endregion

    // region PUBLIC METHODS ----------------------------------------------------------------------

    fun getCharacterById(characterId: CharacterId) {
        viewModelScope.launch {
            _character.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                repository.getCharacterById(characterId).let {
                    if (it.isSuccessful) {
                        _character.postValue(Resource.success(it.body()?.data?.results))
                    } else {
                        _character.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else _character.postValue(Resource.error("No internet connection", null))
        }
    }
    // endregion
}