package com.example.genealogy;

import com.example.genealogy.data.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	EditText et1,et2,et3,et4,et5;
	CheckBox cb1;
	ImageView iv1;
	int Serial;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		Intent it = getIntent();
		Serial = it.getIntExtra("Serial", 0);
		
	}

	@Override
	protected void onResume()
	{
		super.onResume();
        MemberDAODBImpl dao = new MemberDAODBImpl(this);
        Member m = dao.getMember(Serial);        
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        iv1 = (ImageView)findViewById(R.id.imageView1);
        et1.setText(m.Name);
        et2.setText(m.Called);
        et3.setText(m.Birthday);
        et4.setText(m.Tel);
        et5.setText(m.Addr);
        boolean life = true;
        if(m.Life == 0) {
        	life = false;
        }
        cb1.setChecked(life);        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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
		
		if (id == R.id.action_edit)
		{
			Intent i = new Intent(ProfileActivity.this, EditActivity.class);
			i.putExtra("Serial", Serial);
			startActivity(i);
		}

		return super.onOptionsItemSelected(item);
	
	}
}
