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
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("movie", movie.title)
                putExtra("overview", movie.overview)
                putExtra("poster_path", movie.poster_path)
                putExtra("release_date", movie.release_date)
                }
            startActivity(intent)
        }
        getListOfMovies()
    }

    private fun getListOfMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getPopularMovies("8f9a1fd23fb6892a3caa1cc76e16f84e")
            runOnUiThread {
                if (call.isSuccessful) {
                    call.body()?.results?.let { movies ->
                        moviesList.addAll(movies)
                        adapter.notifyDataSetChanged()
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
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}