package com.example.MoRe.network.model.base

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job

class BasePresenter {
    val job: Job? = null
    var exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.localizedMessage?.let { onAPIError(it) }
    }

    private fun onAPIError(message: String) {
        Log.e("onErrorRunAPI", message)
    }
}