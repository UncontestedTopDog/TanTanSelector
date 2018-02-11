package com.example.administrator.tantanselector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * @author yuqirong
 */

public class RoundImageView extends ImageView {

    private Path mPath;
    private RectF mRectF;
    private float[] rids = new float[8];
    private PaintFlagsDrawFilter paintFlagsDrawFilter;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rids[0] = 20;
        rids[1] = 20;
        rids[2] = 20;
        rids[3] = 20;
        rids[4] = 0f;
        rids[5] = 0f;
        rids[6] = 0f;
        rids[7] = 0f;
        mPath = new Path();
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        mPath.addRoundRect(mRectF, rids, Path.Direction.CW);
        canvas.setDrawFilter(paintFlagsDrawFilter);
        canvas.save();
        canvas.clipPath(mPath);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(0, 0, w, h);
    }

}
