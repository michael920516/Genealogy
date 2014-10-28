package com.example.genealogy.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MemberDAODBImpl implements MemberDAO {

	Context context;
	public MemberDAODBImpl (Context context){
		this.context = context;
	}
	@Override
	public int add(Member m) {
		// TODO Auto-generated method stub
		int i = 0;
		MemberDBHelper helper = new MemberDBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("Insert into member (Name,Called,Birthday,Tel,Addr,Life) values ('" + m.Name + "' , '" + m.Called  + "' , '" + m.Birthday + "' , '" + m.Tel + "' , '" + m.Addr  + "' , '" + m.Life +"' )");
		db.close();
		return i;
	}	

	@Override
	public Member[] getAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> list = new ArrayList<Member>();
		MemberDBHelper helper = new MemberDBHelper(context);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * From member", null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			list.add(new Member(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
			cursor.moveToNext();
		}
		db.close();
		Member[] rtValue = new Member[list.size()];
		list.toArray(rtValue);
		return rtValue;
				
	}

	@Override
	public Member getMember(int Serial) {
		// TODO Auto-generated method stub
		MemberDBHelper helper = new MemberDBHelper(context);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * from member where Serial =" + Serial, null);
		cursor.moveToFirst();
		Member p = new Member(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
		db.close();
		return p;
	}

	@Override
	public Member[] search(String keyword) {
		// TODO Auto-generated method stub
		ArrayList<Member> list = new ArrayList<Member>();
		MemberDBHelper helper = new MemberDBHelper(context);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * From member where Name like '%" + keyword + "%'", null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			list.add(new Member(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
			cursor.moveToNext();
		}
		db.close();
		Member[] rtValue = new Member[list.size()];
		list.toArray(rtValue);
		return rtValue;
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int Serial) {
		// TODO Auto-generated method stub
		MemberDBHelper helper = new MemberDBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from member where Serial="+ Serial);
		db.close();
	}

	@Override
	public void edit(Member m) {
		// TODO Auto-generated method stub
		MemberDBHelper helper = new MemberDBHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("Update member Set Name = '" + m.Name + "' , Called = '" + m.Called  + "' , Birthday = '" + m.Birthday + "' , Tel = '" + m.Tel + "' , Addr = '" + m.Addr +  "' , Life = " + m.Life + " where Serial= " + m.Serial);
		db.close();
	}
	@Override
	public Member[] searchCall(String keyword) {
		// TODO Auto-generated method stub
		ArrayList<Member> list = new ArrayList<Member>();
		MemberDBHelper helper = new MemberDBHelper(context);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * From member where Called like '%" + keyword + "%'", null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			list.add(new Member(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
			cursor.moveToNext();
		}
		db.close();
		Member[] rtValue = new Member[list.size()];
		list.toArray(rtValue);
		return rtValue;
	}

}
