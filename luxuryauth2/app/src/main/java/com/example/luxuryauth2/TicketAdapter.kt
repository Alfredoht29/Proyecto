package com.example.luxuryauth2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter:RecyclerView.Adapter<TicketHolder>() {
    private lateinit var data : ArrayList<Ticket>

    init {
        data = ArrayList<Ticket>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.ticket_list,parent,false)
        return TicketHolder(view)
    }

    override fun onBindViewHolder(holder: TicketHolder, position: Int) {
        val ticket = data.get(position)
        holder.tvModeloList.text=ticket.modelo
        holder.tvMarcaList.text=ticket.marca
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,Autorizacion::class.java)
            intent.putExtra("id",ticket.id)
            intent.putExtra("modelo",ticket.modelo)
            intent.putExtra("marca",ticket.marca)
            intent.putExtra("imagen",ticket.imagen)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun guardar(ticket: Ticket) {
        data.add(ticket)
        notifyDataSetChanged()
    }
    fun limpiar() {
        data.clear()
    }
}