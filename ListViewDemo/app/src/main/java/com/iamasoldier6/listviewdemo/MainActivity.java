package com.iamasoldier6.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
//    "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango", "Peach",
//            "Lemon", "Pitaya", "Durin"};
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                MainActivity.this, android.R.layout.simple_list_item_1, data);
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
//        listView.setSelection(10);
    }

    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.mipmap.ic_launcher);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.mipmap.ic_launcher);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.mipmap.ic_launcher);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.mipmap.ic_launcher);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.mipmap.ic_launcher);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.mipmap.ic_launcher);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.mipmap.ic_launcher);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.mipmap.ic_launcher);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.mipmap.ic_launcher);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.mipmap.ic_launcher);
        fruitList.add(mango);
        Fruit peach = new Fruit("Peach", R.mipmap.ic_launcher);
        fruitList.add(peach);
        Fruit lemon = new Fruit("Lemon", R.mipmap.ic_launcher);
        fruitList.add(lemon);
        Fruit pitaya = new Fruit("Pitaya", R.mipmap.ic_launcher);
        fruitList.add(pitaya);
        Fruit durin = new Fruit("Durin", R.mipmap.ic_launcher);
        fruitList.add(durin);
    }

}
