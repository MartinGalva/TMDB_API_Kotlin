package com.example.segundoparcialgalvarini

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieResponse>
}
