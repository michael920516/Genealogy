package com.example.genealogy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemberDBHelper extends SQLiteOpenHelper {

	final static String DB_NAME = "Member";
	final static int DB_VERSION = 1;
	public MemberDBHelper(Context context) {
		super(context, DB_NAME,null,DB_VERSION);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	db.execSQL("Create table member (Serial integer Primary Key AUTOINCREMENT, Name varchar(20), Called varchar(10), Birthday varchar(10),Tel varchar(20), Addr varchar(50),Life integer(1))");
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
}
