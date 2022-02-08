package com.mtanmay.appyhighinternship.ui.feed

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.mtanmay.appyhighinternship.api.API
import com.mtanmay.appyhighinternship.api.Article
import com.mtanmay.appyhighinternship.api.Response
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class FeedPagingSource(
    private val api: API,
    private val location: String
) : RxPagingSource<Int, Article>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Article>> {

        val position = params.key ?: 1

        return api.getTopHeadlines(country = location)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }

    }

    private fun toLoadResult(response: Response, position: Int): LoadResult<Int, Article> {
        val articles = response.articles
        return LoadResult.Page(
            data = articles,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == response.totalResults) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null


}