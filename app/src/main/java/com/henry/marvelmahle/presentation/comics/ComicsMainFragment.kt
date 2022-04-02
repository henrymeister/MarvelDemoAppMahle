package com.henry.marvelmahle.presentation.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.henry.marvelmahle.R
import com.henry.marvelmahle.data.model.comic.ComicId
import com.henry.marvelmahle.data.model.comic.ComicResult
import com.henry.marvelmahle.ext.hideInProgress
import com.henry.marvelmahle.ext.showInProgress
import com.henry.marvelmahle.utils.Status
import kotlinx.android.synthetic.main.character_home_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class ComicsMainFragment(private val characterId: String): Fragment() {
    // region PROPERTIES ---------------------------------------------------------------------------

    private val viewModel : ComicsMainVM by viewModel()
    private lateinit var adapter: ComicAdapter

    private val onAdapterItemClick: (ComicId) -> Unit = { comicId ->
        //navigateToCharacterDetails(characterId)
    }
    // endregion

    // region LIFECYCLE ----------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.comics_home_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        setLayout()
        viewModel.getCharacterComicsList(characterId)
    }
    // end region

    // region PRIVATE METHODS ----------------------------------------------------------------------

    private fun setLayout() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ComicAdapter(arrayListOf(), onAdapterItemClick)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setObserver() {
        viewModel.characterComicsList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    showInProgress()
                    recyclerView.visibility = View.GONE
                    tv_noResultFound.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    hideInProgress()
                    if (it.data == null || it.data.isEmpty()) {
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
                }
            }
        }
    }

    private fun renderList(comics: List<ComicResult>) {
        adapter.setData(comics, onAdapterItemClick)
        adapter.notifyItemRangeChanged(0, comics.size)
    }
    // endregion
}