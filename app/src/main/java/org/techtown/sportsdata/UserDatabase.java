package org.techtown.sportsdata;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018-11-28.
 */

@IgnoreExtraProperties
public class UserDatabase {
    public String email;
    public String part;
    static public int partNum;

    private DatabaseReference databaseReference;

    public UserDatabase(){


    }
    public UserDatabase(String email, String part){
        this.email = email;
        this.part = part;
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child("part");
    }

    @Exclude
    public Map<String, Integer> toMap(){
        HashMap<String, Integer> result = new HashMap<>();
        //Log.d("rophykey", databaseReference.child(part).getDatabase()+" ");
        //Log.d("rophy1", partNum+" ");

        /*ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Long> map = (Map<String, Long>) dataSnapshot.getValue();
                //HashMap<String, Integer> result = new HashMap<>();
                partNum = Integer.parseInt(map.get(part).toString());
                //result.put(part,partNum);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RecordFragment", "loadUsers:onCancelled", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(userListener);*/


        return  result;
    }
}