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
/* 
    public Message addNewMessage (int posted_by, String message_text, long time_posted_epoch) {

        return socialMediaDAO.addNewMessage(posted_by, message_text, time_posted_epoch);
    }
*/
    public List<Message> getAllMessages() {
        return socialMediaDAO.getAllMessages();
    }

    public List<Message> getMessageById() {

         return socialMediaDAO.getMessageById(SocialMediaController.getMessageByIdHandler.messageId);
    }


}