package com.lge.loadon;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String item_selecionado = "timeline";
    private String ip_server = "";

    IPHelper ipHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ipHelper = new IPHelper(getBaseContext());

        Cursor cursor = ipHelper.getWritableDatabase().rawQuery("select valor from ips where id = 0",null);
        if(cursor.moveToNext()){
            this.ip_server = cursor.getString(0);
        }

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://" + this.ip_server + ":85/QSmart/LoadON/timeline.php");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        hideBar();
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkConnection();
                h.postDelayed(this, 60000);
            }
        }, 0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = ipHelper.getWritableDatabase().rawQuery("select valor from ips where id = 0",null);
        if(cursor.moveToNext()){
            this.ip_server = cursor.getString(0);
        }
        checkConnection();
        hideBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Cursor cursor = ipHelper.getWritableDatabase().rawQuery("select valor from ips where id = 0",null);
        if(cursor.moveToNext()){
            this.ip_server = cursor.getString(0);
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_timeline) {
            item_selecionado = "timeline";
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("http://" + this.ip_server + ":85/QSmart/LoadON/timeline.php");
        } else if (id == R.id.action_lotes) {
            item_selecionado = "lotes";
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("http://" + this.ip_server + ":85/QSmart/LoadON/lotes.php");
        } else if (id == R.id.action_tempo) {
            item_selecionado = "tempo";
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("http://" + this.ip_server + ":85/QSmart/LoadON/tempo.php");
        }else if (id == R.id.action_load) {
            item_selecionado = "load";
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("http://" + this.ip_server + ":85/QSmart/LoadON/load.php");
        }else if (id == R.id.action_ip) {
            Intent intent =  new Intent(this,IPActivity.class);
            startActivity(intent);

            cursor = ipHelper.getWritableDatabase().rawQuery("select valor from ips where id = 0",null);
            if(cursor.moveToNext()){
                this.ip_server = cursor.getString(0);
            }
        }

        this.hideBar();
        return super.onOptionsItemSelected(item);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void checkConnection(){
        if(isOnline()){
            if (item_selecionado.equals("timeline")) {
                item_selecionado = "timeline";
                WebView myWebView = (WebView) findViewById(R.id.webview);
                myWebView.loadUrl("http://" + this.ip_server + ":85/QSmart/LoadON/timeline.php");
            } else if (item_selecionado.equals("lotes")) {
                item_selecionado = "lotes";
                WebView myWebView = (WebView) findViewById(R.id.webview);
                myWebView.loadUrl("http://" + this.ip_server + ":85/QSmart/LoadON/lotes.php");
            } else if (item_selecionado.equals("tempo")) {
                item_selecionado = "tempo";
                WebView myWebView = (WebView) findViewById(R.id.webview);
                myWebView.loadUrl("http://" + this.ip_server + ":85/QSmart/LoadON/tempo.php");
            }else if (item_selecionado.equals("load")) {
                item_selecionado = "load";
                WebView myWebView = (WebView) findViewById(R.id.webview);
                myWebView.loadUrl("http://" + this.ip_server +":85/QSmart/LoadON/load.php");
            }
        }else{
            Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
        }
    }

    protected void hideBar(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
