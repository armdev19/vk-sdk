package com.infernal93.vksdk.providers

import android.os.Handler
import com.infernal93.vksdk.models.FriendModel
import com.infernal93.vksdk.presenters.FriendsPresenter

class FriendsProvider(var presenter: FriendsPresenter) {

    fun testLoadFriends(hasFriends: Boolean) {
        Handler().postDelayed({
            val friendsList: ArrayList<FriendModel> = ArrayList()
            if (hasFriends) {
                val friend1 = FriendModel(name = "Ivan", surname = "Ivanov", city = null,
                    avatar = "https://sun9-18.userapi.com/c851024/v851024548/186e9a/f4dyui3ClXo.jpg", isOnline = false)
                val friend2 = FriendModel(name = "Armen", surname = "Mkhitaryan", city = "Saint Piterburg",
                    avatar = "https://sun9-63.userapi.com/c847216/v847216984/12cb4e/SCEhQc79rXA.jpg", isOnline = true)
                val friend3 = FriendModel(name = "Egor", surname = "Sidorov", city = "Moscow",
                    avatar = "https://sun1-86.userapi.com/c841239/v841239152/63281/AiR6iXGeO2M.jpg", isOnline = false)
                friendsList.add(friend1)
                friendsList.add(friend2)
                friendsList.add(friend3)
            }

            presenter.friendsLoaded(friendsList = friendsList)
        }, 2000)
    }
}