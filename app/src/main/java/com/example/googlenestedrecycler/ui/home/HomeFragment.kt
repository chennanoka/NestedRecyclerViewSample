package com.example.googlenestedrecycler.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googlenestedrecycler.R
import com.example.googlenestedrecycler.adapter.ParentRecyclerAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recycler: RecyclerView = root.findViewById(R.id.recycler)
        recycler.adapter = ParentRecyclerAdapter {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return root
    }
}