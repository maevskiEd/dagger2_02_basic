@file:Suppress("UNUSED_PARAMETER")

package com.example.dagger2_02_basic.data

import javax.inject.Inject

class Analytics @Inject constructor(){

    fun trackNewsRequest(newsId: String) {
        // Do nothing
    }
}