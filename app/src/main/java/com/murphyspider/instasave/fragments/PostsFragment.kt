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
import com.murphyspider.instasave.utils.InstagramHelper
import com.murphyspider.instasave.utils.MediaCallback


class PostsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_download_post, container, false)
        val btnDownload = view.findViewById<Button>(R.id.btn_download)
        val btnCopyCaption = view.findViewById<Button>(R.id.btn_copy_caption)

        val textViewCaption = view.findViewById<TextView>(R.id.textview_caption)

        val editTextUrl = view.findViewById<EditText>(R.id.edittext_post_url)

        // copy caption
        btnCopyCaption.setOnClickListener { it ->
            if (textViewCaption.text.toString() != "") {
                val clipboard =
                    view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Caption", textViewCaption.text.toString())
                clipboard.setPrimaryClip(clipData)
                Snackbar.make(it, R.string.copied, Snackbar.LENGTH_SHORT).show()
            }
        }

        // download post
        btnDownload.setOnClickListener { it ->
            var url = editTextUrl.text.toString()
            if (url != "") {
                if (!url.contains("https") ||
                    !url.contains("/p/") ||
                    !url.contains("/tv/") ||
                    !url.contains("/reel/")
                ) {
                    Snackbar.make(it, R.string.please_wait, Snackbar.LENGTH_SHORT).show()
                    InstagramHelper().getMedia(url, view.context, object : MediaCallback {
                        override fun onSuccess(result: String) {
                            textViewCaption.setText(result)
                        }

                        override fun onError(result: Any) {
                            Log.i("err in media callback:", "err")
                        }
                    })
                } else {
                    Snackbar.make(it, R.string.enter_url, Snackbar.LENGTH_LONG).show()
                }

            } else {
                Snackbar.make(it, R.string.enter_url, Snackbar.LENGTH_LONG).show()
            }
        }
        return view
    }
}