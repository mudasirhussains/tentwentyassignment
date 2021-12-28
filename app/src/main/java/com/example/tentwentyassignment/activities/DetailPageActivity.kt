package com.example.tentwentyassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tentwentyassignment.adapters.GenresListingAdapter
import com.example.tentwentyassignment.databinding.ActivityDetailPageBinding
import com.example.tentwentyassignment.models.Genre
import com.example.tentwentyassignment.utils.Constants
import com.example.tentwentyassignment.viewmodels.DetailPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.bumptech.glide.request.RequestOptions


@AndroidEntryPoint
class DetailPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPageBinding
    lateinit var mViewModel: DetailPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setBindings()
        callMoviesApi()
        setObserver()
        callBacks()
    }

    private fun setObserver() {
        mViewModel.mDetailPageResponse.observe(this, Observer {
            if (it != null) {
                setGenreAdapter(it.genres)
                    Glide.with(this)
                        .load(Constants.IMAGE_URL+it.backdropPath)
                        .apply(RequestOptions().override(600, 200))
                        .into(binding.posterImage!!)
                binding.txtReleaseDate.text = "In Theaters " + it.releaseDate
                binding.txtDescription.text = it.overview
                binding.progressBarDetailPage?.visibility = View.GONE
            }
        })
    }

    private fun setGenreAdapter(genres: List<Genre>) {
        binding.recyclerGenres.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = GenresListingAdapter(this, genres)
        binding.recyclerGenres.adapter = adapter
    }


    private fun callMoviesApi() {
        binding.progressBarDetailPage?.visibility = View.VISIBLE
        var movieId = intent.getIntExtra("mIntentID", 0)
        mViewModel.callDetailPageData(movieId)

    }

    private fun callBacks() {
        binding.imageBackFromDetail.setOnClickListener {
            finish()
        }
    }

    private fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun setBindings() {
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this).get(DetailPageViewModel::class.java)
    }
}