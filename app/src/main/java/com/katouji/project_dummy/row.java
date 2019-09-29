package com.katouji.project_dummy;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



    public class row extends AppCompatActivity {


        ListView listView;
        String mTitle[] = {"Bali: Beats of Paradise", "Gundala", "Avenger: Endgame", "Wirosableng", "IT: 2"};
        String mDescription[] = {"Film 1 Bali", "Film 2 Gundala", "Film 3 Avenger", "Film 4 Wiro Sableng", "Film 5 IT"};
        int images[] = {R.drawable.b, R.drawable.c, R.drawable.a, R.drawable.e, R.drawable.d};


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.row);

            listView = findViewById(R.id.listView);


            MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position ==  0) {
                        Toast.makeText(row.this, "Film 1", Toast.LENGTH_SHORT).show();
                    }
                    if (position ==  0) {
                        Toast.makeText(row.this, "Film 2", Toast.LENGTH_SHORT).show();
                    }
                    if (position ==  0) {
                        Toast.makeText(row.this, "Film 3", Toast.LENGTH_SHORT).show();
                    }
                    if (position ==  0) {
                        Toast.makeText(row.this, "Film 4", Toast.LENGTH_SHORT).show();
                    }
                    if (position ==  0) {
                        Toast.makeText(row.this, "Film 5", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // so item click is done now check list view
        }

        class MyAdapter extends ArrayAdapter<String> {

            Context context;
            String rTitle[];
            String rDescription[];
            int rImgs[];

            MyAdapter (Context c, String title[], String description[], int imgs[]) {
                super(c, R.layout.row, R.id.textView1, title);
                this.context = c;
                this.rTitle = title;
                this.rDescription = description;
                this.rImgs = imgs;

            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = layoutInflater.inflate(R.layout.row, parent, false);
                ImageView images = row.findViewById(R.id.image);
                TextView myTitle = row.findViewById(R.id.textView1);
                TextView myDescription = row.findViewById(R.id.textView2);


                images.setImageResource(rImgs[position]);
                myTitle.setText(rTitle[position]);
                myDescription.setText(rDescription[position]);


                return row;
            }
        }
    }


