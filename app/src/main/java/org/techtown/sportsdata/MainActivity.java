package org.techtown.sportsdata;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity{
    private ListView listview ;
    private ListViewAdapter adapter;
    public FirebaseFirestore db;
    Intent in;
    String part;


    private ArrayList<ListViewItem> data = null;

    //static public ArrayList<ItemForSending> planList;
    //private GoogleCloudMessaging mGcm;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        in = getIntent();
        part = in.getStringExtra("part");

        // Storage 이미지 다운로드 경로


        //StorageReference imageRef = mStorageRef.child(storagePath);

        // Adapter 생성

        db = FirebaseFirestore.getInstance();
        listview = (ListView) findViewById(R.id.ex_listview);
        adapter = new ListViewAdapter();
        adapter.notifyDataSetChanged();
        data = new ArrayList<ListViewItem>();
        //planList = new ArrayList<ItemForSending>();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("image_path",data.get(position).getIcon());
                intent.putExtra("name",data.get(position).getTitle());
                intent.putExtra("pose",data.get(position).getDesc());
                intent.putExtra("way",data.get(position).getWay());
                intent.putExtra("part",part);
                startActivity(intent);

            }
        });


        readManyContacts();
        //listview.setAdapter(adapter);

        //context = getApplicationContext();
        //regid = getRegistrationId(context);
    }

    int i =1;

    private void readManyContacts(){

        db.collection(part)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (final DocumentSnapshot document : task.getResult()) {
                                ListViewItem item = new ListViewItem(part.toLowerCase()+"0"+document.getId()+".jpg",document.getString("name"),document.getString("pose"),document.getString("way"));
                                data.add(item);
                                adapter.addItem(item.getIcon(),item.getTitle(),item.getDesc(),item.getWay());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        listview.setAdapter(adapter);
                    }
                });
    }

}
