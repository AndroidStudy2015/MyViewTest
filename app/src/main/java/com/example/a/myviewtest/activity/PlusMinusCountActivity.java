package com.example.a.myviewtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.PlusMinusCount;

public class PlusMinusCountActivity extends AppCompatActivity {

    private PlusMinusCount mPlusMinusCount;
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_minus_count);
        mPlusMinusCount = (PlusMinusCount) findViewById(R.id.plus_minus_Count);
        mPlusMinusCount.setmPlusMinusCountClickListener(new PlusMinusCount.PlusMinusCountClickListener() {
            @Override
            public void plusClick() {
//                Toast.makeText(PlusMinusCountActivity.this, "加号按钮被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void minusClick() {
//                Toast.makeText(PlusMinusCountActivity.this, "减号按钮被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        mBt = (Button) findViewById(R.id.bt);

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlusMinusCountActivity.this, mPlusMinusCount.getCount() + "", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
