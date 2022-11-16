package com.example.android_email.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_email.Adapters.ChatAdapter;
import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.Message;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.R;
import com.example.android_email.databinding.ActivityChatBinding;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private User user;
    private User contact;
    private List<Message> messages;
    private ChatAdapter chatAdapter;
    private AppDataBase db;
    private Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDataBase.getInstance(getApplicationContext());
        user = SignInActivity.getSignedUser();
        chat = ContactsActivity.getSelectedChat();
        if (chat == null)
            startActivity(new Intent(this, ContactsActivity.class));
        contact = getTheOtherUserFromChat(chat, user);
        messages = db.messageDAO().getChatMessages(chat.id);

        binding.texName.setText(contact.username);

        setListeners();
        initAdapter();
    }

    private void initAdapter() {
        chatAdapter = new ChatAdapter(messages, user.username);
        binding.chatRecyclerView.setAdapter(chatAdapter);
        binding.chatRecyclerView.setVisibility(View.VISIBLE);
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> startActivity(new Intent(this, ContactsActivity.class)));
        binding.layoutSend.setOnClickListener(v -> sendMessage());
        binding.imageInfo.setOnClickListener(v -> info());
    }

    private void info() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.InfoTitle);
        builder.setIcon(R.drawable.ic_info);
        builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        View dialogView = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, null);
        TextView tvRight = dialogView.findViewById(android.R.id.text1);
        TextView tvLeft = dialogView.findViewById(android.R.id.text2);
        tvRight.setText(String.format(getResources().getString(R.string.RightUser), user.username));
        tvLeft.setText(String.format(getResources().getString(R.string.LeftUser), contact.username));
        builder.setView(dialogView);
        builder.show();
    }

    private void sendMessage() {
        Message message = new Message();
        message.sender = user.username;
        message.receiver = contact.username;
        message.message = binding.inputMessage.getText().toString().trim();
        message.chatId = chat.id;

        long messageId = db.messageDAO().insert(message);
        messages.add(db.messageDAO().get(messageId));

        binding.inputMessage.getText().clear();

        chatAdapter.notifyItemInserted(messages.size()-1);
        binding.chatRecyclerView.smoothScrollToPosition(this.messages.size()-1);
    }

    private User getTheOtherUserFromChat(Chat chat, User user) {
        String theOtherUserUsername = user.username.equals(chat.user1) ? chat.user2 : chat.user1;
        return db.userDao().get(theOtherUserUsername);
    }
}