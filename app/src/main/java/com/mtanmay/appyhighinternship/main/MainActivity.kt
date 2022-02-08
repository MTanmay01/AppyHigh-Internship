package com.mtanmay.appyhighinternship.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.mtanmay.appyhighinternship.databinding.ActivityMainBinding
import com.vmadalin.easypermissions.EasyPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this)

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {}

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {}
}