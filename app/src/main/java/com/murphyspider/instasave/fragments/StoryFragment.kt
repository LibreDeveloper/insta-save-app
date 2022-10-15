package com.murphyspider.instasave.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.murphyspider.instasave.R
import com.murphyspider.instasave.api.profileInfo.IgProfile
import com.murphyspider.instasave.utils.IgProfileCallback
import com.murphyspider.instasave.utils.InstagramHelper


class StoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_download_story, container, false)
        val btnDownload = view.findViewById<Button>(R.id.btn_download_story)
        val editTextUsername = view.findViewById<EditText>(R.id.edittext_username_url)
        val btnCopyBio = view.findViewById<Button>(R.id.btn_copy_bio)
        val textViewBio = view.findViewById<TextView>(R.id.textview_bio)

        btnDownload.setOnClickListener { it ->
            if (editTextUsername.text.toString() != "") {
                var username = editTextUsername.text.toString()
                if (username.contains("https://")){
                    if(!username.contains("/p/") || !username.contains("/tv/") || !username.contains("/reel/")){
                        username = username.replace("https://instagram.com/","")
                        username = username.split("?")[0]
                    }
                }
                InstagramHelper().getProfile(username,view.context,object : IgProfileCallback {
                    override fun onSuccess(result: IgProfile) {
                        Snackbar.make(it, R.string.downloading, Snackbar.LENGTH_SHORT).show()
                        InstagramHelper().getStories(result.data.user.id,view.context)
                        textViewBio.setText(result.data.user.biography)
                    }

                    override fun onError(result: Any) {
                        Log.i("story dl err:",result.toString())
                    }
                })
            } else {
                Snackbar.make(it, R.string.enter_username, Snackbar.LENGTH_SHORT).show()
            }
        }
        // copy bio
        btnCopyBio.setOnClickListener { it ->
            if (textViewBio.text.toString() != "") {
                val clipboard =
                    view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Bio", textViewBio.text.toString())
                clipboard.setPrimaryClip(clipData)
                Snackbar.make(it, R.string.copied, Snackbar.LENGTH_SHORT).show()
            }
        }
        return view
    }
}