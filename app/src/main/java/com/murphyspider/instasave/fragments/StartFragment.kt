package com.murphyspider.instasave.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.murphyspider.instasave.R


class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_start, container, false)
        var btnLogin = view.findViewById<Button>(R.id.btn_login)


        // login click
        btnLogin.setOnClickListener(View.OnClickListener { it ->
            val fragManager = activity?.supportFragmentManager?.beginTransaction()
            fragManager?.disallowAddToBackStack()
            fragManager?.replace(R.id.frame_start, LoginFragment())
            fragManager?.commit()

        })
        return view
    }
}