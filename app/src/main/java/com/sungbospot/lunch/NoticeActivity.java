package com.sungbospot.lunch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Locale;

import static com.sungbospot.lunch.MealActivity.isNetwork;

public class NoticeActivity extends AppCompatActivity {
    String url = "http://www.sungbo.hs.kr/70447/subMenu.do";
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        if(isNetwork(getApplicationContext())){
            webView = findViewById(R.id.notice_webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new mWebViewClient());
            webView.loadUrl(url);
        }else{
            Toast.makeText(getApplicationContext(), "네트워크 연결을 확인해주세요", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private class mWebViewClient extends WebViewClient {
        ProgressDialog dialog;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            dialog = new ProgressDialog(NoticeActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("로딩중입니다.");
            dialog.setCancelable(false);
            dialog.show();
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(NoticeActivity.this, String.format(Locale.getDefault(), "%d : 오류가 발생하였습니다.\n%s", errorCode, description), Toast.LENGTH_SHORT).show();
            if (dialog.isShowing()) {
                dialog.cancel();
                finish();
            }
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            if (dialog.isShowing()) {
                view.loadUrl("javascript:(function() { " +
                        "document.getElementById('usrCmntDivDivTop').style.display='none';" +
                        "document.getElementById('top_menu_031_260171').style.display='none';" +
                        "document.getElementById('section_1').style.display='none';" +
                        "document.getElementById('section_2').style.display='none';" +
                        "document.getElementById('section_3').style.display='none';" +
                        "document.getElementById('section_4').style.display='none';" +
                        "document.getElementById('section_5').style.display='none';" +
                        "document.getElementById('cntTitle').style.display='none';" +
                        "document.getElementById('boardMngr_layer').style.display='none';" +
                        "document.getElementById('Footer').style.display='none';" +
                        "})()");
                dialog.cancel();
            }
        }
    }
}