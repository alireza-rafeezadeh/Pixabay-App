package com.app.core.data.repository.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.core.data.datasource.search.SearchRemoteDataSource
import com.app.core.domain.ResultWrapper
import com.app.core.domain.search.Hit
import kotlinx.coroutines.flow.collect
import retrofit2.HttpException
import java.io.IOException


class SearchPagingSource(val query : String,val searchRemoteDataSource : SearchRemoteDataSource) : PagingSource<Int,Hit>() {
    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        val position = params.key ?: 1
        try {
            val response = searchRemoteDataSource.search(query, position.toString())

            var nextKey : Int? = 1

            var data : List<Hit> = emptyList()
            response.collect {
                if (it is ResultWrapper.Success){
                    nextKey = if (it.data.hits.isEmpty()) {
                        null
                    } else {
                        // initial load size = 3 * NETWORK_PAGE_SIZE
                        // ensure we're not requesting duplicating items, at the 2nd request
                        position + 1
                    }

                    data = it.data.hits

//                     LoadResult.Page(
//                        data = it.data.hits,
//                        prevKey = if (position == 1) null else position - 1,
//                        nextKey = nextKey
//                    )
                }
            }


            return LoadResult.Page(
                data = data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}