package Service;

import Model.Account;
import Model.Message;
import DAO.SocialMediaDAO;

public class SocialMediaBlogService {
    SocialMediaDAO socialMediaDAO;

    public SocialMediaBlogService(){
        socialMediaDAO = new SocialMediaDAO();
    }
//not necesserly, the code would allow the testing of SocialMediaBlogService independently of SocialMediaDAO
    public SocialMediaBlogService(SocialMediaDAO socialMediaDAO){
        this.socialMediaDAO = socialMediaDAO;
    }

    public Account addAccount(Flight flight){
        return flightDAO.insertFlight(flight);
    }



}