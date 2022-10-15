package com.murphyspider.instasave.utils

import com.murphyspider.instasave.api.profileInfo.IgProfile

interface IgProfileCallback {
    fun onSuccess(result: IgProfile)
    fun onError(result: Any)
}