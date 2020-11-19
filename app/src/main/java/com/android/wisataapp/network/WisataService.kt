package com.android.wisataapp.network

import com.android.wisataapp.model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface WisataService {
    @GET("api?action=findAll")
    fun getDataWisata(): Call<ResponseServer>
}