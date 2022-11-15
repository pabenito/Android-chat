package com.example.android_email.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_email.Activities.ChatActivity;
import com.example.android_email.Activities.ContactsActivity;
import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.databinding.ItemContainerUserBinding;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private Activity avtivity;
    private List<Chat> contacts;
    private User user;

    public ContactsAdapter(List<Chat> contacts, User user, ContactsActivity activity) {
        this.contacts = contacts;
        this.user = user;
        this.avtivity = activity;
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
            String contact = chat.user1 != user.username ? chat.user2 : chat.user1;

            binding.tvUser.setText(contact); ;
            binding.getRoot().setOnClickListener(v -> {
                ContactsActivity.setSelectedChat(chat);
                avtivity.startActivity(new Intent(avtivity, ChatActivity.class));
            });
        }
    }

}
