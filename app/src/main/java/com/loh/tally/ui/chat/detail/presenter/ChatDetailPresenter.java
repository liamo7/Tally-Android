package com.loh.tally.ui.chat.detail.presenter;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.domain.database.chat.ChatService;
import com.loh.tally.domain.model.ChatMessage;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

import java.util.Date;

import javax.inject.Inject;

/**
 * File: ChatDetailPresenter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class ChatDetailPresenter extends BasePresenter<ChatDetailContract.View> implements ChatDetailContract.Presenter {

    private final ChatService chatService;

    @Inject
    public ChatDetailPresenter(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void sendMessage() {
        String message = getView().getMessage();

        if (message.isEmpty()) {
            return;
        }

        String moduleID = getView().getModuleID();

        if (moduleID != null) {
            ChatMessage chatMessage = new ChatMessage(new Date().getTime(), message);
            chatService.getModuleChat(getView().getModuleID()).push().setValue(chatMessage);
            getView().clearMessage();
            getView().hideKeypad();
        }
    }

    @Override
    public DatabaseReference getChatReference() {
        String moduleID = getView().getModuleID();
        return chatService.getModuleChat(moduleID);
    }
}
