package com.example.segundoparcialgalvarini

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var tvTitle: TextView
    private lateinit var tvText: TextView
    private lateinit var btnReturn: Button
    private lateinit var imageViewMovie: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvTitle = findViewById(R.id.tv_title)
        tvText = findViewById(R.id.tv_text)
        imageViewMovie = findViewById(R.id.imagMovie)

        btnReturn = findViewById(R.id.bt_return)

        val bundle = intent.extras
        val title = bundle?.getString("movie", "")
        val overview = bundle?.getString("overview", "")
        val posterPath = bundle?.getString("poster_path", "")

        tvTitle.text = title
        tvText.text = overview
        Picasso.get().load("https://image.tmdb.org/t/p/w154/${posterPath}").into(imageViewMovie)

        btnReturn.setOnClickListener {
            finish()
        }

    }
}