package br.com.amd.tvshows.ui.showdetails

import android.graphics.drawable.Animatable
import android.view.View
import br.com.amd.tvshows.R
import br.com.amd.tvshows.databinding.SeasonListItemBinding
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.viewbinding.BindableItem

class ExpandableSeasonHeaderItem(
    private val seasonTitle: String
) : BindableItem<SeasonListItemBinding?>(), ExpandableItem {

    override fun getLayout(): Int = R.layout.season_list_item

    override fun initializeViewBinding(view: View) = SeasonListItemBinding.bind(view)

    private var expandableGroup: ExpandableGroup? = null

    override fun bind(viewBinding: SeasonListItemBinding, position: Int) {
        with(viewBinding) {
            tvSeasonTitle.text = seasonTitle

            // Initial icon state -- not animated.
            ivActionIcon.visibility = View.VISIBLE
            ivActionIcon.setImageResource(if (expandableGroup!!.isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
            ivActionIcon.setOnClickListener {
                expandableGroup!!.onToggleExpanded()
                bindIcon(viewBinding)
            }
        }
    }

    private fun bindIcon(viewBinding: SeasonListItemBinding) {
        with(viewBinding) {
            ivActionIcon.visibility = View.VISIBLE
            ivActionIcon.setImageResource(if (expandableGroup!!.isExpanded) R.drawable.expand_animated else R.drawable.collapse_animated)
            val drawable = ivActionIcon.drawable as Animatable
            drawable.start()
        }
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }
}