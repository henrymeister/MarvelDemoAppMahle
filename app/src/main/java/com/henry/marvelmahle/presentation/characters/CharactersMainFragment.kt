package com.henry.marvelmahle.presentation.characters

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.henry.marvelmahle.R
import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResult
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
        setLayout()
        setAppBar()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_character, menu)

        val searchItem = menu.findItem(R.id.charactersSearch)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Search Character"
        searchView.setIconifiedByDefault(false)
        searchView.onActionViewExpanded()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchCharacter(newText)
                }
                return true
            }
        })

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
        setHasOptionsMenu(true)
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

    private fun renderList(characters: List<CharacterResult>) {
        adapter.setData(characters, onAdapterItemClick)
        adapter.notifyItemRangeChanged(0, characters.size)
    }
    // endregion

    // region NAVIGATION ----------------------------------------------------------------------

    private fun navigateToCharacterDetails(characterId: CharacterId) {
        val bundle = bundleOf("characterId" to characterId)

        Navigation.findNavController(this.requireView()).navigate(
            R.id.characterMainFragment_to_characterDetailsFragment,
            bundle
        )
    }
    // endregion
}