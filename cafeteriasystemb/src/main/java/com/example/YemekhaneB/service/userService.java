package com.example.YemekhaneB.service;
import com.example.YemekhaneB.api.AuthenticationResponse;
import com.example.YemekhaneB.model.Role;
import com.example.YemekhaneB.model.Transaction;
import com.example.YemekhaneB.model.Title;
import com.example.YemekhaneB.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.YemekhaneB.auth.TokenService;
import java.util.List;

//servicelerde bilgileri alır ve business logice göre bir şeyler yapıp geri verir
@Service
public class userService {
    private final com.example.YemekhaneB.repository.userRepository userRepository;
    private final TitleService TitleService;
    private  final RoleService RoleService;
    private final PasswordEncoder PasswordEncoder;
    private final TokenService TokenService;
    private final AuthenticationManager AuthenticationManager;
    public userService(com.example.YemekhaneB.repository.userRepository userRepository,
                       TitleService TitleService,
                       RoleService RoleService,PasswordEncoder PasswordEncoder,
                       TokenService TokenService,AuthenticationManager AuthenticationManager) {
        this.userRepository = userRepository;
        this.TitleService = TitleService;
        this.RoleService = RoleService;
        this.PasswordEncoder = PasswordEncoder;
        this.TokenService = TokenService;
        this.AuthenticationManager = AuthenticationManager;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public AuthenticationResponse createUser(User newUser) {
        if(existingUsername(newUser)== true){
            return null;
        }
        Long titleId = newUser.getTitleId();
        Title result = null;
        Role role = new Role();
        role.setroleid(0L);
        Role role2 = new Role();
        if (titleId != null) {
            TitleService.saveTitle(newUser.getTitle());
            result = TitleService.saveTitle(newUser.getTitle());
            newUser.setTitle(result);
        }

            RoleService.saveRole(role);
            role2 = RoleService.saveRole(role);
            newUser.setRole(role2);
            newUser.setBalance(0L);
        newUser.setPassword(PasswordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);
        var JwtToken = TokenService.generateToken(newUser);
        return AuthenticationResponse.builder().token(JwtToken).build();

    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"));

    }
    public AuthenticationResponse LoginAuth(User user) {
        AuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        var username = user.getUsername();
        var user2 = userRepository.findByusername(username).orElseThrow();
        var JwtToken = TokenService.generateToken(user2);
        return AuthenticationResponse.builder().token(JwtToken).build();

    }
    public User getUserByUsername(String username) {
        // Use the appropriate method from your repository or data access layer
        return userRepository.findByUsername(username);
    }
    public String changeById(User updateUser){
        User mainUser = getUserById(updateUser.getId());
        if (updateUser.getUsername()!="" && updateUser.getUsername() != null){
            mainUser.setUsername(updateUser.getUsername());
        }
        if (updateUser.getPassword()!="" && updateUser.getPassword() != null){
            mainUser.setPassword(updateUser.getPassword());
        }
        if (updateUser.getTitleId() != null){
            if (updateUser.getTitleId() == 1 ||  updateUser.getTitleId() == 2 || updateUser.getTitleId() == 3 ||
                    updateUser.getTitleId() == 4 || updateUser.getTitleId() == 5) {
                Title result = null;

                if (TitleService.getTitleByTitleId(updateUser.getTitleId()) == null) {


                    TitleService.saveTitle(updateUser.getTitle());
                    result = TitleService.saveTitle(updateUser.getTitle());
                    mainUser.setTitle(result);
                } else {
                    result = TitleService.getTitleByTitleId(updateUser.getTitleId());
                    mainUser.setTitle(result);

                }
            }else{return "Title value should be between 1-5";}
        }
       if (updateUser.getroleid() != null){
           if(updateUser.getroleid()== 1 || updateUser.getroleid() == 0) {
               Role role = new Role();

               if (RoleService.getRoleByRoleId(updateUser.getroleid()) == null) {

                   RoleService.saveRole(updateUser.getRole());
                   role = RoleService.saveRole(updateUser.getRole());
                   mainUser.setRole(role);
               } else {
                   role = RoleService.getRoleByRoleId(updateUser.getroleid());
                   mainUser.setRole(role);

               }
           }else {return "Role Id should be 1 for Admins, 0 for Users";}
        }
        if (updateUser.getBalance() != null){
            mainUser.setBalance(updateUser.getBalance());
        }
        userRepository.save(mainUser);
        return "Successful" ;
    }

    public boolean changeBalance(Transaction newTransaction){
        User user  = getUserById(newTransaction.getuserid());
        if (user == null) {

            return false;
        }
        Long oldbalance =user.getBalance();

            if (newTransaction.getTransactiontype().equals("Purchase")) {
                if ( oldbalance - newTransaction.getAmount() >= 0){
                    user.setBalance(oldbalance - newTransaction.getAmount());
                    userRepository.save(user);
                    return true;
                }else{

                    System.out.println("burda null veriyo");

                    return false;}


            } else if(newTransaction.getTransactiontype().equals("Add") ){
                user.setBalance(oldbalance + newTransaction.getAmount());
                userRepository.save(user);
                return true;
            }

        System.out.println("yada burda veriyo");

        return false;
    }
    public boolean existingUsername(User user){
        if(userRepository.findByUsername(user.getUsername()) != null){
            return true;
        }
        return false;
    }
    public Long getBalance(String username){
        User user = getUserByUsername(username);
        return user.getBalance();
    }
    public Long getroleId(String username){
        User user = getUserByUsername(username);
        return user.getroleid();
    }
    public boolean purchasetitleId(Long id,User user){
        if (id == 1 ||  id == 2 || id == 3 ||
               id == 4 || id == 5) {
            Title result = null;

            if (TitleService.getTitleByTitleId(id) == null) {
                Title newTitle = new Title();
                newTitle.setTitleId(id);
                TitleService.saveTitle(newTitle);
                result = TitleService.saveTitle(newTitle);
               user.setTitle(result);
                userRepository.save(user);
                return true;
            } else {
                result = TitleService.getTitleByTitleId(id);
                user.setTitle(result);
                userRepository.save(user);
                return true;

            }
        }
        return false;
    }
}
