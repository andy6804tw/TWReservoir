package com.f74372017.twreservoir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andy6804tw on 2017/1/19.
 */

public class DBAccess extends SQLiteOpenHelper {
    /**TABLE todolist**/
    protected final static String TABLE_NAME="twreservoir";//建議字串常數
    protected final static String ID_FIELD="_id";
    protected final static String WATER_FIELD="water";
    protected final static String DAY_FIELD="day";
    protected final static String UPDATE_FIELD="up_date";
    protected final static String DOWN_FIELD="down";
    protected final static String NAME_FIELD="name";
    protected final static String PERCENTAGE_FIELD="percentage";
    protected final static String POSITION_FIELD="position";


    public DBAccess(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {//建構子1.哪個Activity呼叫2.資料庫名稱 3.資料庫物件4.版本
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//第一次建構資料表呼叫,應用程式第一次執行會做
         /*
            create table todoList{
                _id integer primary key autoincrement,
                title text,
                date text,
                time text
            }
         */
        String sql="create table "+TABLE_NAME+"("+ID_FIELD+" integer primary key autoincrement,"
                +WATER_FIELD+" text,"
                +DAY_FIELD+" text,"
                +UPDATE_FIELD+" text,"
                +DOWN_FIELD+" text,"
                +NAME_FIELD+" text,"
                +PERCENTAGE_FIELD+" text,"
                +POSITION_FIELD+" text)";
        //Log.e("SQLDB",sql);
        db.execSQL(sql);//不回傳資料的資料庫都能跑,更新新增刪除



    }

    @Override//當應用程式有更新再次開起來會檢查新app資料庫版本和舊資料是否一致
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exits "+TABLE_NAME);
        onCreate(db);

    }

    public long add(String water,String day,String update,String down,String name,String percentage,String position){

        SQLiteDatabase db=getWritableDatabase();//物件可寫入資料
        ContentValues values=new ContentValues();
        values.put(WATER_FIELD,water);
        values.put(DAY_FIELD,day);
        values.put(UPDATE_FIELD,update);
        values.put(DOWN_FIELD,down);
        values.put(NAME_FIELD,name);
        values.put(PERCENTAGE_FIELD,percentage);
        values.put(POSITION_FIELD,position);
        long result=db.insert(TABLE_NAME,null,values);

        db.close();

        return result;
    }
    public Cursor getData(String whereStr, String orderbyStr){
        /*
         select _id, title, date, time
          from todolist
          where _id=5        過濾條件
          order by date      日期排序
        */
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.query(TABLE_NAME,new String[]{ID_FIELD,WATER_FIELD,DAY_FIELD,UPDATE_FIELD,DOWN_FIELD,NAME_FIELD,PERCENTAGE_FIELD,POSITION_FIELD}
                ,whereStr,null,null,null,orderbyStr);//是在記憶體的空間 裡面包含很多筆資料 可走訪資料想像成陣列
        return c;



    }
    //修改
    long update(String water,String day,String update,String down,String name,String percentage,String position,String whereClause) {
        SQLiteDatabase db=this.getWritableDatabase();//取得讀寫資料表物件
        ContentValues values =new ContentValues();
        values.put(WATER_FIELD,water);
        values.put(DAY_FIELD,day);
        values.put(UPDATE_FIELD,update);
        values.put(DOWN_FIELD,down);
        values.put(NAME_FIELD,name);
        values.put(PERCENTAGE_FIELD,percentage);
        values.put(POSITION_FIELD,position);
        //執行更新資料
        long result=db.update(TABLE_NAME, values, whereClause, null);
        db.close();
        return result;//回傳更新資料筆數
    }
    //刪除
    int delete(String _id){
        SQLiteDatabase db=this.getWritableDatabase();//取得讀寫資料表物件
        int result=db.delete(TABLE_NAME, ID_FIELD+" ="+_id, null); //進行刪除
        db.close();
        return result;//回傳刪除筆數
    }



}
