package com.example.prateek.converse;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;


public class ImageChoose extends ActionBarActivity{

    ImageView img;
    ImageLoader imgLoader;
    NetworkImageView networkImageView;
    ProgressDialog progressDialog;
    RequestQueue rq;
    String[] linksofpic={
                        "http://www.petaindia.com/wp-content/uploads/2014/12/narendra-modi.jpg",
                        "http://hdwallpaperswala.com/wp-content/uploads/2013/12/arvind_kejriwal_wallpaper_for_computer.jpg",
                        "http://www.indianlibertarians.org/wp-content/uploads/2014/12/kiran-bedi.jpg",
                        "http://upload.wikimedia.org/wikipedia/commons/3/3e/Rahul_Gandhi_1.jpg"
                        };
    Bitmap add_image;
    int[] tochooseImages = {R.id.srk_id,R.id.sallu_id,R.id.amir_id,R.id.aish_id};//id's of network image view
    int[] freeplaces={0,0,0,0};
    int[] camefrom = {5,5,5,5};
    int[] R_id = {R.id.image_1, R.id.image_2, R.id.image_3, R.id.image_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_choose);
        initializeVars();

        progressDialog.setMessage("Loading Pictures");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        for(int i=0;i<tochooseImages.length;i++)
        {
            networkImageView = (NetworkImageView)findViewById(tochooseImages[i]);
            final int finalI = i;
            imgLoader = new ImageLoader(rq,new ImageLoader.ImageCache() {
                private final LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(10);
                @Override
                public Bitmap getBitmap(String s) {
                    return cache.get(s);
                }

                @Override
                public void putBitmap(String s, Bitmap bitmap) {
                    cache.put(s,bitmap);
                    if(finalI == tochooseImages.length-1)
                    {
                        progressDialog.dismiss();
                    }
                }
            });
            networkImageView.setImageUrl(linksofpic[i], imgLoader);
        }

        for (int i = 0; i < R_id.length; i++) {
            img = (ImageView) findViewById(R_id[i]);
            img.setImageBitmap(getRoundedBitmap(add_image, 400, Color.WHITE));
            img.setAdjustViewBounds(true);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(8, 8, 8, 8);
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(), "" + ((Integer) v.getTag()).toString(), Toast.LENGTH_SHORT).show();
                    ImageView image;
                    if(!(camefrom[(Integer)v.getTag()]==5)) {
                        image = (ImageView) findViewById(tochooseImages[camefrom[(Integer) v.getTag()]]);
                        image.setVisibility(View.VISIBLE);
                        image = (ImageView) findViewById(R_id[(Integer) v.getTag()]);
                        image.setImageDrawable(getResources().getDrawable(R.drawable.add_image));
                        freeplaces[(Integer) v.getTag()] = 0;
                        camefrom[(Integer) v.getTag()] = 5;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"First Set the Image",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        settingonclicklistener();

    }

    private void settingonclicklistener() {

        NetworkImageView imag;

        for(int j=0;j<tochooseImages.length;j++) {
            imag = (NetworkImageView) findViewById(tochooseImages[j]);
            imag.setTag(j);
            final NetworkImageView finalImag = imag;
            final NetworkImageView finalImag1 = imag;
            final int finalJ = j;
            imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(ImageChoosethis,""+v.getId(),Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < freeplaces.length; i++) {
                        if (freeplaces[i] == 0) {
                            ImageView imageView = (ImageView) findViewById(R_id[i]);
                            switch (R_id[i])
                            {
                                case R.id.image_1:camefrom[i]= finalJ;
                                    break;
                                case R.id.image_2:camefrom[i]= finalJ;
                                    break;
                                case R.id.image_3:camefrom[i]=finalJ;
                                    break;
                                case R.id.image_4:camefrom[i]=finalJ;
                                    break;
                            }
                            imageView.setImageDrawable(finalImag.getDrawable());
                            freeplaces[i] = 1;
                            finalImag1.setVisibility(View.INVISIBLE);
                            break;
                        } else {
                            if (i == 3) {
                                Toast.makeText(ImageChoose.this, "Not Possible", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
    }

    public static Bitmap getRoundedBitmap(Bitmap bitmap, int pixels, int color) {

        Bitmap inpBitmap = bitmap;
        int width = 0;
        int height = 0;
        width = inpBitmap.getWidth();
        height = inpBitmap.getHeight();

        if (width <= height) {
            height = width;
        } else {
            width = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(inpBitmap, rect, rect, paint);

        return output;
    }

    private void initializeVars() {

        add_image = BitmapFactory.decodeResource(ImageChoose.this.getResources(), R.drawable.add_image);
        rq = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_choose, menu);
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
