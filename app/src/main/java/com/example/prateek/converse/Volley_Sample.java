package com.example.prateek.converse;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Volley_Sample extends ActionBarActivity {
    RequestQueue rq;
    ImageLoader imgLoader;
    ProgressDialog progressDialog;
    NetworkImageView networkImageView;
    String link="http://www.bleedred.org/api/bleedred_api/all_api/?format=json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley__sample);
        rq = Volley.newRequestQueue(Volley_Sample.this);
        networkImageView = (NetworkImageView)findViewById(R.id.imgDemo);
        String url="http://www.artistacollection.com/images/detailed/1/CC2100-Red-Color.jpg";

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        imgLoader = new ImageLoader(rq,new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(10);
            @Override
            public Bitmap getBitmap(String s) {
                return cache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                cache.put(s,bitmap);
            }
        });
        networkImageView.setImageUrl(url,imgLoader);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,link,null,
            new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("objects");
                    JSONObject innerObj = jsonArray.getJSONObject(0);
                    Toast.makeText(getApplicationContext(),""+innerObj.getString("userName"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();
                Toast.makeText(Volley_Sample.this,""+volleyError,Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jsonObjectRequest);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_volley__sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
