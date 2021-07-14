package com.example.kuis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class introKedua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_kedua);
    }

    public void introMateri3(View view) {
        Intent intent = new Intent(this, introKetiga.class);
        startActivity(intent);
    }

    public void introMateri(View view) {
        Intent intent = new Intent(this, Intro.class);
        startActivity(intent);
    }
}