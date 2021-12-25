package com.example.tentwentyassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tentwentyassignment.R
import com.example.tentwentyassignment.adapters.GenresListingAdapter
import com.example.tentwentyassignment.databinding.ActivityDetailPageBinding
import com.example.tentwentyassignment.models.GenreItemModel

class DetailPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setBindings()
        setGenreAdapter()
        callBacks()
    }
    private fun callBacks() {
        binding.txtReleaseDate.text = intent.getStringExtra("mReleaseDate")
        binding.txtDescription.text = intent.getStringExtra("mDescription")
        binding.imageBackFromDetail.setOnClickListener {
            finish()
        }
    }

    private fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun setGenreAdapter() {
        val mGenreItemModelList = ArrayList<GenreItemModel>()
        mGenreItemModelList.add(GenreItemModel("Action", R.color.genre_one))
        mGenreItemModelList.add(GenreItemModel("Thriller", R.color.genre_two))
        mGenreItemModelList.add(GenreItemModel("Science", R.color.genre_three))
        mGenreItemModelList.add(GenreItemModel("Friction", R.color.genre_four))
        binding.recyclerGenres.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = GenresListingAdapter(this, mGenreItemModelList)
        binding.recyclerGenres.adapter = adapter
    }

    private fun setBindings() {
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}