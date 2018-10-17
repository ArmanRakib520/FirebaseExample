package com.mcc.firebaseexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    private EditText name, email;
    private Button submit, view, browse;
    private ImageView image;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private StorageReference mStorage;
    private static final int PICK_IMAGE = 100;
    private ProgressDialog progressDialog;
    private Uri imageUri;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        submit = findViewById(R.id.btnsubmit);
        view = findViewById(R.id.btnview);
        browse = findViewById(R.id.btnchoose);
        image = findViewById(R.id.imgUpload);

        progressDialog = new ProgressDialog(this);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("user");


        mStorage = FirebaseStorage.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model model = new Model(name.getText().toString(), email.getText().toString());
                String key = mRef.push().getKey();

                mRef.child(key).setValue(model);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String name = snapshot.child("name").getValue().toString();
                            String email = snapshot.child("email").getValue().toString();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setType("image/*");
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {

            imageUri = data.getData();
            image.setImageURI(imageUri);

            progressDialog.setMessage("Loading....");
            progressDialog.show();

            final StorageReference filepath = mStorage.child("photos").child(imageUri.getLastPathSegment());
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this, filepath.getDownloadUrl()+"", Toast.LENGTH_SHORT).show();
                    Log.d("UPLOAD", filepath.getDownloadUrl()+"");
                    progressDialog.dismiss();

                }
            });

        }

    }
}
