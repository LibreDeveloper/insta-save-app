package com.murphyspider.instasave.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.murphyspider.instasave.R
import com.murphyspider.instasave.activites.MainActivity
import com.murphyspider.instasave.utils.constants.PrefKeys
import com.murphyspider.instasave.utils.sharedPrefs.SharedPref


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val webView: WebView = findViewById(R.id.webview_login)
        val prefs = SharedPref(this)
        val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        val intent = Intent(this, MainActivity::class.java)


        val alertDialog = AlertDialog.Builder(this)
            .setTitle(R.string.login_dialog)
            .setCancelable(false)
            .setPositiveButton(
                R.string.agree_dialog,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.cancel()
                })
            .setIcon(AppCompatResources.getDrawable(this, R.drawable.ic_important))
            .setNegativeButton(
                R.string.deny_dialog,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    finishAffinity()
                })
            .setMessage(R.string.login_info)
        var dialog = alertDialog.create()


        if (prefs.getString(PrefKeys.COOKIE) == "" || prefs.getString(PrefKeys.COOKIE) == null) {
            dialog.show()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl("https://www.instagram.com/accounts/login/")
            webView.setWebViewClient(object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    val cookies = CookieManager.getInstance().getCookie(url)
                    Log.i("Cookie ---> ", cookies)
                    if (cookies.contains("ds_user_id") && cookies.contains("sessionid") && cookies.contains(
                            "shbid"
                        )
                    ) {
                        prefs.setString(PrefKeys.COOKIE, cookies.replace(" ", ""))
                        prefs.setString(PrefKeys.ANDROID_ID, androidId)
                        startActivity(intent)
                        finish()
                    }
                }
            })
        } else {
            startActivity(intent)
            finish()
        }
    }
}


