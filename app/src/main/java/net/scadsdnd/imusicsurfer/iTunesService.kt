package net.scadsdnd.imusicsurfer

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface iTunesService {

    // Request example
    // https://itunes.apple.com/search?term=abba&country=RU&media=music&entity=album

    // set the request parameter names
    @GET("search")
    fun searchAlbums(@Query("term") term:String,
                     @Query("country") country:String,
                     @Query("media") media:String,
                     @Query("entity") entity:String
    ): Observable<iTunesModel.Result>
    // ^ set the output structure using custom model

    // build the actual request
    companion object{
        fun createIMSvc():iTunesService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://itunes.apple.com/")
                .build()

            return retrofit.create(iTunesService::class.java)
        }
    }

}