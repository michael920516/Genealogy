package com.example.genealogy;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.genealogy.data.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Files;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends Activity {
	String[] callItem = {"","本人","妻子","父親","母親","兒子","女兒","哥哥","弟弟","姊姊","妹妹","祖父","祖母","堂哥","堂弟","堂姊","堂妹","舅舅","阿姨","伯父","叔叔","姑姑" };
	int Serial,life;
	Context context;
	EditText et1, et3, et4, et5;
	CheckBox cb1, cb2;
	ImageView iv1;
	Button bt1, bt2, bt3;
	Spinner sp2;
	ArrayAdapter<String> itemList2;
	Object item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		context = this;
        et1 = (EditText) findViewById(R.id.editText1);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);  
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        iv1 = (ImageView)findViewById(R.id.imageView1);

        
         
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
        itemList2 = new ArrayAdapter<String>(AddActivity.this, android.R.layout.simple_spinner_item,callItem); 
        sp2.setAdapter(itemList2);
        sp2.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				// TODO Auto-generated method stub
				item = parent.getItemAtPosition(pos);
				Log.d("mike", "item= " + item+"");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
        
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        
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
		        MemberDAO dao = new MemberDAODBImpl(context);
		        int i = dao.add(new Member(Serial,et1.getText().toString(),item.toString(),et3.getText().toString(),et4.getText().toString(),et5.getText().toString(),life));		        
//		        Log.d("DB", "rowId:" + i);
		        InputStream is = null;
		        OutputStream os = null;
		        File dest = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "p" + i + ".jpg");
		        
//		        Log.d("DB", dest.toString());

		        if (photoSourcePath != null)
		        {
			        try {
			            is = new FileInputStream(photoSourcePath);
			            os = new FileOutputStream(dest);
			            byte[] buffer = new byte[1024];
			            int length;
			            while ((length = is.read(buffer)) > 0) {
			                os.write(buffer, 0, length);
			            }
			        } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
			            try {
							is.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            try {
							os.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
		
		        }

		        
				finish();
			}});
		bt3.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// dispatchTakePictureIntent();
				
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(intent, 567);
			}});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
	    	Uri uri = Uri.parse(mCurrentPhotoPath);
	    	iv1.setImageURI(uri);
	    }
	}
	
	static final int REQUEST_TAKE_PHOTO = 1;

	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // Ensure that there's a camera activity to handle the intent
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        // Create the File where the photo should go
	        File photoFile = null;
	        try {
	            photoFile = createImageFile();
	        } catch (IOException ex) {
	            // Error occurred while creating the File

	        }
	        // Continue only if the File was successfully created
	        if (photoFile != null) {
	            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
	                    Uri.fromFile(photoFile));
	            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
	        }
	    }
	}
	
	String mCurrentPhotoPath, photoSourcePath;
	
	private File createImageFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "JPEG_" + timeStamp + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    photoSourcePath = image.getAbsolutePath();
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
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
