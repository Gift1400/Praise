package za.ac.service.bibleVerseService;

import org.springframework.stereotype.Service;
import za.ac.domain.BibleVerse;
import za.ac.domain.Enums.LifeSituation;
import za.ac.repository.IBibleVerseRepository;

import java.util.Collections;
import java.util.*;

@Service
public class BibleVerseServiceImpl implements IBibleVerse{

    private final IBibleVerseRepository repository;


    public BibleVerseServiceImpl(IBibleVerseRepository repository) {
        this.repository = repository;
    }

    @Override
    public BibleVerse create(BibleVerse bibleVerse) {
        return repository.save(bibleVerse);
    }

    @Override
    public BibleVerse read(String bibleVerseId) {
        return repository.findById(bibleVerseId).orElse(null);
    }

    @Override
    public BibleVerse update(BibleVerse bibleVerse) {
        return repository.save(bibleVerse);
    }

    @Override
    public boolean delete(String bibleVerseId) {
        if(repository.existsById(bibleVerseId)){
            repository.deleteById(bibleVerseId);
            return true;
        }
        return false;
    }

    @Override
    public List<BibleVerse> getAll() {
        return repository.findAll();
    }

    @Override
    public List<BibleVerse> getVerseBySituation(LifeSituation situation) {
        if(situation == null){
            return Collections.emptyList();
        }
        return repository.getVerseBySituation(situation);
    }

}
