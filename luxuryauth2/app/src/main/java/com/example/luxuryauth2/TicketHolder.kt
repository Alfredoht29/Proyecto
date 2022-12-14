package com.example.luxuryauth2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketHolder:RecyclerView.ViewHolder {
    val tvModeloList:TextView
    val tvMarcaList:TextView
    constructor(itemView: View):super(itemView){
        tvModeloList=itemView.findViewById(R.id.tvModeloList)
        tvMarcaList=itemView.findViewById(R.id.tvMarcaList)
    }
}