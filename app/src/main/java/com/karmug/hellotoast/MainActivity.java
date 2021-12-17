package com.karmug.hellotoast;
import android.os.Bundle;
import android.text.Editable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private int semCount;
    private TextView semOutput;
    private EditText input;
    private TextView resultTextView;
    private  ArrayMap<ArrayList<String>, ArrayList<Float>> semInfo;
    private ArrayList<Float> userInputs;
    private float cgpa=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        semOutput = findViewById(R.id.sem_output);
        input = findViewById(R.id.input);
        countText = findViewById(R.id.count);
        resultTextView = findViewById(R.id.result);


        Button calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(this::calculate);


        Button countButton = findViewById(R.id.countButton);
        countButton.setOnClickListener(this::count);

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this::reset);

        InputStream inputStream = getResources().openRawResource(R.raw.csedata);
        cseManager = new SemManager(inputStream);
        semCount = cseManager.getNumOfSems();
        //givenCPS = new ArrayList<>();
    }

    private void showToast(View view)
    {
        Toast myToast = Toast.makeText(this,R.string.toast_message,Toast.LENGTH_SHORT);
        myToast.show();
    }

    private void reset(View view)
    {
        count=1;
        countText.setText(String.valueOf(count));
        resultTextView.setText(R.string.result);
        semOutput.setText(R.string.output);
        cgpa=0;
        semInfo=null;
        userInputs=null;

    }

    private void count(View view)
    {
        if(count<semCount)
        {
            count++;
            countText.setText(String.valueOf(count));
            semInfo = cseManager.getSemInfoForOneSem(count);
            semOutput.setText(makeString());
        }
        //Log.i("MainActivity",String.valueOf(semCount));
    }

    private ArrayList<Float> parseFloat(String s)
    {
        ArrayList<Float> givenCps = new ArrayList<>();
        String[] strings = s.split(",");
        for(String strs : strings)
        {
            givenCps.add(Float.parseFloat(strs));
        }
        return givenCps;
    }

    private String makeString()
    {
        ArrayList<String> semName = semInfo.keyAt(0);
        String name = "";
        for(String s : semName)
        {
            name  = name + " " + s + "\n" ;
        }
        return name+" \n "+"The current semester is : "+count;
    }

    private void calculate(View view)
    {
        if(count<=semCount)
        {
            semInfo = cseManager.getSemInfoForOneSem(count);
            semOutput.setText(makeString());
            Editable inputText = input.getText();
            Log.i("MainActivity",makeString());
            try {
                userInputs = parseFloat(inputText.toString());
            }
            catch (Exception e)
            {
                resultTextView.setText("please enter your result correctly!");
            }
            cgpa = cseManager.getCGPAForOneSem(count, userInputs);
            Log.i("CGPA ", String.valueOf(cgpa));
            if(cgpa>0)
            {
                resultTextView.setText("Your CGPA is : " + String.valueOf(cgpa));
            }
            else
            {
                resultTextView.setText("Please Enter your CPS!!");
            }
        }

    }

}