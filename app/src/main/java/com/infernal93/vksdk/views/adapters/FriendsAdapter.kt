package com.infernal93.vksdk.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infernal93.vksdk.R
import com.infernal93.vksdk.entity.Friend
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_friend.view.*

/**
 * Created by Armen Mkhitaryan on 07.10.2019.
 */
class FriendsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSourceList: ArrayList<Friend> = ArrayList()
    private var mFriendsList: ArrayList<Friend> = ArrayList()

    fun setupFriends(friendList: ArrayList<Friend>) {
        mSourceList.clear()
        mSourceList.addAll(friendList)
        filter(query = "")
    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it.name.contains(query, ignoreCase = true) || it.surname.contains(query, ignoreCase = true)) {
                mFriendsList.add(it)
            } else {
                it.city?.let { city -> if (city.contains(query, ignoreCase = true)) {
                 mFriendsList.add(it)
                }}
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)

        return FriendsViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {

        return mFriendsList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendsViewHolder) {
            holder.bind(friend = mFriendsList[position])
        }
    }



    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(friend: Friend) {

            friend.avatar?.let { url ->

                Picasso.with(itemView.context).load(url)
                    .into(itemView.friend_civ_avatar)
            }


            itemView.friend_txt_username.text = "${friend.name} ${friend.surname}"
            itemView.friend_txt_city.context.getString(R.string.friend_no_city)
            friend.city?.let { city -> itemView.friend_txt_city.text = city }

            if (friend.isOnline) {
                itemView.friend_img_online.visibility = View.VISIBLE
            } else {
                itemView.friend_img_online.visibility = View.GONE
            }
        }
    }

}