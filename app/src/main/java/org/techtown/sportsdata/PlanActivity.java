package org.techtown.sportsdata;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.techtown.sportsdata.PartFragment.planList;

public class PlanActivity extends AppCompatActivity {
    private ListView listview;
    private PlanLIstViewAdapter adapter;
    private DatabaseReference databaseReference;
    ItemForSending item;
    Map<String, Integer> userValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);


        listview = (ListView) findViewById(R.id.plan_listview);
        adapter = new PlanLIstViewAdapter();
        adapter.notifyDataSetChanged();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child("part");

        for (int i = 0; i < planList.size(); i++) {
            item = planList.get(i);
            adapter.addItem(item.getS_part(), item.getS_name(), item.getS_set(), item.getS_count());
            //writeNewUser(item.getS_set(), item.getS_part());
        }

        listview.setAdapter(adapter);



        final ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
/*                Map<String, Long> map = (Map<String, Long>) dataSnapshot.getValue();
                textView.setText(map.values().toString());
                UserDatabase.absNum = Integer.parseInt(map.get("Abs").toString());
                UserDatabase.armNum = Integer.parseInt(map.get("Arm").toString());
                UserDatabase.backNum = Integer.parseInt(map.get("Back").toString());
                UserDatabase.chestNum = Integer.parseInt(map.get("Chest").toString());
                UserDatabase.glutesNum = Integer.parseInt(map.get("Glutes").toString());
                UserDatabase.quadsNum = Integer.parseInt(map.get("Quads").toString());*/
                userValues = new HashMap<String,Integer>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    long set = (long)noteDataSnapshot.getValue();
                    String part = noteDataSnapshot.getKey();
                    userValues.put(part,(int)set);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RecordFragment", "loadUsers:onCancelled", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(userListener);
    }

    private void writeNewUser(String email, String part) {
        databaseReference.child(part).setValue(Integer.valueOf(email)+userValues.get(part));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_gohome:
                Toast.makeText(getApplicationContext(), "홈으로 돌아가기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PlanActivity.this, TabActivity.class);
                intent.putExtra("initialize", false);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_delete:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("전체 삭제하시겠습니까?");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        planList = new ArrayList<ItemForSending>();
                        finish();
                    }
                });
                alertDialogBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickSendButton(View view) {
        //토토에게 전송하기
       // new JSONTask().execute("http://192.168.0.153:8000/post");
        for(int i = 0; i< planList.size();i++){
            item=planList.get(i);
            writeNewUser(item.getS_set(), item.getS_part());
        }
        Toast.makeText(PlanActivity.this,"토토에게 전송되었습니다.", Toast.LENGTH_LONG).show();

    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override

        protected String doInBackground(String... urls) {

            try {

                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONArray jsonArray = new JSONArray();

                for(int i = 0; i< planList.size();i++){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("part",planList.get(i).getS_part());
                    jsonObject.accumulate("ex_name", planList.get(i).getS_name());
                    jsonObject.accumulate("count", planList.get(i).getS_count());
                    jsonObject.accumulate("set", planList.get(i).getS_set());
                    jsonArray.put(jsonObject);
                }




                HttpURLConnection con = null;

                BufferedReader reader = null;

                try {

                    //URL url = new URL(“http://192.168.25.16:3000/users“);

                    URL url = new URL(urls[0]);

                    //연결을 함

                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST");//POST방식으로 보냄

                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정

                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송

                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음

                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미

                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미

                    con.connect();

                    //서버로 보내기위해서 스트림 만듬

                    OutputStream outStream = con.getOutputStream();

                    //버퍼를 생성하고 넣음

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));

                    writer.write(jsonArray.toString());

                    writer.flush();

                    writer.close();//버퍼를 받아줌

                    //서버로 부터 데이터를 받음

                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";

                    while ((line = reader.readLine()) != null) {

                        buffer.append(line);

                    }

                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                } finally {

                    if (con != null) {

                        con.disconnect();

                    }

                    try {

                        if (reader != null) {

                            reader.close();//버퍼를 닫아줌

                        }

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

            return null;

        }


        @Override

        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            //btn.setText(result);//서버로 부터 받은 값을 출력해주는 부

        }

    }
}


