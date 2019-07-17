package zhang.bw.com.open_my;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
import zhang.bw.com.common.util.RealPathFromUriUtils;
import zhang.bw.com.open_my.presenter.MyPresenter;

@Route(path = Constant.ACTIVITY_URL_MY)
public class MyActivity extends WDActivity {

    @BindView(R2.id.aaa)
    RelativeLayout aaa;
    @BindView(R2.id.image_view)
    ImageView imageView;
    @BindView(R2.id.wwww)
    RelativeLayout wwww;
    @BindView(R2.id.wenzhneg)
    TextView wenzhneg;
    @BindView(R2.id.aaaw)
    ImageView aaaw;
    @BindView(R2.id.pppp)
    RelativeLayout pppp;
    @BindView(R2.id.mmmm)
    ImageView mmmm;
    @BindView(R2.id.qqqqqqq)
    RelativeLayout qqqqqqq;
    @BindView(R2.id.nnnnnnnnnnnnn)
    RelativeLayout nnnnnnnnnnnnn;
    @BindView(R2.id.grid_view)
    GridView gridView;
    @BindView(R2.id.bbbbbbbbbbbbbbbbb)
    RelativeLayout bbbbbbbbbbbbbbbbb;
    @BindView(R2.id.wd_image_back)
    ImageView wdImageBack;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void initView() {
        wdImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 1; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            if (i == 1) {
                map.put("ItemImage", R.mipmap.my_icon_file_n);
                map.put("ItemText", "我的档案");

            } else if (i == 2) {
                map.put("ItemImage", R.mipmap.my_icon_wallet_n);
                map.put("ItemText", "我的钱包");

            } else if (i == 3) {
                map.put("ItemImage", R.mipmap.common_button_collection_small_n);
                map.put("ItemText", "我的收藏");

            } else if (i == 4) {
                map.put("ItemImage", R.mipmap.my_icon_advice_n);
                map.put("ItemText", "被采纳建议");

            } else if (i == 5) {
                map.put("ItemImage", R.mipmap.my_icon_video_n);
                map.put("ItemText", "我的视频");

            } else if (i == 6) {
                map.put("ItemImage", R.mipmap.my_icon_circle_n);
                map.put("ItemText", "我的病友圈");

            } else if (i == 7) {
                map.put("ItemImage", R.mipmap.common_icon_attention_small_n);
                map.put("ItemText", "我的关注");

            } else if (i == 8) {
                map.put("ItemImage", R.mipmap.my_icon_task_n);
                map.put("ItemText", "我的任务");

            } else if (i == 9) {
                map.put("ItemImage", R.mipmap.my_icon_set_n);
                map.put("ItemText", "设置管理");

            }
            lstImageItem.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, lstImageItem, R.layout.my_jiugge_layout,
                new String[]{"ItemImage", "ItemText"},
                new int[]{R.id.ItemImage, R.id.ItemText});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int index = i + 1;
                if (index == 1) {
                    Intent intent=new Intent(MyActivity.this,WddaActivity.class);
                    startActivity(intent);
                } else if (index == 2) {
                    // intentByRouter(Constant.ACTIVITY_URL_WALLECT);
                } else if (index == 3) {
                    //intentByRouter(Constant.ACTIVITY_URL_COLLECT);
                } else if (index == 4) {
                    //intentByRouter(Constant.ACTIVITY_URL_SUGGEST);
                } else if (index == 5) {
                    //intentByRouter(Constant.ACTIVITY_URL_VIDEO);
                } else if (index == 6) {
                    //intentByRouter(Constant.ACTIVITY_URL_CIRCLE);
                } else if (index == 7) {
                    // intentByRouter(Constant.ACTIVITY_URL_ATTENTION);
                } else if (index == 8) {
                    // intentByRouter(Constant.ACTIVITY_URL_TASK);
                } else if (index == 9) {
                    Intent intent=new Intent(MyActivity.this,WdszActivity.class);
                    startActivity(intent);
                }
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
