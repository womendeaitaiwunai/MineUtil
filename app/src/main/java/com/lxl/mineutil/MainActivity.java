package com.lxl.mineutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<String> versions=new ArrayList<>();
    private AgainTimerTask againTimerTask;
    private EditText time;
    private int time2Time=100000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVersion();
        initView();

        DBUtil dbUtil=new DBUtil(this);
        JsonData jsonData=new JsonData();
        jsonData.setImei("111111");
        jsonData.setType(1);
        jsonData.setPhoneCode("222222");
        jsonData.setRandomCode("666666");
        dbUtil.insertDate(jsonData.getClass());
    }

    private void initView() {
        time= (EditText) findViewById(R.id.time);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.change).setOnClickListener(this);
    }

    private void PoJieKuaiPi(){
        String imei=getRand(15);
        String randomCode=getRand(6);
        String token=getRand(17);
        Request test=new Request();
        Packet packet=new Packet();

        JsonData jsonData=new JsonData();
        jsonData.setImei(imei);
        jsonData.setType(1);
        jsonData.setPhoneCode(getTel());
        jsonData.setRandomCode(randomCode);
        packet.setJsonData(jsonData);

        test.setMid(imei);
        test.setAccesstoken(token);
        test.setVersion(versions.get(getNum(0,versions.size()-1)));
        test.setPacket(packet);

        Gson gson=new Gson();
        String json=gson.toJson(test);
        PostStringBuilder postStringBuilder = OkHttpUtils.postString().url("http://app.kp99.cn/client/clientManager?action=getRandomMsg");
        postStringBuilder.mediaType(MediaType.parse("application/json; charset=utf-8"));
        postStringBuilder.addHeader("Accept-Encoding","gzip" );
        postStringBuilder.addHeader("Host","app.kp99.cn" );
        postStringBuilder.addHeader("Connection","Keep-Alive" );
        postStringBuilder.addHeader("Content-Type","application/json; charset=utf-8" );

        postStringBuilder.content(json);
        postStringBuilder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "第一步发生错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Result result;
                try{
                    result=gson.fromJson(response,Result.class);
                }catch (Exception e){
                    return;
                }
                if (result!=null&&result.getResult()==1){
                    Toast.makeText(MainActivity.this, "完成第一部.等待破解..", Toast.LENGTH_SHORT).show();

                }else if (result==null){
                    Toast.makeText(MainActivity.this, "返回空", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "返回错误码"+result.getResult(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initVersion(){
        versions.add("5.4.8.032");
        versions.add("5.4.9.021");
        versions.add("5.4.7.022");
        versions.add("5.4.6.011");
        versions.add("5.4.5");
        versions.add("5.4.4");
        versions.add("5.4.3");
        versions.add("5.4.2");
        versions.add("5.4.1");
        versions.add("5.4.0");
    }


    private String getRand(int length){
        StringBuilder imei=new StringBuilder();
        for (int i = 0; i < length; i++) {
            java.util.Random random=new java.util.Random();// 定义随机类
            String result=random.nextInt(10)+"";
            imei.append(result);
        }
        return imei.toString();
    }

    /**
     * 返回手机号码
     */
    private static String[] telFirst=("133,153,180,181,189,173,177,130,131,132,155,156,185,186,145,176," +
            "185,134,135,136,137,138,139,150,151,152,158,159,182,183,184,147,178,184").split(",");
    private static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+thrid;
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                againTimerTask=new AgainTimerTask(time2Time);
                againTimerTask.setTimerTask(new AgainTimerTask.TimeTaskResult() {
                    @Override
                    public void run() {
                        PoJieKuaiPi();
                    }
                }).startTimer();
                break;
            case R.id.stop:
                againTimerTask.stopTimer();
                break;
            case R.id.change:
                againTimerTask.stopTimer();
                String timeLong=time.getText()+"";
                try {
                    time2Time=Integer.parseInt(timeLong);
                }catch (Exception e){
                    time2Time=1000;
                }finally {
                    againTimerTask=new AgainTimerTask(time2Time);
                }
                break;

        }
    }
}
