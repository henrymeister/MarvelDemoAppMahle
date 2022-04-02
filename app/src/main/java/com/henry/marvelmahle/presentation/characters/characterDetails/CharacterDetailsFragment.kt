package com.henry.marvelmahle.presentation.characters.characterDetails

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.henry.marvelmahle.R
import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResult
import com.henry.marvelmahle.ext.hideInProgress
import com.henry.marvelmahle.ext.showInProgress
import com.henry.marvelmahle.presentation.comics.ComicsMainFragment
import com.henry.marvelmahle.presentation.series.SeriesMainFragment
import com.henry.marvelmahle.utils.Status
import kotlinx.android.synthetic.main.character_detail_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : Fragment() {

    private val viewModel : CharacterDetailsVM by viewModel()

    // region LIFECYCLE ----------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.character_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = arguments?.get("characterId") as CharacterId

        setObserver()
        setTabLayout(characterId)

        viewModel.getCharacterById(characterId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_character_details, menu)

        val backButton = menu.findItem(R.id.backButton).actionView

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    // endregion

    // region PRIVATE METHODS ----------------------------------------------------------------------

    private fun setObserver() {
        viewModel.character.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    showInProgress()
                }
                Status.SUCCESS -> {
                    hideInProgress()
                    if (it.data == null) {
                        Toast.makeText(
                            requireContext().applicationContext,
                            "Character not found",
                            Toast.LENGTH_LONG
                        ).show()
                        findNavController().popBackStack()
                    } else {
                        setLayout(it.data[0])
                    }
                }
                Status.ERROR -> {
                    hideInProgress()
                    findNavController().popBackStack()
                    Toast.makeText(
                        requireContext().applicationContext,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setLayout(characterResult: CharacterResult) {
        setAppBar(characterResult.name)
        tvCharacterName.text = characterResult.name
        tvDescriptionContent.text = characterResult.description

        Glide.with(requireContext().applicationContext)
            .load(characterResult.thumbnail.path + "." + characterResult.thumbnail.extension)
            .error(R.drawable.ic_launcher_background)
            .circleCrop()
            .into(ivCharacterAvatar)
    }

    private fun setTabLayout(characterId: String) {
        val fragments = arrayListOf(
            ComicsMainFragment(characterId),
            SeriesMainFragment(characterId)
        )

        vpCharacter.adapter = ViewPagerAdapter(fragments, requireActivity())

        TabLayoutMediator(tlCharacter, vpCharacter) { tab, position ->
            when (position) {
                0 -> tab.text = "Comics"
                1 -> tab.text = "Series"
            }
        }.attach()
    }

    private fun setAppBar(name: String) {
        (activity as AppCompatActivity).supportActionBar?.title = name
        setHasOptionsMenu(true)
    }
    // endregion

}