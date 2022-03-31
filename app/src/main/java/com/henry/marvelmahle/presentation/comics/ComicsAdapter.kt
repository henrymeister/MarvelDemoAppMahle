package com.henry.marvelmahle.presentation.comics

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
import com.henry.marvelmahle.data.model.comic.ComicId
import com.henry.marvelmahle.data.model.comic.ComicResult
import kotlinx.android.synthetic.main.comics_item_layout.view.*

class ComicAdapter(
    private var seriesList: ArrayList<ComicResult>,
    private var onItemClickListener: (ComicId) -> Unit
) : RecyclerView.Adapter<ComicAdapter.DataViewHolder>() {

    //region RECYCLER METHODS ----------------------------------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.comics_item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = seriesList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(seriesList[position], onItemClickListener)
    // endregion

    //region HOLDERS -------------------------------------------------------------------------------

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comic: ComicResult, onItemClickListener: (ComicId) -> Unit) {
            itemView.comic_name.text = comic.title
            itemView.comic_description.text = comic.description

            Glide.with(itemView.comic_image)
                .load(comic.thumbnail.path + "." + comic.thumbnail.extension)
                .error(R.drawable.ic_launcher_background)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.comic_progressbar.isVisible = false
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
                        itemView.comic_progressbar.isVisible = false
                        Log.e("CHARACTER", "IMAGE Ok")
                        return false
                    }
                })
                .circleCrop()
                .into(itemView.comic_image)


            itemView.setOnClickListener {
                onItemClickListener.invoke(comic.id)
            }
        }
    }
    // endregion


    // region PUBLIC METHODS -----------------------------------------------------------------------

    fun setData(list: List<ComicResult>, onItemClickListener: (ComicId) -> Unit) {
        seriesList = ArrayList()
        seriesList.addAll(list)
        this.onItemClickListener = onItemClickListener
    }
    // endregion
}