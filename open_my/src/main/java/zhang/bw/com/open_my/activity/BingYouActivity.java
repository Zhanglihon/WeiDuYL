package zhang.bw.com.open_my.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;

public class BingYouActivity extends AppCompatActivity {

    @BindView(R2.id.binyou_image_back)
    ImageView binyouImageBack;
    @BindView(R2.id.binyou_recyclerview)
    RecyclerView binyouRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_you);
        ButterKnife.bind(this);
    }
}
