package zhang.bw.com.open_main;

import androidx.appcompat.app.AppCompatActivity;
import zhang.bw.com.common.util.Constant;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = Constant.ACTIVITY_URL_MAIN)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
