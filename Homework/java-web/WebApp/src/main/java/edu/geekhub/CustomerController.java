package edu.geekhub;

import com.google.gson.Gson;
import edu.geekhub.user.User;
import edu.geekhub.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    UserService userService;

    @GetMapping()
    public String allCustomers() {
        return new Gson().toJson(userService.getUsers());
    }

    @GetMapping("customers/{id}")
    public String customerById(@PathVariable("id") Integer id) {
        return new Gson().toJson(userService.getUserById(id));
    }

    @PostMapping()
    public boolean create(@RequestBody User user) {
        userService.addUser(user);
        return true;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return true;
    }

    @PutMapping("customers/{id}")
    public boolean update(@RequestBody User user){
        userService.update(user.getId(), user);
        return true;
    }
}