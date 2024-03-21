package com.example.lovepaw.Rabbit;

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

public class RabbitUploadActivity extends AppCompatActivity {
    ImageView uploadimage5;
    Button savebutton5;
    EditText uploadNeed4, uploadName4, uploadVariety4, uploadArea4, uploadMoney4, uploadDetail4;
    String imageURL;
    Uri uri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbitupload);

        uploadimage5 = findViewById(R.id.uploadImage5);
        savebutton5 = findViewById(R.id.saveButton5);
        uploadNeed4 = findViewById(R.id.uploadNeed4);
        uploadName4 = findViewById(R.id.uploadName4);
        uploadVariety4 = findViewById(R.id.uploadVariety4);
        uploadArea4 = findViewById(R.id.uploadArea4);
        uploadMoney4= findViewById(R.id.uploadMoney4);
        uploadDetail4 = findViewById(R.id.uploadDetail4);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadimage5.setImageURI(uri);
                        } else {
                            Toast.makeText(RabbitUploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadimage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        savebutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData5();
            }
        });
    }
    public void saveData5(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("android照片").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(RabbitUploadActivity.this);
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
                uploadData5();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData5(){

        String Need= uploadNeed4.getText().toString();
        String Name= uploadName4.getText().toString();
        String Variety= uploadVariety4.getText().toString();
        String Area= uploadArea4.getText().toString();
        String Money= uploadMoney4.getText().toString();
        String Detail= uploadDetail4.getText().toString();


        DataClass5 dataClass5=new DataClass5(Need,Name,Variety,Area,Money,Detail,imageURL);

        FirebaseDatabase.getInstance().getReference("Android Mouse").child(Need).setValue(dataClass5).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RabbitUploadActivity.this,"儲存",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RabbitUploadActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
