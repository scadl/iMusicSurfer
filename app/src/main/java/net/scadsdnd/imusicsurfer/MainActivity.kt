package net.scadsdnd.imusicsurfer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // container to hold response data
    private var disposableWiki: Disposable? = null
    private var disposableITM: Disposable? = null

    // Create service objects from service classes
    private val WikipediaSVC by lazy {
        WikipediaService.create()
    }
    private val iTunesSVC by lazy {
        iTunesService.createIMSvc()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add event listeners to buttons
        btn_search.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearch(edit_search.text.toString())
            }
        }
        btn_search2.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                albumSearchRun(edit_search.text.toString())
            }
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private fun beginSearch(searchString: String) {
        disposableWiki = WikipediaSVC.hitCountCheck("query", "json", "search", searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {

                        txt_search_result.text =
                            "${result.query.searchinfo.totalhits} articles in Wikipedia"
                    }

                    result.query.search.forEach {
                        txt_search_result.text =
                            "${txt_search_result.text}\n [${it.pageid}] ${it.title}"
                    }

                },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    private fun albumSearchRun(userKeyword: String) {
        // set the request patamters values
        disposableITM = iTunesSVC.searchAlbums(userKeyword, "RU", "music", "album")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                        txt_search_result.text = "${result.resultCount} albums in iTunes"
                        viewManager = LinearLayoutManager(this)
                        viewAdapter = iTunesRcVAdapt(result.results)
                        recyclerView = findViewById<RecyclerView>(R.id.root_list).apply {
                            setHasFixedSize(true)
                            layoutManager = viewManager
                            adapter = viewAdapter
                        }
                    }
                },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )

    }

    override fun onPause() {
        super.onPause()
        disposableWiki?.dispose()
    }
}
