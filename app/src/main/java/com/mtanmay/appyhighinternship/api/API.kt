package com.mtanmay.appyhighinternship.api

import com.mtanmay.appyhighinternship.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    fun getTopHeadlines(

        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY,

        @Query("country")
        country: String = "in",

        @Query("pageSize")
        pageSize: Int = 50

    ): Single<Response>

}