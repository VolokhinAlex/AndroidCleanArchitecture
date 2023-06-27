package com.volokhinaleksey.androidcleanarchitecture.datasource.photos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.volokhinaleksey.androidcleanarchitecture.models.PhotoDTO
import retrofit2.HttpException
import java.io.IOException

class PhotosPagingSource(
    private val photosDataSource: PhotosDataSource,
    private val orderBy: String,
    private val token: String
) : PagingSource<Int, PhotoDTO>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoDTO>): Int? {
        val anchorPos = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPos) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDTO> {
        val page = params.key ?: 1
        return try {
            val resultResponse = photosDataSource.getPhotos(
                token = token,
                page = page,
                perPage = PER_PAGE,
                orderBy = orderBy
            )
            val nextKey = if (resultResponse.isEmpty()) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(data = resultResponse, prevKey, nextKey)
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