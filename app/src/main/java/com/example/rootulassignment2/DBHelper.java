package com.example.rootulassignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //initializing the database name and version
    static final String DBNAME = "School.db";
    static final int VERSION = 1;
    //Database and table initialized, all the columns initiated
    static final String TABLE_1_NAME = "Grades";
    static final String Table_1_COL1 = "StudentId";
    static final String Table_1_COL2 = "StudentName";
    static final String Table_1_COL3 = "Program";
    static final String Table_1_COL4 = "Course1";
    static final String Table_1_COL5 = "Course2";
    static final String Table_1_COL6 = "Course3";
    static final String Table_1_COL7 = "Course4";
    static final String CREATE_TABLE1 = "CREATE TABLE "+ TABLE_1_NAME +"("
            + Table_1_COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Table_1_COL2 +" TEXT NOT NULL,"
            + Table_1_COL3 +" TEXT NOT NULL,"
            + Table_1_COL4 +" INTEGER NOT NULL,"
            + Table_1_COL5 +" INTEGER NOT NULL,"
            + Table_1_COL6 +" INTEGER NOT NULL,"
            + Table_1_COL7 +" INTEGER NOT NULL);";
    static final String DROP_TABLE1="DROP TABLE IF EXISTS "+ TABLE_1_NAME;
    static final String TABLE_2_NAME = "Improvement";
    static final String Table_2_COL1 = "ImprovementId";
    static final String Table_2_COL2 = "StudentId";
    static final String Table_2_COL3 = "Course";
    static final String Table_2_COL4 = "Marks";
    static final String CREATE_TABLE2 = "create table  "+ TABLE_2_NAME +"("
            + Table_2_COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Table_2_COL2 +" INTEGER NOT NULL,"
            + Table_2_COL3 +" TEXT NOT NULL,"
            + Table_2_COL4 +" INTEGER NOT NULL);";
    static final String DROP_TABLE2="DROP TABLE IF EXISTS "+ TABLE_2_NAME;



    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //creating the database table
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE1);
        db.execSQL(DROP_TABLE2);
        onCreate(db);
    }
//Implementing insert grades in school table using context values
    public boolean InsertGrades(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Table_1_COL2,student.getStudentName());
        cv.put(Table_1_COL3,student.getProgram());
        cv.put(Table_1_COL4,student.getCourse1());
        cv.put(Table_1_COL5,student.getCourse2());
        cv.put(Table_1_COL6,student.getCourse3());
        cv.put(Table_1_COL7,student.getCourse4());

        long result = db.insert(TABLE_1_NAME,null,cv);

        return ((result==-1) ? false : true);
    }
    //Implementing insert grades in improvement table using context values
    public boolean InsertImprovments(int id,String selectedCourse,int imp_marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Table_2_COL2,id);
        cv.put(Table_2_COL3,selectedCourse);
        cv.put(Table_2_COL4,imp_marks);
        long res = db.insert(TABLE_2_NAME,null,cv);
        return ((res == -1) ? false : true);
    }
//implementing listing all the student using cursor rawQuery syntax
    public Cursor listStudent() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursorObj;
        cursorObj=db.rawQuery("select * from " + TABLE_1_NAME, null);
        if(cursorObj != null)
        {
            cursorObj.moveToFirst();
        }
        return cursorObj;
    }
    //implementing searching in grade using cursor rawQuery syntax
    public Cursor searchGrade(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursorObj;
        cursorObj=db.rawQuery("SELECT * FROM " + TABLE_1_NAME +" WHERE StudentId='"+id+"'", null);

        if(cursorObj != null)
        {
            cursorObj.moveToFirst();
        }
        return cursorObj;
    }
    //updating improve marks using update query in cursor also using raw query, checking if<=100 condition and than only updating the school table
    public Cursor improveMarks(int id,String course,int imp_mark){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor check;
        check = db.rawQuery("update "
                + TABLE_1_NAME + " set " + course
                + "=" + course + "+" + imp_mark + " where "
                + Table_1_COL1 + "='"+id+"'" + "AND "+course+"+"+imp_mark+"<=100", null);
        check.moveToFirst();
        check.close();
        return check;
    }

}
