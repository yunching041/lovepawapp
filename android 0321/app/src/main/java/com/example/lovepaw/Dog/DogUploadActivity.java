package com.example.lovepaw.Dog;

import static com.example.lovepaw.R.id.uploadPetname;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lovepaw.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DogUploadActivity extends AppCompatActivity {
    ImageView uploadimage2;
    Button savebutton3;
    EditText uploadNeed, uploadPetname, uploadVariety, uploadArea, uploadMoney, uploadDetail;
    String imageURL;
    Uri uri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogupload);
        uploadimage2 = findViewById(R.id.uploadImage2);
        savebutton3 = findViewById(R.id.saveButton3);
        uploadNeed = findViewById(R.id.uploadDemand);
        uploadPetname = findViewById(R.id.uploadPetname);
        uploadVariety = findViewById(R.id.uploadVariety);
        uploadArea = findViewById(R.id.uploadArea);
        uploadMoney = findViewById(R.id.uploadMoney);
        uploadDetail = findViewById(R.id.uploadDetail);
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        savebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uri != null) {
                    saveData2();
                } else {
                    Toast.makeText(DogUploadActivity.this, "Please select an image first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadimage2.setImageURI(uri);
                        } else {
                            Toast.makeText(DogUploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
    }





    public void saveData2() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("android照片").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(DogUploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri urlImage = task.getResult();
                            imageURL = urlImage.toString();
                            uploadData2();
                            dialog.dismiss();
                        } else {
                            // 下載 URL 失敗的處理
                            dialog.dismiss();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // 上傳失敗的處理
                dialog.dismiss();
            }
        });
    }



    public void uploadData2() {
        String Need = uploadNeed.getText().toString();
        String Name = uploadPetname.getText().toString();
        String Variety = uploadVariety.getText().toString();
        String Area = uploadArea.getText().toString();
        String Money = uploadMoney.getText().toString();
        String Detail = uploadDetail.getText().toString();

        DataClass2 dataClass2 = new DataClass2(Need, Name, Variety, Area, Money, Detail, imageURL);

        FirebaseDatabase.getInstance().getReference("Android Dog").child(Need).setValue(dataClass2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DogUploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DogUploadActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
