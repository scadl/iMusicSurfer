package net.scadsdnd.imusicsurfer

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface WikipediaService {

    /* Building WikiRequest
    https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=Something

    Resposnse:
    {
        batchcomplete: "",
        continue: {
            sroffset: 10,
            continue: "-||"
        },
        query: {
            searchinfo: {
            totalhits: 16776
        },
        search: [...
     */

    @GET("api.php")
    fun hitCountCheck(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") srsearch: String
    ): Observable<WikipediaModel.Result>

    companion object {
        fun create(): WikipediaService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://en.wikipedia.org/w/")
                .build()

            return retrofit.create(WikipediaService::class.java)
        }
    }


}