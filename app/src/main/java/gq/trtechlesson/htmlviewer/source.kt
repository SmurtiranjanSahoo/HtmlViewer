package gq.trtechlesson.htmlviewer

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


@Suppress("DEPRECATION")
class source : AppCompatActivity() {

    companion object{
        const val WEB_ADDRESS = "web_address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source)


//        val toolbarMain = findViewById<Toolbar>(R.id.toolbar_main)
//        setSupportActionBar(toolbarMain)
//        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

//        val searchBar = findViewById<SearchView>(R.id.source_search)
//        val searchText = findViewById<SearchView>(R.id.source_search).query.toString()
//
//        val sourceVie = findViewById<WebView>(R.id.sourceView)
//        val sourceText = sourceVie.text


        // floating button fragment dialog


        // Source weview
        val webAddress = intent.getStringExtra(WEB_ADDRESS)

        val sourceView = findViewById<WebView>(R.id.sourceView)
        sourceView.settings.setJavaScriptEnabled(true)

        sourceView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }

            val progressBar = findViewById<ProgressBar>(R.id.progressBar2)

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }

        }

      val http = webAddress?.substring(0, 4)

        if (http == "http"){
           sourceView.loadUrl("view-source:$webAddress")
        } else if(http != "http") {
            sourceView.loadUrl("view-source:https://$webAddress")
        }

        // sourcetext

        val httpclient: HttpClient = DefaultHttpClient() // Create HTTP Client

        val httpget = HttpGet("view-source:https://$webAddress") // Set the action you want to do

        val response: HttpResponse = httpclient.execute(httpget) // Executeit

        val entity: HttpEntity = response.getEntity()
        val `is`: InputStream = entity.getContent() // Create an InputStream with the response

        val reader = BufferedReader(InputStreamReader(`is`, "iso-8859-1"), 8)
        val sb = StringBuilder()
        var line: String? = null
        while (reader.readLine().also({ line = it }) != null) // Read line by line
            sb.append(""" $line """.trimIndent())
        val resString = sb.toString() // Result is here


        `is`.close() // Close the stream


        val SearchFloatButton = findViewById<FloatingActionButton>(R.id.searchbtn)
        SearchFloatButton.setOnClickListener{
            val dialog = SearchDialogFragment()

            dialog.show(supportFragmentManager, "searchDialog")

            val args = Bundle()
            args.putString("SourceTextToString",resString)
            dialog.arguments = args;


        }


    }

//    fun urlChecker( webAddress: WebAddress){
//        val http = webAddress.substring(0, 4)
//
//        if (http == "http"){
//            sourceView.loadUrl("view-source:$webAddress")
//        } else if(http != "http") {
//            sourceView.loadUrl("view-source:https://$webAddress")
//        }
//    }

    override fun onBackPressed() {
        val sourceView = findViewById<WebView>(R.id.sourceView)
        if (sourceView.canGoBack()) {
            sourceView.goBack()
        } else{
            Toast.makeText(applicationContext, "Exit Source page", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }

    }

}


