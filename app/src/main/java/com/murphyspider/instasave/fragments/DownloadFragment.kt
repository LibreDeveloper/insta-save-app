package com.murphyspider.instasave.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.murphyspider.instasave.R
import com.murphyspider.instasave.utils.constants.IgHeaders
import com.murphyspider.instasave.utils.constants.MediaTypes
import com.murphyspider.instasave.utils.carousel.CarouselModel
import com.murphyspider.instasave.utils.constants.PrefKeys
import com.murphyspider.instasave.utils.image.ImageModel
import com.murphyspider.instasave.utils.sharedPrefs.SharedPref
import com.murphyspider.instasave.utils.video.VideoModel
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DownloadFragment : Fragment() {
    lateinit var jsonString: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.download_layout, container, false)

        val editTextUrl: EditText = view.findViewById(R.id.edittext_url)
        val btnDownload: Button = view.findViewById(R.id.btn_download)
        val prefs = SharedPref(view.context)
        var url = ""

        editTextUrl.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                Log.i("editext --->",p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                Log.i("editext --->",p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0!!.contains("/?igshid")) {
                    var txt = p0.split("?")
                    if (p0.contains("reel")) {
                        txt = listOf(txt[0].replace("reel", "p"))
                    }
                    Log.i("URL -->", txt[0])
                    url = txt[0]
                }
            }
        })

        btnDownload.setOnClickListener { it ->
            var cookie = prefs.getString(PrefKeys.COOKIE).toString()
            cookie = cookie.replace("\\","")
            val okHttpClient = OkHttpClient()
            val request = Request.Builder()
                .url("${url}?__a=1")
                .addHeader("User-Agent", IgHeaders.USER_AGENT_DESKTOP)
                .addHeader("Host", IgHeaders.HOST_DESKTOP)
                .addHeader("Cookie", cookie)
                .build()
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("req err ---> ", e.message.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        val gson = Gson()
                        val jsonStr = response.body()?.string()
                        if ("carousel_media" in jsonStr!!) {
                            Log.i("is carousel!", "ok")
                            downloadCarousel(gson, jsonStr)
                            Snackbar.make(it, "Download completed", Snackbar.LENGTH_SHORT).show()
                        }
                        if ("image_versions2" in jsonStr && !jsonStr.contains("carousel_media")) {
                            Log.i("is image!", "ok")
                            downloadImage(gson, jsonStr)
                            Snackbar.make(it, "Download completed", Snackbar.LENGTH_SHORT).show()
                        }
                        if ("video_versions" in jsonStr && !jsonStr.contains("carousel_media")) {
                            Log.i("is video!", "ok")
                            downloadVideo(gson, jsonStr)
                            Snackbar.make(it, "Download completed", Snackbar.LENGTH_SHORT).show()
                        } else {
                            Log.i("is carousel!", "no")
                            Log.i("res --->", jsonStr)
                            Log.i("cookie --->", prefs.getString(PrefKeys.COOKIE).toString())
                        }

                    } catch (e: Exception) {
                        Log.i("ex --->", e.message.toString())
                    }
                }
            })
        }

        return view
    }

    fun downloadCarousel(gson: Gson, jsonStr: String) {
        val res = gson.fromJson(jsonStr, CarouselModel::class.java)
        val mediaCount = res.items[0].carousel_media_count
        val client = OkHttpClient()
        for (i in 0 until mediaCount) {
            if (res.items[0].carousel_media[i].media_type == MediaTypes.IMAGE) {
                val req = Request.Builder()
                    .url(res.items[0].carousel_media[i].image_versions2.candidates[0].url)
                    .build()
                val r = client.newCall(req).execute()
                var fileName = URLUtil.guessFileName(
                    res.items[0].carousel_media[i].image_versions2.candidates[0].url,
                    null,
                    null
                )
                val stream = r.body()?.byteStream()
                var img = BitmapFactory.decodeStream(stream)
                val filePath = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    fileName
                )
                val outputSteam = FileOutputStream(filePath)
                img.compress(Bitmap.CompressFormat.JPEG, 100, outputSteam)
                outputSteam.flush()
                outputSteam.close()
                Log.i("file name --> ", fileName)
            }
            if (res.items[0].carousel_media[i].media_type == MediaTypes.VIDEO) {
                val req = Request.Builder()
                    .url(res.items[0].carousel_media[i].video_versions[0].url)
                    .build()
                val r = client.newCall(req).execute()
                val fileName = URLUtil.guessFileName(
                    res.items[0].carousel_media[i].video_versions[0].url,
                    null,
                    null
                )
                val stream = r.body()?.byteStream()
                val buff = ByteArray(1024 * 4)
                val mediaFile = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    fileName
                )
                val output = FileOutputStream(mediaFile)
                while (true) {
                    val readed = stream?.read(buff)
                    if (readed == -1) {
                        break
                    }
                    output.write(buff, 0, readed!!);
                }
                output.flush()
                output.close()
                Log.i("file name --> ", fileName)
            }

        }
    }

    fun downloadImage(gson: Gson, jsonStr: String) {
        val res = gson.fromJson(jsonStr, ImageModel::class.java)
        val client = OkHttpClient()
        val req = Request.Builder()
            .url(res.items[0].image_versions2.candidates[0].url)
            .build()
        val r = client.newCall(req).execute()
        var fileName = URLUtil.guessFileName(
            res.items[0].image_versions2.candidates[0].url,
            null,
            null
        )
        val stream = r.body()?.byteStream()
        val img = BitmapFactory.decodeStream(stream)
        val filePath = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            fileName
        )
        val outputSteam = FileOutputStream(filePath)
        img.compress(Bitmap.CompressFormat.JPEG, 100, outputSteam)
        outputSteam.flush()
        outputSteam.close()
        Log.i("file name --> ", fileName)
    }

    fun downloadVideo(gson: Gson, jsonStr: String) {
        val res = gson.fromJson(jsonStr, VideoModel::class.java)
        val client = OkHttpClient()
        val req = Request.Builder()
            .url(res.items[0].video_versions[0].url)
            .build()
        val r = client.newCall(req).execute()
        val fileName = URLUtil.guessFileName(
            res.items[0].video_versions[0].url,
            null,
            null
        )
        val stream = r.body()?.byteStream()
        val buff = ByteArray(1024 * 4)
        val mediaFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            fileName
        )
        val output = FileOutputStream(mediaFile)
        while (true) {
            var readed = stream?.read(buff)
            if (readed == -1) {
                break
            }
            output.write(buff, 0, readed!!);
        }
        output.flush()
        output.close()
        Log.i("file name --> ", fileName)
    }
}