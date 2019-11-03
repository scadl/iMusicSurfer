package net.scadsdnd.imusicsurfer

// Set the structure of response
object iTunesModel {
    data class Result(val resultCount: Int, val results:Array<Album>)
    data class Album(
        val collectionName: String,
        val collectionId: Int,
        val artworkUrl100: String,
        val copyright: String,
        val collectionPrice: Int,
        val currency: String
    )
}