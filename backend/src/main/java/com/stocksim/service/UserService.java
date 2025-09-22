package com.stocksim.service;
import org.springframework.stereotype.Service;
import com.stocksim.repository.UserRepository;
import com.stocksim.model.User;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){this.repo = repo;}
    public User register(String username, String password){
        User u = new User();
        u.setUsername(username);
        u.setPassword(password); // NOTE: In real app hash passwords
        u.setCash(100000.0);
        return repo.save(u);
    }
    public Optional<User> login(String username, String password){
        return repo.findByUsername(username).filter(u -> u.getPassword().equals(password));
    }
    public Optional<User> findById(Long id){ return repo.findById(id); }
}
