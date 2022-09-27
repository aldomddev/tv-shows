package br.com.amd.tvshows.ui.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.amd.tvshows.R
import br.com.amd.tvshows.databinding.FragmentShowDetailsBinding
import br.com.amd.tvshows.ui.common.ViewState
import br.com.amd.tvshows.ui.model.ShowEpisodeVO
import br.com.amd.tvshows.ui.model.ShowVO
import coil.load
import com.google.android.material.chip.Chip
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsFragment : Fragment() {

    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!

    private val navArgs: ShowDetailsFragmentArgs by navArgs()
    private val viewModel: ShowDetailsViewModel by viewModels()

    private lateinit var adapter: GroupieAdapter
    private var show: ShowVO? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchShowDetails(navArgs.showId)
        setAdapter()
        setObservers()
        setListeners()
    }

    private fun setAdapter() {
        adapter = GroupieAdapter().apply {
            setOnItemClickListener(onEpisodeClickListener)
        }

        binding.rvSeasonsAndEpisodes.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
    }

    private fun setObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            binding.swipeToRefresh.isRefreshing = viewState is ViewState.Loading

            when (viewState) {
                ViewState.Error -> binding.showErrorState()
                is ViewState.Loaded -> binding.showLoadedState(viewState.data)
                ViewState.Loading,
                ViewState.Empty -> { /* no action */ }
            }
        }
    }

    private fun setListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchShowDetails(navArgs.showId)
        }

        binding.cbFavorite.setOnClickListener {
            viewModel.onFavoriteStateChanged(show, binding.cbFavorite.isChecked)
        }
    }

    private fun FragmentShowDetailsBinding.showErrorState() {
        // TODO
    }

    private fun FragmentShowDetailsBinding.showLoadedState(showDetails: ShowVO) {
        show = showDetails

        tvShowName.text = showDetails.name
        tvShowSummary.text = showDetails.summary.parseAsHtml()

        ivShowPoster.load(showDetails.mediumImageUrl)

        val schedule = StringBuilder(getString(R.string.episode_details_airs_every))
        if (showDetails.schedule.days.isNotEmpty()) {
            showDetails.schedule.days.forEach { day ->
                schedule.append("$day ")
            }
        }
        schedule.append(getString(R.string.episode_details_airs_at, showDetails.schedule.time))
        tvAirs.text = schedule

        cgGenres.removeAllViews()
        if (showDetails.genres.isNotEmpty()) {
            showDetails.genres.forEach { genre ->
                val chip = Chip(requireContext()).apply { text = genre }
                cgGenres.addView(chip)
            }
        }

        cbFavorite.isVisible = true
        cbFavorite.isChecked = showDetails.isFavorite

        if (showDetails.seasons.isNotEmpty()) {
            showDetails.seasons.forEach { season ->
                val seasonExpandableGroup = ExpandableSeasonHeaderItem(getString(R.string.episode_details_season_number, season.number))
                adapter.add(ExpandableGroup(seasonExpandableGroup).apply {
                    season.episodes.forEach { episode ->
                        add(EpisodeItem(episode))
                    }
                })
            }
        }
    }

    private val onEpisodeClickListener = OnItemClickListener { item, _ ->
        if (item is EpisodeItem) {
            navigateToEpisodeDetailsScreen(item = item.getEpisode())
        }
    }

    private fun navigateToEpisodeDetailsScreen(item: ShowEpisodeVO) {
        val action = ShowDetailsFragmentDirections.actionShowDetailsScreenToEpisodeDetailsScreen(item)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}