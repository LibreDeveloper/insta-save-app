package com.murphyspider.instasave.activites

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.murphyspider.instasave.R
import com.murphyspider.instasave.fragments.HighlightFragment
import com.murphyspider.instasave.fragments.PostsFragment
import com.murphyspider.instasave.fragments.StoryFragment
import com.murphyspider.instasave.prefs.SharedPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_InstaSave)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        val tabLayoutMain = findViewById<TabLayout>(R.id.main_tabs)
        val prefs = SharedPreferences(this)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.add(R.id.frame_main, PostsFragment())
        fragmentManager.commit()

        val n = isOnline(this)
        if (!n){
            AlertDialog.Builder(this)
                .setTitle("Internet access required!")
                .setMessage("Please turn on WIFI or Mobile data to continue.")
                .setCancelable(false)
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                    this.finishAffinity()
                })
                .create()
                .show()
        }

        val reqPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted->{
            Snackbar.make(tabLayoutMain,"Permission granted.",Snackbar.LENGTH_SHORT).show()
        }}
        if(Build.VERSION.SDK_INT == 23){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                AlertDialog.Builder(this)
                    .setTitle(R.string.title_permission)
                    .setMessage(R.string.check_permission)
                    .setCancelable(false)
                    .setPositiveButton(R.string.accept_permission, DialogInterface.OnClickListener { dialogInterface, i ->
                        reqPermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    })
                    .create()
                    .show()
            }
        }



        tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 ->{
                        val fragmentManager = supportFragmentManager.beginTransaction()
                        fragmentManager.replace(R.id.frame_main,PostsFragment())
                        fragmentManager.commit()
                    }
                    1 ->{
                        val fragmentManager = supportFragmentManager.beginTransaction()
                        fragmentManager.replace(R.id.frame_main, StoryFragment())
                        fragmentManager.commit()
                    }
                    2 ->{
                        val fragmentManager = supportFragmentManager.beginTransaction()
                        fragmentManager.replace(R.id.frame_main, HighlightFragment())
                        fragmentManager.commit()
                    }
                    else ->{
                        Log.i("tab else:","nothing selected")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.i("tab click:","onTabUnselected")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.i("tab click:","onTabReselected")
            }
        })
    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}