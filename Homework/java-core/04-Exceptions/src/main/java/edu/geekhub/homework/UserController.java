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
            if (user.getId() == null) throw new IdException("Id is null");
            String email = user.getEmail();
            if (email == null) throw new EmailException("Email is null");
            if (email.isEmpty()) throw new EmailException("Email is empty");
            if (email.contains(" ")) throw new EmailException("Email contains space");
            if (email.contains("(") || email.contains(")") || email.contains("{") || email.contains("}") ||
                    email.contains("/") || email.contains("[") || email.contains("]") || email.contains("'"))
                throw new EmailException("Email contains special characters");
            String username = user.getUserName();
            if (username == null) throw new UserNameException("User name is null");
            if (username.isEmpty()) throw new UserNameException("User name is empty");
            if (username.contains(" ")) throw new UserNameException("UserName has more than one word");
            char[] usernamearray = username.toCharArray();
            for (int i = 0; i < username.length(); i++) {
                if (Character.isUpperCase(usernamearray[i])) {
                    throw new UserNameException("UserName contains upercase");
                }
            }
            String fullname = user.getFullName();
            if (fullname == null) throw new FullNameException("Full name name is null");
            if (fullname.isEmpty()) throw new FullNameException("Full name is empty");
            char[] fullnamearray = fullname.toCharArray();
            int countspace = 0;
            for (int i = 0; i < fullname.length(); i++) {
                if (fullnamearray[i] == ' ')
                    countspace++;
            }
            if (countspace >= 2) throw new FullNameException("Full name has more then two words");
            for(int i =0;i<fullname.length();i++)
            {
                if (Character.isDigit(fullnamearray[i]))
                    throw new FullNameException("Full name has a digit");
            }
            int age = user.getAge();
            if (age <= 18)
                throw new AgeException("Age under 18");
            if (age>=100)
                throw new AgeException("Age more than 100");
            if ((Integer)age==null)
                throw new AgeException("Age is null");
            String notes = user.getNotes();
            if (notes == null)
                throw new NoteException("Note is null");
            if(notes.isEmpty())
                throw new NoteException("Note is empty");
            long followers = user.getAmountOfFollowers();
            if (followers<=0)
                throw new FollowersException("Followers less than 0");
            if ((Long)followers==null)
                throw new FollowersException("Followers is null");
            return Response.ok(request.getData());
        } catch (Exception e) {
        }
        return null;
    }
}




