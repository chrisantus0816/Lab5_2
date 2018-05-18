package com.example.chrisantus.lab5_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    EditText input;
    TextView textView1;
    TextView textView2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input=findViewById(R.id.inputEdit);
        button=findViewById(R.id.button1);
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new factoTask().execute();
            }
        });
    }

    private class factoTask extends AsyncTask<Void, Integer, Void>{
        int result=1;
        int max;
        @Override
        protected void onPreExecute(){
            max=Integer.valueOf(input.getText().toString());
            textView1.setText("");
        }

        @Override
        protected Void doInBackground(Void... params){
            for(int i=max;i>0;i--){
                try{
                    publishProgress(i);
                    Thread.sleep(500);
                    result=result*i;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            textView1.append(" "+values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid){
            textView2.setText(" = "+String.valueOf(result));
        }

    }
}
