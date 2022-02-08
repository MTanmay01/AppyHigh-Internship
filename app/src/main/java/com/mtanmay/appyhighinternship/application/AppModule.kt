package com.mtanmay.appyhighinternship.application

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mtanmay.appyhighinternship.api.API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = Level.BASIC
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    @Singleton
    fun provideGeoCoder(@ApplicationContext context: Context): Geocoder =
        Geocoder(context)

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): API =
        retrofit.create(API::class.java)

}