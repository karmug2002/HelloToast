package com.karmug.hellotoast;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.ArrayList;

import basepackage.SemManager;


public class MainActivity extends AppCompatActivity
{

    private int count = 1;
    private TextView countText;
    private SemManager cseManager;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countText = findViewById(R.id.textView_count);
        outputText = findViewById(R.id.textView_output);

        Button toastButton = findViewById(R.id.toast_button);
        toastButton.setOnClickListener(this::showToast);

        Button showButton = findViewById(R.id.show_button);
        showButton.setOnClickListener(this::show);

        Button countButton = findViewById(R.id.count_button);
        countButton.setOnClickListener(this::count);

        Button zeroButton = findViewById(R.id.reset_button);
        zeroButton.setOnClickListener(this::zero);

        InputStream inputStream = getResources().openRawResource(R.raw.csedata);
        cseManager = new SemManager(inputStream);

    }

    private void showToast(View view)
    {
       Toast myToast = Toast.makeText(this,R.string.toast_message,Toast.LENGTH_SHORT);
       myToast.show();
    }

    private void zero(View view)
    {
        count=1;
        countText.setText(String.valueOf(count));
        outputText.setText(R.string.output);
    }

    private void count(View view)
    {
        ++count;
        countText.setText(String.valueOf(count));
    }

    private void show(View view)
    {
        if(count>0 && count<9){
            ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo = cseManager.getSemInfoForOneSem(count);
            outputText.setText("The current dep is CSE and the current semester is: " + count + "\n" + semInfo.toString());
        }
        else
        {
            outputText.setText(String.format("There are only %d semesters in this department!\n Press the reset Button",cseManager.getNumOfSems()));
        }
    }

}