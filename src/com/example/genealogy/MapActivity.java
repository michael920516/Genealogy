package com.example.genealogy;

import java.util.ArrayList;

import com.example.genealogy.data.Member;
import com.example.genealogy.data.MemberDAODBImpl;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity {

	Button bt111,bt112,bt121,bt122;
	Button bt211,bt212,bt221,bt222,bt231,bt232,bt241,bt242,bt251,bt252;
	Button bt311,bt312,bt321,bt322,bt331,bt332,bt341,bt342,bt351,bt352,bt361,bt362,bt371,bt372;
	Button bt411,bt412,bt421,bt422,bt431,bt432,bt441,bt442;
	Button bt511,bt512,bt521,bt522;
	TextView mType,mValue;
	int Serial;
	MemberDAODBImpl dao;
	Member [] data;
	ArrayAdapter<String> adapter11,adapter12;
	ArrayList<Boolean> listShow;
	ArrayList<String> getItem;
	String [] mTitle = {"祖父","祖母","外祖父","外祖母",
						"姑丈","姑媽","伯母"};
	Spinner sll11;
	View relativeLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map);
//        bt111 = (Button) findViewById(R.id.button111);
//        bt112 = (Button) findViewById(R.id.button112);
//        bt121 = (Button) findViewById(R.id.button121);
//        bt122 = (Button) findViewById(R.id.button122);
        
//        tv1111 = (TextView) findViewById(R.id.textView1111);
//        tv1112 = (TextView) findViewById(R.id.textView1112);
//        tv1113 = (TextView) findViewById(R.id.textView1113);

        // add TextView  

        //end add TextView
        
        
        listShow = new ArrayList();
        getItem = new ArrayList();
       
       
	    dao = new MemberDAODBImpl(this);	
	//first level button
	    MakeBT(25,55,0);   
	    MakeBT(48,55,1);
	    MakeBT(321,55,2);
	    MakeBT(340,55,3);
	//Second level button
	    MakeBT(6,190,4);
	    MakeBT(25,190,5);
	    MakeBT(60,190,6);
	    MakeBT(79,190,7);
	    MakeBT(180,190,8);
	    MakeBT(199,190,9);
	    MakeBT(320,190,10);
	    MakeBT(339,190,11);
	    MakeBT(370,190,12);
	    MakeBT(389,190,13);
	 //Third level button
	    MakeBT(6,325,14);
	    MakeBT(25,325,15);
	    MakeBT(60,325,16);
	    MakeBT(79,325,17);
	    MakeBT(115,325,18);
	    MakeBT(134,325,19);	    	    
	    MakeBT(180,325,20);
	    MakeBT(199,325,21);
	    MakeBT(250,325,22);
	    MakeBT(269,325,23);
	    MakeBT(321,325,24);
	    MakeBT(340,325,25);	    
	    MakeBT(370,325,26);
	    MakeBT(389,325,27);
	 //Forth level button
	    MakeBT(115,470,28);
	    MakeBT(134,470,29);	    	    
	    MakeBT(161,470,30);
	    MakeBT(180,470,31);
	    MakeBT(200,470,32);
	    MakeBT(219,470,33);
	    MakeBT(250,470,34);
	    MakeBT(269,470,35);
	 //Fifth level button
	    MakeBT(161,615,36);
	    MakeBT(180,615,37);
	    MakeBT(200,615,38);
	    MakeBT(219,615,39);    
	}
    void MakeTV(int left,int top,int right,int bottom) {
    	relativeLayout = findViewById(R.id.rLayout);
        mType = new TextView(MapActivity.this);
        mType.setLayoutParams(new TableLayout.LayoutParams(
        		LayoutParams.WRAP_CONTENT,
        		LayoutParams.WRAP_CONTENT,1f));
        mType.setPadding(left, top, right, bottom);
        mType.setTextSize(18);
        mType.setTypeface(Typeface.DEFAULT_BOLD);
//        mType.setGravity(Gravity.LEFT | Gravity.CENTER);
//        mType.setText(t);
        ((RelativeLayout) relativeLayout).addView(mType);  
    }
    void MakeBT(int left,int top, int i) {
    	final int mt = i;
    	relativeLayout = findViewById(R.id.rLayout);
        Button bt = new Button(this);
        bt.setLayoutParams(new LayoutParams(25,100));     
        bt.setId(i);
        bt.setBackgroundColor(0000);
        ((RelativeLayout) relativeLayout).addView(bt);  
        RelativeLayout.LayoutParams bt2=(RelativeLayout.LayoutParams)bt.getLayoutParams();
        bt2.leftMargin=left;//your left margin here
        bt2.topMargin=top;//you       
        ((Button)findViewById(i)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String CallTable = mTitle[mt].toString();
				Toast.makeText(MapActivity.this, CallTable, Toast.LENGTH_SHORT).show();
	            data = dao.searchCall(CallTable);
	            if(data.length > 0) {
	            	for(int count=0;count<data.length;count++) {
		            	int n = count+1;		            
		            	getItem.add(data[count].Name);
		            	switch (n) {
		            	case 1 :
		            		MakeTV( 150, 120, 130, 120 );
		            		mType.setText(data[count].Name+"");
		            		break;
		            	case 2 :
		            		MakeTV( 150, 140, 130, 120 );
		            		mType.setText(data[count].Name+"");
		            		break;
		            	case 3 :
		            		MakeTV( 150, 150, 130, 120 );
		            		mType.setText(data[count].Name+"");
		            		break;
		            	}
	            	}  	
	            }else {
	            	MakeTV( 150, 120, 130, 120 );
	            	mType.setText("無資料");     
	            }            
			}});

    }
    void delay(int d) {
	    final int welcomeScreenDisplay = d;
	    /** create a thread to show splash up to splash time */
	    Thread welcomeThread = new Thread() {
	        int wait = 0;
	        @Override
	        public void run() {
	            try {
	                super.run();
	                while (wait < welcomeScreenDisplay) {
	                    sleep(100);
	                    wait += 100;
	                }
	            } catch (Exception e) {
	                Log.d("mike", "EXc=" + e);
	            }      
	        }    
	    };
	    welcomeThread.start();
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
