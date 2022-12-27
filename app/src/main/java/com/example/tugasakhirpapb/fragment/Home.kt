package com.example.tugasakhirpapb.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tugasakhirpapb.AddRs
import com.example.tugasakhirpapb.Login
import com.example.tugasakhirpapb.adapter.AdapterRS
import com.example.tugasakhirpapb.adapter.CarouselAdapter
import com.example.tugasakhirpapb.databinding.FragmentHomeBinding
import com.example.tugasakhirpapb.model.ModelRS
import com.google.firebase.firestore.*
import com.google.firebase.firestore.Query

class Home : Fragment() {

    private lateinit var database: FirebaseFirestore
    private lateinit var rsRecyclerView: RecyclerView
    private lateinit var rsArrayList: ArrayList<ModelRS>
    private lateinit var rsAdapter: AdapterRS

    private lateinit var addRs : Button

    private lateinit var binding: FragmentHomeBinding

//    private lateinit var viewPager2 : ViewPager2
//    private lateinit var handler : Handler
//    private lateinit var imageList : ArrayList<Int>
//    private lateinit var adapter : CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseFirestore.getInstance()

        binding = FragmentHomeBinding.inflate(inflater, container, false)

//        carousel()

        rsArrayList = arrayListOf()
        rsRecyclerView = binding.listItem
        rsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rsRecyclerView.setHasFixedSize(true)

        rsAdapter = AdapterRS(rsArrayList, requireContext())
        rsRecyclerView.adapter = rsAdapter

        getRSData()

        addRs = binding.btnAddRs
        addRs.setOnClickListener{
            val intent = Intent(activity, AddRs::class.java)
            startActivity(intent)
        }

        return binding.root
    }

//    private fun carousel() {
//        viewPager2 = binding.carousel
//        handler = Handler(Looper.myLooper()!!)
//        imageList = ArrayList()
//
//        imageList.add(R.drawable.carousel_1)

//        viewPager.apply {
//            clipChildren = false  // No clipping the left and right items
//            clipToPadding = false  // Show the viewpager in full width without clipping the padding
//            offscreenPageLimit = 3  // Render the left and right items
//            (getChildAt(0) as RecyclerView).overScrollMode =
//                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
//        }
//
//        data = arrayListOf(
//            R.drawable.ic_
//        )
//        viewPager.adapter = CarouselAdapter(data)
//    }

    private fun getRSData() {
        database.collection("RS").orderBy("nama", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            rsArrayList.add(dc.document.toObject(ModelRS::class.java))
                        }
                    }
                    rsAdapter?.notifyDataSetChanged()
                }
            })
    }
}