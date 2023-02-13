package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.SocialMediaDAO;
import Model.Account;
import Model.Message;
import Service.SocialMediaBlogService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */


public class SocialMediaController {

    public SocialMediaBlogService socialMediaBlogService;
    public SocialMediaDAO socialMediaDAO;

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        socialMediaBlogService = new SocialMediaBlogService ();
        
        app.post("/register", this::accountRegistrationHandler); //OK
        app.post("/login", this::userAuthorizationHandler);  // equals?!
        app.post("/messages", this::newMessageCreationHandler);  // OK
        app.get("/messages", this::getAllMessagesHandler);  // OK
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler); // Port issues
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler); // issue in DAO
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserHandler); //OK

        return app;
    }
        
        private void accountRegistrationHandler(Context ctx) throws JsonProcessingException {
            
            ObjectMapper om = new ObjectMapper();
            Account account = om.readValue(ctx.body(), Account.class);
            Account createdAccount = socialMediaBlogService.addAccount(account);
            if(createdAccount != null && !account.getUsername().isBlank() && account.getPassword().length() > 4){

                ctx.json(om.writeValueAsString(createdAccount));
                
            }else{
                ctx.status(400);
            }
     }

     private void userAuthorizationHandler(Context ctx) throws JsonProcessingException {
            
        ObjectMapper om = new ObjectMapper();
        Account accountInfo = om.readValue(ctx.body(), Account.class);
        Account accAuthorization = socialMediaBlogService.userAuthorization(accountInfo.getUsername(), accountInfo.getPassword());
        
        if(accAuthorization != null){
            
            ctx.json(accAuthorization);
            
        }else{
            ctx.status(401);
        }
 }

 private void newMessageCreationHandler(Context ctx) throws JsonProcessingException {
            
    ObjectMapper om = new ObjectMapper();
    Message message = om.readValue(ctx.body(), Message.class);
    Message newMessage = socialMediaBlogService.addNewMessage(message);
    if(newMessage != null){

        ctx.json(om.writeValueAsString(newMessage));
        
    }else{
        ctx.status(400);
    }
}

public void getAllMessagesHandler(Context ctx){
    List<Message> messages = socialMediaBlogService.getAllMessages();
    ctx.json(messages);
}
 

private void getMessageByIdHandler(Context ctx) {
    int messageId = Integer.parseInt(ctx.pathParam("message_id"));
    ctx.json(socialMediaBlogService.getMessageById(messageId));
    ctx.pathParam("message_id");   
}


private void deleteMessageByIdHandler(Context ctx){
    int messageId = Integer.parseInt(ctx.pathParam("message_id"));
    ctx.json(socialMediaBlogService.getMessageById(messageId));
    ctx.pathParam("message_id");   
        
/*
Message deletedMessage = socialMediaBlogService.deleteMessage(messageId);
        if (deletedMessage != null) {
            return ctx.json(deletedMessage);
        } else {
            ctx.status(200);
            return ctx.result("");
        }
 */
}


private void updateMessageByIdHandler(Context ctx) throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    Message message = om.readValue(ctx.body(), Message.class);
    int message_id = Integer.parseInt(ctx.pathParam("message_id"));
    Message updatedMessage = socialMediaBlogService.updateMessage(message_id, message.getMessage_text());
    System.out.println(updatedMessage);
    if(updatedMessage == null){
        ctx.status(400);
    }else{
        ctx.json(om.writeValueAsString(updatedMessage));
    }

}


/*
public Context updateMessageByIdHandler(Context ctx) {
   
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        String messageText = ctx.body();

        Message updatedMessage = socialMediaBlogService.updateMessage(messageId, messageText);
        if (updatedMessage != null) {
            return ctx.status(200).json(updatedMessage);
        } else {
            return ctx.status(400);
        }
    }
*/

    public Context getAllMessagesByUserHandler(Context ctx) {
    
        int accountId = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = socialMediaBlogService.getMessagesByAccountId(accountId);
        return ctx.json(messages);
      }


    /*
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    
}
