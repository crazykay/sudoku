package com.crazykay.sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class MyDialog extends Dialog {

    // 存放自定义的按钮
    public final View button[] = new View[9];
    public final int used[];
    private MyView myView;
    private View cleanButton;

    public MyDialog(Context context, int used[], MyView myView) {
        super(context);
        this.used = used;
        this.myView = myView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("pick a number~");
        setContentView(R.layout.dialog);
        findView();
        for (int i = 0; i < used.length; i++) {
            if (used[i] != 0)
                button[used[i] - 1].setVisibility(View.INVISIBLE);
        }
        setListeners();
    }

    private void setListeners() {
        for (int i = 0; i < button.length; i++) {
            final int fi = i + 1;
            button[i].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    returnResult(fi);
                }
            });
            cleanButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    myView.cleanNumber();
                    dismiss();
                }
            });
        }
        // MyDialog
    }

    // 通知刷新九宫格数据
    protected void returnResult(int fi) {
        myView.setSelectTile(fi);
        dismiss();

    }

    private void findView() {
        button[0] = findViewById(R.id.Button01);
        button[1] = findViewById(R.id.Button02);
        button[2] = findViewById(R.id.Button03);
        button[3] = findViewById(R.id.Button04);
        button[4] = findViewById(R.id.Button05);
        button[5] = findViewById(R.id.Button06);
        button[6] = findViewById(R.id.Button07);
        button[7] = findViewById(R.id.Button08);
        button[8] = findViewById(R.id.Button09);
        cleanButton = findViewById(R.id.cleanButton1);
    }

}
