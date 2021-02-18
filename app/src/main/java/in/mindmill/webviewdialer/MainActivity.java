package in.mindmill.webviewdialer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private static final String HTML = getHtmlContent();



    private static final String TEL_PREFIX = "tel:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wv = (WebView) findViewById(R.id.webview);
        wv.setWebViewClient(new CustomWebViewClient());
        wv.loadDataWithBaseURL(null, HTML, "text/html", "utf-8",null);
       // wv.loadData(HTML, "text/html", "utf-8");
    }

    private class CustomWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView wv, String url) {
            if (url.startsWith(TEL_PREFIX)) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;
            }
            return false;
        }
    }
    private static String getHtmlContent() {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<body>" +
                "<a href='tel:1234567890'>" +
                "<img src=\"file:///android_asset/img/icon_call.png\">" +
                "</a>" +
                "</body>" +
                "</html>";
    }
}