package com.capstone.fokusin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.fokusin.R

class CustomListAdapter(private val menuItems: Array<String>) :
    RecyclerView.Adapter<CustomListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = menuItems[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val iconImageView: ImageView = itemView.findViewById(R.id.imageViewIcon)

        fun bind(item: String) {
            titleTextView.text = item

            // Set icon based on position or any other logic
            // For example, you can set different icons for different menu items
            when (adapterPosition) {
                0 -> iconImageView.setImageResource(R.drawable.help_ic)
                1 -> iconImageView.setImageResource(R.drawable.user_manual)
                2 -> iconImageView.setImageResource(R.drawable.logout_ic)
            }
        }
    }
    private var itemClickListener: ItemClickListener? = null

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

}
