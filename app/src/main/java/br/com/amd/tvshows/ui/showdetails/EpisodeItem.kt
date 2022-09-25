package br.com.amd.tvshows.ui.showdetails

import android.view.View
import br.com.amd.tvshows.R
import br.com.amd.tvshows.databinding.EpisodeListItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class EpisodeItem(
    private val episodeName: String
) : BindableItem<EpisodeListItemBinding>() {

    override fun getLayout(): Int = R.layout.episode_list_item

    override fun initializeViewBinding(view: View) = EpisodeListItemBinding.bind(view)

    override fun bind(viewBinding: EpisodeListItemBinding, position: Int) {
        viewBinding.tvEpisodeTitle.text = episodeName
    }
}