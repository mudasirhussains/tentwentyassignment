package com.example.tentwentyassignment.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tentwentyassignment.R
import com.example.tentwentyassignment.activities.DetailPageActivity
import com.example.tentwentyassignment.adapters.WatchItemListingAdapter
import com.example.tentwentyassignment.databinding.FragmentWatchBinding
import com.example.tentwentyassignment.interfaces.ItemClickListener
import com.example.tentwentyassignment.models.WatchItemsModel


class WatchFragment : Fragment(), ItemClickListener {
    private lateinit var binding: FragmentWatchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchBinding.inflate(inflater, container, false)
        populateDataInModel()
        clickListeners()
        return binding.root
    }

    private fun populateDataInModel() {
        val data = ArrayList<WatchItemsModel>()
        data.add(
            WatchItemsModel(
                "Free Guy",
                R.drawable.watch_one,
                requireActivity().resources.getString(R.string.item_release_date),
                requireActivity().resources.getString(R.string.item_description)
            )
        )
        data.add(
            WatchItemsModel(
                "The King's Man",
                R.drawable.watch_two,
                requireActivity().resources.getString(R.string.item_release_date),
                requireActivity().resources.getString(R.string.item_description)
            )
        )
        data.add(
            WatchItemsModel(
                "Jojo Rabbit",
                R.drawable.watch_three,
                requireActivity().resources.getString(R.string.item_release_date),
                requireActivity().resources.getString(R.string.item_description)
            )
        )
        data.add(
            WatchItemsModel(
                "Free Guy",
                R.drawable.watch_one,
                requireActivity().resources.getString(R.string.item_release_date),
                requireActivity().resources.getString(R.string.item_description)
            )
        )

        binding.recyclerWatchFragment.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = WatchItemListingAdapter(data, this)
        binding.recyclerWatchFragment.adapter = adapter
    }

    private fun clickListeners() {
        binding.imageSearchView.setOnClickListener {
            setCurrentFragment(SearchFragment())
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainer, fragment)
            commit()
        }

    override fun watchItemCLicked(mReleaseDate: String, mDesc: String) {
        val intent = Intent(requireContext(), DetailPageActivity::class.java)
        intent.putExtra("mReleaseDate", mReleaseDate)
        intent.putExtra("mDescription", mDesc)
        startActivity(intent)
    }

}