package cn.luozhanming.github.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import cn.luozhanming.github.R
import cn.luozhanming.library.base.BaseActivity
import kotlinx.android.synthetic.main.activity_oauth_web.*

class OAuthWebActivity : BaseActivity() {


    private lateinit var mWebView: WebView
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oauth_web)
        initView()
    }

    override fun initView() {
        mProgressBar = findViewById(R.id.progressBar)
        mWebView = findViewById(R.id.webView)
        initWebView()
    }

    private fun initWebView() {
        val settings = mWebView.settings
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        settings.javaScriptEnabled = true
        //设置自适应屏幕，两者合用
        settings.useWideViewPort = true//将图片调整到适合webview的大小
        settings.loadWithOverviewMode = true// 缩放至屏幕的大小
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN//支持内容重新布局
        settings.setSupportZoom(false)  //不支持缩放
        settings.domStorageEnabled = true  //允许dom存储
        settings.cacheMode = WebSettings.LOAD_NO_CACHE//不需要缓存
        settings.allowFileAccess = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.allowFileAccessFromFileURLs = false
        }
        webView.webViewClient = getWebViewClient()
        webView.webChromeClient = getChromeClient()
    }

    private fun getChromeClient(): WebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            if (newProgress >= 95) {
                mProgressBar.visibility = View.GONE
            } else {
                mProgressBar.visibility = View.VISIBLE
                mProgressBar.progress = newProgress
            }
        }
    }

    private fun getWebViewClient(): WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.url?.apply {
                if (this.contains("http://luozhanming.com")) {
                    val code = parseCodeUrl(this)
                    if (!TextUtils.isEmpty(code)) {  //不为空的话返回code
                        val data = Intent()
                        data.putExtra("cdoe", code)
                        setResult(RESULT_OK, data)
                        finish()
                        //TODO 返回登录界面并调用获取accesstoken
                    } else {
                        //TODO 返回登录界面并提示登录失败
                    }
                    return false
                }
            }
            return false
        }
    }


    /**
     * 解析带code参数的URL
     * @return code
     * */
    private fun parseCodeUrl(url: String): String? {
        val query = url.substring(url.indexOf("?") + 1)
        if (!TextUtils.isEmpty(query)) {
            query.split("&").forEach {
                val split = it.split("=")
                when (split[0]) {
                    "code" -> return@parseCodeUrl split[1]
                }
            }
        }
        return null
    }
}