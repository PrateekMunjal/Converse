package com.example.prateek.converse;

import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ImageAdapter extends BaseAdapter {
    private GridView.LayoutParams layoutParams;
    int old_x,old_y;
    private Context mContext;
    private Integer[] mThumbIds = {R.drawable.splash, R.drawable.blue_simple, R.drawable.front_anant, R.drawable.back_anant};

    public ImageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(185, 185));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(10, 10, 10, 10);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //layoutParams = (GridView.LayoutParams) imageView.getLayoutParams();
               // GridLayout.LayoutParams layoutParams1 = new GridLayout.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT,GridView.LayoutParams.WRAP_CONTENT,(int)event.getX()-(imageView.getWidth()/2),(int)event.getY()-(imageView.getHeight()/2));
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        int x_cord = (int) event.getRawX();
                        int y_cord = (int) event.getRawY();

                        layoutParams.height = 185;
                        layoutParams.width = 185;
                        imageView.setAdjustViewBounds(true);


                        imageView.setLayoutParams(layoutParams);
                        if (x_cord > old_x) {
                            imageView.setX(x_cord - (92));
                            //imageView.setY(y_cord - 92);

                        } else {
                            imageView.setX((92) - x_cord);
                            //imageView.setY(92-y_cord);
                        }
                        old_x = (int) imageView.getX();
                        old_y = (int) imageView.getY();
                        break;

                    default:
                        break;
                }

                return true;
            }
        });
        return imageView;
    }
}
