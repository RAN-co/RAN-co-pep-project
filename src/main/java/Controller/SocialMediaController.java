package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        ObjectMapper om = new ObjectMapper();

        //#1 Account registration
        private void accountRegistrationHandler(Context ctx) throws JsonProcessingException {
        app.post("localhost:8080/register", ctx -> {
              
            Account account = om.readValue(ctx.body(), Account.class);
            Account createdAccount = userRegistrationService.addAccount(account);
            if(createdAccount == null || createdAccount.isBlank() || password == null || password.length() < 4){
                ctx.status(400);
                
            }else{
                ctx.json(om.writeValueAsString(createdAccount));
            }
     });}
    

     //#2 login
     app.post("localhost:8080/login", ctx -> {
        
     });
        
//#3 creation of new messages
    app.post("localhost:8080/messages", ctx -> {
    
        Message message = om.readValue(ctx.body(), Message.class);
        Message newMessage = messageService.addMessage(message);
        if(!message.getMessageText().isBlank() && message.getMessageText().length() < 255 && userExists(message.getPostedBy())) {
            int messageId = persistMessage(message);
            message.setMessageId(messageId);
            ctx.json(message);
            ){
            ctx.json(om.writeValueAsString(newMessage));
        }else{
            ctx.status(400);
        }

    );}}

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


}