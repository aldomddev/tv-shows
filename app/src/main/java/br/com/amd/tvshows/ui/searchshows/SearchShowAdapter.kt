package br.com.amd.tvshows.ui.searchshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.amd.tvshows.databinding.ShowListItemBinding
import br.com.amd.tvshows.ui.model.ShowVO
import br.com.amd.tvshows.ui.shows.ShowViewHolder

class SearchShowAdapter(
    private val onItemClick: (ShowVO) -> Unit
): ListAdapter<ShowVO, ShowViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ShowListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(binding = binding, onItemClick = onItemClick)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
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