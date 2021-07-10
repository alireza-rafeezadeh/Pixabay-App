package com.app.core.data.datasource

import android.util.Log
import com.app.core.domain.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class BaseDataSource {
    suspend fun <T : Any> flowOnIO(api: suspend () -> Response<T>) =
        flow {
            try {
                val response = api.invoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultWrapper.Success(it))
                    }
                } else {
                    emit(ResultWrapper.ErrorString(response.message()))
                }
            } catch (e: Exception) {
                Log.i("data_exception", "${e.message}")
                emit(ResultWrapper.ErrorString(e.message ?: "Unknown Error occurred!"))
            }
        }.flowOn(Dispatchers.IO)
}
