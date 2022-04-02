package com.henry.marvelmahle.presentation.characters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.henry.marvelmahle.R
import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResult
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(
    private var charactersList: ArrayList<CharacterResult>,
    private var onItemClickListener: (CharacterId) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.DataViewHolder>() {

    //region RECYCLER METHODS ----------------------------------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_character, parent,
                false
            )
        )

    override fun getItemCount(): Int = charactersList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(charactersList[position], onItemClickListener)
    // endregion

    //region HOLDERS -------------------------------------------------------------------------------

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: CharacterResult, onItemClickListener: (CharacterId) -> Unit) {
            itemView.tvCharacterDetailsName.text = character.name
            itemView.tvComicsCount.text = character.comics.available
            itemView.tvSeriesCount.text = character.series.available
            Glide.with(itemView.ivAvatar)
                .load(character.thumbnail.path + "." + character.thumbnail.extension)
                .error(R.drawable.ic_launcher_background)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.character_progressbar.isVisible = false
                        Log.e("CHARACTER", "IMAGE KO")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.character_progressbar.isVisible = false
                        Log.e("CHARACTER", "IMAGE Ok")
                        return false
                    }
                })
                .circleCrop()
                .into(itemView.ivAvatar)


            itemView.setOnClickListener {
                onItemClickListener.invoke(character.id)
            }
        }
    }
    // endregion


    // region PUBLIC METHODS -----------------------------------------------------------------------

    fun setData(list: List<CharacterResult>, onItemClickListener: (CharacterId) -> Unit) {
        charactersList = ArrayList()
        charactersList.addAll(list)
        this.onItemClickListener = onItemClickListener
    }
    // endregion
}