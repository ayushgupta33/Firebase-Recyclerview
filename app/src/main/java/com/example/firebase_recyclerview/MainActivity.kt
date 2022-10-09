package com.example.firebase_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<ItemData>


    lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        list = ArrayList()

        var layoutManger= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManger
        var adapter= RecyclerAdapter(list,this)


        firebaseDatabase = FirebaseDatabase.getInstance()

        firebaseDatabase.reference.child("images").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){
                    var data: ItemData?=dataSnapshot.getValue(ItemData::class.java)

                    list.add(data!!)

                }


                adapter.notifyDataSetChanged()
                recyclerView.adapter=adapter

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
            }

        })

    }
}