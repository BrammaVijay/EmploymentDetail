package com.example.employmentdetailactivity.application

import com.example.employmentdetailactivity.model.dataClass.EmployeDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {
        @Provides
        fun providesBaseUrl()= Constant.BASE_URL

        @Provides
        @Singleton
        fun provideRetroFitInstance(BASE_URL:String): EmployeDetailApi =
            Retrofit.Builder()

                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .build()

                .create(EmployeDetailApi::class.java)

    }
