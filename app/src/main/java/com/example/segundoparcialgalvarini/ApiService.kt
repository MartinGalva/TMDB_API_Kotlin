package com.example.segundoparcialgalvarini

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun getPopularMovies(apiKey: String): Response<MovieResponse>
}
