package br.com.amd.tvshows.ui.episodedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.amd.tvshows.R
import br.com.amd.tvshows.databinding.FragmentEpisodeDetailsBinding
import br.com.amd.tvshows.ui.model.ShowEpisodeVO
import coil.load

class EpisodeDetailsFragment : Fragment() {

    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: EpisodeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindEpisodeDetails(args.episode)
    }

    private fun FragmentEpisodeDetailsBinding.bindEpisodeDetails(episode: ShowEpisodeVO) {
        tvEpisodeName.text = episode.name
        tvEpisodeNumberAndSeason.text =
            getString(R.string.episode_details_season_and_number, episode.number, episode.season)
        tvShowSummary.text = episode.summary.parseAsHtml()

        if (episode.mediumImageUrl.isNotEmpty()) {
            ivEpisodePoster.load(episode.mediumImageUrl)
        } else {
            ivEpisodePoster.load(R.drawable.ic_tv_shows)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}