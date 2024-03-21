package com.example.lovepaw.Cat;

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

import com.example.lovepaw.Dog.DataClass2;
import com.example.lovepaw.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CatUploadActivity extends AppCompatActivity {
    ImageView uploadimage3;
    Button savebutton3;
    EditText uploadNeed2, uploadName2, uploadVariety2, uploadArea2, uploadMoney2, uploadDetail2;
    String imageURL;
    Uri uri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catupload);

        uploadimage3 = findViewById(R.id.uploadImage3);
        savebutton3 = findViewById(R.id.saveButton3);
        uploadNeed2 = findViewById(R.id.uploadNeed2);
        uploadName2 = findViewById(R.id.uploadName2);
        uploadVariety2 = findViewById(R.id.uploadVariety2);
        uploadArea2 = findViewById(R.id.uploadArea2);
        uploadMoney2 = findViewById(R.id.uploadMoney2);
        uploadDetail2 = findViewById(R.id.uploadDetail2);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadimage3.setImageURI(uri);
                        } else {
                            Toast.makeText(CatUploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        savebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData3();
            }
        });
    }
    public void saveData3(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("android照片").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(CatUploadActivity.this);
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
                uploadData3();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData3(){

        String Need= uploadNeed2.getText().toString();
        String Name= uploadName2.getText().toString();
        String Variety= uploadVariety2.getText().toString();
        String Area= uploadArea2.getText().toString();
        String Money= uploadMoney2.getText().toString();
        String Detail= uploadDetail2.getText().toString();


        DataClass2 dataClass2=new DataClass2(Need,Name,Variety,Area,Money,Detail,imageURL);

        FirebaseDatabase.getInstance().getReference("Android Cat").child(Need).setValue(dataClass2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CatUploadActivity.this,"儲存",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CatUploadActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}