package com.murphyspider.instasave.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.murphyspider.instasave.api.carouselMedia.CarouselMedia
import com.murphyspider.instasave.api.imagePost.ImagePost
import com.murphyspider.instasave.api.postInfo.PostInfo
import com.murphyspider.instasave.api.profileInfo.IgProfile
import com.murphyspider.instasave.api.story.StoriesMedia
import com.murphyspider.instasave.api.videoPost.VideoPost
import com.murphyspider.instasave.consts.Cookies
import com.murphyspider.instasave.consts.MediaType
import com.murphyspider.instasave.prefs.PrefKeys
import com.murphyspider.instasave.prefs.SharedPreferences

class InstagramHelper : AppCompatActivity() {
    var media_id = ""
    var postCaption: String = ""
    var profBio: String = ""
    var profPic: String = ""

    fun getMedia(postUrl: String, context: Context, callback: MediaCallback) {
        val prefs = SharedPreferences(context)
        var pUrl = postUrl.split("?")[0]
        var url =
            "https://i.instagram.com/api/v1/oembed/?url=${pUrl}"
        val queue = Volley.newRequestQueue(context)
        val req: StringRequest = object : StringRequest(
            Request.Method.GET, url,
            { res ->
                val gson = Gson()
                val jString = gson.fromJson(res, PostInfo::class.java)
                media_id = jString.media_id
                Log.i("media id:", media_id)
                var url2 =
                    "https://i.instagram.com/api/v1/media/${media_id}/info/"
                val queue2 = Volley.newRequestQueue(context)
                val req2: StringRequest = object : StringRequest(
                    Request.Method.GET, url2,
                    { res ->
                        val gson = Gson()
                        val jString = gson.fromJson(res, ImagePost::class.java)
                        val mediaType = jString.items[0].media_type
                        callback.onSuccess(jString.items[0].caption.text)
                        if (mediaType == MediaType.IMAGE) {
                            val imageString = gson.fromJson(res, ImagePost::class.java)
                            val imgUrl = imageString.items[0].image_versions2.candidates[0].url
                            var imgName = imgUrl.replace("https://", "").replace("/", "").replace(".webp",".jpg")
                            Log.i("file name:",imgName)
                            val dlReq = DownloadManager.Request(Uri.parse(imgUrl))
                                .setTitle("Insta Save")
                                .setDescription("Downloading post...")
                                .setDestinationInExternalPublicDir(
                                    Environment.DIRECTORY_DOWNLOADS,
                                    "/Insta Save/${imgName}"
                                )
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setAllowedOverMetered(true)
                                .setAllowedOverRoaming(false)
                            val dlManager =
                                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                            dlManager.enqueue(dlReq)
                        } else if (mediaType == MediaType.VIDEO) {
                            val videoString = gson.fromJson(res, VideoPost::class.java)
                            val videoUrl = videoString.items[0].videoVersions[0].url
                            callback.onSuccess(videoString.items[0].caption.text)
                            var videoName = videoUrl.replace("https://", "").replace("/", "")
                            val dlReq = DownloadManager.Request(Uri.parse(videoUrl))
                                .setTitle("Insta Save")
                                .setDescription("Downloading post...")
                                .setDestinationInExternalPublicDir(
                                    Environment.DIRECTORY_DOWNLOADS,
                                    "/Insta Save/${videoName}"
                                )
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setAllowedOverMetered(true)
                                .setAllowedOverRoaming(false)
                            val dlManager =
                                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                            dlManager.enqueue(dlReq)
                        } else if (mediaType == MediaType.CAROUSEL) {
                            val carouselString = gson.fromJson(res, CarouselMedia::class.java)
                            val mediaCount = carouselString.items[0].carouselMediaCount
                            callback.onSuccess(carouselString.items[0].caption.text)
                            for (i in 0..mediaCount - 1) {
                                if (carouselString.items[0].carouselMedia[i].mediaType == MediaType.VIDEO) {
                                    val carouselUrl =
                                        carouselString.items[0].carouselMedia[i].videoVersions[0].url
                                    var carouselName =
                                        carouselUrl.replace("https://", "").replace("/", "")
                                    val dlReq = DownloadManager.Request(Uri.parse(carouselUrl))
                                        .setTitle("Insta Save")
                                        .setDescription("Downloading post...")
                                        .setDestinationInExternalPublicDir(
                                            Environment.DIRECTORY_DOWNLOADS,
                                            "/Insta Save/${carouselName}"
                                        )
                                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                        .setAllowedOverMetered(true)
                                        .setAllowedOverRoaming(false)
                                    val dlManager =
                                        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                                    dlManager.enqueue(dlReq)
                                } else if (carouselString.items[0].carouselMedia[i].mediaType == MediaType.IMAGE) {
                                    val carouselUrl =
                                        carouselString.items[0].carouselMedia[i].imageVersions2.candidates[0].url
                                    var carouselName =
                                        carouselUrl.replace("https://", "").replace("/", "").replace(".webp",".jpg")
                                    val dlReq = DownloadManager.Request(Uri.parse(carouselUrl))
                                        .setTitle("Insta Save")
                                        .setDescription("Downloading post...")
                                        .setDestinationInExternalPublicDir(
                                            Environment.DIRECTORY_DOWNLOADS,
                                            "/Insta Save/${carouselName}"
                                        )
                                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                        .setAllowedOverMetered(true)
                                        .setAllowedOverRoaming(false)
                                    val dlManager =
                                        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                                    dlManager.enqueue(dlReq)
                                }
                            }
                        } else {
                            Log.i("media err:", "err")
                        }
                    }, { err: VolleyError ->
                        Log.i("volley err", err.networkResponse.statusCode.toString())
                        Log.i("volley err", err.networkResponse.data.toString())
                        Log.i("volley err", err.networkResponse.headers.toString())
                    }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val header = HashMap<String, String>()
                        header["Accept"] = Cookies.ACCEPT
                        header["Cookie"] = prefs.getValue(PrefKeys.USER_COOKIE).toString()
                        header["User-Agent"] = Cookies.MOBILE_USER_AGENT
                        header["Connection"] = Cookies.CONNECTION
                        header["Host"] = Cookies.MOBILE_HOST
                        return header
                    }
                }

                queue2.add(req2)
            }, { err: VolleyError ->
                Log.i("volley err", err.networkResponse.statusCode.toString())
                Log.i("volley err", err.networkResponse.data.toString())
                Log.i("volley err", err.networkResponse.headers.toString())
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["Accept"] = Cookies.ACCEPT
                header["Cookie"] = prefs.getValue(PrefKeys.USER_COOKIE).toString()
                header["User-Agent"] = Cookies.MOBILE_USER_AGENT
                header["Connection"] = Cookies.CONNECTION
                header["Host"] = Cookies.MOBILE_HOST
                return header
            }
        }

        queue.add(req)
    }

    fun getProfile(userName: String, context: Context, callback: IgProfileCallback) {
        val prefs = SharedPreferences(context)
        var url =
            "https://i.instagram.com/api/v1/users/web_profile_info/?username=${userName}"
        val queue = Volley.newRequestQueue(context)
        val req: StringRequest = object : StringRequest(
            Request.Method.GET, url,
            { res ->
                val gson = Gson()
                val jString = gson.fromJson(res, IgProfile::class.java)
                val profUrl = jString.data.user.profilePicUrlHd
                val pName = profUrl.replace("https://", "").replace("/", "")
                callback.onSuccess(jString)
                val dlReq = DownloadManager.Request(Uri.parse(profUrl))
                    .setTitle("Insta Save")
                    .setDescription("Downloading profile...")
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        "/Insta Save/${pName}.jpg"
                    )
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(false)
                val dlManager =
                    context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                dlManager.enqueue(dlReq)

            }, { err: VolleyError ->
                Log.i("volley err", err.networkResponse.statusCode.toString())
                Log.i("volley err", err.networkResponse.data.toString())
                Log.i("volley err", err.networkResponse.headers.toString())
                callback.onError(err.networkResponse.headers.toString())
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["Accept"] = Cookies.ACCEPT
                header["Cookie"] = prefs.getValue(PrefKeys.USER_COOKIE).toString()
                header["User-Agent"] = Cookies.MOBILE_USER_AGENT
                header["Connection"] = Cookies.CONNECTION
                header["Host"] = Cookies.MOBILE_HOST
                return header
            }
        }
        queue.add(req)
    }

    fun getStories(userId: String, context: Context) {
        val prefs = SharedPreferences(context)
        val url = "https://i.instagram.com/api/v1/feed/user/${userId}/reel_media/"
        val queue = Volley.newRequestQueue(context)
        val req: StringRequest = object : StringRequest(
            Request.Method.GET, url,
            { res ->
                val gson = Gson()
                val jString = gson.fromJson(res, StoriesMedia::class.java)
                Log.i("stories count:",jString.items.count().toString())
                Log.i("stories count:",jString.media_count.toString())
                for (i in 0..jString.media_count-1) {
                    Log.i("story:",i.toString())
                    val mediaType = jString.items[i].media_type
                    if (mediaType == MediaType.IMAGE) {
                        val imageString = gson.fromJson(res, ImagePost::class.java)
                        val imgUrl = imageString.items[i].image_versions2.candidates[0].url
                        var imgName = imgUrl.replace("https://", "").replace("/", "")

                        val dlReq = DownloadManager.Request(Uri.parse(imgUrl))
                            .setTitle("Insta Save")
                            .setDescription("Downloading post...")
                            .setDestinationInExternalPublicDir(
                                Environment.DIRECTORY_DOWNLOADS,
                                "/Insta Save/${imgName}.jpg"
                            )
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            .setAllowedOverMetered(true)
                            .setAllowedOverRoaming(false)
                        val dlManager =
                            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        dlManager.enqueue(dlReq)
                    } else if (mediaType == MediaType.VIDEO) {
                        val videoString = gson.fromJson(res, VideoPost::class.java)
                        val videoUrl = videoString.items[i].videoVersions[0].url
                        var videoName = videoUrl.replace("https://", "").replace("/", "")

                        val dlReq = DownloadManager.Request(Uri.parse(videoUrl))
                            .setTitle("Insta Save")
                            .setDescription("Downloading post...")
                            .setDestinationInExternalPublicDir(
                                Environment.DIRECTORY_DOWNLOADS,
                                "/Insta Save/${videoName}.mp4"
                            )
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            .setAllowedOverMetered(true)
                            .setAllowedOverRoaming(false)
                        val dlManager =
                            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        dlManager.enqueue(dlReq)
                    }
                }
            }, { err: VolleyError ->
                Log.i("volley err", err.networkResponse.statusCode.toString())
                Log.i("volley err", err.networkResponse.data.toString())
                Log.i("volley err", err.networkResponse.headers.toString())
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>()
                header["Accept"] = Cookies.ACCEPT
                header["Cookie"] = prefs.getValue(PrefKeys.USER_COOKIE).toString()
                header["User-Agent"] = Cookies.MOBILE_USER_AGENT
                header["Connection"] = Cookies.CONNECTION
                header["Host"] = Cookies.MOBILE_HOST
                return header
            }

        }
        queue.add(req)
    }

//    fun getHighlights(userId: String, context: Context) {
//        val url = "https://i.instagram.com/api/v1/highlights/${userId}/highlights_tray/"
//        val queue = Volley.newRequestQueue(context)
//        val req: StringRequest = object : StringRequest(
//            Request.Method.GET, url,
//            { res ->
//                val gson = Gson()
//                val jString = gson.fromJson(res, HighlightMedia::class.java)
//                for (i in 1..jString.tray.count()) {
//                    val mediaType = jString.tray[i].items
//                    if (mediaType == MediaType.IMAGE) {
//                        val imageString = gson.fromJson(res, ImagePost::class.java)
//                        val imgUrl = imageString.items[0].image_versions2.candidates[0].url
//                        var imgName = imgUrl.replace("https://", "").replace("/", "")
//                            .replace(".", "")
//                        val dlReq = DownloadManager.Request(Uri.parse(imgUrl))
//                            .setTitle("Insta Save")
//                            .setDescription("Downloading post...")
//                            .setDestinationInExternalPublicDir(
//                                Environment.DIRECTORY_DOWNLOADS,
//                                "/Insta Save/${imgName}.jpg"
//                            )
//                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                            .setAllowedOverMetered(true)
//                            .setAllowedOverRoaming(false)
//                        val dlManager =
//                            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//                        dlManager.enqueue(dlReq)
//                    } else if (mediaType == MediaType.VIDEO) {
//                        val videoString = gson.fromJson(res, VideoPost::class.java)
//                        val videoUrl = videoString.items[0].videoVersions[0].url
//                        var videoName = videoUrl.replace("https://", "").replace("/", "")
//                            .replace(".", "")
//                        val dlReq = DownloadManager.Request(Uri.parse(videoUrl))
//                            .setTitle("Insta Save")
//                            .setDescription("Downloading post...")
//                            .setDestinationInExternalPublicDir(
//                                Environment.DIRECTORY_DOWNLOADS,
//                                "/Insta Save/${videoName}.mp4"
//                            )
//                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                            .setAllowedOverMetered(true)
//                            .setAllowedOverRoaming(false)
//                        val dlManager =
//                            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//                        dlManager.enqueue(dlReq)
//                    }
//                }
//            }, { err: VolleyError ->
//                Log.i("volley err", err.networkResponse.statusCode.toString())
//                Log.i("volley err", err.networkResponse.data.toString())
//                Log.i("volley err", err.networkResponse.headers.toString())
//            }) {
//            override fun getHeaders(): MutableMap<String, String> {
//                val header = HashMap<String, String>()
//                header["Accept"] = Cookies.ACCEPT
//                header["Cookie"] = Cookies.CSRF
//                header["User-Agent"] = Cookies.MOBILE_USER_AGENT
//                header["Connection"] = Cookies.CONNECTION
//                header["Host"] = Cookies.MOBILE_HOST
//                return header
//            }
//
//        }
//        queue.add(req)
//    }


}