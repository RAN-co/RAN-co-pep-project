package Service;

import Model.Account;
import Model.Message;

import java.util.List;

import Controller.SocialMediaController;
import DAO.SocialMediaDAO;

public class SocialMediaBlogService {
    public SocialMediaDAO socialMediaDAO;

    public SocialMediaBlogService(){
       this.socialMediaDAO = new SocialMediaDAO();
    }

    public Account addAccount(Account account){
    
        return socialMediaDAO.addAccount(account);
        }

    public Account userAuthorization (String username, String password) {

        return socialMediaDAO.userAuthorization(username, password);
    }

    public Message addNewMessage (int posted_by, String message_text, long time_posted_epoch) {

        return socialMediaDAO.addNewMessage(posted_by, message_text, time_posted_epoch);
    }
/*
    public List<Message> getAllMessages() {
        return socialMediaDAO.getAllMessages();
    }
 */
    public Message getMessageById(int message_id) {

         return socialMediaDAO.getMessageById(message_id);
    }

    public Message deleteMessage(int messageId) {

        return socialMediaDAO.deleteMessage(messageId);
    }

    public Message updateMessage(int messageId, String messageText) {
        if (messageText == null || messageText.isBlank() || messageText.length() > 255) {
            return null;
        }
        return socialMediaDAO.updateMessage(messageId, messageText);
    }

    public List<Message> getMessagesByAccountId(int accountId) {
        return socialMediaDAO.getMessagesByAccountId(accountId);
      }

}