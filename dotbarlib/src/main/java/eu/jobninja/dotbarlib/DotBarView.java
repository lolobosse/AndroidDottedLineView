package eu.jobninja.dotbarlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by laurentmeyer on 27/04/16.
 */
public class DotBarView extends View {

    int numberOfDots = 4;
    int currentStatus = 2;
    int sideMargin = 20;
    int[] colorArray;
    private Paint[] paints;
    boolean emptyOption = true;
    int colorEmpty;
    private Paint emptyPaint;
    private int dotRadius = 13;
    private int barSize = 5;


    public DotBarView(Context context) {
        super(context);
        init();
    }

    public DotBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        int[] colors = {Color.BLUE, Color.RED, Color.CYAN, Color.MAGENTA};
        int colorEmpty = Color.LTGRAY;
        setColorEmpty(colorEmpty);
        setColorArray(colors);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, dotRadius * 4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        double yOfAllDots = getMeasuredHeight() / 2;
        double separationOfAllDots = (getMeasuredWidth() - 2 * sideMargin) / numberOfDots;
        int radius = dotRadius;
        double startLeft = separationOfAllDots / 2;

        if (emptyOption){
            for (int i = currentStatus; i < numberOfDots; i++) {
                float circleCenterX = (float) (startLeft + separationOfAllDots * i + radius);
                canvas.drawCircle(circleCenterX, (float) yOfAllDots, radius, emptyPaint);
            }

            canvas.drawRect((float) (startLeft + separationOfAllDots * (currentStatus-1)+ radius), (float)yOfAllDots - barSize/2, (float) (startLeft + separationOfAllDots * (numberOfDots-1)  + radius) ,(float)yOfAllDots +barSize/2, emptyPaint);
        }

        for (int i = 0; i < currentStatus; i++) {
            float circleCenterX = (float) (startLeft + separationOfAllDots * i + radius);
            canvas.drawCircle(circleCenterX, (float) yOfAllDots, radius, paints[currentStatus -1]);
        }

        canvas.drawRect((float) (startLeft + radius), (float)yOfAllDots - barSize/2, (float) (startLeft + separationOfAllDots * (currentStatus-1)  + radius), (float)yOfAllDots + barSize/2, paints[currentStatus-1]);
    }

    public int getNumberOfDots() {
        return numberOfDots;
    }

    public void setNumberOfDots(int numberOfDots) {
        this.numberOfDots = numberOfDots;
        invalidate();
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
        invalidate();
    }

    public void setColorArray(int[] colorArray) {
        if (colorArray.length != numberOfDots) {
            throw new IllegalArgumentException("Not matching the number of dots");
        }
        paints = new Paint[colorArray.length];
        for (int i = 0; i < colorArray.length; i++) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(colorArray[i]);
            paints[i] = paint;
        }
        this.colorArray = colorArray;
    }

    public void setEmptyOption(boolean emptyOption) {
        this.emptyOption = emptyOption;
        invalidate();
    }

    public void setColorEmpty(int colorEmpty) {
        this.colorEmpty = colorEmpty;
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(colorEmpty);
        this.emptyPaint = p;
        invalidate();
    }

    public void setDotRadius(int dotRadius) {
        this.dotRadius = dotRadius;
        invalidate();
    }

    public void setBarSize(int barSize) {
        this.barSize = barSize;
        invalidate();
    }
}
