package com.example.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.data.Content
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.JsonHelper


class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TvShowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listTvShowAdapter = ListTvShowAdapter()
        val data = JsonHelper.getJsonDataFromAsset(requireActivity(), "TvShows.json")
        viewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        viewModel.getContent(data)

        with(binding.rvTvShow) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = listTvShowAdapter
        }

        listTvShowAdapter.setData(viewModel.content)
        listTvShowAdapter.notifyDataSetChanged()
        listTvShowAdapter.setOnItemClickCallback(object :
            ListTvShowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Content, isAttend: Boolean) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("id", data.id)
                intent.putExtra("type", "tv_show")
                startActivity(intent)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}