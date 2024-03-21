// FirstTabFragment.java
package com.example.lovepaw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstTabFragment extends Fragment {

    private EditText editTextName, editTextID, editTextPhone;
    private RadioGroup radioGroup;
    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_firstpersonal, container, false);

        // 获取对 Cloud Database 的引用
        databaseReference = FirebaseDatabase.getInstance().getReference("userProfiles");

        // 初始化视图
        editTextName = root.findViewById(R.id.editTextTextPassword3);
        editTextID = root.findViewById(R.id.editTextTextPassword2);
        editTextPhone = root.findViewById(R.id.editTextPhone);
        radioGroup = root.findViewById(R.id.radioGroup);

        root.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 从视图中获取用户输入的数据
                String name = editTextName.getText().toString().trim();
                String id = editTextID.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String gender = ((RadioButton) root.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

                // 创建一个 UserProfile 对象
                UserProfile userProfile = new UserProfile(name, id, phone, gender);

                // 将数据写入到 Cloud Database 中
                String userId = databaseReference.push().getKey();
                databaseReference.child(userId).setValue(userProfile);

                // 提示用户数据已上传成功
                Toast.makeText(getContext(), "数据已上传到 Cloud Database 中", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
