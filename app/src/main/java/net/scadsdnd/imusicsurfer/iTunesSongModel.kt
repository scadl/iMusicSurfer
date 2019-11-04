package net.scadsdnd.imusicsurfer

// Set the structure of response
object iTunesSongModel {
    data class Result(val resultCount: Int, val results:Array<Album>)
    data class Album(
        val collectionName: String,
        val trackName: String
    )
}