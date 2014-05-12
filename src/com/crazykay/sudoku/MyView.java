package com.crazykay.sudoku;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    int selectX;
    int selectY;
    private float width;
    private float hight;
    private int times = 0;
    private Game game = new Game();

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        this.width = w / 9f;
        this.hight = h / 9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画背景
        Paint backgroungPaint = new Paint();
        backgroungPaint.setColor(getResources().getColor(R.color.gainsboro));
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroungPaint);
        // 深色线
        Paint darkPaint = new Paint();
        darkPaint.setColor(getResources().getColor(R.color.dimgray));
        // 高亮线
        Paint highlightpPaint = new Paint();
        highlightpPaint.setColor(getResources().getColor(R.color.ivory));
        // 亮线
        Paint lightPaint = new Paint();
        lightPaint.setColor(getResources().getColor(R.color.gainsboro));

        for (int i = 1; i < 9; i++) {
            canvas.drawLine(0, i * hight, getWidth(), i * hight, lightPaint);
            canvas.drawLine(0, i * hight + 1, getWidth(), i * hight + 1,
                    highlightpPaint);
            canvas.drawLine(i * width, 0, i * width, getHeight(), lightPaint);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(),
                    highlightpPaint);
        }
        for (int i = 1; i < 9; i++) {
            if (i % 3 == 0) {
                canvas.drawLine(0, i * hight, getWidth(), i * hight, darkPaint);
                canvas.drawLine(0, i * hight + 1, getWidth(), i * hight + 1,
                        darkPaint);
                canvas.drawLine(i * width, 0, i * width, getHeight(), darkPaint);
                canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(),
                        darkPaint);
            }
        }
        // 绘数字
        Paint numberPaint1 = new Paint();
        numberPaint1.setStyle(Paint.Style.STROKE);
        numberPaint1.setTextSize(hight * 0.75f);
        numberPaint1.setAntiAlias(true);
        numberPaint1.setTextAlign(Paint.Align.CENTER);
        numberPaint1.setColor(Color.BLACK);

        Paint numberPaint2 = new Paint();
        numberPaint2.setStyle(Paint.Style.STROKE);
        numberPaint2.setTextSize(hight * 0.75f);
        numberPaint2.setAntiAlias(true);
        numberPaint2.setTextAlign(Paint.Align.CENTER);
        numberPaint2.setColor(Color.RED);

        FontMetrics fontMetrics = numberPaint1.getFontMetrics();

        float x = width / 2;
        float y = hight / 2 - (fontMetrics.ascent + fontMetrics.descent) / 2;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                canvas.drawText(game.getTitleString(i, j), i * width + x, j
                        * hight + y, numberPaint1);
                canvas.drawText(game.getFinalTitleString(i, j), i * width + x, j
                        * hight + y, numberPaint2);
            }
        }
        times++;
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);
        selectX = (int) (event.getX() / width);
        selectY = (int) (event.getY() / hight);

        int used[] = game.getUsedTilesByCoor(selectX, selectY);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < used.length; i++) {
            sb.append(used[i]);
        }
        if (!game.ifFinal(selectX, selectY)) {
            MyDialog myDialog = new MyDialog(getContext(), used, this);

            myDialog.show();

        }
        return super.onTouchEvent(event);
    }

    public void setSelectTile(int fi) {
        if (game.setTileIfValid(selectX, selectY, fi))
            ;
        invalidate();

    }

    public void cleanNumber() {
        if (game.setClean(selectX, selectY))
            invalidate();

    }

}

