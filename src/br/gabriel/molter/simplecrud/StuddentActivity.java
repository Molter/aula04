package br.gabriel.molter.simplecrud;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StuddentActivity extends Activity {
	
	EditText mName, mGrade;
	private long mId = 0;
	private SQLiteDatabase mDb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.studdent_layout);
		
		Button button = (Button) findViewById(R.id.submitButton);
		
		mName = (EditText)findViewById(R.id.studdent_name);
		mGrade = (EditText)findViewById(R.id.studdent_grade);
		
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = mName.getText().toString();
				String grade = mGrade.getText().toString();
				
				if(name.isEmpty() || grade.isEmpty()){
					Toast.makeText(getApplicationContext(), getString(R.string.field_errors), Toast.LENGTH_LONG).show();
					return;
				}
				insertIntoDb(name, grade);
				
			}
		});
		
		DbHelper helper = new DbHelper(this);
		mDb = helper.getWritableDatabase();
		
		
		if(getIntent().getExtras() != null){
			mId = getIntent().getExtras().getLong("ID");
			
			Cursor result = mDb.query("studdent", new String[]{"name", "grade"},"_id = ?" , new String[]{String.valueOf(mId)}, null, null, null);
			
			result.moveToFirst();
			mName.setText(result.getString(result.getColumnIndex("name")));
			mGrade.setText(result.getString(result.getColumnIndex("grade")));
			
		}
		
		
	}
	
	private void insertIntoDb(String name, String grade){
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("grade", grade);
		
		
		if(mId > 0){
			mDb.update("studdent", values, "_id = ?", new String[]{String.valueOf(mId)});
		}else{
			
			mDb.insert("studdent", null, values);
		}
		
		Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show();
		
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if(mId > 0){
			getMenuInflater().inflate(R.menu.studdent, menu);
		}
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.delete_item_menu:
			mDb.delete("studdent", "_id = ?", new String[]{String.valueOf(mId)});
			Toast.makeText(this, getString(R.string.deleted), Toast.LENGTH_LONG).show();
			finish();
			break;
		}
		return true;
	}
	
}
