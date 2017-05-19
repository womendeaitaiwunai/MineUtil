package com.lxl.mineutil.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxl on 2016/8/31.
 * 使用　　
 * DBUtil dbUtil=new DBUtil(MainActivity.this);
 * dbUtil.insertDate(jsonData);
 * dbUtil.query(jsonData.getClass(),-1);
 */
public class DBUtil {
    private Class<?> calss;

    private static String dbname = "PhotoStudioDB";
    private String dbShare="DbShare";
    private int dbNum=0;
    private static int version = 1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private DbHelper dbHelper;
    private Context context;

    public  void insertDate(Object object){
        Class tClass=object.getClass();
        this.calss= tClass;
        version=sharedPreferences.getInt("dbVersion",1);
        if (version!=1){
            edit.putInt("dbVersion",version+1);
            edit.commit();
        }
        dbNum=sharedPreferences.getInt("DbNum",0);
        edit.putInt("DbNum",dbNum+1);
        edit.commit();

        edit.putString("dbNum"+(dbNum+1),tClass.getName());
        edit.commit();

        Log.i("版本",version+"");
        dbHelper=new DbHelper(context,version);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL(getInserSQL(tClass),getSqlValue(object));
            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        } catch (Exception ex) {
            db.close();
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }
	
	
	 //五：查：根据id查询数据库
    public List<String> query(Class dbName, int id){
        //1.获取相应的（可读的）数据库
        List<String> results=new ArrayList<>();
        Field[] field = dbName.getDeclaredFields();
        if (dbHelper==null) dbHelper=new DbHelper(context,version);
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        //2.执行更新语句
        Cursor rawQuery = null;
        try {
            if (id==-1){
                rawQuery= readableDatabase.rawQuery("select * from "+dbName.getSimpleName(),new String[]{});
            }else {
                rawQuery= readableDatabase.rawQuery("select * from "+dbName.getSimpleName()+"+ where id=?",  new String[]{String.valueOf(id)});
            }

            while(rawQuery.moveToNext()){
                StringBuilder result=new StringBuilder("");
                for (int i = 1; i < field.length+1; i++) {
                    result=result.append(rawQuery.getString(i)+",");
                }
                String endResult=result.toString().endsWith(",")?(result.toString().substring(0,result.toString().length()-1)):result.toString();
                Log.i("得到的数据",endResult);
                results.add(endResult);
            }
            //3.关闭结果集与数据库
            rawQuery.close();
            readableDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    private String getInserSQL(Class classs){
        Field[] field = classs.getDeclaredFields();
        String column="";
        String values="";
        for (int i = 0; i < field.length; i++) {
            column=column+field[i].getName()+",";
            values=values+"?,";
        }
        if (field.length>0){
            //取消最后一个逗号
            column=column.substring(0,column.length()-1);
            column="("+column+")";
            values=values.substring(0,values.length()-1);
            values="("+values+")";
        }else Log.e("传递Class","属性值空");
        Log.i("插入--->","Insert into "+classs.getSimpleName()+column+"values"+values);
        return  "Insert into "+classs.getSimpleName()+column+" values "+values;
    }

    private Object[] getSqlValue(Object object){
        Field[] field = object.getClass().getDeclaredFields();
        Object[] objects=new Object[field.length];
        for (int i = 0; i < field.length; i++) {
            try {
                field[i].setAccessible( true ); // 设置些属性是可以访问的
                objects[i]= field[i].get(object);
            } catch (Exception e) {
                Log.i("获取Method","错误");
                e.printStackTrace();
            }
        }
        String result="";
        for (int i = 0; i < objects.length; i++) {
            result=result+objects[i].toString()+",";
        }
        Log.i("值是",result.substring(0,result.length()-1));
        return objects;
    }

    public void delectData(Class delectClass,String phone){
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            writableDatabase.execSQL( "DELETE FROM " +delectClass.getSimpleName()+" where phone="+phone);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            writableDatabase.close();
        } catch (Exception ex) {
            Log.e("删除数据","失败");
            writableDatabase.close();
            ex.printStackTrace();
        } finally {
            writableDatabase.close();
        }
    }

    public String firstUp(String changeString){
        char[] cs=changeString.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }



    public DBUtil(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(dbShare,Context.MODE_PRIVATE);
        version=sharedPreferences.getInt("dbVersion",1);
        //dbHelper=new DbHelper(context,version);
        edit=sharedPreferences.edit();
        dbNum= sharedPreferences.getInt("DbNum",0);
    }

    public class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context,int version) {
            super(context,dbname,null,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            dbNum=sharedPreferences.getInt("DbNum",0);
            for (int i = 0; i < dbNum; i++) {
                String className=sharedPreferences.getString("dbNum"+(i+1),"");
                if (!TextUtils.isEmpty(createDbSql(className)))
                    db.execSQL(createDbSql(className));
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            dbNum=sharedPreferences.getInt("DbNum",0);
            for (int i = 0; i < dbNum; i++) {
                try {
                    String className=sharedPreferences.getString("dbNum"+(i+1),"");
                    Class<?> dbClass=Class.forName(className);
                    db.execSQL("DROP TABLE IF EXISTS "+dbClass.getSimpleName());
                } catch (ClassNotFoundException e) {
                    Log.e("删除数据库","失败");
                    e.printStackTrace();
                }

            }
        }
    }

    public String createDbSql(String className){
        String sql="";
        try {
            Class<?> classe=Class.forName(className);
            sql="create table if not exists "+classe.getSimpleName()+"(_id integer primary key autoincrement,";
            Field[] field = classe.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                Class<?> type = field[i].getType();
                String classType=type.getName();
                String value=field[i].getName();
                sql=sql+value+" "+selectType(classType)+",";
            }
            if (sql.endsWith(",")){
                sql=sql.substring(0,sql.length()-1);
                sql=sql+")";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.i("创建数据库",sql);
        return sql;
    }

    public String selectType(String classType){
//        switch (classType.toLowerCase()){
//            case "int":
//                return "INTEGER";
//            case "java.lang.string":
//                return "varchar(60)";
//        }
        return "varchar(60)";
    }
}
