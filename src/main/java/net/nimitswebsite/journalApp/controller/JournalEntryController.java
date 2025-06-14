//package net.nimitswebsite.journalApp.controller;
//
//import net.nimitswebsite.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//
//    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry) {
//        journalEntries.put(myEntry.getId(), myEntry);
//        return true;
//    }
//
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//    }
//
//    //Delete data by id
//
//    @DeleteMapping("id/{myId}")
//    public JournalEntry DeleteJournalEntryById(@PathVariable Long myId){
//        return journalEntries.remove(myId);
//    }
//
//    //Update data by id
//    @PutMapping("id/{myId}")
//    public JournalEntry updateJournalEntryById(@PathVariable Long myId,@RequestBody JournalEntry myEntry){
//      return journalEntries.put(myId, myEntry);
//    }
//
//
//
//}
