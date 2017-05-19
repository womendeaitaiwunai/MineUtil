package com.lxl.mineutil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lxl.mineutil.adapter.PhoneAdapter;
import com.lxl.mineutil.bean.PhoneBean;
import com.lxl.mineutil.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxl on 2017/5/19.
 */

public class ShowAllPhoneActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhoneAdapter phoneAdapter;
    private List<String> phones=new ArrayList<>();
    private DBUtil dbUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView= (RecyclerView) findViewById(R.id.user_view);
        TextView title= (TextView) findViewById(R.id.title);
        title.setText("破解的号码");

        LinearLayoutManager layoutManager=new LinearLayoutManager(ShowAllPhoneActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        dbUtil=new DBUtil(ShowAllPhoneActivity.this);
        List<String> phoneList=dbUtil.query(PhoneBean.class,-1);
        phones.addAll(phoneList);
        phoneAdapter=new PhoneAdapter(phones);
        phoneAdapter.setLongClickItemListener(new PhoneAdapter.LongClickItemListener() {
            @Override
            public void longClickListener(String phone) {
                showDelDialog(phone);
            }
        });
        phoneAdapter.setItemClickListener(new PhoneAdapter.ItemClickListener() {
            @Override
            public void clickListener(String phone) {
                showDialog(phone);
            }
        });
        recyclerView.setAdapter(phoneAdapter);
    }

    private void showDialog(final String phone){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("是否开始跳转破解：\n"+phone);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent();
                intent.putExtra("phone",phone);
                setResult(0x02,intent);
                finish();
            }
        });
        builder.show();
    }

    private void showDelDialog(final String phone){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("是否删除这号码的记录：\n"+phone);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbUtil.delectData(PhoneBean.class,phone);
                List<String> phoneList=dbUtil.query(PhoneBean.class,-1);
                phoneAdapter.refreshView(phoneList);
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
