package br.com.amd.tvshows.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.amd.tvshows.R
import br.com.amd.tvshows.databinding.FragmentFavoritesBinding
import br.com.amd.tvshows.ui.common.ViewState
import br.com.amd.tvshows.ui.model.ShowVO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var favoriteShowsAdapter: FavoriteShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindAdapter()
        setObservers()
        viewModel.getFavoriteShows()
    }

    private fun setObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                ViewState.Empty -> binding.showEmptyState()
                ViewState.Error -> binding.showErrorState()
                is ViewState.Loaded -> binding.showLoadedState(viewState.data.toMutableList())
                ViewState.Loading -> {
                    // TODO
                }
            }
        }
    }

    private fun FragmentFavoritesBinding.bindAdapter() {
        favoriteShowsAdapter =
            FavoriteShowsAdapter(::navigateToShowDetailsScreen, ::onFavoriteClick)

        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)

        with(rvFavoriteShows) {
            layoutManager = gridLayoutManager
            adapter = favoriteShowsAdapter
        }
    }

    private fun FragmentFavoritesBinding.showEmptyState() {
        tvStatusMessage.isVisible = true
        tvStatusMessage.text = getString(R.string.favorite_shows_empty_list)
        rvFavoriteShows.isVisible = false
    }

    private fun FragmentFavoritesBinding.showErrorState() {
        tvStatusMessage.isVisible = true
        tvStatusMessage.text = getString(R.string.favorite_shows_error_message)
        rvFavoriteShows.isVisible = false
    }

    private fun FragmentFavoritesBinding.showLoadedState(favorites: List<ShowVO>) {
        tvStatusMessage.isVisible = false
        rvFavoriteShows.isVisible = true
        favoriteShowsAdapter.submitList(favorites)
    }

    private fun onFavoriteClick(item: ShowVO, added: Boolean) {
        viewModel.onFavoriteStateChanged(item, added)
    }

    private fun navigateToShowDetailsScreen(showVO: ShowVO) {
        val action =
            FavoritesFragmentDirections.actionFavoritesScreenToShowDetailsScreen(showId = showVO.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}