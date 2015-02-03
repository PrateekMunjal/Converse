package com.example.prateek.converse;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import 	android.view.animation.AlphaAnimation;
import 	android.view.animation.AccelerateInterpolator;
import android.view.View.OnLongClickListener;

import static android.widget.AdapterView.*;
import static com.example.prateek.converse.R.*;



public class MainActivity extends ActionBarActivity {

   Switch aSwitch;
    ToggleButton toggleButton;
    Button new_act,slider,volley_btn;
    private LinearLayout.LayoutParams layoutParams ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        final Animation fadeIn = new AlphaAnimation(1,0);
        fadeIn.setInterpolator(new AccelerateInterpolator()); //add this
        fadeIn.setDuration(500);
        slider = (Button)findViewById(id.button2);
        volley_btn = (Button)findViewById(id.volley_btn);
        volley_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Volley_Sample.class));
            }
        });
        slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ImageChoose.class));
                overridePendingTransition(anim.abc_fade_in, anim.abc_fade_out);
            }
        });


        new_act = (Button)findViewById(id.button);
        new_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Prefs.class));
            }
        });

        //Setting flag 0 if last state of toggle was off
        int flag=0;
        final TextView txt = (TextView)findViewById(id.cntr);

        initializers();

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(toggleButton.isPressed())
               {
                   final int sdk = android.os.Build.VERSION.SDK_INT;
                   if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                       toggleButton.setBackgroundDrawable( getResources().getDrawable(R.drawable.bw) );
                   } else {
                       toggleButton.setBackground( getResources().getDrawable(drawable.bw));
                   }

                   Toast.makeText(getApplicationContext(),"Shyd in b/w",Toast.LENGTH_SHORT).show();
               }
                if(isChecked)
                {
                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            int sdk = android.os.Build.VERSION.SDK_INT;
                            {
                                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                    toggleButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.on));
                                } else {
                                    toggleButton.setBackground(getResources().getDrawable(drawable.on));
                                }
                            }
                        }
                    }, 50);

                    Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int sdk = android.os.Build.VERSION.SDK_INT;
                            {
                                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                    toggleButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.off));
                                } else {
                                    toggleButton.setBackground(getResources().getDrawable(drawable.off));
                                }
                            }
                        }
                    },50);
                    Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initializers() {

        toggleButton = (ToggleButton)findViewById(id.toggle_btn);
       // aSwitch = (Switch)findViewById(R.id.swtch);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
