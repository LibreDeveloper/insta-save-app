package com.murphyspider.instasave.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import com.murphyspider.instasave.R
import com.murphyspider.instasave.activites.MainActivity
import com.murphyspider.instasave.fragments.StartFragment
import com.murphyspider.instasave.prefs.PrefKeys
import com.murphyspider.instasave.prefs.SharedPreferences


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_InstaSave)
        setContentView(R.layout.layout_start)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)


        val prefs = SharedPreferences(this)
        if (prefs.getValue(PrefKeys.USER_COOKIE) != null) {
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            this.finish()
        } else {

            val fragmentManager = supportFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.frame_start, StartFragment())
            fragmentManager.commit()
        }
    }
}