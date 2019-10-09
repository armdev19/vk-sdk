package com.infernal93.vksdk.presenters

import android.content.Intent
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.infernal93.vksdk.R
import com.infernal93.vksdk.views.LoginView
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun login(isSuccess: Boolean) {
        viewState.startLoading()
        Handler().postDelayed({
            viewState.endLoading()
            if (isSuccess) viewState.openFriends()
            else viewState.showError(textResource = R.string.login_error_credentials)

        }, 500)
    }

    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        val callback = object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken?) { viewState.openFriends() }

            override fun onError(error: VKError?) { viewState.showError(textResource = R.string.login_error_credentials) }
        }

        return VKSdk.onActivityResult(requestCode, resultCode, data, callback)
    }
}