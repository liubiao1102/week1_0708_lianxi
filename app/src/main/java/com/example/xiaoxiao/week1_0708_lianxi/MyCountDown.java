package com.example.xiaoxiao.week1_0708_lianxi;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyCountDown extends View {

    private int time;
    private float radius;
    private int textcolor;
    private int bgcolor;
    private int cx;
    private int cy;
    private Paint paint;
    private Handler handler;

    public MyCountDown(Context context) {
        super(context);
        init(context,null);
    }



    public MyCountDown(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyCountDown(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //初始化属性
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyCountDown);
            time = array.getInteger(R.styleable.MyCountDown_CountDown_time,3);
            radius = array.getDimension(R.styleable.MyCountDown_CountDown_radius, 25);
            textcolor = array.getColor(R.styleable.MyCountDown_CountDown_textcolor, Color.BLACK);
            bgcolor = array.getColor(R.styleable.MyCountDown_CountDown_bgcolor, Color.WHITE);
            //释放
            array.recycle();

        }else{
            time=3;
            radius=30;
            textcolor = Color.BLACK;
            bgcolor = Color.WHITE;
        }


        //创建画笔
        paint = new Paint();
        //设置画笔属性
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cx = w/2;
        cy = h / 2;
    }

    //画布
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Path path = new Path();
        //画圆
        path.addCircle(cx,cy,radius,Path.Direction.CW);
        canvas.drawPath(path, paint);
        //画字
        paint.setColor(textcolor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(18);
        canvas.drawText(""+time,cx,cy,paint);
    }

    public void start(final MainActivity mainActivity, final Class<OtherActivity> otherActivityClass) {
        //条件结束
//跳转到activity
//刷新页面
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //条件结束
                if (time == 0) {
                    //跳转到activity
                    Intent intent = new Intent(mainActivity, otherActivityClass);
                    mainActivity.startActivity(intent);
                    mainActivity.finish();

                }else{
                    time--;
                    //刷新页面
                    setTime( time);
                    sendEmptyMessageDelayed(1, 1000);
                }
            }
        };
        handler.sendEmptyMessageDelayed(1, 1000);
    }
    public void  setTime(int time){
        invalidate();
    }
    //停止倒计时
    public void stop() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (myLinstern != null) {
                myLinstern.click(this);
            }
        }
        return super.onTouchEvent(event);
    }

    //设置点击事件借口回调
    public  interface MyLinstern{
        void click(View view);
    }


    public void setMyLinstern(MyLinstern myLinstern) {
        this.myLinstern = myLinstern;
    }

    private MyLinstern myLinstern;



}
