package br.com.amd.tvshows.ui.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.amd.tvshows.databinding.FragmentShowsBinding
import br.com.amd.tvshows.ui.model.ShowVO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowsFragment : Fragment() {

    private var _binding: FragmentShowsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShowsViewModel by viewModels()

    private lateinit var showsAdapter: ShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindAdapter()

        lifecycleScope.launch {
            viewModel.shows.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    showsAdapter.submitData(it)
                }
        }
    }

    private fun FragmentShowsBinding.bindAdapter() {
        showsAdapter = ShowsAdapter(::onShowClick)
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)

        with(rvAllShows) {
            layoutManager = gridLayoutManager
            adapter = showsAdapter
        }
    }

    private fun onShowClick(showVO: ShowVO) {
        val action =
            ShowsFragmentDirections.actionNavigationShowsToNavigationShowDetails(showId = showVO.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}