package com.app.core.util

import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher


// todo: https://stackoverflow.com/questions/66503911/unit-testing-a-repository-with-paging-3-using-a-a-remote-mediator-and-paging-sou/66686920#66686920
@ExperimentalCoroutinesApi
suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
    val dcb = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }
    val items = mutableListOf<T>()
    val dif = object : PagingDataDiffer<T>(dcb, TestCoroutineDispatcher()) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            newCombinedLoadStates: CombinedLoadStates,
            lastAccessedIndex: Int,
            // todo: https://stackoverflow.com/questions/63522656/what-is-the-correct-way-to-check-the-data-from-a-pagingdata-object-in-android-un
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size)
                items.add(newList.getFromStorage(idx))
            onListPresentable()
            return null
        }
    }
    dif.collectFrom(this)
    return items
}