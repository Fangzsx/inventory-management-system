package com.fangs.inventory_management_system.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fangs.inventory_management_system.R
import com.fangs.inventory_management_system.model.ItemModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.item_layout.view.*

class ItemAdapter (option : FirestoreRecyclerOptions<ItemModel>) : FirestoreRecyclerAdapter<ItemModel, ItemAdapter.ViewHolder>(option) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: ItemModel) {

        holder.itemName.text = model.itemName
        holder.itemQuantity.text = model.itemQuantity.toString()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemName: TextView = itemView.tv_item_name
        val itemQuantity: TextView = itemView.tv_item_quantity
    }
}
