package com.example.TentwentAssignment.util

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.flow.*

object Utility {

    inline fun <ResultType, RequestType> networkBoundResource(
        crossinline query: suspend () -> Flow<ResultType>,
        crossinline fetch: suspend () -> RequestType,
        crossinline saveFetchResult: suspend (RequestType) -> Unit,
        crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
        crossinline shouldFetch: (ResultType) -> Boolean = { true }
    ) = flow<Resource<ResultType>> {
        emit(Resource.Loading(null))
        val data = query().first()

        val flow = if (shouldFetch(data)) {
            emit(Resource.Loading(data))
            try {
                saveFetchResult(fetch())
                query().map { Resource.Success(it) }
            } catch (throwable: Throwable) {
                onFetchFailed(throwable)
                query().map { Resource.Error(throwable.message.toString(), it) }
            }
        } else {
            query().map { Resource.Success(it) }
        }

        emitAll(flow)
    }

    fun checkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connMgr != null) {
            val activeNetworkInfo = connMgr.activeNetworkInfo
            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                return if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    true
                } else activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
            }
        }
        return false
    }

}