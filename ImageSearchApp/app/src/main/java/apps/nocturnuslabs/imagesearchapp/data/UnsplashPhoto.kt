package apps.nocturnuslabs.imagesearchapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Parcelable is used to transfer this data between fragments using Navigation component
//the only types that are allowed are int/string/parcelable
@Parcelize
data class UnsplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) : Parcelable {

    @Parcelize
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String
    ) : Parcelable

    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String,
    ) : Parcelable {
        val attributionUrl get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"
    }
}