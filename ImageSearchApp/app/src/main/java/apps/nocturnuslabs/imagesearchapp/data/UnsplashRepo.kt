package apps.nocturnuslabs.imagesearchapp.data

import apps.nocturnuslabs.imagesearchapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepo @Inject constructor(private val unsplashApi: UnsplashApi) {
}