package com.nowcoder.wenda.service;

import com.nowcoder.wenda.dao.MessageDAO;
import com.nowcoder.wenda.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;

    @Autowired
    SensitiveService sensitiveService;

    public int addMessage(Message message) {
        String content = sensitiveService.filter(message.getContent());
        content = HtmlUtils.htmlEscape(content);
        message.setContent(content);
        return messageDAO.assMessage(message) > 0 ? message.getId() : 0;
    }

    public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
        return messageDAO.getConversationDetail(conversationId, offset, limit);
    }

    public List<Message> getConversationList(int userId, int offset, int limit) {
        return messageDAO.getConversationList(userId, offset, limit);
    }

    public int getConversattionUnreadCount(int userId,
                                   String conversationId) {
        return messageDAO.getConversattionUnreadCount(userId, conversationId);
    }

}
