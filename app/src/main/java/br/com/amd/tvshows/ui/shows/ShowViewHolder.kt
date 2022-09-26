package br.com.amd.tvshows.ui.shows

import androidx.recyclerview.widget.RecyclerView
import br.com.amd.tvshows.R
import br.com.amd.tvshows.databinding.ShowListItemBinding
import br.com.amd.tvshows.ui.model.ShowVO
import coil.load

class ShowViewHolder(
    private val binding: ShowListItemBinding,
    val onItemClick: (ShowVO) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: ShowVO

    init {
        binding.root.setOnClickListener {
            onItemClick(item)
        }
    }

    fun bind(item: ShowVO) {
        this.item = item

        with(binding) {
            tvShowName.text = item.name
            if (item.mediumImageUrl.isNotEmpty()) {
                ivShowPoster.load(item.mediumImageUrl)
            } else {
                ivShowPoster.load(R.drawable.ic_tv_shows)
            }
        }
    }
}