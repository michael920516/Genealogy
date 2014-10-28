package com.example.genealogy;

import java.util.ArrayList;

import com.example.genealogy.data.Member;
import com.example.genealogy.data.MemberDAODBImpl;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity {

	Button bt111,bt112,bt121,bt122;
	Button bt211,bt212,bt221,bt222,bt231,bt232,bt241,bt242,bt251,bt252;
	Button bt311,bt312,bt321,bt322,bt331,bt332,bt341,bt342,bt351,bt352,bt361,bt362,bt371,bt372;
	Button bt411,bt412,bt421,bt422,bt431,bt432,bt441,bt442;
	Button bt511,bt512,bt521,bt522;
	TextView tv111,tv112,tv113;
	int Serial;
	MemberDAODBImpl dao;
	Member [] data;
	ArrayAdapter<String> adapter11,adapter12;
	ArrayList<Boolean> listShow;
	ArrayList<String> getItem;
	Spinner sll11;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map);
        bt111 = (Button) findViewById(R.id.button111);
        bt112 = (Button) findViewById(R.id.button112);
//        bt121 = (Button) findViewById(R.id.button121);
//        bt122 = (Button) findViewById(R.id.button122);
        tv111 = (TextView) findViewById(R.id.textView111);
        tv112 = (TextView) findViewById(R.id.textView112);
        tv113 = (TextView) findViewById(R.id.textView113);
        listShow = new ArrayList();
        getItem = new ArrayList();
       
	    dao = new MemberDAODBImpl(this);	            
        bt111.setOnClickListener(new Button.OnClickListener(){  	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				String CallTable = "兒子"; 
//				Toast.makeText(MapActivity.this, CallTable, Toast.LENGTH_SHORT).show();
			            data = dao.searchCall(CallTable);
//			            Toast.makeText(MapActivity.this, data.length, Toast.LENGTH_SHORT).show();
			            for(int i=0;i<data.length;i++) {
			            	int n = i+111; 
			            	getItem.add(data[i].Name);
			            	switch (n) {
			            	case 111 :
			            		tv111.setText(data[i].Name+"");
			            		break;
			            	case 112 :
			            		tv112.setText(data[i].Name+"");
			            		break;
			            	case 113 :
			            		tv113.setText(data[i].Name+"");
			            		break;
			            	}
			            	Toast.makeText(MapActivity.this, data[i].Name + "" , Toast.LENGTH_SHORT).show();
			            }   
		}});
        
 	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
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
