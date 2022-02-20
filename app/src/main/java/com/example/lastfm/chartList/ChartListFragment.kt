package com.example.lastfm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfm.chartList.ChartListViewModel
import com.example.lastfm.data.TrackAdapter
import com.example.lastfm.databinding.FragmentTopTracksListBinding
import com.example.lastfm.login.CustomPreference
import com.example.lastfm.login.LoginFragment
import com.example.lastfm.login.LoginViewModel

class ChartListFragment : Fragment() {
    private lateinit var binding: FragmentTopTracksListBinding
    private val viewModel: ChartListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopTracksListBinding.inflate(inflater, container, false)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.showListOfTracks()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        val adapter = TrackAdapter()
        val layoutManager = LinearLayoutManager(requireContext())

        FragmentTopTracksListBinding.bind(view).apply {
            recycler.addItemDecoration(dividerItemDecoration)
            viewModel.showListOfTracks()
            recycler.adapter = adapter
            recycler.layoutManager = layoutManager
            viewModel.trackLiveData.observe(viewLifecycleOwner) {
                binding.swipeRefresh.isRefreshing = false
                adapter.track = it
            }
            logOut.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, LoginFragment.newInstance())
                    .commit()
            }
        }
    }

    companion object {
        fun newInstance(): ChartListFragment {
            return ChartListFragment()
        }
    }
}