package net.scadsdnd.imusicsurfer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val WikipediaSVC by lazy {
        WikipediaService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener{
            if(edit_search.text.toString().isNotEmpty()){
                beginSearch(edit_search.text.toString())
            }
        }

        btn_search2.setOnClickListener{
            if(edit_search.text.toString().isNotEmpty()){
                // itunes search request
            }
        }
    }

    private fun beginSearch(searchString: String){
        disposable = WikipediaSVC.hitCountCheck("query", "json", "search", searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> txt_search_result.text = "${result.query.searchinfo.totalhits} result found" },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
