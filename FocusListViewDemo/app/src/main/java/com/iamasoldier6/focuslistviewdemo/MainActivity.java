package com.iamasoldier6.focuslistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        List<String> data = new ArrayList<String>();
        data.add(" I am a cat 1");
        data.add(" I am a cat 2");
        data.add(" I am a cat 3");
        data.add(" I am a cat 4");
        data.add(" I am a cat 5");

        final FocusAdapter adapter = new FocusAdapter(this, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.setCurrentItem(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
