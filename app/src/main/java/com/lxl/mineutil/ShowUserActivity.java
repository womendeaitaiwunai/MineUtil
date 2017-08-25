package com.lxl.mineutil;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.just.library.AgentWeb;
import com.lxl.mineutil.adapter.BaseActivity;
import com.lxl.mineutil.adapter.UserAdapter;
import com.lxl.mineutil.bean.UserBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class ShowUserActivity extends BaseActivity{
    private RecyclerView userView;
    private String enterpriseKID;
    private List<UserBean.EnterpriseUserListBean> userListBean=new ArrayList<>();
    private UserAdapter adapter;
    private AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        userView= (RecyclerView) findViewById(R.id.user_view);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLinearLayout= (LinearLayout) findViewById(R.id.root_view);
        enterpriseKID=getIntent().getStringExtra("enterpriseKID");

        LinearLayoutManager layoutManager=new LinearLayoutManager(ShowUserActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        userView.setLayoutManager(layoutManager);
        adapter=new UserAdapter(userListBean);
        userView.setAdapter(adapter);

        adapter.setPoJieListener(new UserAdapter.PoJieListener() {
            @Override
            public void startPoJie(UserBean.EnterpriseUserListBean userListBean) {
                copy(userListBean.getPassword(),ShowUserActivity.this);
                Toast.makeText(ShowUserActivity.this, "已经复制密码，准备破解", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse("https://www.somd5.com/");
//                intent.setData(content_url);
//                startActivity(intent);
                startActivityByIntent(ShowUserActivity.this, WebActivity.class,false);
            }
        });
        getUser();
    }

    /**
     * 实现文本复制功能
     * @param content
     */
    public static void copy(String content, Context context) {
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        //cmb.setText(content.trim());
        cmb.setPrimaryClip(ClipData.newPlainText("",content.trim()));
    }

    private void getUser(){
        showProgressDialog("正在破解...");
        String json="enterpriseKID="+enterpriseKID+"&DataTimeStamp=0&dataTimeStampPerimt=0";
        PostStringBuilder postStringBuilder = OkHttpUtils.postString().url("http://app.kp99.cn/client/qryEnterpriseUserAndUserPerimt.jhtml");
        postStringBuilder.mediaType(MediaType.parse("application/x-www-form-urlencoded"));
        postStringBuilder.content(json);
        postStringBuilder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                hideProgressDialog();
            }
            @Override
            public void onResponse(String response, int id) {
                hideProgressDialog();
                try {
                    UserBean userBean=new Gson().fromJson(response,UserBean.class);
                    adapter.addData(userBean.getEnterpriseUserList());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
