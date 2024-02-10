package com.example.YemekhaneB.api;

import com.example.YemekhaneB.model.User;
import com.example.YemekhaneB.service.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//controllerlar bilgileri alır ve servislere gönderir
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {

    private final userService userService;

    public UserController(userService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String searchUsername) {
        List<User> allUsers = userService.getUsers();

        // If a search query is provided, filter the users by username
        if (searchUsername != null && !searchUsername.isEmpty()) {
            String lowerCaseSearchUsername = searchUsername.toLowerCase();
            allUsers = allUsers.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(lowerCaseSearchUsername))
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@RequestBody Long id){
        User result = getUserbyId(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    private  User getUserbyId(Long id) {
        return userService.getUserById(id);
    }



    @PutMapping("/adminu")
    public ResponseEntity<ApiResponse> ChangeEverything(@RequestBody User updatedUser) {
        if (updatedUser.getId() == null) {
            return new ResponseEntity<>(new ApiResponse("Invalid Request: Id is missing"), HttpStatus.BAD_REQUEST);
        }

        userService.changeById(updatedUser);
        String Result = userService.changeById(updatedUser);

        return new ResponseEntity<>(new ApiResponse(Result), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteByUsername(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/auth/create")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody User NewUser){ //neyi geri vereceğimizi yazıyoruz

        return new ResponseEntity<>(userService.createUser(NewUser),HttpStatus.CREATED); //postta created veriliyo
    }
    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody User user) {

       return new ResponseEntity<>(userService.LoginAuth(user),HttpStatus.OK);

    }
    @GetMapping("balance/{username}")
    public ResponseEntity<Long> getBalance(@PathVariable  String username){
        return new ResponseEntity<>(userService.getBalance(username),HttpStatus.OK);
    }
    @GetMapping("role/{username}")
    public ResponseEntity<Long> getroleId(@PathVariable  String username){
        return new ResponseEntity<>(userService.getroleId(username),HttpStatus.OK);
    }
}