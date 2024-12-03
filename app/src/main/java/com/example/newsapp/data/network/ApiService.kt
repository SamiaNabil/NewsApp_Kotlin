package com.example.newsapp.data.network

import com.example.example.Articles
import com.example.example.NewsResponse
import com.example.example.Source
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET ("everything")
    suspend fun getNews(@Query("q")q:String,@Query("apiKey") apiKey:String):NewsResponse


}
object RetrofitInstance{
    val retrofit=Retrofit.Builder()
       .baseUrl(
            "https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService= retrofit.create(ApiService::class.java)
}