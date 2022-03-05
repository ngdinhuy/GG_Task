package com.example.bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAddCate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cate);
        EditText edtRes=(EditText) findViewById(R.id.editTextThemCate);
        Button btnThemCate=(Button) findViewById(R.id.buttonThemCate);
        btnThemCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                String res=edtRes.getText().toString();
                intent.putExtra("dataThem",res);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}