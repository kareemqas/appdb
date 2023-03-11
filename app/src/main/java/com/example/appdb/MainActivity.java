package com.example.appdb;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    int count = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
EditText name,phone,address;
Button save , get;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference myRef = database.getReference();
        save.setOnClickListener(view -> {
            name = findViewById(R.id.name);
            phone = findViewById(R.id.phone);
            address = findViewById(R.id.addres);

            HashMap<String, EditText> presone = new HashMap<String, EditText>();

            presone.put("name", name);
            presone.put("phone", phone);
            presone.put("address", address);

            myRef.child("presone").child(""+count).setValue(presone);
            count++;
        });
        get.setOnClickListener(view -> {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    textView.setText(value);
                    Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

        });


    }
}