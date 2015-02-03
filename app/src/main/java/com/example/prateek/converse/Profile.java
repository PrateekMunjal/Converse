package com.example.prateek.converse;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;
import android.view.animation.*;


public class Profile extends ActionBarActivity {

    ImageButton img;
    ImageSwitcher imageSwitcher;
    GestureDetectorCompat gs;
    LinearLayout linearLayout;
    int[] images = {R.drawable.blue_simple,R.drawable.splash};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        img = (ImageButton)findViewById(R.id.imageButton1);
        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        linearLayout = (LinearLayout)findViewById(R.id.innerLayout);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.
                        MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                return myView;
            }
        });
        linearLayout.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            @Override
            public void onSwipeRight() {

            }
        });
    }
    public void next(View view)
    {
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        //imageSwitcher.setImageResource(R.drawable.blue_simple);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
