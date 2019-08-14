package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.open_inquiry.R;
import com.example.open_inquiry.R2;

public class MyPingActivity1 extends AppCompatActivity {
    @BindView(R2.id.ping_fan)
    TextView ping_fan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ping1);
        ButterKnife.bind(this);
        ping_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
