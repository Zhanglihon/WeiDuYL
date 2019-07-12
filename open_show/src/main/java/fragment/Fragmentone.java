package fragment;

import android.text.TextPaint;
import android.widget.TextView;

import com.example.open_show.R;
import com.example.open_show.R2;

import butterknife.BindView;
import zhang.bw.com.common.core.WDFragment;

public class Fragmentone extends WDFragment {
    @BindView(R2.id.one_text1)
    TextView one_text1;
    @Override
    protected int getLayoutId() {
        return R.layout.fragmentone;
    }

    @Override
    protected void initView() {
        TextPaint paint = one_text1.getPaint();
        paint.setFakeBoldText(true);

    }
}
