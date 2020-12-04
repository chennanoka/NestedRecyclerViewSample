package com.example.googlenestedrecycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.googlenestedrecycler.R
import com.google.android.material.card.MaterialCardView

class ChildRecyclerAdapter(private val items: List<String>, val onclick: (title: String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_content_card, parent, false)
        return ContentCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContentCardViewHolder) {
            val str = items[position]
            holder.title.text = str
            holder.root.setOnClickListener {
                onclick(str)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ContentCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView by lazy { itemView.findViewById(R.id.title) }
        val root: MaterialCardView by lazy { itemView.findViewById(R.id.root) }
    }

}