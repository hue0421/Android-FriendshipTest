package com.example.homeworkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FriendShipActivity extends AppCompatActivity {
    ImageView iv1, iv2;
    TextView tv1, tv2, tv;
    String[] info = new String[20]; // DB에 넣을 정답 값
    int[] images = {R.drawable.pizza, R.drawable.fried_chicken, R.drawable.chacha, R.drawable.jjam, R.drawable.sun, R.drawable.wind, R.drawable.bear, R.drawable.soju,
            R.drawable.sea, R.drawable.mountain, R.drawable.cat, R.drawable.dog, R.drawable.tansan, R.drawable.eon,
            R.drawable.rain, R.drawable.winter, R.drawable.tea, R.drawable.coffee, R.drawable.action, R.drawable.romance,
            R.drawable.rest, R.drawable.travel, R.drawable.message, R.drawable.call, R.drawable.singer, R.drawable.actor,
            R.drawable.comicbook, R.drawable.fictionbook, R.drawable.waternoodle, R.drawable.hotnoodle,
            R.drawable.drama, R.drawable.enter, R.drawable.fried, R.drawable.spicy,
            R.drawable.subway, R.drawable.bus, R.drawable.friend, R.drawable.heart, R.drawable.phone, R.drawable.notebook}; // 이미지 번호
    String[] quest = {"피자", "치킨", "짜장면", "짬뽕", "더울때", "추울때", "맥주", "소주", "바다", "산", "고양이", "강아지", "탄산음료", "이온음료", "비", "눈", "차", "커피", "액션", "로맨스",
            "휴식", "여행", "문자", "전화", "가수", "배우", "만화책", "소설책", "물냉면", "비빔냉면", "드라마", "예능", "후라이드", "양념", "지하철", "버스", "우정", "사랑", "휴대폰", "노트북"};
    int i = 0; // iv1 인덱스
    int j = 1; // iv2 인덱스
    int k = 0; // info 인덱스
    String temp = "";
    Intent intent = null;
    String[] answer = {};
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_ship);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        tv1 = findViewById(R.id.tvQuest1);
        tv2 = findViewById(R.id.tvQuest2);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = tv1.getText().toString();
                info[k] = temp;
                k++;
                i+=2;
                j+=2;
                if(i < images.length) {
                    iv1.setImageResource(images[i]);
                    iv2.setImageResource(images[j]);
                    tv1.setText(quest[i]);
                    tv2.setText(quest[j]);

                } else {
                    intent = getIntent();
                    answer = intent.getStringArrayExtra("answer");
                    for(int i=0; i<answer.length; i++) {
                        if(info[i].equals(answer[i])) {
                            count+=5;
                        }
                    }
                    Intent intent = new Intent(FriendShipActivity.this, ResultActivity.class);
                    intent.putExtra("count", count);
                    startActivity(intent);
                }
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = tv2.getText().toString();
                info[k] = temp;
                i+=2;
                j+=2;
                k++;
                if(i < images.length) {
                    iv1.setImageResource(images[i]);
                    iv2.setImageResource(images[j]);
                    tv1.setText(quest[i]);
                    tv2.setText(quest[j]);
                } else {
                    intent = getIntent();
                    answer = intent.getStringArrayExtra("answer");
                    for(int i=0; i<answer.length; i++) {
                        if(info[i].equals(answer[i])) {
                            count+=5;
                        }
                    }
                    Intent intent = new Intent(FriendShipActivity.this, ResultActivity.class);
                    intent.putExtra("count", count);
                    startActivity(intent);
                }
            }
        });
    }
}

