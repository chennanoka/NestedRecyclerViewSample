package com.example.googlenestedrecycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.googlenestedrecycler.R
import org.apache.commons.lang3.tuple.ImmutablePair


class ParentRecyclerAdapter(private val onclick: (title: String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val positionMap = hashMapOf<Int, ImmutablePair<Int, Int>>()

    override fun getItemViewType(position: Int): Int {
        return position + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType % 2 == 0) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
            ContentViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
            TitleViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContentViewHolder) {
            val indexNum = getItemViewType(position) / 2
            val list = mutableListOf<String>()
            for (i in 1..10) {
                list.add(indexNum.toString() + "-" + i.toString())
            }
            holder.recyclerView.adapter = ChildRecyclerAdapter(list, onclick)
            holder.tposition = position
        }
    }

    override fun getItemCount(): Int {
        return 15
    }


    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)

        if (holder is ContentViewHolder) {
            val layoutManager = holder.recyclerView.layoutManager as LinearLayoutManager
            val position = layoutManager.findFirstVisibleItemPosition()
            val offset = layoutManager.findViewByPosition(position)?.left ?: 0
            positionMap[holder.tposition] = ImmutablePair(position, offset)
        }

    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder is ContentViewHolder) {
            val scrolled = positionMap[holder.tposition]
            val layoutManager = holder.recyclerView.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(scrolled?.left ?: 0, scrolled?.right ?: 0)
        }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recyclerView: RecyclerView by lazy { itemView.findViewById(R.id.recycler) }
        var tposition: Int = 0

        init {
            recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val linearSnapHelper = LinearSnapHelper()
            linearSnapHelper.attachToRecyclerView(recyclerView)
        }
    }

}