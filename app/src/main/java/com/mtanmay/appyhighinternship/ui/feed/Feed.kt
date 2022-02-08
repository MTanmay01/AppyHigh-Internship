package com.mtanmay.appyhighinternship.ui.feed

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtanmay.appyhighinternship.R
import com.mtanmay.appyhighinternship.api.Article
import com.mtanmay.appyhighinternship.databinding.FragmentFeedBinding
import com.mtanmay.appyhighinternship.utils.GpsUtils
import com.mtanmay.appyhighinternship.utils.PermissionUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Feed : Fragment(R.layout.fragment_feed), Adapter.OnItemClickListener {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    private var mAdapter = Adapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        binding.apply {

            newsList.setHasFixedSize(true)
            newsList.layoutManager = LinearLayoutManager(requireContext())
            newsList.adapter = mAdapter
            mAdapter.setOnItemClickListener(this@Feed)

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PermissionUtils.requestLocationPermission(requireActivity())

        if (PermissionUtils.hasLocationPermission(requireContext())) {
            gpsEnablePrompt()
        }

        viewModel.updateCountry(requireContext())

        if (viewModel.loadAgain) {
            viewModel.getTopHeadlines().observe(viewLifecycleOwner) {
                mAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
            viewModel.loadAgain = false
        }

        binding.apply {

            btnLocIn.setOnClickListener {
                viewModel.getTopHeadlines("in").observe(viewLifecycleOwner) {
                    mAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                }
            }

            btnLocUs.setOnClickListener {
                viewModel.getTopHeadlines("us").observe(viewLifecycleOwner) {
                    mAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                }
            }

        }

    }

    private fun gpsEnablePrompt() {

        if (!GpsUtils.isEnabled(requireContext())) {

            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)

            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Enable GPS")
                .setMessage("This app requires GPS to work properly")
                .setPositiveButton("Enable") { _, _ ->
                    startActivity(intent)
                }
                .setCancelable(true)
                .create()

            alertDialog.show()

        }
    }

    override fun onItemClick(article: Article) {

        val action = FeedDirections.openWebPage(article.url)
        findNavController().navigate(action)

//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
//        startActivity(intent)
    }


}