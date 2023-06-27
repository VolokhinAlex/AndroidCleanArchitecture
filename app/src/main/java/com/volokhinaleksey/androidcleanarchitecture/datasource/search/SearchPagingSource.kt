package com.volokhinaleksey.androidcleanarchitecture.datasource.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoDTO
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val searchDataSource: SearchDataSource,
    private val query: String,
    private val token: String
) : PagingSource<Int, PhotoDTO>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoDTO>): Int? {
        val anchorPos = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPos) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDTO> {
        val page = params.key ?: 1
        if (query.isEmpty()) return LoadResult.Page(data = listOf(), prevKey = null, nextKey = null)
        return try {
            val resultResponse = searchDataSource.searchPhoto(
                token = token,
                page = page,
                query = query,
                perPage = PER_PAGE
            )
            val nextKey = if (resultResponse.results.isEmpty()) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(data = resultResponse.results, prevKey = prevKey, nextKey = nextKey)
        } catch (ioException: IOException) {
            return LoadResult.Error(ioException)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val PER_PAGE = 10
    }
}