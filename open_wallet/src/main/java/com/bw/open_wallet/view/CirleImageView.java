package com.bw.open_wallet.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.appcompat.widget.AppCompatImageView;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/6/24 9:43
 * @Description：描述信息
 */
//@Route(path = Constant.ACTIVITY_URL_CIRLE)
public class CirleImageView extends AppCompatImageView {

    private Paint paint;
    private Matrix matrix;
    private float width;
    private float height;
    private float rsdwsd;

    public CirleImageView(Context context) {
        this(context,null);
    }

    public CirleImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CirleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        rsdwsd = Math.min(width, height) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable =getDrawable();
        if (drawable==null){
            super.onDraw(canvas);
            return;
        }
        if(drawable instanceof BitmapDrawable){
            paint.setShader(initBitmapShader((BitmapDrawable) drawable));
            canvas.drawCircle(width/2,height/2,rsdwsd,paint);
            return;
        }
        super.onDraw(canvas);
    }
    private BitmapShader initBitmapShader(BitmapDrawable drawable){
        Bitmap bitmap= drawable.getBitmap();
        BitmapShader bitmapShader = new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        float max = Math.max(width / bitmap.getWidth(), height / bitmap.getWidth());
        matrix.setScale(max,max);
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }
}
