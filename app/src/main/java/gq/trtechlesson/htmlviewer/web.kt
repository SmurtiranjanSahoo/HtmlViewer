package gq.trtechlesson.htmlviewer


import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.URL


class web : AppCompatActivity() {

    // companion object declaration
    companion object{
        const val WEB_ADDRESS = "web_address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        // WEBVIEW

        val webAddress = intent.getStringExtra(WEB_ADDRESS)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.setJavaScriptEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }

        //  progressbar
            val progressBar = findViewById<ProgressBar>(R.id.progressBar1)

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
                webView.loadUrl("$webAddress")
            } else if(http != "http") {
                webView.loadUrl("https://$webAddress")
            }

        // To OPEN Link In Exteral Browser
//        val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
//        startActivity(i)


//        val url = URL(webAddress)
//        val host = url.host
//        actionBar?.title = "yt"


    }

    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.webView)
        if (webView.canGoBack()) {
            webView.goBack()
        } else{
            Toast.makeText(applicationContext, "Exit web page", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }

    }

    // action menu bar initialization
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actionbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    // menu bar button handling
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){

        R.id.menu_back -> {
            onBackPress()
            true
        }
        R.id.menu_refresh -> {
            val webView = findViewById<WebView>(R.id.webView)
            webView.reload()
            true
        }
        R.id.menu_forward -> {
            onForwardPress()
            true
        }
        else -> super.onOptionsItemSelected(item)

    }

    fun onBackPress() {
        val webView = findViewById<WebView>(R.id.webView)
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            Toast.makeText(applicationContext, "Can't go Back!", Toast.LENGTH_SHORT).show()
        }
    }

    fun onForwardPress() {
        val webView = findViewById<WebView>(R.id.webView)
        if (webView.canGoForward()){
            webView.goForward();
        } else {
            Toast.makeText(applicationContext, "Can't go further!", Toast.LENGTH_SHORT).show()
        }
    }


}

