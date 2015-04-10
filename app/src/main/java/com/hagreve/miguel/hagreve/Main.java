package com.hagreve.miguel.hagreve;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main extends ActionBarActivity {

    // guardar resposta (para quando se roda o ecran)
    private static String response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Intent intent = new Intent(getApplicationContext(),HaGreveActivity.class);
            startActivity(intent);

        if (response == null)
        {
          //  new AsyncTask<>()
         //   new AsyncTask<Void,Void,String>()
        }
       /* try
        {
            URL url = new URL("https://adeetc.thothapp.com/api/v1/teachers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream input = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input.read()));
                    StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine())!= null){
                response.append(line).append('\n');
        }
                JSONObject teacherObj = new JSONObject(response.toString());
                JSONArray teachersList = teacherObj.getJSONArray("teachers");
                String[] teachers = new String[teachersList.length()];

            for (int i=0; i<teachersList.length(); ++i)
                {
                 //   teachers[i] = teachersList.getJSONObject();
                }

        }
        catch (Exception e)
        {
            Log.e("PDM", e.getMessage());
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
