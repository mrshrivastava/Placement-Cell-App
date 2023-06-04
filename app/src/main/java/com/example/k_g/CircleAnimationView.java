<<<<<<< HEAD
package com.example.k_g;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleAnimationView  extends View{

    private Paint paint;
    private float radius;
    private float strokeWidth;
    private float startAngle;
    private float sweepAngle;

    public CircleAnimationView(Context context) {
        super(context);
        init();
    }

    public CircleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.rgb(187,184,252));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setAntiAlias(true);
        strokeWidth = paint.getStrokeWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius,
                startAngle, sweepAngle, false, paint);

    }

    public void animateCircle(float value) {
        float startValue = 0f;
        float endValue = 360f;

        ValueAnimator animator = ValueAnimator.ofFloat(startValue, endValue);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (float) animation.getAnimatedValue();
                radius = (Math.min(getWidth(), getHeight()) - strokeWidth) / 2f;
                startAngle = -90f; // Adjust the start angle as per your requirement
                invalidate();
            }
        });
        animator.start();
    }
}
=======
package com.example.k_g;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleAnimationView  extends View{

    private Paint paint;
    private float radius;
    private float strokeWidth;
    private float startAngle;
    private float sweepAngle;

    public CircleAnimationView(Context context) {
        super(context);
        init();
    }

    public CircleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.rgb(187,184,252));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setAntiAlias(true);
        strokeWidth = paint.getStrokeWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius,
                startAngle, sweepAngle, false, paint);

    }

    public void animateCircle(float value) {
        float startValue = 0f;
        float endValue = 360f;

        ValueAnimator animator = ValueAnimator.ofFloat(startValue, endValue);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (float) animation.getAnimatedValue();
                radius = (Math.min(getWidth(), getHeight()) - strokeWidth) / 2f;
                startAngle = -90f; // Adjust the start angle as per your requirement
                invalidate();
            }
        });
        animator.start();
    }
}


>>>>>>> origin/master
