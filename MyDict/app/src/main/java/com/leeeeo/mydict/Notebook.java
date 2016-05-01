package com.leeeeo.mydict;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class Notebook extends ListActivity{



    private SimpleCursorAdapter adapter;
    private Db db;
    private SQLiteDatabase dbRead,dbWrite;
    private AdapterView.OnItemLongClickListener listViewItemLongClickListener=new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                       final int position, long id) {

            new AlertDialog.Builder(Notebook.this).setTitle("提醒").setMessage("您确定要删除该项吗").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Cursor c = adapter.getCursor();
                    c.moveToPosition(position);

                    int itemId = c.getInt(c.getColumnIndex("_id"));
                    dbWrite.delete("vocab", "_id=?", new String[]{itemId+""});
                    refreshListView();
                }
            }).show();

            return true;
        }
    };

    public Notebook() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        adapter = new SimpleCursorAdapter(this, R.layout.user_list_cell, null, new String[]{"word","trans"}, new int[]{R.id.tvName,R.id.tvSex});
        setListAdapter(adapter);

        refreshListView();

        getListView().setOnItemLongClickListener(listViewItemLongClickListener);

//		Db db = new Db(this);
//		SQLiteDatabase dbWrite = db.getWritableDatabase();
//
//		ContentValues cv = new ContentValues();
//		cv.put("name", "小张");
//		cv.put("sex", "男");
//		dbWrite.insert("user", null, cv);
//
//		cv = new ContentValues();
//		cv.put("name", "小李");
//		cv.put("sex", "女");
//		dbWrite.insert("user", null, cv);
//
//		dbWrite.close();

//		SQLiteDatabase dbRead = db.getReadableDatabase();
//		Cursor c = dbRead.query("user", null, null, null, null, null, null);
//
//		while(c.moveToNext()){
//			String name = c.getString(c.getColumnIndex("name"));
//			String sex = c.getString(c.getColumnIndex("sex"));
//			System.out.println(String.format("name=%s,sex=%s", name,sex));
//		}
    }


    private void refreshListView(){
        Cursor c = dbRead.query("vocab", null, null, null, null, null, null);
        adapter.changeCursor(c);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
