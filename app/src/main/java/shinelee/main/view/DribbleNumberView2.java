package shinelee.main.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import shinelee.main.util.ScreenUtils;

/**
 * Created by shine on 2017/4/21.
 */

public class DribbleNumberView2 extends View {
    private final String X = "x";
    private int numMarginLeft = 0;
    private int strokeWidth = 5;
    private int textColor = Color.WHITE;
    private int strokeColor = Color.WHITE;
    private int textSize = ScreenUtils.sp2px(20);

    private String numStr;
    private Paint textPaint;
    private Paint strokePaint;
    private int numX;
    private int numY;
    private int viewHeight;
    private Rect rect = new Rect();
    private Rect numRect = new Rect();
    private int viewWidth;

    private int xx;
    private int xy;

    public DribbleNumberView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        strokePaint = new Paint();
        strokePaint.setColor(strokeColor);
        // strokePaint.setTextAlign(Paint.Align.CENTER);
        strokePaint.setTextSize(textSize);
        strokePaint.setTypeface(Typeface.DEFAULT_BOLD);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setAntiAlias(true);
        strokePaint.setAlpha(76);
        strokePaint.setStrokeJoin(Paint.Join.ROUND);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        //    textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (TextUtils.isEmpty(numStr)) {
            return;
        }

        canvas.drawText(X, xx, xy, strokePaint);
        canvas.drawText(X, xx, xy, textPaint);


        canvas.drawText(numStr, numX, numY, strokePaint);
        canvas.drawText(numStr, numX, numY, textPaint);
    }

    public void setNumber(int num) {
        this.numStr = String.valueOf(num);

        xx = getPaddingLeft();

        textPaint.getTextBounds(X, 0, 1, rect);
        numX = xx + rect.width() + numMarginLeft;
        int xHeight = rect.height();

        textPaint.getTextBounds(numStr, 0, numStr.length(), numRect);

        Log.d("lll", "width:" + textPaint.measureText(numStr));

        numY = numRect.height() + strokeWidth + getPaddingTop();
        viewWidth = numX + numRect.width() + strokeWidth + getPaddingLeft() + getPaddingRight();
        viewHeight = numRect.height() + strokeWidth * 2 + getPaddingTop() + getPaddingBottom();

        xy = xHeight + (viewHeight - xHeight) / 2;

        requestLayout();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (TextUtils.isEmpty(numStr)) {
            return;
        }

        int widthSpec, heightSpec;
        widthSpec = MeasureSpec.makeMeasureSpec(viewWidth, MeasureSpec.EXACTLY);
        heightSpec = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY);
        setMeasuredDimension(widthSpec, heightSpec);
    }
}
