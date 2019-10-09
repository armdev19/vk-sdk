package com.infernal93.vksdk.providers

import android.os.Handler
import android.util.Log
import com.google.gson.JsonParser
import com.infernal93.vksdk.R
import com.infernal93.vksdk.models.FriendModel
import com.infernal93.vksdk.presenters.FriendsPresenter
import com.vk.sdk.api.*

class FriendsProvider(var presenter: FriendsPresenter) {
    private val TAG = "FriendsProvider"

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

    fun loadFriends() {
        val request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "sex, city, country, photo_100, online"))
        request.executeWithListener(object: VKRequest.VKRequestListener() {

            override fun onComplete(response: VKResponse?) {
                super.onComplete(response)

                val jsonParser = JsonParser()
                val parsedJson = jsonParser.parse(response!!.json.toString()).asJsonObject

                val friendsList: ArrayList<FriendModel> = ArrayList()

                parsedJson.get("response").asJsonObject.getAsJsonArray("items").forEach {

                    val city = if (it.asJsonObject.get("city") == null) {
                        null
                    } else {
                        it.asJsonObject.get("city").asJsonObject.get("title").asString
                    }

                    val friend = FriendModel(name = it.asJsonObject.get("first_name").asString,
                        surname = it.asJsonObject.get("last_name").asString,
                        city = city,
                        avatar = it.asJsonObject.get("photo_100").asString,
                        isOnline = it.asJsonObject.get("online").asInt == 1)
                    friendsList.add(friend)
                    //Log.e(TAG, "element - $it")
                }

                presenter.friendsLoaded(friendsList = friendsList)


                //Log.e(TAG, "response ${response!!.json}")
            }

            override fun onError(error: VKError?) {
                super.onError(error)
                presenter.showError(textResource = R.string.friends_error_loading)
            }
        })
    }
}