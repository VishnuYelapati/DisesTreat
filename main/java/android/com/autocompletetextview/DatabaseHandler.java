package android.com.autocompletetextview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srinu on 2/15/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "DiseaseDataBase.db";

    // Contacts table name
    public static final String TABLE_DISEASE = "DiseaseTable";
    public static final String DISEASE_ID = "Disease_id";
    public static final String DISEASE_NAME = "Disease_Name";
    public static final String DISEASE_DRUG = "Disease_Drug";
    public static final String DISEASE_SYMPTOMS = "Disease_Symptoms";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String MAIN_USER_TABLE = " CREATE TABLE " +TABLE_DISEASE
                + " (" + DISEASE_ID +" TEXT ,"
                + DISEASE_NAME + " TEXT,"
                + DISEASE_DRUG + " TEXT,"
                + DISEASE_SYMPTOMS + " TEXT"
                + ");";
        db.execSQL(MAIN_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISEASE);

        // Create tables again
        onCreate(db);


    }

    public void insert(String tableName,ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tableName, null, values);
        db.close();
    }

    void addDiseaseInfo(DiseaseInfo diseaseinfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DISEASE_ID,diseaseinfo.id);
        values.put(DISEASE_NAME, diseaseinfo.name); // Contact Name
        values.put(DISEASE_DRUG, diseaseinfo.drug); // Contact Phone
        values.put(DISEASE_SYMPTOMS, diseaseinfo.symptom); //Contact phone no

        // Inserting Row
        db.insert(TABLE_DISEASE, null, values);
        db.close(); // Closing database connection
    }

    DiseaseInfo getDiseaseInfo(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        DiseaseInfo contact=null;
        try {
            Cursor cursor = db.query(TABLE_DISEASE, new String[]{DISEASE_ID,
                            DISEASE_NAME, DISEASE_DRUG, DISEASE_SYMPTOMS}, DISEASE_NAME + "=?",
                    new String[]{String.valueOf(name)}, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                contact = new DiseaseInfo(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3));
                return contact;
            }
        }catch(Exception e){
            Log.v("ExceptionMessage", e.getMessage());
            return null;
        }
           // return contact
           return contact;
    }

    public List<DiseaseInfo> getAllContacts() {
        List<DiseaseInfo> contactList = new ArrayList<DiseaseInfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DISEASE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DiseaseInfo diseaseinfo = new DiseaseInfo();
                diseaseinfo.setId(cursor.getString(0));
                diseaseinfo.setName(cursor.getString(1));
                diseaseinfo.setDrug(cursor.getString(2));
                diseaseinfo.setSymptom(cursor.getString(3));
                contactList.add(diseaseinfo);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(DiseaseInfo diseaseinfo,String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DISEASE_ID, diseaseinfo.getName());
        values.put(DISEASE_NAME, diseaseinfo.getName());
        values.put(DISEASE_DRUG, diseaseinfo.getDrug());
        values.put(DISEASE_SYMPTOMS, diseaseinfo.getSymptom());

        // updating row
        return db.update(TABLE_DISEASE, values, DISEASE_NAME + " = ?",
                new String[] { String.valueOf(name) });
    }

    // Deleting single contact
    public void deleteDiseaseInfo(DiseaseInfo diseaseinfo,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DISEASE, DISEASE_ID + " = ?",
                new String[]{String.valueOf(name)});
        db.close();
    }

    public void deleteAllDiseaseInfo() {
       SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DISEASE,null,null);
        db.close();
    }

    // Getting contacts Count
    public int getDiseaseInfoCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_DISEASE;
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }


    public boolean getLoginSuccess(String diseaseName)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor curRetriveData = db.rawQuery("Select * from "+TABLE_DISEASE+" where Disease_Name="+diseaseName,null);//+DatabaseHandler.TABLE_Player+"= where "+DatabaseHandler.Player_MailId+"='"+Player_MailId+"'", null);

            if(curRetriveData!=null)
            {
                curRetriveData.moveToFirst();

                if(curRetriveData.getCount()>0){
                    return true;
                }else{
                    return false;
                }
            }
        }
        catch (Exception e)
        {

            e.printStackTrace();
            return false;
        }
        return false;

    }



    public Cursor RetriveData(String table)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor curRetriveData = db.rawQuery(table,null);//"Select * from "+TABLE_Player+"where "+where, null);
            if(curRetriveData!=null)
                curRetriveData.moveToFirst();
            return curRetriveData;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public void UpdateTable(String TableName,ContentValues cvUpdate,String Where_Condition)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.update(TableName, cvUpdate, Where_Condition, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public void DeleteTable(String tablename,String whereClause) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            if (whereClause.equals("")) {
                db.delete(tablename, null, null);
            } else {
                db.delete(tablename, whereClause, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

}
