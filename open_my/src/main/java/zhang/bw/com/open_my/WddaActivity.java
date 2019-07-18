package zhang.bw.com.open_my;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.core.WDActivity;

public class WddaActivity extends WDActivity {

    @BindView(R2.id.wdda_bj)
    Button wddaBj;
    @BindView(R2.id.wdda_sc)
    Button wddaSc;
    private PopupWindow window;
    private View inflate;
    private TextView wdda_qu;
    private TextView wdda_que;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdda;
    }

    @Override
    protected void initView() {
        //删除
        wddaSc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inflate = View.inflate(WddaActivity.this,R.layout.wdda_popup, null);
                wdda_qu = inflate.findViewById(R.id.wdda_qu);
                wdda_que = inflate.findViewById(R.id.wdda_que);
                window = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                window.setTouchable(true);
                window.setFocusable(true);
                //window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lan_3087ea)));
                window.showAtLocation(inflate, Gravity.CENTER,0,0);
                window.showAsDropDown(inflate,100,100);

                //点击取消按钮
                wdda_qu.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });

                //点击确认按钮
                wdda_que.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });

            }
        });
        //编辑
        wddaBj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WddaActivity.this, WdbjActivity.class);
                startActivity(intent);
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
