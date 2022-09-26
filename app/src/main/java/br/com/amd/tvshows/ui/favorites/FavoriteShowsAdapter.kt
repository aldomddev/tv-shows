package br.com.amd.tvshows.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.amd.tvshows.databinding.FavoriteShowListItemBinding
import br.com.amd.tvshows.ui.model.ShowVO

class FavoriteShowsAdapter(
    private val onItemClick: (ShowVO) -> Unit,
    private val onFavoriteClick: (item: ShowVO, added: Boolean) -> Unit
) : ListAdapter<ShowVO, FavoriteShowViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteShowViewHolder {
        val binding =
            FavoriteShowListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteShowViewHolder(
            binding = binding,
            onItemClick = onItemClick,
            onFavoriteClick = onFavoriteClick
        )
    }

    override fun onBindViewHolder(holder: FavoriteShowViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShowVO>() {
            override fun areItemsTheSame(oldItem: ShowVO, newItem: ShowVO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ShowVO, newItem: ShowVO): Boolean {
                return areItemsTheSame(oldItem, newItem)
            }
        }
    }
}