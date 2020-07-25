package com.uu.set.numberplace.view

import android.content.Context
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.uu.set.numberplace.R

class Ad {
    companion object {
        fun initAd(context: Context) {
            MobileAds.initialize(
                context
            ) { }
            RequestConfiguration.Builder().setTestDeviceIds(listOf("BF25A5CF4D2AAA87F9987110F45F4509"))
        }

        fun loadAd(view: View): Unit {
            val adView = view.findViewById<AdView>(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
    }
}