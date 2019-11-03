package net.scadsdnd.imusicsurfer

object WikipediaModel {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo, val search: Array<Article>)
    data class SearchInfo(val totalhits: Int)
    data class Article(val title: String, val pageid: Int)
}