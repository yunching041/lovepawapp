package com.example.lovepaw;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lovepaw.adapters.RecentConversationsAdpter;
import com.example.lovepaw.databinding.FragmentMessageBinding;
import com.example.lovepaw.listeners.ConversionListener;
import com.example.lovepaw.models.ChatMessage;
import com.example.lovepaw.models.User;
import com.example.lovepaw.utilities.Constants;
import com.example.lovepaw.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @SuppressLint("StaticFieldLeak")
    private static FragmentMessageBinding binding;


    public static class BaseActivity extends AppCompatActivity implements ConversionListener{

        private PreferenceManager preferenceManager;
        private List<ChatMessage> conversations;
        private RecentConversationsAdpter conversationsAdpter;
        private FirebaseFirestore database;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
            super.onCreate(savedInstanceState, persistentState);
            preferenceManager = new PreferenceManager(getApplicationContext());
            init();
            loadUserDetails();
            getToken();
            setListeners();
            listenConversation();
        }

        private void init(){
            conversations = new ArrayList<>();
            conversationsAdpter = new RecentConversationsAdpter(conversations ,this);
            binding.conersationsRecyclerView.setAdapter(conversationsAdpter);
            database = FirebaseFirestore.getInstance();
        }

        private void setListeners(){
            binding.fabNewChat.setOnClickListener(v ->
                    startActivity(new Intent(getApplicationContext(), UsersActivity.class)));
        }

        private void loadUserDetails(){
            byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            binding.imageProfile.setImageBitmap(bitmap);
        }

        private void showToast(String message){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        private void listenConversation(){
            database.collection(Constants.KEY_COLLECTION_CONVERSATIONS)
                    .whereEqualTo(Constants.KEY_SENDER_ID,preferenceManager.getString(Constants.KEY_USER_ID))
                    .addSnapshotListener(eventListener);
            database.collection(Constants.KEY_COLLECTION_CONVERSATIONS)
                    .whereEqualTo(Constants.KEY_RECEIVER_ID,preferenceManager.getString(Constants.KEY_USER_ID))
                    .addSnapshotListener(eventListener);
        }

        private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
            if (error != null){
                return;
            }
            if (value != null){
                for (DocumentChange documentChange : value.getDocumentChanges()){
                    if (documentChange.getType() == DocumentChange.Type.ADDED){
                        String senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                        String receiverId = documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID);
                        ChatMessage chatMessage = new ChatMessage();
                        chatMessage.senderId = senderId;
                        chatMessage.receiverId = receiverId;
                        if (preferenceManager.getString(Constants.KEY_USER_ID).equals(senderId)){
                            chatMessage.conversionImage = documentChange.getDocument().getString(Constants.KEY_RECRIVER_IMAGE);
                            chatMessage.conversionName = documentChange.getDocument().getString(Constants.KEY_RECRIVER_NAME);
                            chatMessage.conversionId = documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID);
                        }else {
                            chatMessage.conversionImage = documentChange.getDocument().getString(Constants.KEY_SENDER_IMAGE);
                            chatMessage.conversionName = documentChange.getDocument().getString(Constants.KEY_SENDER_NAME);
                            chatMessage.conversionId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                        }
                        chatMessage.message = documentChange.getDocument().getString(Constants.KEY_LAST_MESSAGE);
                        chatMessage.dateObject = documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP);
                        conversations.add(chatMessage);
                    } else if (documentChange.getType() == DocumentChange.Type.MODIFIED) {
                        for (int i = 0;i < conversations.size();i++){
                            String senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                            String receiverId = documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID);
                            if (conversations.get(i).senderId.equals(senderId) && conversations.get(i).receiverId.equals(receiverId)){
                                conversations.get(i).message = documentChange.getDocument().getString(Constants.KEY_LAST_MESSAGE);
                                conversations.get(i).dateObject = documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP);
                                break;
                            }
                        }
                    }
                }
                Collections.sort(conversations,(obj1, obj2) -> obj2.dateObject.compareTo(obj1.dateObject));
                conversationsAdpter.notifyDataSetChanged();
                binding.conersationsRecyclerView.smoothScrollToPosition(0);
                binding.conersationsRecyclerView.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.GONE);
            }
        };

        private void getToken(){
            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
        }

        private void updateToken(String token){
            preferenceManager.putString(Constants.KEY_FCM_TOKEN,token);
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            DocumentReference documentReference =
                    database.collection(Constants.KEY_COLLECTION_USERS).document(
                            preferenceManager.getString(Constants.KEY_USER_ID)
                    );
            documentReference.update(Constants.KEY_FCM_TOKEN,token)
                    .addOnSuccessListener(unused -> showToast("Token updated successfully"))
                    .addOnFailureListener(e -> showToast("Unable to update token"));
        }

        @Override
        public void onConversionClicked(User user) {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            intent.putExtra(Constants.KEY_USER,user);
            startActivity(intent);
        }
    }

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


}