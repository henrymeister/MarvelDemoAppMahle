package com.henry.marvelmahle.presentation.characters

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.henry.marvelmahle.R
import com.henry.marvelmahle.data.model.CharacterId
import com.henry.marvelmahle.data.model.Results
import com.henry.marvelmahle.ext.hideInProgress
import com.henry.marvelmahle.ext.showInProgress
import com.henry.marvelmahle.utils.Status
import kotlinx.android.synthetic.main.character_home_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersMainFragment : Fragment() {

    // region PROPERTIES ---------------------------------------------------------------------------

    private val viewModel : CharactersMainVM by viewModel()
    private lateinit var adapter: CharacterAdapter

    private val onAdapterItemClick: (CharacterId) -> Unit = { characterId ->
        navigateToCharacterDetails(characterId)
    }
    // endregion


    // region LIFECYCLE ----------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.character_home_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        hideInProgress()
        setLayout()
        setAppBar()
    }
    //endregion

    // region PRIVATE METHODS ----------------------------------------------------------------------

    private fun setObserver() {
        viewModel.characterList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    showInProgress()
                    recyclerView.visibility = View.GONE
                    tv_noResultFound.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    hideInProgress()
                    if (it.data == null) {
                        recyclerView.visibility = View.GONE
                        tv_noResultFound.visibility = View.VISIBLE
                    } else {
                        renderList(it.data)
                        recyclerView.visibility = View.VISIBLE
                        tv_noResultFound.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    hideInProgress()
                    recyclerView.visibility = View.GONE
                    tv_noResultFound.visibility = View.VISIBLE
                    Toast.makeText(
                        requireContext().applicationContext,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setAppBar() {
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.home)
    }

    private fun setLayout() {

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CharacterAdapter(arrayListOf(), onAdapterItemClick)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun renderList(characters: List<Results>) {
        adapter.setData(characters, onAdapterItemClick)
        adapter.notifyItemRangeChanged(0, characters.size)
    }
    // endregion

    // region NAVIGATION ----------------------------------------------------------------------

    private fun navigateToCharacterDetails(characterId: CharacterId) {
        val bundle = bundleOf("characterId" to characterId)

        /*Navigation.findNavController(this.requireView()).navigate(
            R.id.characterMainFragment_to_characterDetailsFragment,
            bundle
        )*/
    }
    // endregion
}