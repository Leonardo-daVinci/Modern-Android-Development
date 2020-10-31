package apps.nocturnuslabs.imagesearchapp.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import apps.nocturnuslabs.imagesearchapp.data.UnsplashRepo

class GalleryViewModel @ViewModelInject constructor(
    private val unsplashRepo: UnsplashRepo
) : ViewModel() {
}