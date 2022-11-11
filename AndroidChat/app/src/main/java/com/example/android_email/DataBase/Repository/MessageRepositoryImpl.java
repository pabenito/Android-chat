package com.example.android_email.DataBase.Repository;

import com.example.android_email.DataBase.DAO.MessageDAO;
import com.example.android_email.DataBase.Entity.Messages;

import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {
    private MessageDAO dao;

    public MessageRepositoryImpl(MessageDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Messages> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Messages> loadAllByIds(String messageId) {
        return dao.loadAllByIds(messageId);
    }

    @Override
    public void insert(Messages messages) {
        dao.insert(messages);

    }

    @Override
    public void delete(Messages messages) {
        dao.delete(messages);
    }

    @Override
    public void update(Messages messages) {
        dao.update(messages);
    }
}
