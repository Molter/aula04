package br.gabriel.molter.simplecrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	
	CrudAdapter mAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		
		mAdapter= new CrudAdapter(this);
		listView.setAdapter(mAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				Intent intent = new Intent(getApplicationContext(), StuddentActivity.class);
				intent.putExtra("ID", id);
				
				startActivity(intent);
			}
			
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		mAdapter.refreshData();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_item_menu:
			Intent intent = new Intent(this, StuddentActivity.class);
			startActivity(intent);
			break;
		case R.id.refresh_list:
			mAdapter.refreshData();
			break;
		}
		
		return true;
	}
}