package id.my.nutrikita.ui.newsview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import id.my.nutrikita.R
import id.my.nutrikita.databinding.ActivityNewsViewBinding

class NewsViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(EXTRA_NEWS_URL)

        val webView = binding.webView
        if (url != null) {
            webView.loadUrl(url)
        }
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Toast.makeText(
                    this@NewsViewActivity,
                    getString(R.string.web_success_announce), Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        const val EXTRA_NEWS_URL = "extra news url"
    }
}