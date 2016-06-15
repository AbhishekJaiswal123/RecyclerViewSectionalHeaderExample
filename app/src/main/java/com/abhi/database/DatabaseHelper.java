package com.abhi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Abhishek on 10-09-2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
	
	private final static String DATABASE_NAME = "com.abhi.database";
	private final static int DATABASE_VERSION = 1;
	
	private static DatabaseHelper mDatabaseHelper = null;
	
	public static DatabaseHelper getDBinstance(Context pContext){
		if(mDatabaseHelper == null){
			mDatabaseHelper = new DatabaseHelper(pContext);
		}
		return mDatabaseHelper;
		
	}
	
	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(CREATE_TABLE_IMAGE);
				

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if(newVersion > oldVersion){
			db.execSQL("DROP TABLE IF EXISTS "+CREATE_TABLE_IMAGE);
		}
	}
	
	
	public static final String TABLE_IMAGE = "images";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ALBUM_ID = "albumId";
	public static final String COLUMN_IMAGEID = "id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_URL = "url";
	public static final String COLUMN_THUMBURL = "thumbnailUrl";

	
	
	private String CREATE_TABLE_IMAGE = "CREATE TABLE "+TABLE_IMAGE
			+"("
			+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+COLUMN_ALBUM_ID+" TEXT, "
			+COLUMN_IMAGEID+" TEXT, "
			+COLUMN_TITLE+" TEXT, "
			+COLUMN_URL+" TEXT, "
			+COLUMN_THUMBURL+" TEXT "
			+");";
	
}
