package com.katouji.project_dummy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DosenList extends ArrayAdapter<Data> {

    private Activity context;
    private List<Data> dosenList;

    public DosenList(Activity context, List<Data> dosenList) {
        super(context,R.layout.list_layout, dosenList);
        this.context = context;
        this.dosenList = dosenList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewNama = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewMatkul = (TextView) listViewItem.findViewById(R.id.textViewMatkul);

        Data data = dosenList.get(position);

        textViewNama.setText(data.getNama());
        textViewMatkul.setText(data.matakuliah);

        return listViewItem;
    }
}
