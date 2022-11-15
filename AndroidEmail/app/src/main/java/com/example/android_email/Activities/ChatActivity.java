package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;

import android.app.appsearch.observer.DocumentChangeInfo;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.android_email.Adapters.ChatAdapter;
import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.DAO.MessageDAO;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.Message;
import com.example.android_email.Models.ChatMessage;
import com.example.android_email.Models.User;
import com.example.android_email.R;
import com.example.android_email.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private User reveiverUser;
    private List<ChatMessage> chatMessageList;
    private ChatAdapter chatAdapter;
    private AppDataBase db;
    private int chatID;

    public ChatActivity() {
    }


    private void update(){
        List <Message> messages = db.messageDAO().getChatMessages(chatID);
        int count = chatMessageList.size();
        chatMessageList.clear();
        for(Message m : messages){
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.sender = m.sender;
            chatMessage.message = m.message;
            chatMessage.receiver = m.receiver;
            chatMessageList.add(chatMessage);
        }

        if(count==0){
            chatAdapter.notifyDataSetChanged();
        }else{
            chatAdapter.notifyItemRangeInserted(chatMessageList.size(),chatMessageList.size());
            binding.chatRecyclerView.smoothScrollToPosition(chatMessageList.size()-1);

        }
        binding.chatRecyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        init();
        loadReceiverDetails();
    }


    private void init() {
        // preferenceManager = new PreferenceManager(getApplicationContext());
        chatMessageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessageList, SignInActivity.getSignedUser().username);
        binding.chatRecyclerView.setAdapter(chatAdapter);
        db = AppDataBase.getInstance(getApplicationContext());
        chatID = ContactsActivity.getSelectedChat().id;
    }

    private void sendMessage() {
        Message message = new Message();
        message.sender = SignInActivity.getSignedUser().username;
        message.receiver = reveiverUser.name;
        message.message = binding.inputMessage.getText().toString();
        Chat chat = db.chatDAO().getByPar(message.receiver, message.sender);
        message.chatId = chatID;
        db.messageDAO().insert(message);
        binding.inputMessage.setText(null);
        binding.chatRecyclerView.setVisibility(View.GONE);
        update();
    }

    private void loadReceiverDetails() {
        String signedUser = SignInActivity.getSignedUser().username;
        Chat chat = db.chatDAO().get(chatID);
        String chatName = chat.user1 != signedUser ? chat.user2 : chat.user1;
        reveiverUser = new User();
        reveiverUser.name = chatName;
        binding.texName.setText(chatName);
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> startActivity(new Intent(this, ContactsActivity.class)));
        binding.layoutSend.setOnClickListener(v -> sendMessage());
    }

}