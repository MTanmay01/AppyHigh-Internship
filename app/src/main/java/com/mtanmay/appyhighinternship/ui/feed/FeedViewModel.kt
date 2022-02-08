package com.mtanmay.appyhighinternship.ui.feed

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.android.gms.location.FusedLocationProviderClient
import com.mtanmay.appyhighinternship.api.Article
import com.mtanmay.appyhighinternship.utils.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: Repository,
    private val client: FusedLocationProviderClient,
    private val geocoder: Geocoder
) : ViewModel() {

    var loadAgain = true
    private var mCountry: String = "in"

    @SuppressLint("MissingPermission")
    fun getTopHeadlines(location: String = mCountry): LiveData<PagingData<Article>> {
        loadAgain = false
        Timber.d("Getting headlines for country $mCountry")
        return repository.getTopHeadlines(location).cachedIn(viewModelScope)
    }

    @SuppressLint("MissingPermission")
    fun updateCountry(context: Context) {

        if (NetworkUtil.isConnected(context)) {
            client.lastLocation
                .addOnSuccessListener { location ->

                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude

                        val listAddress = geocoder.getFromLocation(
                            latitude,
                            longitude,
                            1
                        )

                        val fetchedCountry = listAddress.first().countryName
                        if (fetchedCountry == "India")
                            mCountry = "in"
                        else
                            mCountry = "us"

                    }

                }
                .addOnFailureListener {
                    // show no internet connection dialog / some error occured
                    Timber.e("Location task failed to fetch location\n${it.stackTraceToString()}")
                }

        } else {

            AlertDialog.Builder(context)
                .setTitle("Oops")
                .setMessage("No internet connection")
                .setNegativeButton("Dismiss") { _, _ -> }
                .setCancelable(true)
                .create()
                .show()
        }

    }

}