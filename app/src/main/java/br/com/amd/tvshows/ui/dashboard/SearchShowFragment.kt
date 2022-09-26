package br.com.amd.tvshows.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.amd.tvshows.databinding.FragmentSearchShowBinding
import br.com.amd.tvshows.ui.model.ShowVO
import br.com.amd.tvshows.ui.shows.ShowsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchShowFragment : Fragment() {

    private var _binding: FragmentSearchShowBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchShowViewModel by viewModels()

    private lateinit var showsAdapter: SearchShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindSearchView()
        binding.bindAdapter()
        setObservers()
    }

    private fun FragmentSearchShowBinding.bindAdapter() {
        showsAdapter = SearchShowAdapter(::onShowClick)
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)

        with(rvSearchShows) {
            layoutManager = gridLayoutManager
            adapter = showsAdapter
        }
    }

    private fun FragmentSearchShowBinding.bindSearchView() {
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { q ->
                    viewModel.search(showName = q)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("AMD - Query=$newText")
                return false
            }
        })
    }

    private fun setObservers() {
        viewModel.searchViewState.observe(viewLifecycleOwner) { viewState ->
            when(viewState) {
                SearchShowViewModel.SearchShowViewState.Empty -> {}
                SearchShowViewModel.SearchShowViewState.Error -> {}
                is SearchShowViewModel.SearchShowViewState.Loaded -> showsAdapter.submitList(viewState.data)
                SearchShowViewModel.SearchShowViewState.Loading -> {}
            }
        }
    }

    private fun onShowClick(showVO: ShowVO) {
        val action =
            SearchShowFragmentDirections.actionSearchScreenToShowDetailsScreen(showId = showVO.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}