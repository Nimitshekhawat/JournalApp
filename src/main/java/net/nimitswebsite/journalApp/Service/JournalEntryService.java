package net.nimitswebsite.journalApp.Service;

import net.nimitswebsite.journalApp.Repsository.JournalRepository;
import net.nimitswebsite.journalApp.entity.JournalEntry;
import net.nimitswebsite.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try{
            User user = userService.findByUsername(username);
            journalRepository.save(journalEntry);
            user.getJournalEntries().add(journalEntry);
            userService.saveEntry(user);
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("Error saving journal entry",e);
        }

    }
    public void saveEntry(JournalEntry journalEntry) {
        journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalRepository.findById(id);

    }

    @Transactional
    public boolean DeleteEntryById(ObjectId id, String username) {
        boolean removed=false;
        try {
            User user = userService.findByUsername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveEntry(user);
                journalRepository.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Error deleting journal entry",e);
        }
        return removed;
    }




}
