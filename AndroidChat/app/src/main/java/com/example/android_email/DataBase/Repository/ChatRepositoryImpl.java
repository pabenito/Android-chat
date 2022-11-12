package com.example.android_email.DataBase.Repository;

import com.example.android_email.DataBase.DAO.ChatDAO;
import com.example.android_email.DataBase.Entity.Chats;

import java.util.List;

public class ChatRepositoryImpl implements ChatRepository {
    private ChatDAO dao;

    public ChatRepositoryImpl(ChatDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Chats> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Chats> loadAllByIds(String chatId) {
        return dao.loadAllByIds(chatId);
    }

    @Override
    public void insert(Chats chats) {
        dao.insert(chats);
    }

    @Override
    public void delete(Chats chats) {
        dao.delete(chats);
    }

    @Override
    public void update(Chats chats) {
        dao.update(chats);
    }
}
