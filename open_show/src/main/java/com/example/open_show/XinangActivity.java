package com.example.open_show;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.bean.XiangBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.FindDrugsKnowledge;
import zhang.bw.com.common.core.exception.ApiException;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class XinangActivity extends AppCompatActivity {
    private FindDrugsKnowledge findDrugsKnowledge;
    @BindView(R2.id.xiang_name1)
    TextView xiang_name1;
    @BindView(R2.id.xiang_cheng)
    TextView xiang_cheng;
    @BindView(R2.id.xiang_ji)
    TextView xiang_ji;
    @BindView(R2.id.xiang_zhu)
    TextView xiang_zhu;
    @BindView(R2.id.xiang_fa1)
    TextView xiang_fa1;
    @BindView(R2.id.xiang_fa2)
    TextView xiang_fa2;
    @BindView(R2.id.xiang_fa3)
    TextView xiang_fa3;
    @BindView(R2.id.xiang_yao)
    TextView xiang_yao;
    @BindView(R2.id.xiang_bao)
    TextView xiang_bao;
    @BindView(R2.id.xiang_liang)
    TextView xiang_liang;
    @BindView(R2.id.xiang_cang)
    TextView xiang_cang;
    @BindView(R2.id.xiang_yi)
    TextView xiang_yi;
    @BindView(R2.id.xiang_pi)
    TextView xiang_pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinang);
        ButterKnife.bind(this);
        String rr = getIntent().getStringExtra("rr");
        findDrugsKnowledge = new FindDrugsKnowledge(new Backc());
        findDrugsKnowledge.reqeust(rr);
    }
    class Backc implements DataCall<XiangBean>{
        @Override
        public void success(XiangBean data, Object... args) {

            if(data!=null){
                xiang_name1.setText(data.name);
                xiang_cheng.setText(data.component);
                xiang_ji.setText(data.taboo);
                xiang_zhu.setText(data.effect);
                xiang_fa1.setText("1~3岁  10~25斤  "+data.usage);
                xiang_fa2.setText("4~6岁  16~21斤  "+data.usage);
                xiang_fa3.setText("7~9岁  22~27斤  "+data.usage);
                xiang_yao.setText(data.shape);
                xiang_bao.setText(data.packing);
                xiang_liang.setText(data.sideEffects);
                xiang_cang.setText(data.storage);
                xiang_yi.setText(data.mindMatter);
                xiang_pi.setText(data.approvalNumber);

            }

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
