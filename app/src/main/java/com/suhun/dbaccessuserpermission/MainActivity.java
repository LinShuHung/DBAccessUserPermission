package com.suhun.dbaccessuserpermission;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private TextView resultLog, birthday;
    private EditText idUpdate, idDelete, name, tel;
    private MySqliteOpenHelper mySqliteOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDataBase();
    }

    private void initView(){
        resultLog = findViewById(R.id.lid_result);
        birthday = findViewById(R.id.lid_birthdayInput);
        idUpdate = findViewById(R.id.lid_cidUpdateInput);
        idDelete = findViewById(R.id.lid_cidDeleteInput);
        name = findViewById(R.id.lid_cnameInput);
        tel = findViewById(R.id.lid_ctelInput);
    }

    private void initDataBase(){
        mySqliteOpenHelper = new MySqliteOpenHelper(this, "suhunDataBase", null, 1);
        db = mySqliteOpenHelper.getWritableDatabase();
    }

    public void queryFun(View view){
        execquery();
    }

    private void execquery(){
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = db.query("cust", null, null, null, null, null, null);

        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow("cid"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("cname"));
            String tel = cursor.getString(cursor.getColumnIndexOrThrow("ctel"));
            String birthday = cursor.getString(cursor.getColumnIndexOrThrow("cbirthday"));

            stringBuffer.append(String.format("%s:%s:%s:%s\n", id, name, tel, birthday));
        }
        resultLog.setText(stringBuffer);
    }

    public void insertFun(View view){
        ContentValues values = new ContentValues();
        values.put("cname", name.getText().toString());
        values.put("ctel", tel.getText().toString());
        values.put("cbirthday", birthday.getText().toString());
        db.insert("cust", null, values);
        execquery();
        name.setText("");
        tel.setText("");
        birthday.setText("");
    }

    public void updateFun(View view){
        ContentValues values = new ContentValues();
        values.put("cname", name.getText().toString());
        values.put("ctel", tel.getText().toString());
        values.put("cbirthday", birthday.getText().toString());
        db.update("cust", values, "cid = ?", new String[]{idUpdate.getText().toString()});
        execquery();
        name.setText("");
        tel.setText("");
        birthday.setText("");
    }

    public void deleteFun(View view){
        db.delete("cust", "cid = ?", new String[]{idDelete.getText().toString()});
        execquery();
    }

    public void userPermissionCheckFun(View view){

    }

    public void birthdaySelectFun(View view){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                birthday.setText(String.format("%s/%s/%s", year, month+1, dayOfMonth));
            }
        }, 2023, 0, 1);
        dialog.show();
    }
}