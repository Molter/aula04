package br.gabriel.molter.simplecrud;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CrudAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInfalter;
	private ArrayList<Student> mStuddents;

	public CrudAdapter(Context context) {
		mContext = context;
		mInfalter = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);

		refreshData();
		
	}

	public void refreshData() {
		mStuddents= new ArrayList<Student>();
		
		DbHelper helper = new DbHelper(mContext);
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		Cursor resultCursor = db.query("studdent", new String[]{"_id", "name", "grade"}, null, null, null, null, "name ASC");
		
		if(resultCursor.getCount() == 0){
			return;
		}
		
		resultCursor.moveToFirst();
		do {
			Student studdent = new Student();
			
			studdent.setId(resultCursor.getLong(resultCursor.getColumnIndex("_id")));
			
			studdent.setName(resultCursor.getString(resultCursor.getColumnIndex("name")));
			
			studdent.setGrade(resultCursor.getString(resultCursor.getColumnIndex("grade")));
			
			mStuddents.add(studdent);
		} while (resultCursor.moveToNext());
			
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mStuddents.size();
	}

	@Override
	public Object getItem(int position) {
		return mStuddents.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mStuddents.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View layout = mInfalter.inflate(R.layout.item_list, null);
		
		Student studdent = mStuddents.get(position);
		
		TextView name = (TextView) layout.findViewById(R.id.list_item_nome);
		name.setText(studdent.getName());
		
		TextView grade = (TextView) layout.findViewById(R.id.list_item_grade);
		grade.setText(studdent.getGrade());
		
		return layout;
	}

}
