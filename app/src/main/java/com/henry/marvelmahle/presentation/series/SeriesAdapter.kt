package com.henry.marvelmahle.presentation.series

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
import com.henry.marvelmahle.data.model.series.SeriesId
import com.henry.marvelmahle.data.model.series.SeriesResult
import kotlinx.android.synthetic.main.item_series.view.*

class SeriesAdapter(
    private var seriesList: ArrayList<SeriesResult>,
    private var onItemClickListener: (SeriesId) -> Unit
) : RecyclerView.Adapter<SeriesAdapter.DataViewHolder>() {

    // region RECYCLER METHODS ----------------------------------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_series, parent,
                false
            )
        )

    override fun getItemCount(): Int = seriesList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(seriesList[position], onItemClickListener)
    // endregion

    // region HOLDERS -------------------------------------------------------------------------------

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(serie: SeriesResult, onItemClickListener: (SeriesId) -> Unit) {
            itemView.tvSerieName.text = serie.title
            itemView.tvSerieDescription.text = serie.description

            Glide.with(itemView.ivSerie)
                .load(serie.thumbnail.path + "." + serie.thumbnail.extension)
                .error(R.drawable.ic_launcher_background)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.pbSerie.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.pbSerie.isVisible = false
                        return false
                    }
                })
                .circleCrop()
                .into(itemView.ivSerie)


            itemView.setOnClickListener {
                onItemClickListener.invoke(serie.id)
            }
        }
    }
    // endregion


    // region PUBLIC METHODS -----------------------------------------------------------------------

    fun setData(list: List<SeriesResult>, onItemClickListener: (SeriesId) -> Unit) {
        seriesList = ArrayList()
        seriesList.addAll(list)
        this.onItemClickListener = onItemClickListener
    }
    // endregion
}