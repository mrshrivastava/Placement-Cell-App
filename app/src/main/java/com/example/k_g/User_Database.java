package com.example.k_g;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class User_Database extends SQLiteOpenHelper {
    private Context context;
    private static final String Database_Name="User_login_Status";
    private static final int Database_Version=1;
    private static final String Table_Name="User_Data";
    private static final String Column_ID="ID";
    private static final String Column_Mail="Mail";
    private static final String Column_Name="Name";
    private static final String Column_Dp="Dp";
    private static final String Column_User="User";
    private static final String Column_College_Database="College_Database";
    private static final String Column_Logged_In_Status="Logged_In_Status";



    public User_Database(@Nullable Context context) {
        super(context, Database_Name,null, Database_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+Table_Name+
                " ("+ Column_ID+" TEXT PRIMARY KEY, "+
                Column_Mail+" TEXT, "+
                Column_Name+" TEXT, "+
                Column_Dp+" TEXT, "+
                Column_User+" TEXT, "+
                Column_College_Database+" TEXT, "+
                Column_Logged_In_Status+" INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }
    void addData(String mail, String name, String dp, String user, String database, int status)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Column_ID,"user");
        cv.put(Column_Mail,mail);
        cv.put(Column_Name,name);
        cv.put(Column_Dp,dp);
        cv.put(Column_User,user);
        cv.put(Column_College_Database,database);
        cv.put(Column_Logged_In_Status,status);
        db.insert(Table_Name,null,cv);

    }
    Cursor readAllData()
    {
        String query="Select * from "+Table_Name+" where ID='user';";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null)
        {
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
    void deleteOneRow()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Table_Name,"ID=?",new String[]{"user"});
    }
}
