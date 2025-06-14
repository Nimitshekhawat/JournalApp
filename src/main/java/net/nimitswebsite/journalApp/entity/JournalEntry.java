package net.nimitswebsite.journalApp.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "journal_Entries")
public class JournalEntry {
    private ObjectId id;

    @NonNull
    private String title;

    private String content;
    private LocalDateTime Date;

//    public LocalDateTime getDate() {
//        return Date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        Date = date;
//    }
//
//
//
//
//    @Id
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//


}
