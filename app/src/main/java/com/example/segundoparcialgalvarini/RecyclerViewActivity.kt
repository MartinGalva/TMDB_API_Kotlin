package com.example.segundoparcialgalvarini

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var moviesList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView = findViewById(R.id.recycler)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(applicationContext)
        recyclerView.adapter = adapter

        adapter.submitList(moviesList)
        adapter.onItemClickListener = { movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("movie", movie.title)
            intent.putExtra("overview", movie.overview)
            intent.putExtra("poster_path", movie.poster_path)
            startActivity(intent)
        }

        getListOfMovies()
    }

    private fun getListOfMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getPopularMovies("api_key=8f9a1fd23fb6892a3caa1cc76e16f84e")
            val response = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val movies = response?.results
                    if (movies != null) {
                        moviesList.addAll(movies)
                    }
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Fallo en la llamada", Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/top_rated?")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}