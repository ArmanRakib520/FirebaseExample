package com.mcc.firebaseexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    EditText name,email;
    Button submit,view;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.etname);
        email=findViewById(R.id.etemail);
        submit=findViewById(R.id.btnsubmit);
        view=findViewById(R.id.btnview);
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("user");



        storageReference = FirebaseStorage.getInstance().getReference();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model model=new Model(name.getText().toString(),email.getText().toString());
                String key=mRef.push().getKey();

                mRef.child(key).setValue(model);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                            String name=snapshot.child("name").getValue().toString();
                            String email=snapshot.child("email").getValue().toString();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
