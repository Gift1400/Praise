package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.BibleVerse;
import za.ac.service.bibleVerseService.BibleVerseServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/bibleVerse")
public class BibleVerseController {

    public final BibleVerseServiceImpl bibleVerseService;

    @Autowired
    public BibleVerseController(BibleVerseServiceImpl bibleVerseService) {
        this.bibleVerseService = bibleVerseService;
    }

    @PostMapping("/create")
    public BibleVerse create(@RequestBody BibleVerse bibleVerse){
        return bibleVerseService.create(bibleVerse);
    }
    
    @GetMapping("/read/{bibleVerseId}")
    public BibleVerse read(@PathVariable String bibleVerseId){
        return bibleVerseService.read(bibleVerseId);
    }
    
    @PutMapping("/update")
    public BibleVerse update(@RequestBody BibleVerse bibleVerse){
        return bibleVerseService.update(bibleVerse);
    }
    
    @DeleteMapping("/delete/{bibleVerseId}")
    public boolean delete(@PathVariable String bibleVerseId){
        if(bibleVerseService.delete(bibleVerseId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<BibleVerse> getAll(){
        return bibleVerseService.getAll();
    }






}
