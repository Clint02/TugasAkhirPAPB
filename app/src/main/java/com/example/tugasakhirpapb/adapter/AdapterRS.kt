package com.example.tugasakhirpapb.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasakhirpapb.R
import com.example.tugasakhirpapb.model.ModelRS
import com.google.android.flexbox.FlexboxLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class AdapterRS(private val rs_list: ArrayList<ModelRS>, private val context: Context) :
    RecyclerView.Adapter<AdapterRS.MyViewHolder>() {

    private lateinit var database: FirebaseFirestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_rs, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = rs_list[position]

        holder.nama.text = currentItem.nama
        holder.jam.text = currentItem.jam
        holder.alamat.text = currentItem.alamat
    }

    override fun getItemCount(): Int {
        return rs_list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView
        val jam: TextView
        val alamat: TextView
        val layout: FlexboxLayout

        init {
            nama = itemView.findViewById(R.id.tv_nama)
            jam = itemView.findViewById(R.id.tv_jam)
            alamat = itemView.findViewById(R.id.tv_alamat)
            layout = itemView.findViewById(R.id.rs_layout)
            database = FirebaseFirestore.getInstance()
            layout.setOnClickListener { popupMenus(it) }
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun popupMenus(v: View) {
            val position = rs_list[adapterPosition]
            val popupMenus = Dialog(context)
            popupMenus.setContentView(R.layout.detail_rs)

            val btn_update = popupMenus.findViewById<Button>(R.id.btn_update)
            val btn_delete = popupMenus.findViewById<Button>(R.id.btn_delete)

            btn_update.setOnClickListener {
                val name = popupMenus.findViewById<EditText>(R.id.name)
                val time = popupMenus.findViewById<EditText>(R.id.time)
                val address = popupMenus.findViewById<EditText>(R.id.address)

                val mapUpdate = hashMapOf(
                    "nama" to name.text.toString(),
                    "jam" to time.text.toString(),
                    "alamat" to address.text.toString()
                )
                val query =
                    database.collection("RS").whereEqualTo("nama", position.nama)
                        .whereEqualTo("jam", position.jam).get()
                query.addOnSuccessListener {
                    for (document in it)
                        database.collection("RS").document(document.id)
                            .set(mapUpdate, SetOptions.merge())
                    Toast.makeText(context, "Update successful", Toast.LENGTH_LONG).show()
                    popupMenus.dismiss()
                }
                position.nama = name.text.toString()
                position.jam = name.text.toString()
                position.alamat = name.text.toString()
            }

            btn_delete.setOnClickListener {
                val query = database.collection("RS").whereEqualTo("nama", position.nama)
                    .whereEqualTo("jam", position.jam).whereEqualTo("alamat", position.alamat).get()
                query.addOnSuccessListener {
                    for (document in it)
                        database.collection("RS").document(document.id).delete()
                    Toast.makeText(context, "Delete successful", Toast.LENGTH_LONG).show()
                    popupMenus.dismiss()
                    notifyDataSetChanged()
                }
            }
            popupMenus.show()
        }
    }
}
