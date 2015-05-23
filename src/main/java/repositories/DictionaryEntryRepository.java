package repositories;

import data.DictionaryEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by mmatosevic on 22.5.2015.
 */
public interface DictionaryEntryRepository extends CrudRepository<DictionaryEntry, Long> {
    Iterable<DictionaryEntry> findByEnglish(String english);
    Iterable<DictionaryEntry> findByEnglishLike(String english);
}
