package com.android.wisataapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.wisataapp.R
import com.android.wisataapp.detail.DetailActivity
import com.android.wisataapp.model.Wisata
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_wisata.view.*

class WisataAdapter(private var data: ArrayList<Wisata>?) :
    RecyclerView.Adapter<WisataAdapter.WisataHolder>() {
    class WisataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.itemImage
        val textName: TextView = itemView.itemNamaTempat
        val textNamaTempat: TextView = itemView.itemNamaLokasi

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)

        return WisataHolder(view)
    }

    override fun onBindViewHolder(holder: WisataHolder, position: Int) {
        Glide.with(holder.itemView.context).load(data?.get(position)?.gambar).into(holder.img)
        holder.textName.text = data?.get(position)?.nama_tempat
        holder.textNamaTempat.text = data?.get(position)?.lokasi

        //Action Click
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("title", data?.get(position)?.nama_tempat)
            intent.putExtra("img", data?.get(position)?.gambar)
            intent.putExtra("desc", data?.get(position)?.deskripsi)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

}