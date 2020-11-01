package apps.nocturnuslabs.imagesearchapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import apps.nocturnuslabs.imagesearchapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepo @Inject constructor(private val unsplashApi: UnsplashApi) {

    fun getSearchResults(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            UnsplashPagingSource(unsplashApi, query)
        }
    ).liveData
}