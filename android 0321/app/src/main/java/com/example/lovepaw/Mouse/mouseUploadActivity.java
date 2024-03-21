package com.example.lovepaw.Mouse;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class mouseUploadActivity extends AppCompatActivity {
    ImageView uploadimage4;
    Button savebutton4;
    EditText uploadNeed3, uploadName3, uploadVariety3, uploadArea3, uploadMoney3, uploadDetail3;
    String imageURL;
    Uri uri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouseupload);

        uploadimage4 = findViewById(R.id.uploadImage4);
        savebutton4 = findViewById(R.id.saveButton4);
        uploadNeed3 = findViewById(R.id.uploadNeed3);
        uploadName3 = findViewById(R.id.uploadName3);
        uploadVariety3 = findViewById(R.id.uploadVariety3);
        uploadArea3 = findViewById(R.id.uploadArea3);
        uploadMoney3 = findViewById(R.id.uploadMoney3);
        uploadDetail3 = findViewById(R.id.uploadDetail3);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadimage4.setImageURI(uri);
                        } else {
                            Toast.makeText(mouseUploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        savebutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData4();
            }
        });
    }
    public void saveData4(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("android照片").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(mouseUploadActivity.this);
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
                uploadData4();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData4(){

        String Need= uploadNeed3.getText().toString();
        String Name= uploadName3.getText().toString();
        String Variety= uploadVariety3.getText().toString();
        String Area= uploadArea3.getText().toString();
        String Money= uploadMoney3.getText().toString();
        String Detail= uploadDetail3.getText().toString();


        DataClass4 dataClass4=new DataClass4(Need,Name,Variety,Area,Money,Detail,imageURL);

        FirebaseDatabase.getInstance().getReference("Android Mouse").child(Need).setValue(dataClass4).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(mouseUploadActivity.this,"儲存",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mouseUploadActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}