package com.android.wisataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
    }

    private fun showData(data: ArrayList<Wisata>?) {
        listWisata.adapter = WisataAdapter(data)
    }
}