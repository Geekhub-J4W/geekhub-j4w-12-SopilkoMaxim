package edu.geekhub.homework;

import edu.geekhub.controller.Controller;
import edu.geekhub.exceptions.ConnectionInterruptedException;
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
        User user;
        try{
        user = getValidUser(request);
        }catch (IllegalArgumentException | ClassCastException e)
        {
            System.out.println(e.getMessage());
            return Response.fail(request.getData());
        }

        try{
            service.add(user);
        }catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            return Response.fail(request.getData());
        }
        return Response.ok(request.getData());

    }

    private User getValidUser(Request request) {
        User user;
        if(request.getData() == null)
        {
            throw new IllegalArgumentException("Data is null");
        }
        else {
            user = (User) request.getData();
        }
        return user;
    }
}




