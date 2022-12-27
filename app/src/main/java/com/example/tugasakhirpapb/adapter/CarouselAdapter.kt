package com.example.tugasakhirpapb.adapter

class CarouselAdapter {}

//package com.example.tugasakhirpapb.adapter

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.widget.ViewPager2
//import com.example.tugasakhirpapb.R
//
//class CarouselAdapter(
//    private val carouselDataList: ArrayList<Int>,
//    private val viewPager2: ViewPager2
//) :
//    RecyclerView.Adapter<CarouselAdapter.ImageViewHolder>() {
//
//    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.imageView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
//        return ImageViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        holder.imageView.setImageResource(carouselDataList[position])
//        if (position == carouselDataList.size-1) {
//            viewPager2.post(runnable)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return carouselDataList.size
//    }
//
//    private val runnable = Runnable {
//        carouselDataList.addAll(carouselDataList)
//        notifyDataSetChanged()
//    }
//
//}