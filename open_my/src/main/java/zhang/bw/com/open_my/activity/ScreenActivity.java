package zhang.bw.com.open_my.activity;

import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.open_my.R;
import zhang.bw.com.open_my.R2;

public class ScreenActivity extends WDActivity {

    @BindView(R2.id.screen_image_back)
    ImageView screenImageBack;
    @BindView(R2.id.light_seekbar)
    SeekBar lightSeekbar;
    private AudioManager mAudioManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_screen;
    }

    @Override
    protected void initView() {
        screenImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        initViews();
        initVolume();
        initLight();
    }

    private void initLight() {
        float currentBright = 0.0f;
        try {
            // 系统亮度值范围：0～255，应用窗口亮度范围：0.0f～1.0f。
            currentBright = Settings.System.getInt(
                    getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS) * 100;
        } catch (Exception e) {
            e.printStackTrace();
        }
        lightSeekbar.setProgress((int) currentBright);
        // 转换成百分比
        //lightTv.setText("当前亮度：" + (int) currentBright + "%");
    }

    private void initVolume() {

    }

    private void initViews() {
        lightSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                WindowManager.LayoutParams lp = getWindow()
                        .getAttributes();
                lp.screenBrightness = Float.valueOf(progress)
                        * (1f / 100f);
                // 调节亮度
                getWindow().setAttributes(lp);
                //lightTv.setText("当前亮度：" + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void destoryData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
