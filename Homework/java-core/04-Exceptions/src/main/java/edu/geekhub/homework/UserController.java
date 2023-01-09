package edu.geekhub.homework;

import edu.geekhub.controller.Controller;
import edu.geekhub.models.User;
import edu.geekhub.models.request.Request;
import edu.geekhub.models.request.Response;

// Don't move this class
public class UserController implements Controller {

    private final UserService service;

    public UserController() {
        this.service = new UserService();
    }

    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    public Response process(Request request) {
        try {
            User user = (User) request.getData();
            
        } catch (Exception e) {
        }
        User user = (User) request.getData();
        return null;
    }
}




