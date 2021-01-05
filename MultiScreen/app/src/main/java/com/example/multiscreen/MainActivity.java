package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.i("MainActivity", "this is onCreate method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///////////////////////////// Numbers Listeners ////////////////////////////////////////////
        TextView numbers =findViewById(R.id.numbers);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "this is a toast", Toast.LENGTH_SHORT).show();

                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });
          //////////////////////////////// Colors listeners ////////////////////////////////////////
        TextView colors =findViewById(R.id.colors);

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "this is a toast", Toast.LENGTH_SHORT).show();

                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        ///////////////////////////////// family listeners ////////////////////////////////////////
        TextView family =findViewById(R.id.family);

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "this is a toast", Toast.LENGTH_SHORT).show();

                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        /////////////////////////////////////// phrases listeners /////////////////////////////////////
        TextView phrases =findViewById(R.id.phrases);

        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "this is a toast", Toast.LENGTH_SHORT).show();

                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });

    }

//    public void openNumbersActivity(View view){
//        //TODO: write your code here
//        Intent intent = new Intent(this,NumbersActivity.class);
//        startActivity(intent);
//    }
//
//    public void openFamilyActivity(View view){
//        //TODO: write your code here
//        Intent intent = new Intent(this,FamilyActivity.class);
//        startActivity(intent);
//    }
//
//    public void openColorsActivity(View view){
//        //TODO: write your code here
//        Intent intent = new Intent(this,ColorsActivity.class);
//        startActivity(intent);
//    }
//
//    public void openPhrasesActivity(View view){
//        //TODO: write your code here
//        Intent intent = new Intent(this,PhrasesActivity.class);
//        startActivity(intent);
//    }

}