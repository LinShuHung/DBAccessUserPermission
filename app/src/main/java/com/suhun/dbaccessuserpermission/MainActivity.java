package com.suhun.dbaccessuserpermission;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
        mySqliteOpenHelper = new MySqliteOpenHelper(this, "suhunDB", null, 1);
        db = mySqliteOpenHelper.getWritableDatabase();
    }

    public void queryFun(View view){

    }

    public void insertFun(View view){

    }

    public void updateFun(View view){

    }

    public void deleteFun(View view){

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