package net.nimitswebsite.journalApp.Service;

import net.nimitswebsite.journalApp.Repsository.JournalRepository;
import net.nimitswebsite.journalApp.Repsository.UserRepository;
import net.nimitswebsite.journalApp.entity.JournalEntry;
import net.nimitswebsite.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;


    public void saveNewEntry(User userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER"));
        userRepository.save(userEntry);
    }
    public void saveEntry(User userEntry) {
        userRepository.save(userEntry);
    }

    public void saveadmin(User userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("ADMIN"));
        userRepository.save(userEntry);
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);

    }
    public boolean DeleteEntryById(ObjectId id) {
        userRepository.deleteById(id);
        return true;
    }
    public User findByUsername(String username) {
       return userRepository.findByUsername(username);
    }




}
