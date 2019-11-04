package net.scadsdnd.imusicsurfer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {

    // container to hold response data
    private var disposableITM: Disposable? = null

    // Create service objects from service classes
    private val iTunesSVC by lazy {
        iTunesService.createIMSvc()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val songId:Int = intent.getIntExtra("albumId", 0)
        findViewById<TextView>(R.id.textViewAlTitle).text = "Loading..."

        albumSongLoad(songId)

    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private fun albumSongLoad(songId: Int) {
        // set the request patamters values
        disposableITM = iTunesSVC.getAlbumSongs(songId.toString(), "song")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                        textViewAlTitle.text = "${result.results[0].collectionName}"
                        viewManager = LinearLayoutManager(this)
                        viewAdapter = iTunesRcSAdapt(result.results)
                        recyclerView = findViewById<RecyclerView>(R.id.song_list).apply {
                            setHasFixedSize(true)
                            layoutManager = viewManager
                            adapter = viewAdapter
                        }
                    }
                },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()  }
            )

    }

    override fun onPause() {
        super.onPause()
        disposableITM?.dispose()
    }
}
