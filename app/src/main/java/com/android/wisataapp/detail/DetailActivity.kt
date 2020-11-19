package com.android.wisataapp.detail

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.wisataapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")
        val image = intent.getStringExtra("img")

        detailTitle.text = title
        detailDesc.text = desc
        Glide.with(this).load(image).into(detailImage)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}