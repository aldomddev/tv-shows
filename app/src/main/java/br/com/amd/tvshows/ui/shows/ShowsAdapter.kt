package br.com.amd.tvshows.ui.shows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import br.com.amd.tvshows.databinding.ShowListItemBinding
import br.com.amd.tvshows.ui.model.ShowVO

class ShowsAdapter(
    private val onItemClick: (ShowVO) -> Unit
) : PagingDataAdapter<ShowVO, ShowViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ShowListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(binding = binding, onItemClick = onItemClick)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
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