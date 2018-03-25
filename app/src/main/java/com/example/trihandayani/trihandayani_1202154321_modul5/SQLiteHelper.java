package com.example.trihandayani.trihandayani_1202154321_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

/**
 * Created by HMJ on 3/25/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_trihandayani_studycase5";
    private static final String TABLE_NAME = "tb_todo_list";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_COL1 = "id";
    private static final String KEY_COL2 = "activity_name";
    private static final String KEY_COL3 = "description";
    private static final String KEY_COL4 = "priority";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQry = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_COL1 + " INTEGER PRIMARY KEY,"
                + KEY_COL2 + " TEXT," + KEY_COL3 + " TEXT," + KEY_COL4 + " TEXT)";
        db.execSQL(createQry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String qry = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(qry);
        onCreate(db);
    }

    public void saveData(ToDoItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COL2, item.getName());
        values.put(KEY_COL3, item.getDesc());
        values.put(KEY_COL4, item.getPriority());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public LinkedList<ToDoItem> selectAll(){
        LinkedList<ToDoItem> list = new LinkedList<ToDoItem>();
        String qry = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry, null);

        if(cursor.moveToFirst()){
            do{
                ToDoItem item = new ToDoItem();
                item.setId(Integer.valueOf(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setDesc(cursor.getString(2));
                item.setPriority(cursor.getString(3));
                list.add(item);
            }while (cursor.moveToNext());
        }

        return list;
    }

    public boolean delete(ToDoItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "id =" + item.getId(), null) > 0;
    }
}
