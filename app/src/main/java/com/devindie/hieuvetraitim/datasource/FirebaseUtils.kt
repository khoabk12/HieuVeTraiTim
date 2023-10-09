package com.devindie.hieuvetraitim.datasource

import android.util.Log
import com.devindie.hieuvetraitim.devutils.log
import com.devindie.hieuvetraitim.entities.Feelings
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtils {
    private val database =
        FirebaseDatabase.getInstance("https://hieuvetraitim-f46df-default-rtdb.firebaseio.com/").reference

    fun getAllFeelings(onGetSuccess: (ArrayList<Feelings>) -> Unit) {
        Log.e("firebase loading...", "....")
        val feelings = arrayListOf<Feelings>()
        database.root.get().addOnSuccessListener {
            for (child in it.children) {
                val feeling = child.getValue(Feelings::class.java)
                feeling?.let { it1 -> feelings.add(it1) }
            }
            onGetSuccess.invoke(feelings)
        }.addOnFailureListener { log(it.message.toString()) }
    }
}