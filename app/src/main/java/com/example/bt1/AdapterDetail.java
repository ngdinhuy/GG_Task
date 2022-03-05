package com.example.bt1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterDetail extends ArrayAdapter<Detail> {
    public AdapterDetail(@NonNull Context context, int resource, @NonNull ArrayList<Detail> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View viewHolder=convertView;
        if(convertView==null){
            viewHolder= LayoutInflater.from(getContext()).inflate(R.layout.row,parent,false);
        }
        Detail detail=getItem(position);
        RadioButton radioButtonCheck=(RadioButton) viewHolder.findViewById(R.id.radioButton);
        TextView txtNgay=(TextView) viewHolder.findViewById(R.id.textViewNgay);
        TextView txtTen=(TextView) viewHolder.findViewById(R.id.textViewTen);
        TextView txtGio=(TextView) viewHolder.findViewById(R.id.textViewGio);
        txtNgay.setText(detail.getNgay());
        txtGio.setText(detail.getGio());
        txtTen.setText(detail.getTen());
        return viewHolder;
    }
}
