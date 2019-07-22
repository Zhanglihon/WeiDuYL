package zhang.bw.com.open_my;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.WDActivity;

public class WdbjActivity extends WDActivity {

    @BindView(R2.id.wdbj_start_time)
    ImageView wdbjStartTime;
    @BindView(R2.id.wdbj_end_time)
    ImageView wdbjEndTime;
    @BindView(R2.id.gridView)
    GridView gridView;
    @BindView(R2.id.wdbj_text_start)
    TextView wdbjTextStart;
    @BindView(R2.id.wdbj_text_end)
    TextView wdbjTextEnd;
    private TimePickerView pvTime;
    private TimePickerView pvTime1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdbj;
    }

    @Override
    protected void initView() {
        //时间选择器
        //选中事件回调
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                wdbjTextStart.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true,true,true,false,false,false})
                .build();

        //点击选择时间
        wdbjStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
        //时间选择器
        //选中事件回调
        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                wdbjTextEnd.setText(getTime1(date));
            }
        })
                .setType(new boolean[]{true,true,true,false,false,false})
                .build();
        //点击选择时间
        wdbjEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pvTime1.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime1.show();
            }
        });
    }

    private String getTime1(Date date) {
        //可根据需要自行截取数据显示
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private String getTime(Date date) {
        //可根据需要自行截取数据显示
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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
