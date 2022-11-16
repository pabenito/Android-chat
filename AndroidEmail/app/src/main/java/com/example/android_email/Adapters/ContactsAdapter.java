package com.example.android_email.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_email.Activities.ChatActivity;
import com.example.android_email.Activities.ContactsActivity;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.databinding.ItemContainerUserBinding;

import java.util.List;

//Class to adapt the contacts on the front-end in the contacts activity
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private Activity activity;
    private List<Chat> contacts;
    private User user;

    public ContactsAdapter(List<Chat> contacts, User user, ContactsActivity activity) {
        this.contacts = contacts;
        this.user = user;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactsViewHolder(
                ItemContainerUserBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.setData(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    class ContactsViewHolder extends RecyclerView.ViewHolder {

        ItemContainerUserBinding binding;

        ContactsViewHolder(ItemContainerUserBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setData(Chat chat) {
            String contact = getTheOtherUserFromChat(chat, user);
            binding.tvUser.setText(contact); ;
            binding.getRoot().setOnClickListener(v -> {
                ContactsActivity.setSelectedChat(chat);
                activity.startActivity(new Intent(activity, ChatActivity.class));
            });
        }
    }

    private String getTheOtherUserFromChat(Chat chat, User user) {
        return user.username.equals(chat.user1) ? chat.user2 : chat.user1;
    }
}
