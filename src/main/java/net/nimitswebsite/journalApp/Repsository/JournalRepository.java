package net.nimitswebsite.journalApp.Repsository;

import net.nimitswebsite.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


@Component
public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId> {

}

// Controller --> Service --> Repository