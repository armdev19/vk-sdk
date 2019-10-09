package com.infernal93.vksdk.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.infernal93.vksdk.R
import com.infernal93.vksdk.presenters.LoginPresenter
import com.infernal93.vksdk.views.LoginView
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : MvpAppCompatActivity(), LoginView {
    private val TAG: String = LoginActivity::class.java.simpleName


    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login_enter.setOnClickListener {
            VKSdk.login(this@LoginActivity, VKScope.FRIENDS)
            //loginPresenter.login(isSuccess = true)

        }
        // Get SHA1 key for VK SDK app
        //val fingerprints = VKUtil.getCertificateFingerprint(this, this.packageName)
        //Log.e(TAG, "fingerprint ${fingerprints!![0]}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!loginPresenter.loginVk(requestCode = requestCode, resultCode = resultCode, data = data))
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun startLoading() {
        btn_login_enter.visibility = View.GONE
        cpv_login.visibility = View.VISIBLE
    }

    override fun endLoading() {
        btn_login_enter.visibility = View.VISIBLE
        cpv_login.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        Toast.makeText(applicationContext, getString(textResource), Toast.LENGTH_SHORT).show()
    }

    override fun openFriends() {
        startActivity(Intent(applicationContext, FriendsActivity::class.java))
    }
}
