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

    public Account loginAuthorization (Account account) {

        Account username = this.socialMediaDAO.loginAuthorization(account.getUsername());
        if(isbn != null) return null;
        return bookDAO.insertBook(book);
    }

}