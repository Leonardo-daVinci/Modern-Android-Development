package apps.nocturnuslabs.imagesearchapp.api

import apps.nocturnuslabs.imagesearchapp.data.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)