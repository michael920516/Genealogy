package com.example.genealogy;

import java.io.File;
import java.util.ArrayList;

import com.example.genealogy.data.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	Member[] data;
	ArrayList<Boolean> listShow;
	Context context;
	ListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		context = this;
		listShow = new ArrayList<Boolean>();
		//MyTest();
	}
    protected void onResume()
    {
    	super.onResume();
        MemberDAO dao = new MemberDAODBImpl(this);
        data = dao.getAll();
        for (int i=0;i<data.length;i++)
        {
        	listShow.add(false);
        }
        ListView lv = (ListView) findViewById(R.id.listView1);
        adapter = new ListAdapter(this,data);
        lv.setAdapter(adapter);   

        lv.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				int Serial = data[position].Serial;
				Intent it = new Intent(SecondActivity.this, ProfileActivity.class);
				it.putExtra("Serial", Serial);
				startActivity(it);
				// Toast.makeText(context, "" + position, Toast.LENGTH_LONG).show();
		}});

    }
    void MyTest()
    {
        MemberDAO dao = new MemberDAODBImpl(this);
        dao.add(new Member(0, "Bob", "������", "19330519","123456789","Taipei", 1));
        dao.add(new Member(0, "Jone", "������", "33051922","23456789","Taichen", 1));
        dao.add(new Member(0, "ken", "������", "0519233","3456789","Tainan", 1));
        data = dao.getAll();
        for (int i=0;i<data.length;i++)
        {
        	Log.d("DB", data[i].Name + "," + data[i].Called);
        }
        // dao.removeAll();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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
        if (id == R.id.action_add)
        {
        	Intent it = new Intent(SecondActivity.this, AddActivity.class);
        	startActivity(it);
        }
        
        if (id == R.id.action_delete)
        {
        	AlertDialog.Builder alert = new AlertDialog.Builder(context);
        	alert.setTitle("請確認刪除?");

        	alert.setPositiveButton("確認", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					MemberDAO dao = new MemberDAODBImpl(context);
			        Log.d("PHONE", "Befor for loop");
					for (int i=0;i<listShow.size();i++)
					{
						if (listShow.get(i))
						{   
					        dao.delete(data[i].Serial);
						}
					}
			        Log.d("PHONE", "Before data re-getAll");
			        data = dao.getAll();
			        Log.d("PHONE", "Before listShow Clear");
			        listShow.clear();
			        for (int i=0;i<data.length;i++)
			        {
			        	listShow.add(false);
			        }
			        Log.d("PHONE", "listShow Size:" + listShow.size() + ", data size:" + data.length);
			        adapter.data = data;
			        adapter.notifyDataSetChanged();
					
				}});
        	alert.setNegativeButton("取消", null);
        	alert.show();
        	
        }
        
        if (id == R.id.action_search)
        {
        	
        	AlertDialog.Builder alert = new AlertDialog.Builder(this);
        	alert.setTitle("請輸入搜尋關鍵字:");
            final EditText input = new EditText(this);
            alert.setView(input);        	
        	alert.setPositiveButton("確定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
		            MemberDAO dao = new MemberDAODBImpl(context);
		            data = dao.search(input.getText().toString());
		            for (int i=0;i<data.length;i++)
		            {
		            	listShow.add(false);
		            }
		            adapter.data = data;		            
		        	adapter.notifyDataSetChanged();
				}});
        	alert.setNegativeButton("取消", null);
        	alert.show();
        	
	
        }
		return super.onOptionsItemSelected(item);
	}
    class ListAdapter extends BaseAdapter {
    	Member[] data;
    	Activity act;
    	private LayoutInflater inflater = null;
    	public ListAdapter(Activity a, Member[] data) {
    		this.act = a;
    		this.data = data;
    		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	}
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			View v = inflater.inflate(R.layout.listview, null);
			TextView tv = (TextView) v.findViewById(R.id.tvName);
			tv.setText(data[position].Name);
			TextView tv1 = (TextView) v.findViewById(R.id.tvCalled);
			tv1.setText(data[position].Called); 
			
			ImageView iv = (ImageView) v.findViewById(R.id.imageView1);
		
	        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "p" + data[position].Serial + ".jpg");	        
	        if (file.exists()) {	        
	        	Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + "p" + data[position].Serial + ".jpg");
	        	iv.setImageBitmap(bm);
	        }else{        
				for(int pic=1;pic<=data.length;pic++) {
					if(position == pic) {
					String uri = "@drawable/p" + pic;					
					int imageResource = getResources().getIdentifier(uri, null, getPackageName());
					Log.d("mike", "imageResource= " + imageResource);
					iv.setImageResource(imageResource);
//					iv.setImageResource(R.drawable.p1);
					}
				}
	        }
			
			CheckBox chk = (CheckBox) v.findViewById(R.id.checkBox1);
			chk.setChecked(listShow.get(position));
			chk.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					listShow.set(position, arg1);
				}});
			
			return v;
		}
    	
    }

}
