package com.crazykay.sudoku;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
