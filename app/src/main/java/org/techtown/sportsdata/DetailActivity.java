package org.techtown.sportsdata;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.techtown.sportsdata.PartFragment.planList;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView tv_name;
    private TextView tv_way;

    private Bitmap bitmap;
    private String name;
    private String way;

    private StorageReference mStorageRef;
    private StorageReference ref;
    private Spinner spinner_set;
    private Spinner spinner_count;
    private Button btn_send;
    private String part;

    ItemForSending plan_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        imageView = findViewById(R.id.detail_image);
        tv_name = findViewById(R.id.detail_name);
        tv_way = findViewById(R.id.detail_way);
        spinner_set = (Spinner)findViewById(R.id.spinner_set);
        spinner_count = (Spinner)findViewById(R.id.spinner_count);
        btn_send = findViewById(R.id.btn_send);

        final ArrayList<Integer> list_set = new ArrayList<>();
        for(int i=1;i<100;i++){
            list_set.add(i);
        }
        final ArrayList<Integer> list_count = new ArrayList<>();
        for(int i=1;i<100;i++){
            list_count.add(i);
        }
        ArrayAdapter spinnerAdapter1;
        spinnerAdapter1 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list_set);
        spinner_set.setAdapter(spinnerAdapter1);

        ArrayAdapter spinnerAdapter2;
        spinnerAdapter2 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list_count);
        spinner_count.setAdapter(spinnerAdapter2);

        //인텐트로 값 받기
        String storagePath = getIntent().getStringExtra("image_path");
        part = getIntent().getStringExtra("part");
        name = getIntent().getStringExtra("name");
        way = getIntent().getStringExtra("pose") + "\n" +getIntent().getStringExtra("way");

        mStorageRef = FirebaseStorage.getInstance().getReference();
        ref = mStorageRef.child(storagePath);
        try {
            final File localFile = File.createTempFile("Images", "bmp");
            //ref.getFile().addOnCompleteListener()

            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                    tv_name.setText(name);
                    tv_way.setText(way);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DetailActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OnClickAddPlanButton(View view){

        //ItemForSending item = new ItemForSending(part,name,spinner_set.getSelectedItem().toString(),spinner_count.getSelectedItem().toString());
        //planList.add(item);

        Intent intent = new Intent(DetailActivity.this,PlanActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.plan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_addplan:
                plan_item = new ItemForSending(part,name,spinner_set.getSelectedItem().toString(),spinner_count.getSelectedItem().toString());
                planList.add((ItemForSending) plan_item);
                Toast.makeText(getApplicationContext(), "운동 계획에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
