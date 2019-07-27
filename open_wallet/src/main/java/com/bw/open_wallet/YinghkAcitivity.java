package com.bw.open_wallet;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import zhang.bw.com.common.core.WDActivity;
import zhang.bw.com.common.util.Constant;

/**
 * @Author：郭强
 * @E-mail： 69666501@163.com
 * @Date：2019/7/16
 * @Description：上传银行卡
 */
@Route(path = Constant.ACTIVITY_URL_YINGHK)
public class YinghkAcitivity extends WDActivity {
    ImageView image_fan,image_xiang,image_ka;
    Button but_xyb;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_yinghk;
    }

    @Override
    protected void initView() {
        image_fan=findViewById(R.id.Yinghk_image_back);
        image_xiang=findViewById(R.id.image_xiang);
        but_xyb=findViewById(R.id.but_xyb);
        image_ka=findViewById(R.id.image_ka);

        initonclick();
    }

    private void initonclick() {
        image_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        image_xiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去寻找是否已经有了相机的权限
                if (ContextCompat.checkSelfPermission(YinghkAcitivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

                    //Toast.makeText(MainActivity.this,"您申请了动态权限",Toast.LENGTH_SHORT).show();
                    //如果有了相机的权限有调用相机
                    startCamera();

                }else{
                    //否则去请求相机权限
                    ActivityCompat.requestPermissions(YinghkAcitivity.this,new String[]{Manifest.permission.CAMERA},100);

                }


            }
        });
        but_xyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void destoryData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            //如果requestCode为100 就走这里
            case 100:

                //permissions[0].equals(Manifest.permission.CAMERA)
                //grantResults[0] == PackageManager.PERMISSION_GRANTED
                //上面的俩个判断可有可无
                if (permissions[0].equals(Manifest.permission.CAMERA)) {

                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //如果用户同意了再去打开相机
                        Toast.makeText(YinghkAcitivity.this, "非常感谢您的同意,再会", Toast.LENGTH_SHORT).show();
                        startCamera();

                    } else {
                        //因为第一次的对话框是系统提供的 从这以后系统不会自动弹出对话框 我们需要自己弹出一个对话框
                        //进行询问的工作
                        startAlertDiaLog();
                    }

                }

                break;

        }
    }

    public void startAlertDiaLog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(YinghkAcitivity.this);

        alert.setTitle("说明");
        alert.setMessage("需要相机权限 去拍照");
        alert.setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //这里的立即开启 是打开手机的设置页面(打开相机权限)
                startSetting();

            }
        });
        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //如果用户还不打开 只能等用户下次点击时再次询问
                Toast.makeText(YinghkAcitivity.this,"当您点击我们会再次询问",Toast.LENGTH_SHORT).show();

            }
        });
        alert.create();
        alert.show();

    }

    public void startSetting(){

        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

        Uri uri = Uri.fromParts("package",getPackageName(),null);

        intent.setData(uri);

        startActivityForResult(intent,10);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10  && resultCode == RESULT_OK){

            if (ContextCompat.checkSelfPermission(YinghkAcitivity.this,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(YinghkAcitivity.this,"非常感谢您的同意",Toast.LENGTH_SHORT).show();

            }else{

            }

        }
    }
    //拍照调用相机
    public void startCamera(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

}
