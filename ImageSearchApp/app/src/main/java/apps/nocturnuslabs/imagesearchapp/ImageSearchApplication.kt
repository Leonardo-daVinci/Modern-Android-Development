package apps.nocturnuslabs.imagesearchapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Hilt requires each app to have this class for working.
@HiltAndroidApp
class ImageSearchApplication : Application() {
}