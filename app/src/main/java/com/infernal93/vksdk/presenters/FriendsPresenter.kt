package com.infernal93.vksdk.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.infernal93.vksdk.R
import com.infernal93.vksdk.models.FriendModel
import com.infernal93.vksdk.providers.FriendsProvider
import com.infernal93.vksdk.views.FriendsView

/**
 * Created by Armen Mkhitaryan on 07.10.2019.
 */

@InjectViewState
class FriendsPresenter: MvpPresenter<FriendsView>() {

    fun loadFriends() {
        viewState.startLoading()
        //FriendsProvider(presenter = this).testLoadFriends(hasFriends = true)
          FriendsProvider(presenter = this).loadFriends()
    }

    fun friendsLoaded(friendsList: ArrayList<FriendModel>) {
        viewState.endLoading()
        if (friendsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(textResource = R.string.friends_no_items)
        } else {
            viewState.setupFriendsList(friendsList = friendsList)
        }
    }

    fun showError(textResource: Int) {
        viewState.showError(textResource = textResource)
    }
}