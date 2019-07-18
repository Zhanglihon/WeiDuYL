package zhang.bw.com.open_my;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.CXBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.RealPathFromUriUtils;
import zhang.bw.com.open_my.presenter.MyPresenter;
import zhang.bw.com.open_my.presenter.WdxxPresenter;

public class WdxxActivity extends WDActivity {
    @BindView(R2.id.wdxx_image_tx)
    ImageView wdxxImageTx;
    private TextView my_pop_xc;
    private TextView my_pop_xj;
    private PopupWindow popupWindow;
    private String path = Environment.getExternalStorageDirectory() + "/hh.jpg";
    private MyPresenter myPresenter;
    private LoginBean loginBean;
    private WdxxPresenter wdxxPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wdxx;
    }
    @Override
    protected void initView() {
        loginBean = DaoMaster.newDevSession(WdxxActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        myPresenter = new MyPresenter(new Txsc());
        //加载popupwindow的子布局
        View view = View.inflate(WdxxActivity.this, R.layout.my_popupwindow, null);
        my_pop_xc = view.findViewById(R.id.my_pop_xc);
        my_pop_xj = view.findViewById(R.id.my_pop_xj);
        //创建popupwindow
        //contentView, 布局   width, 宽    height 高
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置背景
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);
        //设置焦点
        popupWindow.setFocusable(true);
        //设置可触摸
        popupWindow.setTouchable(true);
        //相机
        my_pop_xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断版本是否6.0以上
                if (Build.VERSION.SDK_INT >= 23) {
                    int permission = ContextCompat.checkSelfPermission(WdxxActivity.this.getApplicationContext(), Manifest.permission.CAMERA);
                    if (permission == PackageManager.PERMISSION_GRANTED) {
                        //如果有了相机的权限就调用相机
                        startCamera();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WdxxActivity.this);
                        builder.setTitle("提示");
                        builder.setMessage("是否开启相机权限?");
                        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //去请求相机权限
                                ActivityCompat.requestPermissions(WdxxActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
                            }
                        });
                        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(WdxxActivity.this, "您拒绝了开启相机权限", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                } else {
                    //不是6.0以上版本直接调用相机
                    startCamera();
                }

            }
        });
        //相册
        my_pop_xc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
            }
        });
        wdxxImageTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据父窗体X Y轴的偏移改变位置
                popupWindow.showAtLocation(View.inflate(WdxxActivity.this, R.layout.activity_wdxx, null), Gravity.CENTER, 0, 600);
            }
        });
    }

    private void startCamera() {
        //创建拍照的隐式意图对象
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //把拍完的照片,输出到指定路径上
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
        //开启页面
        startActivityForResult(intent, 100);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //取出拍照的照片
            Uri uri = Uri.fromFile(new File(path));
            File file=new File(path);
            wdxxImageTx.setImageURI(uri);
            Glide.with(WdxxActivity.this).load(uri).apply(RequestOptions.circleCropTransform()).into(wdxxImageTx);
            myPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),file);
        }
        if (requestCode==200){
            String realPathFromUri=RealPathFromUriUtils.getRealPathFromUri(WdxxActivity.this,data.getData());
            File filea=new File(realPathFromUri);
            Uri uri = data.getData();
            wdxxImageTx.setImageURI(uri);
            Glide.with(WdxxActivity.this).load(uri).apply(RequestOptions.circleCropTransform()).into(wdxxImageTx);
            myPresenter.reqeust(loginBean.getId(),loginBean.getSessionId(),filea);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //grantResults数组与权限字符串数组对应，里面存放权限申请结果
        if (permissions[0].equals(Manifest.permission.CAMERA)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(WdxxActivity.this, "已授权", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(WdxxActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
   @Override
    protected void onStart() {
        super.onStart();
        wdxxPresenter = new WdxxPresenter(new wdxx());
        wdxxPresenter.reqeust(loginBean.getId(),loginBean.getSessionId());
    }
    class Txsc implements DataCall<String> {

        @Override
        public void success(String data, Object... args) {
            Toast.makeText(WdxxActivity.this,"头像上传成功",Toast.LENGTH_SHORT).show();
            Glide.with(WdxxActivity.this).load(data).apply(RequestOptions.circleCropTransform()).into(wdxxImageTx);
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(WdxxActivity.this,"头像上传失败",Toast.LENGTH_SHORT).show();
        }
    }
    class wdxx implements DataCall<CXBean>{

        @Override
        public void success(CXBean data, Object... args) {
            Glide.with(WdxxActivity.this).load(data.headPic).apply(RequestOptions.circleCropTransform()).into(wdxxImageTx);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
