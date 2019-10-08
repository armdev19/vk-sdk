package com.infernal93.vksdk.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.infernal93.vksdk.R
import com.infernal93.vksdk.adapters.FriendsAdapter
import com.infernal93.vksdk.models.FriendModel
import com.infernal93.vksdk.presenters.FriendsPresenter
import com.infernal93.vksdk.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : MvpAppCompatActivity(), FriendsView {


    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter


    private lateinit var mAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)


        txt_friends_search.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mAdapter.filter(s.toString())
            }

        })

        friendsPresenter.loadFriends()
        mAdapter = FriendsAdapter()

        recycler_friends.adapter = mAdapter
        recycler_friends.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        recycler_friends.setHasFixedSize(true)
    }

    // Friends View implementation
    override fun showError(textResource: Int) {
        txt_friends_no_items.text = getString(textResource)
    }

    override fun setupEmptyList() {
        recycler_friends.visibility = View.GONE
        txt_friends_no_items.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        recycler_friends.visibility = View.VISIBLE
        txt_friends_no_items.visibility = View.GONE

        mAdapter.setupFriends(friendList = friendsList)
    }

    override fun startLoading() {
        recycler_friends.visibility = View.GONE
        txt_friends_no_items.visibility = View.GONE
        cpv_friends.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpv_friends.visibility = View.GONE
    }
}
