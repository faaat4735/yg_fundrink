package com.yg.fundrink.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.yg.fundrink.R;

public class WebActivity extends AppCompatActivity {

    private static final String TAG = "webactivity";
    private static final String TITLE = "title";

    private static final String URL = "url";

    /**
     * 标题栏
     */
    private TextView tvTitle;

    /**
     * 返回按钮
     */
    private ImageView ivBack;

    private WebView mWebView;

    private String title;

    private String url;

    public static void launch(Activity activity, String title, String url) {
        Log.e(TAG, "launch");
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(URL, url);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initEvent();
    }


    private void initView() {

        setStatusBarColorPrimary();
        mWebView = findViewById(R.id.webView);
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);

        title = getIntent().getStringExtra(TITLE);
        tvTitle.setText(TextUtils.isEmpty(title) ? "活动" : title);
        url = getIntent().getStringExtra(URL);

        if (url == null) {
            //ToastUtils.show("链接为空");
            Log.e("WebActivity", "链接为空");
            return;
        }
        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                if (tvTitle.getText().toString().equals("活动")) {
//                    tvTitle.setText(view.getTitle());
//                }
//            }
//        });
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(url);
    }


    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                // H5中包含下载链接的话让外部浏览器去处理
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    /**
     * 设置状态栏主题色
     */
    protected void setStatusBarColorPrimary() {
        ImmersionBar
                .with(this)
                .statusBarColor(R.color.colorPrimary)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 设置状态栏白色
     */
    protected void setStatusBarColorWhite() {
        ImmersionBar
                .with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 设置状态栏透明并且全屏
     */
    protected void setStatusBarTranParent() {
        ImmersionBar
                .with(this)
                .transparentStatusBar()
                .statusBarDarkFont(true)
                .init();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.e(TAG, "onResume");
//        mWebView.onResume();
//        mWebView.resumeTimers();
//    }

}
