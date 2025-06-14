package net.nimitswebsite.journalApp.controller;

import net.nimitswebsite.journalApp.Repsository.UserRepository;
import net.nimitswebsite.journalApp.Service.JournalEntryService;
import net.nimitswebsite.journalApp.Service.UserService;
import net.nimitswebsite.journalApp.entity.JournalEntry;
import net.nimitswebsite.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping()
    public ResponseEntity<?> getAllJournalEntries() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> journalEntry = user.getJournalEntries();
        if(journalEntry !=null && !journalEntry.isEmpty()) {
            return new ResponseEntity<>(journalEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> createEntry( @RequestBody JournalEntry myEntry) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Entry is not created, something is wrong :(");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        List<JournalEntry> collects =user.getJournalEntries().stream().filter(journalEntry -> journalEntry.getId().equals(myId)).toList();
        if(!collects.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Delete data by id

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> DeleteJournalEntryById(@PathVariable ObjectId myId) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
        boolean removed = journalEntryService.DeleteEntryById(myId,username);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    Update data by id
    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        List<JournalEntry> collects =user.getJournalEntries().stream().filter(journalEntry -> journalEntry.getId().equals(myId)).toList();
        if(!collects.isEmpty()){
            JournalEntry old_Entry = journalEntryService.findById(myId).orElse(null);
            if(old_Entry!=null){
                old_Entry.setTitle( !Objects.equals(newEntry.getContent(), "") ? newEntry.getTitle() : old_Entry.getTitle());
                old_Entry.setContent(newEntry.getContent()!=null && !Objects.equals(newEntry.getContent(), "") ? newEntry.getContent() : old_Entry.getContent());
            }
            journalEntryService.saveEntry(old_Entry);
            return new ResponseEntity<>(old_Entry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }



}
