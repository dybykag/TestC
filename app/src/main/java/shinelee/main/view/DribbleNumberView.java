package shinelee.main.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import shinelee.main.R;

/**
 * Created by shine on 2017/4/21.
 */

public class DribbleNumberView extends LinearLayout {
    private final TextView numberView;

    private int strokeWidth = 5;
    private int textColor = Color.WHITE;
    private int strokeColor = Color.parseColor("#4dffffff");
    private int textSize = 20;

    private int paddingLeft = 16;

    public DribbleNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        ImageView imageX = new ImageView(context);
        imageX.setImageResource(R.mipmap.live_gift_plus_x);
        addView(imageX);

        numberView = new TextView(context);
        numberView.setTextSize(textSize);
        numberView.setTypeface(Typeface.DEFAULT_BOLD);
        numberView.setTextColor(textColor);
        numberView.setShadowLayer(strokeWidth, 0, 0, strokeColor);
        numberView.setPadding(16, 0, 0, 0);
        addView(numberView);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }


    public void setNumber(int num) {
        numberView.setText(String.valueOf(num));
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
