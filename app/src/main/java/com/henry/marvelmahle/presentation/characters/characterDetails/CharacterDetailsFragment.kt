package com.henry.marvelmahle.presentation.characters.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.henry.marvelmahle.R
import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.presentation.comics.ComicsMainFragment
import com.henry.marvelmahle.presentation.series.SeriesMainFragment
import kotlinx.android.synthetic.main.character_detail_layout.*

class CharacterDetailsFragment : Fragment() {

    // region LIFECYCLE ----------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.character_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = arguments?.get("characterId") as CharacterId

        val fragments = arrayListOf(
            ComicsMainFragment(characterId),
            SeriesMainFragment(characterId)
        )

        viewPager.adapter = ViewPagerAdapter(fragments, requireActivity())

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Comics"
                1 -> tab.text = "Series"
            }
        }.attach()
    }

    // endregion

}