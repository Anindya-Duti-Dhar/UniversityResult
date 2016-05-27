package com.creation.anindya.universityresult;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by w3-15 on 1/25/16.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME            = "MultipleTableDB.db";
    public static final String PERSONAL_DETAILS_TABLE   = "personal_details";
    public static final String COURSE_TABLE             = "course";
    public static final String ACADEMIC_TABLE           = "academic_info";

    // first table column list
    public static final String COLUMN_ID            = "id";
    public static final String COLUMN_NAME          = "name";
    public static final String COLUMN_FATHER_NAME   = "fatherName";
    public static final String COLUMN_PHONE         = "phone";


    public static final String COLUMN_COURSE_NAME   = "courseName";
    public static final String COLUMN_COURSE_RATING = "courseRating";
    public static final String COLUMN_COURSE_MARKS   = "courseMarks";
    public static final String COLUMN_COURSE_TERMS   = "courseTerms";

    public static final String COLUMN_ROLL          = "roll";
    public static final String COLUMN_DEPARTMENT    = "department";


    public static final String CREATE_PERSONAL_TABLE = "CREATE TABLE "
            + PERSONAL_DETAILS_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_ROLL + " TEXT,"
            + COLUMN_NAME+ " TEXT, " + COLUMN_FATHER_NAME+ " TEXT, " + COLUMN_PHONE+ " TEXT " + ")";

    public static final String CREATE_COURSE_TABLE = "CREATE TABLE "
            + COURSE_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_ROLL + " TEXT,"
            + COLUMN_COURSE_NAME+ " TEXT, " + COLUMN_COURSE_RATING+ " TEXT, " + COLUMN_COURSE_TERMS+ " TEXT, " +COLUMN_COURSE_MARKS+ " TEXT " +")";

    public static final String CREATE_ACADEMIC_TABLE = "CREATE TABLE "
            + ACADEMIC_TABLE + "("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ROLL+ " TEXT, " + COLUMN_DEPARTMENT+ " TEXT " + ")";


    public DBHelper(Context context)
    {

        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PERSONAL_TABLE);
        db.execSQL(CREATE_COURSE_TABLE);
        db.execSQL(CREATE_ACADEMIC_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PERSONAL_DETAILS_TABLE );
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE );
        db.execSQL("DROP TABLE IF EXISTS " + ACADEMIC_TABLE );
        onCreate(db);

    }

    public boolean insertPersonalDetails  (String name, String fatherName, String phone, String roll)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("fatherName", fatherName);
        contentValues.put("phone", phone);
        contentValues.put("roll", roll);

        db.insert("personal_details", null, contentValues);
        return true;
    }

    public boolean insertCourse  (String courseName, String courseRating,String courseTerms,String courseMarks, String roll)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("courseTerms", courseTerms);
        contentValues.put("courseMarks", courseMarks);
        contentValues.put("courseName", courseName);
        contentValues.put("courseRating", courseRating);
        contentValues.put("roll", roll);

        db.insert("course", null, contentValues);
        return true;
    }

    public boolean insertAcademic  (String roll, String department)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("roll", roll);
        contentValues.put("department", department);

        db.insert("academic_info", null, contentValues);
        return true;
    }


    public ArrayList<String> getAllInformation()
    {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery("SELECT pd.name,a.department, c.courseName, c.courseRating, pd.roll, c.courseMarks,c.courseTerms " +
                "FROM personal_details pd " +
                "INNER JOIN course c ON c.roll=pd.roll " +
                "INNER JOIN academic_info a ON a.id=pd.id " +
                "WHERE c.courseMarks IN ( SELECT MAX(courseMarks) FROM course GROUP BY roll ) " +
                "ORDER BY c.courseMarks DESC ", null);

        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_ROLL ))+
                    ", "+ res.getString(res.getColumnIndex(COLUMN_NAME ))+
                    ", "+ res.getString(res.getColumnIndex(COLUMN_COURSE_NAME ))+
                    ", "+ res.getString(res.getColumnIndex(COLUMN_COURSE_RATING ))+
                    ", "+ res.getString(res.getColumnIndex(COLUMN_COURSE_TERMS ))+
                    ", "+res.getString(res.getColumnIndex(COLUMN_COURSE_MARKS )));
            res.moveToNext();

//            array_list.add(" Roll: "+ res.getString(res.getColumnIndex(COLUMN_ROLL ))+
//                    ", Name: "+ res.getString(res.getColumnIndex(COLUMN_NAME ))+
//                    ", CourseName: "+ res.getString(res.getColumnIndex(COLUMN_COURSE_NAME ))+
//                    ", Rating: "+ res.getString(res.getColumnIndex(COLUMN_COURSE_RATING ))+
//                    ", Term: "+ res.getString(res.getColumnIndex(COLUMN_TERM ))+
//                    ", Marks: "+res.getString(res.getColumnIndex(COLUMN_MARKS )));
//            res.moveToNext();

        }
        return array_list;
    }

    public boolean deleteAll(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ PERSONAL_DETAILS_TABLE);
        db.execSQL("delete from "+ COURSE_TABLE);
        db.execSQL("delete from "+ ACADEMIC_TABLE);
        return true;
    }

    public boolean deleteItem(int roll){

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            return
                    db.delete("academic_info","roll = "+roll, null) > 0;


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Get data for edit

    //public Cursor getData(int id){
       // SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );

       // Cursor res =  db.rawQuery( "SELECT pd.*, c.*, a.*" +
                //" FROM personal_details pd " +
                //"INNER JOIN course c ON pd.id=c.id " +
               // "INNER JOIN academic_info a ON c.id= a.id " +
               // "WHERE a.id= " + id ,null );

       // return res;
    //}


}

