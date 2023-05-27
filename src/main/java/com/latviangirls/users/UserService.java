package com.latviangirls.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser (User user) throws  Exception{
        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setNickName(user.getNickName());

        this.userRepository.save(user);
    }
    public User verifyUser(String nickName, String password) throws Exception {
        User user = this.userRepository.findByNickNameAndPassword(nickName, password);
        if(user == null) throw new Exception ("Nickname or password is not correct");
        return user;
    }

    public User findUserById(long userId) throws Exception{
        return this.userRepository.findById(userId).orElseThrow(() -> new Exception("User with id" + userId + "not found"));
    }

}
