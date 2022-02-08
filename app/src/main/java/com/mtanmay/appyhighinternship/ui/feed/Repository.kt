package com.mtanmay.appyhighinternship.ui.feed

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mtanmay.appyhighinternship.api.API
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: API
) {

    fun getTopHeadlines(location: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                initialLoadSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FeedPagingSource(api, location)
            }
        ).liveData

}