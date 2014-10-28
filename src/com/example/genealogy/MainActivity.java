package com.example.genealogy;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	Button bt1;
	TextView tv1;
	String who1rid,who2cid;
	Map<String, String> who1,who2,called;
	Collection<Entry<String, String>> calledSet;
	Iterator<Entry<String, String>> it;
	Spinner spinnerWho1, spinnerWho2 ;                                                                  
	ArrayAdapter<String> who1ItemList,who2ItemList; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bt1 = (Button)findViewById(R.id.button1);
        tv1 = (TextView)findViewById(R.id.textView4);
        
    }	
   
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// spinner
		String[] arrayItem = {"","本人","妻子","父親","母親","兒子","女兒","哥哥","弟弟","姊姊","妹妹","舅舅","阿姨","伯父","叔叔","姑姑" };
		
		String[] arrayOfCalled = {"a1b1;本人","a1b2;妻子","a1b3;父親","a1b4;母親","a1b5;兒子","a1b6;女兒","a1b7;哥哥","a1b8;弟弟","a1b9;姊姊","a1b10;妹妹","a1b11;舅舅","a1b12;阿姨","a1b13;伯父","a1b14;叔叔","a1b15;姑姑",
				"a2b1;丈夫","a2b2;本人","a2b3;岳父","a2b4;岳母","a2b5;兒子","a2b6;女兒","a2b7;哥哥","a2b8;弟弟","a2b9;姊姊","a2b10;妹妹","a2b11;舅舅","a2b12;阿姨","a2b13;伯父","a2b14;叔叔","a2b15;姑姑",
				"a3b1;兒子","a3b2;母親","a3b3;祖父","a3b4;祖母","a3b5;兄弟","a3b6;姊妹","a3b7;伯父","a3b8;叔叔","a3b9;姑姑","a3b10;姑姑","a3b11;舅公","a3b12;姨婆","a3b13;伯公","a3b14;叔公","a3b15;姑婆",
				"a4b1;兒子","a4b2;無此稱呼","a4b3;外祖父","a4b4;外祖母","a4b5;兄弟","a4b6;姊妹","a4b7;舅舅","a4b8;舅舅","a4b9;阿姨","a4b10;阿姨","a4b11;外舅公","a4b12;姨婆","a4b13;外伯公","a4b14;外叔公","a4b15;外姑婆",
				"a5b1;爸爸","a5b2;媳婦","a5b3;本人","a5b4;妻子","a5b5;孫子","a5b6;孫女","a5b7;兒子","a5b8;兒子","a5b9;女兒","a5b10;女兒","a5b11;兄弟(妻)","a5b12;姊妹(妻)","a5b13;哥哥","a5b14;弟弟","a5b15;姊姊",
				"a6b1;爸爸","a6b2;無此稱呼","a6b3;本人","a6b4;妻子","a6b5;外孫","a6b6;外孫女","a6b7;兒子","a6b8;兒子","a6b9;女兒","a6b10;女兒","a6b11;兄弟(妻)","a6b12;姊妹(妻)","a6b13;哥哥","a6b14;弟弟","a6b15;姊姊",
				"a7b1;弟弟","a7b2;嫂嫂","a7b3;爸爸","a7b4;媽媽","a7b5;姪子","a7b6;姪女","a7b7;兄弟","a7b8;兄弟","a7b9;姊姊","a7b10;姊妹","a7b11;舅舅","a7b12;阿姨","a7b13;伯父","a7b14;伯叔","a7b15;姊妹",
				"a8b1;哥哥","a8b2;媳","a8b3;爸爸","a8b4;媽媽","a8b5;姪子","a8b6;姪女","a8b7;兄弟","a8b8;兄弟","a8b9;姊妹","a8b10;妹妹","a8b11;舅舅","a8b12;阿姨","a8b13;伯叔","a8b14;叔叔","a8b15;姊妹",
				"a9b1;弟弟","a9b2;無此稱呼","a9b3;爸爸","a9b4;媽媽","a9b5;外甥","a9b6;外甥女","a9b7;兄弟","a9b8;兄弟","a9b9;姊姊","a9b10;姊妹","a9b11;舅舅","a9b12;阿姨","a9b13;伯父","a9b14;伯叔","a9b15;姑姑",
				"a10b1;哥哥","a10b2;無此稱呼","a10b3;爸爸","a10b4;媽媽","a10b5;外甥","a10b6;外甥女","a10b7;兄弟","a10b8;兄弟","a10b9;姊妹","a10b10;妹妹","a10b11;舅舅","a10b12;阿姨","a10b13;伯叔","a10b14;叔叔","a10b15;姑姑",
				"a11b1;外甥(女)","a11b2;舅媽","a11b3;舅公","a11b4;外祖母","a11b5;表兄弟","a11b6;表姊妹","a11b7;舅舅","a11b8;舅舅","a11b9;阿姨","a11b10;阿姨","a11b11;外舅公","a11b12;外姨婆","a11b13;外伯公","a11b14;外叔公","a11b15;外姑婆",
				"a12b1;外甥(女)","a12b2;無此稱呼","a12b3;外祖父","a12b4;外祖母","a12b5;表兄弟","a12b6;表姊妹","a12b7;舅舅","a12b8;舅舅","a12b9;阿姨","a12b10;阿姨","a12b11;外舅公","a12b12;外姨婆","a12b13;外伯公","a12b14;外叔公","a12b15;外姑婆",
				"a13b1;姪子(女)","a13b2;伯母","a13b3;祖父","a13b4;祖母","a13b5;堂兄弟","a13b6;堂姊妹","a13b7;伯父","a13b8;伯叔","a13b9;姑姑","a13b10;姑姑","a13b11;舅公","a13b12;姨婆","a13b13;伯公","a13b14;伯叔公","a13b15;姑婆",
				"a14b1;姪子(女)","a14b2;嬸嬸","a14b3;祖父","a14b4;祖母","a14b5;堂兄弟","a14b6;堂姊妹","a14b7;伯叔","a14b8;叔叔","a14b9;姑姑","a14b10;姑姑","a14b11;舅公","a14b12;姨婆","a14b13;伯公","a14b14;叔公","a14b15;姑婆",
				"a15b1;表姪(女)","a15b2;姑丈","a15b3;祖父","a15b4;祖母","a15b5;表兄弟","a15b6;表姊妹","a15b7;伯叔","a15b8;伯叔","a15b9;姑姑","a15b10;姑姑","a15b11;舅公","a15b12;姨婆","a15b13;伯公","a15b14;伯叔公","a15b15;姑婆"
		};

		//Called
		called = new TreeMap<String, String>(); 
		for (String s : arrayOfCalled) {
			String[] array = s.split(";");
			String sKey = "", sValue = "";
			if (array.length > 1) {
				sKey = array[0];
				sValue = array[1];
				called.put(sKey, sValue);
			}
		}
		
		Context mContext;            
	    mContext = this.getApplicationContext();
		spinnerWho1 = (Spinner)findViewById(R.id.spinner1);	
		spinnerWho2 = (Spinner)findViewById(R.id.spinner2);

		who1ItemList = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, arrayItem);
		who2ItemList = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, arrayItem);
		spinnerWho1.setAdapter(who1ItemList);
		spinnerWho2.setAdapter(who2ItemList);
		
		spinnerWho1.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				// TODO Auto-generated method stub
				Object item1 = parent.getItemAtPosition(pos);
				who1rid = "a" + id;
//				Toast.makeText(MainActivity.this, who1rid + "," + item1+"", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
		}});
		spinnerWho2.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				// TODO Auto-generated method stub
				Object item2 = parent.getItemAtPosition(pos);
				who2cid = "b" + id;
//				Toast.makeText(MainActivity.this, who2cid + "," +  item2+"", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
		}});
		
		bt1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calledSet = called.entrySet();	
				it = calledSet.iterator();    
				String calledId = who1rid + who2cid;
				Log.d("mike", who1rid + "," + who2cid); 
				boolean flag = true;
			    while(it.hasNext()==flag) {	    	
			    	String key = it.next().getKey();
//			    	Toast.makeText(MainActivity.this, "calledId, key= " + flag + calledId +","+ key,Toast.LENGTH_SHORT).show();
			    	if(key.equals(calledId)) {
//			    		Log.d("mike", "key=calledId");
			    		String value = called.get(key);	 
			    		tv1.setText(value);
			    		flag = false;			    		
			    	}	
			    }
				if(flag) {
					tv1.setText("no data");
				}
			}});		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        if (id == R.id.action_profile) {
        	Intent it = new Intent(MainActivity.this,SecondActivity.class);
        	startActivity(it);
        }
        if (id == R.id.action_map) {
        	Intent it = new Intent(MainActivity.this,MapActivity.class);
        	startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
