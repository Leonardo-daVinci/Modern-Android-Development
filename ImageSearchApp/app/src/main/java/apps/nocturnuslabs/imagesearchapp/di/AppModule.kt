package apps.nocturnuslabs.imagesearchapp.di

import apps.nocturnuslabs.imagesearchapp.api.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class) //this class is automatically created for us
object AppModule {  //object is used instead iof class because the dagger code generated is more efficient

    //we create two methods to tell how to create the objects we need - Retrofit and UnsplashApi objects
    //naming convention is provide+type of object we want it to return eg. providesRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi =
        retrofit.create(UnsplashApi::class.java)
}