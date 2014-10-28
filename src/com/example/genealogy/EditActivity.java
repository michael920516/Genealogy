package com.example.genealogy;

import com.example.genealogy.data.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditActivity extends Activity {
	String[] callItem = {"","本人","妻子","父親","母親","兒子","女兒","哥哥","弟弟","姊姊","妹妹","祖父","祖母","堂哥","堂弟","堂姊","堂妹","舅舅","阿姨","伯父","叔叔","姑姑" };
	int Serial,life;
	Context context;
	EditText et1, et2, et3, et4, et5;
	CheckBox cb1, cb2;
	ImageView iv1;
	Button bt1, bt2;
	Spinner sp2;
	ArrayAdapter<String> itemList2;
	Object item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		Serial = getIntent().getIntExtra("Serial", 0);
		context = this;
        MemberDAO dao = new MemberDAODBImpl(this);
        Member m = dao.getMember(Serial);
        et1 = (EditText) findViewById(R.id.editText1);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);  
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        iv1 = (ImageView)findViewById(R.id.imageView1);
        et1.setText(m.Name);
//        et2.setText(m.Called);
        et3.setText(m.Birthday);
        et4.setText(m.Tel);
        et5.setText(m.Addr);
         
        cb1.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked) {
					life = 1;
				}else {
					life = 0;
				}
			}});

        itemList2 = new ArrayAdapter<String>(EditActivity.this, android.R.layout.simple_spinner_item,callItem); 
        sp2.setAdapter(itemList2);
        sp2.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				// TODO Auto-generated method stub
				item = parent.getItemAtPosition(pos);
//				Log.d("mike", "item= " + item+"");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});       
        
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        
        bt1.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}});
        bt2.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Member m = new Member(Serial,et1.getText().toString(),item.toString().toString(),et3.getText().toString(),et4.getText().toString(),et5.getText().toString(),life);
		        MemberDAO dao = new MemberDAODBImpl(context);
//		        Log.d("mike", "m= " + et1.getText().toString() + item.toString()+et3.getText().toString()+et4.getText().toString()+et5.getText().toString()+life);
		        dao.edit(m);
		        finish();
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
