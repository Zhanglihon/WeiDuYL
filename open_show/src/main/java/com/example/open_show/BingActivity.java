package com.example.open_show;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.bean.BingZeng;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDiseaseKnowledge;
import zhang.bw.com.common.core.exception.ApiException;

public class BingActivity extends AppCompatActivity {
    @BindView(R2.id.bing_name1)
    TextView bing_name1;
    @BindView(R2.id.bing_li)
    TextView bing_li;
    @BindView(R2.id.bing_zhuang)
    TextView bing_zhuang;
    @BindView(R2.id.bing_ji)
    TextView bing_ji;
    @BindView(R2.id.bing_xi)
    TextView bing_xi;
    @BindView(R2.id.bing_zi)
    TextView bing_zi;
    private FindDiseaseKnowledge findDiseaseKnowledge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("ll");
        String name = getIntent().getStringExtra("gg");
        bing_name1.setText(name);
        findDiseaseKnowledge = new FindDiseaseKnowledge(new Backn());
        findDiseaseKnowledge.reqeust(id);
    }
    class Backn implements DataCall<BingZeng> {
        @Override
        public void success(BingZeng data, Object... args) {
            if(data!=null){
                bing_li.setText(data.pathology);
                bing_zhuang.setText(data.symptom);
                bing_ji.setText(data.benefitTaboo);
                bing_xi.setText(data.westernMedicineTreatment);
                bing_zi.setText(data.chineseMedicineTreatment);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
