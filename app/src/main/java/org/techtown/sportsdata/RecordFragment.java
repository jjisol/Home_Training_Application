package org.techtown.sportsdata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


/**
 * Created by user on 2018-11-29.
 */

public class RecordFragment extends Fragment {
    private DatabaseReference databaseReference;
    TextView textView;

    public RecordFragment(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child("part");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_record, container, false);
        textView = (TextView)view.findViewById(R.id.textView);

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    long i = (long)noteDataSnapshot.getValue();
                    textView.append(noteDataSnapshot.getKey()+":"+i);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RecordFragment", "loadUsers:onCancelled", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(userListener);
        return view;
    }
}
