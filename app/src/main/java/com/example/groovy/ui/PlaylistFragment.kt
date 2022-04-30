package com.example.groovy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groovy.PlaylistApi
import com.example.groovy.PlaylistService
import com.example.groovy.adapter.PlaylistRecyclerViewAdapter
import com.example.groovy.repository.PlaylistRepository
import com.example.groovy.databinding.FragmentPlaylistBinding
import com.example.groovy.model.ModelPlaylistItem
import com.example.groovy.viewmodel.PlaylistViewModel
import com.example.groovy.viewmodel.PlaylistViewModelFactory
import kotlinx.coroutines.flow.flow


class PlaylistFragment : Fragment() {


    lateinit var viewModel: PlaylistViewModel
    lateinit var viewModelFactory: PlaylistViewModelFactory
    private lateinit var binding: FragmentPlaylistBinding
    private lateinit var playlistAdapter: PlaylistRecyclerViewAdapter
    private val service:PlaylistService = PlaylistService(object : PlaylistApi {
        override fun fetchAllPlaylist(): List<ModelPlaylistItem> {
            return emptyList()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)

        initViewModel()
        initAdapter()
        initObserver()

        return binding.root
    }

    private fun initViewModel() {
        viewModelFactory = PlaylistViewModelFactory(PlaylistRepository(service))
        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistViewModel::class.java]
    }

    private fun initAdapter() {
        with(binding.rvPlaylist) {
            playlistAdapter = PlaylistRecyclerViewAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }
    }

    private fun initObserver() {
        viewModel.playlists.observe(this.viewLifecycleOwner) {
            if (it.isSuccess) {
                if (it.getOrNull() != null)
                    playlistAdapter.setData(it.getOrNull()!!)
            } else {
                Toast.makeText(requireContext(), "${it.getOrThrow()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}