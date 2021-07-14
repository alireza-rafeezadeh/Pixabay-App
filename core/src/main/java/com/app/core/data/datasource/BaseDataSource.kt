package com.app.core.data.datasource

import com.app.core.domain.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class BaseDataSource {
    suspend fun <T : Any> flowOnIO(api: suspend () -> Response<T>) =
        flow {
            try {
                emit(ResultWrapper.InProgress)
                val response = api.invoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResultWrapper.Success(it))
                    }
                } else {
                    emit(ResultWrapper.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e.message ?: "Unknown Error occurred!"))
            }
        }.flowOn(Dispatchers.IO)
}
