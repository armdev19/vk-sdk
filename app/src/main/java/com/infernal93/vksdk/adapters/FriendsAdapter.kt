package com.infernal93.vksdk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.infernal93.vksdk.R
import com.infernal93.vksdk.models.FriendModel

/**
 * Created by Armen Mkhitaryan on 07.10.2019.
 */
class FriendsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mFriendsList: ArrayList<FriendModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)

        return FriendsViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {

        return mFriendsList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class FriendsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}