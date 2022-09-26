package br.com.amd.tvshows.ui.favorites

import androidx.recyclerview.widget.RecyclerView
import br.com.amd.tvshows.R
import br.com.amd.tvshows.databinding.FavoriteShowListItemBinding
import br.com.amd.tvshows.ui.model.ShowVO
import coil.load

class FavoriteShowViewHolder(
    private val binding: FavoriteShowListItemBinding,
    val onItemClick: (ShowVO) -> Unit,
    val onFavoriteClick: (item: ShowVO, added: Boolean) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: ShowVO

    init {
        with(binding) {
            root.setOnClickListener {
                onItemClick(item)
            }

            cbFavorite.setOnClickListener {
                onFavoriteClick(item, cbFavorite.isChecked)
            }
        }
    }

    fun bind(item: ShowVO) {
        this.item = item

        with(binding) {
            tvShowName.text = item.name

            cbFavorite.isChecked = item.isFavorite

            if (item.mediumImageUrl.isNotEmpty()) {
                ivShowPoster.load(item.mediumImageUrl)
            } else {
                ivShowPoster.load(R.drawable.ic_tv_shows)
            }
        }
    }
}