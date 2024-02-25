package com.ygolubev.pexels.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

internal class PhotosPagingSource(
    private val repository: PhotosRepository,
) : PagingSource<Int, Photo>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Photo> = try {
        val nextPageNumber = params.key ?: 1
        val pageSize = params.loadSize
        val page = repository.getCuratedPhotos(nextPageNumber, pageSize)
        LoadResult.Page(
            data = page.data,
            prevKey = null,
            nextKey = if (page.hasNext) nextPageNumber + 1 else null
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
