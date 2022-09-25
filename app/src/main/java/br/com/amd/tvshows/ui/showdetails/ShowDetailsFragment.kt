package br.com.amd.tvshows.ui.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.amd.tvshows.databinding.FragmentShowDetailsBinding
import br.com.amd.tvshows.ui.model.ShowVO
import coil.load
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsFragment : Fragment() {

    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!

    private val navArgs: ShowDetailsFragmentArgs by navArgs()
    private val viewModel: ShowDetailsViewModel by viewModels()

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
        setObservers()
    }

    private fun setObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when(viewState) {
                ShowDetailsViewModel.ShowDetailsViewState.Loading -> binding.showLoadingState()
                ShowDetailsViewModel.ShowDetailsViewState.Error -> binding.showErrorState()
                is ShowDetailsViewModel.ShowDetailsViewState.Loaded -> binding.showLoadedState(viewState.data)
            }
        }
    }

    private fun FragmentShowDetailsBinding.showLoadingState() {

    }

    private fun FragmentShowDetailsBinding.showErrorState() {

    }

    private fun FragmentShowDetailsBinding.showLoadedState(showDetails: ShowVO) {
        tvShowName.text = showDetails.name
        tvShowSummary.text = showDetails.summary.parseAsHtml()

        ivShowPoster.load(showDetails.mediumImageUrl)

        if (showDetails.genres.isNotEmpty()) {
            showDetails.genres.forEach { genre ->
                val chip = Chip(requireContext()).apply { text = genre }
                cgGenres.addView(chip)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}