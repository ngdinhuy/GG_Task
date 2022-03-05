package com.example.bt1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView listViewCate,listViewDetail;
    ImageButton btnAdd,btnListCV;
    Button btnAddDialog,btnCancelDialog;
    EditText edtTenDialog,edtNgayDialog,edtGioDialog,edtGhiChuDialog;
    ListView lstViewCV;
    ArrayList<Category> arrCategory;
    ArrayList<Detail> arrDetail;
    Database databaseCate,databaseDetail;
    String cateDuocChon="general";
    int REQUEST_CODE_INTENT=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });

        btnListCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogList();
            }
        });

        databaseCate=new Database(MainActivity.this, "Database.sqlite",null,1);
        databaseCate.querry("CREATE TABLE IF NOT EXISTS category (ID INTEGER PRIMARY KEY AUTOINCREMENT, ten varcha(50))");

        databaseDetail=new Database(MainActivity.this, "Database.sqlite",null,1);
        databaseDetail.querry("create table if not EXISTS detail (ID integer PRIMARY KEY AUTOINCREMENT, IDCATE VARCHAR(10), TEN varchar(50),NGAY VARCHAR(50), GIO VARCHAR(50), GHICHU VARCHAR(50), isChecked bit)");

        xuatListView();
        //databaseDetail.querry("DROP TABLE detail");

//        databaseCate.querry("INSERT INTO category values(null,'CV1')");
//        databaseCate.querry("INSERT INTO category values(null,'CV2')");
        //databaseCate.querry("DROP TABLE CATEGORY");
    }

    private void anhXa(){
        listViewDetail=(ListView) findViewById(R.id.listViewCV);
        btnListCV=(ImageButton) findViewById(R.id.imageButtonList);
        lstViewCV=(ListView) findViewById(R.id.listViewCV);
        btnAdd=(ImageButton) findViewById(R.id.buttonAdd);
    }
    private void xuatListView(){
        arrDetail=new ArrayList<>();
        Cursor cursor=databaseDetail.getData("SELECT * FROM detail where IDCATE like '"+cateDuocChon+"'");
        while(cursor.moveToNext()){
            String ten=cursor.getString(2);
            String ngay=cursor.getString(3);
            String gio=cursor.getString(4);
            String ghiChu=cursor.getString(5);
            int isChecked=cursor.getInt(6);
            Detail detail=new Detail(cateDuocChon,ten,ngay,gio,ghiChu);
            arrDetail.add(detail);
        }
        AdapterDetail adapterDetail=new AdapterDetail(MainActivity.this,R.layout.row,arrDetail);
        listViewDetail.setAdapter(adapterDetail);
    }
    private void showDialogAdd(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.add_cong_viec);
        dialog.show();
        btnAddDialog=(Button) dialog.findViewById(R.id.buttonAdd);
        btnCancelDialog=(Button) dialog.findViewById(R.id.buttonCancel);
        edtTenDialog=(EditText) dialog.findViewById(R.id.editTextTen);
        edtNgayDialog=(EditText) dialog.findViewById(R.id.editTextNgay);
        edtGioDialog=(EditText) dialog.findViewById(R.id.editTextGio);
        edtGhiChuDialog=(EditText) dialog.findViewById(R.id.editTextGhiChu);

        edtNgayDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay();
            }
        });

        edtGioDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGio();
            }
        });

        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnAddDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten=edtTenDialog.getText().toString();
                String ngay=edtNgayDialog.getText().toString();
                String gio=edtGioDialog.getText().toString();
                String ghiChu=edtGhiChuDialog.getText().toString();
                Detail detail=new Detail(cateDuocChon,ten,ngay,gio,ghiChu);
                databaseDetail.querry("INSERT into detail VALUES(null,'"+cateDuocChon+"','"+ten+"','"+ngay+"','"+gio+"','"+ghiChu+"',"+0+")");
                Toast.makeText(MainActivity.this,ten+" "+ngay+" "+gio,Toast.LENGTH_SHORT).show();
                xuatListView();
            }
        });
    }
    private void chonNgay(){
        Calendar calendar=Calendar.getInstance();
        int ngay=calendar.get(Calendar.DATE);
        int thang=calendar.get(Calendar.MONTH);
        int nam=calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(MainActivity.this, dayOfMonth+" "+month+" "+year, Toast.LENGTH_SHORT).show();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                edtNgayDialog.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam, thang, ngay);
        datePickerDialog.show();
    }
    private void chonGio(){
        Calendar calendar=Calendar.getInstance();
        int gio=calendar.get(Calendar.HOUR);
        int phut=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                edtGioDialog.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }
    //DIALOG CATEGORY

    private void showDialogList(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_list_cate);
        dialog.show();
        listViewCate=(ListView) dialog.findViewById(R.id.listViewDialogCate);
        ImageButton btnAddCateDialog=(ImageButton) dialog.findViewById(R.id.imageButtonThem);
        TextView txtViewThemCate=(TextView) dialog.findViewById(R.id.textViewThem);

        databaseCate=new Database(MainActivity.this, "Database.sqlite",null,1);
        Cursor cursor=databaseCate.getData("SELECT * FROM CATEGORY");
        //databaseCate.querry("DROP TABLE CATEGORY");
        arrCategory=new ArrayList<>();
        ArrayList<String> arrTenCate=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String ten=cursor.getString(1);
            arrTenCate.add(ten);
            Category category=new Category(id,ten);
            arrCategory.add(category);
            //Toast.makeText(this, ten+" "+id, Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrTenCate);
        listViewCate.setAdapter(arrayAdapter);
        listViewCate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cateDuocChon=arrCategory.get(position).getTen();
                Toast.makeText(MainActivity.this, cateDuocChon, Toast.LENGTH_SHORT).show();
                dialog.cancel();
                xuatListView();
            }
        });
        txtViewThemCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCate();
            }
        });
        btnAddCateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCate();
            }
        });
    }
    private void addCate(){
        Intent intent=new Intent(MainActivity.this,ActivityAddCate.class);
        startActivityForResult(intent, REQUEST_CODE_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_INTENT && resultCode==RESULT_OK && data!=null){
            String res=data.getStringExtra("dataThem");
            databaseCate.querry("INSERT INTO CATEGORY VALUES(null,'"+res+"')");
            databaseCate=new Database(MainActivity.this, "Database.sqlite",null,1);
            Cursor cursor=databaseCate.getData("SELECT * FROM CATEGORY");
            //databaseCate.querry("DROP TABLE CATEGORY");
            arrCategory=new ArrayList<>();
            ArrayList<String> arrTenCate=new ArrayList<>();
            while (cursor.moveToNext()){
                int id=cursor.getInt(0);
                String ten=cursor.getString(1);
                arrTenCate.add(ten);
                Category category=new Category(id,ten);
                arrCategory.add(category);
                //Toast.makeText(this, ten+" "+id, Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrTenCate);
            listViewCate.setAdapter(arrayAdapter);
        }
    }
}