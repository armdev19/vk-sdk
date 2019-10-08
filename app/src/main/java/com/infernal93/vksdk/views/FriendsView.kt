package com.infernal93.vksdk.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.infernal93.vksdk.models.FriendModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView: MvpView {

    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupFriendsList(friendsList: ArrayList<FriendModel>)
    fun startLoading()
    fun endLoading()

}