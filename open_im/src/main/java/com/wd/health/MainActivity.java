package com.wd.health;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.wd.health.adapter.ChatAdapter;
import com.wd.health.adapter.CommonFragmentPagerAdapter;
import com.wd.health.enity.FullImageInfo;
import com.wd.health.enity.MessageInfo;
import com.wd.health.fragment.ChatEmotionFragment;
import com.wd.health.fragment.ChatFunctionFragment;
import com.wd.health.util.Constants;
import com.wd.health.util.GlobalOnItemClickManagerUtils;
import com.wd.health.util.MediaManager;
import com.wd.health.widget.EmotionInputDetector;
import com.wd.health.widget.NoScrollViewPager;
import com.wd.health.widget.StateButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.FileContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import retrofit2.http.Path;
import zhang.bw.com.common.DaoMaster;
import zhang.bw.com.common.LoginBeanDao;
import zhang.bw.com.common.bean.CXBean;
import zhang.bw.com.common.bean.LoginBean;
import zhang.bw.com.common.core.DataCall;
import zhang.bw.com.common.core.WdxxPresenter1;
import zhang.bw.com.common.core.exception.ApiException;
import zhang.bw.com.common.util.Constant;
import zhang.bw.com.common.util.RsaCoder;

import static cn.jmessage.biz.httptask.task.GetUserInfoListTask.IDType.username;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
@Route(path = Constant.ACTIVITY_URL_FABIAOPINGLUNIM)
public class MainActivity extends AppCompatActivity {
    @BindView(R2.id.chat_list)
    EasyRecyclerView chatList;
    @BindView(R2.id.emotion_voice)
    ImageView emotionVoice;
    @BindView(R2.id.jianpan1)
    ImageView jianpan1;
    @BindView(R2.id.edit_text)
    EditText editText;
    @BindView(R2.id.voice_text)
    TextView voiceText;
    @BindView(R2.id.emotion_button)
    ImageView emotionButton;
    @BindView(R2.id.emotion_add)
    ImageView emotionAdd;
    @BindView(R2.id.emotion_send)
    StateButton emotionSend;
    @BindView(R2.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R2.id.emotion_layout)
    RelativeLayout emotionLayout;
    @BindView(R2.id.fan1)
    ImageView fan1;
    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;
    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
    private List<MessageInfo> messageInfos;
    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;
    public String ss;
    private WdxxPresenter1 wdxxPresenter;
    private LoginBean loginBean;
    private String s1;
    private Unbinder bind;
    private String headPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
         bind = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        JMessageClient.registerEventReceiver(this);//接收消息
        initWidget();

    }

    private void initWidget() {
        loginBean =DaoMaster.newDevSession(MainActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        wdxxPresenter = new WdxxPresenter1(new Backb());
        wdxxPresenter.reqeust(loginBean.getId(),loginBean.getSessionId());
        try {
            ss = RsaCoder.decryptByPublicKey(loginBean.jiGuangPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        s1 = MD5.MD5(ss);
        Log.e("aa",s1);
        JMessageClient.login(loginBean.userName,s1 , new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                switch (i) {
                    case 0:
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        fan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .bindToVoiceButton1(messageInfos)
                .build();

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);
        chatAdapter = new ChatAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
        boolean isEnabled = true;
        if (!isEnabled) {
            //未打开通知
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("请在“通知”中打开通知权限")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("android.provider.extra.APP_PACKAGE", MainActivity.this.getPackageName());
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //5.0
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("app_package", MainActivity.this.getPackageName());
                                intent.putExtra("app_uid", MainActivity.this.getApplicationInfo().uid);
                                startActivity(intent);
                            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {  //4.4
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setData(Uri.parse("package:" + MainActivity.this.getPackageName()));
                            } else if (Build.VERSION.SDK_INT >= 15) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                            }
                            startActivity(intent);

                        }
                    })
                    .create();
            alertDialog.show();
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        }
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        chatAdapter.addItemClickListener(itemClickListener);
        LoadData();
    }
    class Backb implements DataCall<CXBean>{

        @Override
        public void success(CXBean data, Object... args) {
            headPic = data.headPic;
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    /**
     * item点击事件
     */
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {
            Toast.makeText(MainActivity.this, "onHeaderClick", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImageClick(View view, int position) {
            int location[] = new int[2];
            view.getLocationOnScreen(location);
            FullImageInfo fullImageInfo = new FullImageInfo();
            fullImageInfo.setLocationX(location[0]);
            fullImageInfo.setLocationY(location[1]);
            fullImageInfo.setWidth(view.getWidth());
            fullImageInfo.setHeight(view.getHeight());
            fullImageInfo.setImageUrl(messageInfos.get(position).getImageUrl());
            EventBus.getDefault().postSticky(fullImageInfo);
            startActivity(new Intent(MainActivity.this, FullImageActivity.class));
            overridePendingTransition(0, 0);
        }

        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.mipmap.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.mipmap.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }
    };

    /**
     * 构造聊天数据
     */
    private void LoadData() {
        messageInfos = new ArrayList<>();
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setHeader(headPic);//设置头像
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo.setFilepath(messageInfo.getFilepath());
        messageInfos.add(messageInfo);
        chatAdapter.addAll(messageInfos);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {
        messageInfo.setHeader(headPic);
        messageInfo.setContent(messageInfo.getContent());
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        messageInfos.add(messageInfo);
        chatAdapter.add(messageInfo);
        chatList.scrollToPosition(chatAdapter.getCount() - 1);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                chatAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }
   // 接收在线消息
    public void onEvent(MessageEvent event) {
        Message newMessage = event.getMessage();//获取此次离线期间会话收到的新消息列表
        switch (newMessage.getContentType()){
            case text:
                Log.e("ContentType","text");
                TextContent content = (TextContent)newMessage.getContent();
                final String text = content.getText();//接收到的消息
                new Handler(MainActivity.this.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里执行你要想的操作 比如直接在这里更新ui或者调用回调在 在回调中更新ui
                        MessageInfo message = new MessageInfo();
                        message.setContent(text);
                        message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                        message.setHeader(headPic);
                        messageInfos.add(message);
                        chatAdapter.add(message);
                        chatList.scrollToPosition(chatAdapter.getCount() - 1);
                    }
                });
                break;
            case file:
                final FileContent filecontent = (FileContent)newMessage.getContent();
                final String fileName = filecontent.getFileName();
                new Handler(MainActivity.this.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里执行你要想的操作 比如直接在这里更新ui或者调用回调在 在回调中更新ui
                        String substring = fileName.substring(fileName.length() - 3);
                        MessageInfo message = new MessageInfo();
                        if (substring.equals("amr")){
                            message.setFilepath(filecontent.getLocalPath());
                            Log.e("WKQ",filecontent.getLocalPath());
                        }
                        message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                        message.setHeader(headPic);
                        messageInfos.add(message);
                        chatAdapter.add(message);
                        chatList.scrollToPosition(chatAdapter.getCount() - 1);
                    }
                });
                break;
            case image:
                Log.e("ContentType","image");
                break;
            case voice:
                Log.e("ContentType","voice");
                break;
            case location:
                Log.e("ContentType","location");
                break;
            case eventNotification:
                Log.e("ContentType","eventNotification");
                break;
            case prompt:
                Log.e("ContentType","prompt");
                break;
            case custom:
                Log.e("ContentType","custom");
                break;
            default:
                Log.e("ContentType","default");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().removeStickyEvent(this);
        EventBus.getDefault().unregister(this);
    }
}
