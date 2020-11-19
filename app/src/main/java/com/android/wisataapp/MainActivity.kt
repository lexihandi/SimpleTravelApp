package com.android.wisataapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.wisataapp.adapter.WisataAdapter
import com.android.wisataapp.model.ResponseServer
import com.android.wisataapp.model.Wisata
import com.android.wisataapp.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isConnected()) {
            ConfigNetwork.getRetrofit().getDataWisata().enqueue(object : Callback<ResponseServer> {
                override fun onResponse(
                    call: Call<ResponseServer>,
                    response: Response<ResponseServer>
                ) {
                    Log.d("response server", response.message())

                    if (response.isSuccessful) {
                        progressBar.visibility = View.GONE
                        val status = response.body()?.status_code

                        if (status == 200) {
                            val data = response.body()?.data

                            showData(data)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Log.d("error server", t.message.toString())
                }
            })
        } else {
            progressBar.visibility = View.GONE
            Toast.makeText(this, "Jaringan internet bermasalah", Toast.LENGTH_LONG).show()
        }
    }

    private fun isConnected(): Boolean {
        val connect: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }

    private fun showData(data: ArrayList<Wisata>?) {
        listWisata.adapter = WisataAdapter(data)
    }
}