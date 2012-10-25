package com.example.theNewBoston;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ourViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		//return super.shouldOverrideUrlLoading(view, url);
		return true;
	}

}
