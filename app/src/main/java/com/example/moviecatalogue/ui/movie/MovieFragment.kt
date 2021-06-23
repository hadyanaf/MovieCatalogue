package com.example.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.data.Content
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.JsonHelper


class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMovieAdapter = ListMovieAdapter()
        val data = JsonHelper.getJsonDataFromAsset(requireActivity(), "Movies.json")
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.getContent(data)

        with(binding.rvMovie) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = listMovieAdapter
        }

        listMovieAdapter.setData(viewModel.content)
        listMovieAdapter.notifyDataSetChanged()
        listMovieAdapter.setOnItemClickCallback(object :
            ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Content, isAttend: Boolean) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("id", data.id)
                intent.putExtra("type", "movies")
                startActivity(intent)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}