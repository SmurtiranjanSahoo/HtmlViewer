package gq.trtechlesson.htmlviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.Menu
import android.view.MenuInflater
import android.view.MotionEvent
import android.view.View
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import kotlinx.android.synthetic.main.activity_source.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webAddress = findViewById<EditText>(R.id.web_address_text)
        webAddress.setupClearButtonWithAction()

//        val toolbarMain = findViewById<Toolbar>(R.id.toolbar_main)
//        setSupportActionBar(toolbarMain)
    }

    // Source view button
    fun sourcebutton(view: View) {
        val webAddress = findViewById<EditText>(R.id.web_address_text).editableText.toString();

        if (isvalidUrl()){
            val intent = Intent(this, source::class.java)
            intent.putExtra(web.WEB_ADDRESS, webAddress )
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext,"Enter valid web address",Toast.LENGTH_SHORT).show()
        }
    }

    // Webpage button
    fun webbutton(view: View) {
        val webAddress = findViewById<EditText>(R.id.web_address_text).editableText.toString();

        // TOAST CODE EXAMPLE
  //    Toast.makeText(applicationContext,"Hello $webAddress", Toast.LENGTH_SHORT).show()
        if (isvalidUrl()){
            val intent = Intent(this, web::class.java)
            intent.putExtra(web.WEB_ADDRESS, webAddress )
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext,"Enter valid web address",Toast.LENGTH_SHORT).show()
        }

    }

    // CHECKING FOR VALID URL/ WEB ADDRESS
     fun isvalidUrl(): Boolean{
        val webAddress = findViewById<EditText>(R.id.web_address_text).editableText.toString();
        if(URLUtil.isValidUrl(webAddress) && Patterns.WEB_URL.matcher(webAddress).matches()){

           return true
        } else if(URLUtil.isValidUrl("https://$webAddress") && Patterns.WEB_URL.matcher("https://$webAddress").matches()){
            return true
        }
        return false
    }

    fun EditText.setupClearButtonWithAction() {

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                val clearIcon = if (editable?.isNotEmpty() == true) R.drawable.ic_baseline_clear_24 else 0
                setCompoundDrawablesWithIntrinsicBounds(0, 0, clearIcon, 0)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })

        setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (this.right - this.compoundPaddingRight)) {
                    this.setText("")
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false
        })
    }


}