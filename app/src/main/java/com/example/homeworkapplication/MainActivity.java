package com.example.homeworkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText edName, edFriend;
    Button btnStart, btnFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("우정테스트");


        edName = findViewById(R.id.edName);
        edFriend = findViewById(R.id.edFriend);
        btnStart = findViewById(R.id.btnStart);
        btnFriend = findViewById(R.id.btnFriend);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, QuestActivity.class);
                String name = edName.getText().toString();
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edFriend.getText().toString();
                RegisterTask task = new RegisterTask();
                task.execute(name);
            }
        });

    }
    class RegisterTask extends AsyncTask<String, String, String> {
        String sendMsg, receiverMsg;

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str = "";
                URL url = new URL("http://192.168.35.71:8090/AndroidConn/selectDB");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                sendMsg = "name=" + strings[0];
                osw.write(sendMsg);
                osw.flush();
                osw.close();

                if(conn.getResponseCode()==conn.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(isr);
                    StringBuffer buffer = new StringBuffer();
                    while((str=reader.readLine())!=null) {
                        buffer.append(str);
                    }
                    receiverMsg = buffer.toString();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiverMsg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject json = new JSONObject(s);
                JSONArray jArray = json.getJSONArray("name");
                JSONObject jsonObject = jArray.getJSONObject(0);
                String[] answer = new String[20];
                for(int i=0; i<answer.length; i++) {
                    answer[i] = (String)jsonObject.get(("fa"+(i+1)));
                }
                Intent intent = new Intent(MainActivity.this, FriendShipActivity.class);
                intent.putExtra("answer", answer);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}