package com.example.lovepaw.Other;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lovepaw.R;
import com.example.lovepaw.Rabbit.DataClass5;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class OtherUploadActivity extends AppCompatActivity {
    ImageView uploadimage6;
    Button savebutton6;
    EditText uploadNeed5, uploadName5, uploadVariety5, uploadArea5, uploadMoney5, uploadDetail5;
    String imageURL;
    Uri uri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherupload);

        uploadimage6 = findViewById(R.id.uploadImage6);
        savebutton6 = findViewById(R.id.saveButton6);
        uploadNeed5 = findViewById(R.id.uploadNeed5);
        uploadName5 = findViewById(R.id.uploadName5);
        uploadVariety5 = findViewById(R.id.uploadVariety5);
        uploadArea5 = findViewById(R.id.uploadArea5);
        uploadMoney5= findViewById(R.id.uploadMoney5);
        uploadDetail5 = findViewById(R.id.uploadDetail5);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadimage6.setImageURI(uri);
                        } else {
                            Toast.makeText(OtherUploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadimage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        savebutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData6();
            }
        });
    }
    public void saveData6(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("android照片").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(OtherUploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData6();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData6(){

        String Need= uploadNeed5.getText().toString();
        String Name= uploadName5.getText().toString();
        String Variety= uploadVariety5.getText().toString();
        String Area= uploadArea5.getText().toString();
        String Money= uploadMoney5.getText().toString();
        String Detail= uploadDetail5.getText().toString();


        DataClass6 dataClass6=new DataClass6(Need,Name,Variety,Area,Money,Detail,imageURL);

        FirebaseDatabase.getInstance().getReference("Android Other").child(Need).setValue(dataClass6).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(OtherUploadActivity.this,"儲存",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(OtherUploadActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
