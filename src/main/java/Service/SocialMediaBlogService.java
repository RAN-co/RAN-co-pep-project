package Service;

import Model.Account;
import Model.Message;
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

}