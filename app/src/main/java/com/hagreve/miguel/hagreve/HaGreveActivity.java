package com.hagreve.miguel.hagreve;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class HaGreveActivity extends ListActivity {


    private static ArrayList<String> teachers = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_greve);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, teachers);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ha_greve, menu);
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

    private void LoadTeachers(){

        if(teachers.size() == 0) {

            new AsyncTask<Void, Void, String[]>() {

                @Override // Executa tarefas intermédias em pano de fundo
                protected String[] doInBackground(Void... params) {

                    Log.d("PDM", "Novo pedido");

                    try {

                        URL url = new URL("https://adeetc.thothapp.com/api/v1/teachers");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.connect();

                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line).append('\n');
                        }

                        JSONObject teachersObj = new JSONObject(response.toString());
                        JSONArray teachersList = teachersObj.getJSONArray("teachers");
                        String[] teachers = new String[teachersList.length()];
                        for (int i = 0; i < teachers.length; ++i) {
                            teachers[i] = teachersList.getJSONObject(i).getString("shortName");
                        }
                        return teachers;
                    } catch (Exception e) {
                        Log.e("PDM", e.getMessage());
                    }

                    return null;
                }

                @Override // Retorna o resultado final da Thread
                protected void onPostExecute(String[] res) {
                    teachers.clear();
                    for(String s: res){
                        teachers.add(s);
                        adapter.notifyDataSetChanged();
                    }
                }
            }.execute((Void) null);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SparseBooleanArray checked = getListView().getCheckedItemPositions();
        StringBuilder msg = new StringBuilder("Selected:");
        for (int i = 0; i < checked.size(); ++i) {
            msg.append(" " + checked.keyAt(i));
        }
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
    }
}
