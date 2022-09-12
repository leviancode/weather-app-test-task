package com.leviancode.android.infotextest.data.repositories.impl

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.leviancode.android.infotextest.ui.entities.location.CityUI
import kotlinx.coroutines.tasks.await
import com.leviancode.android.infotextest.utils.extensions.toObjects

class FirestorePagingSource(
    private val queryCitiesByName: Query,
    private val pageSize: Long = 20
) : PagingSource<QuerySnapshot, CityUI>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, CityUI>): QuerySnapshot? {
        return null
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, CityUI> {
        return runCatching<LoadResult<QuerySnapshot, CityUI>> {
            val currentPage = params.key ?: queryCitiesByName
                .limit(pageSize)
                .get()
                .await()
            val lastVisibleProduct = currentPage.documents[currentPage.size() - 1]
            val nextPage = queryCitiesByName
                .startAfter(lastVisibleProduct)
                .limit(pageSize)
                .get()
                .await()
            LoadResult.Page(
                data = currentPage.toObjects(),
                prevKey = null,
                nextKey = nextPage
            )
        }.getOrElse {
            Log.e(TAG, "Failed to load page", it)
            LoadResult.Error(it)
        }
    }

    companion object{
        private const val TAG = "FirestorePagingSource"
    }
}