package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.ContactDetails;
import za.ac.service.contactDetailsService.ContactDetailsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/contactDetails")
public class ContactDetailsController {

    private final ContactDetailsServiceImpl contactDetailsService;

    @Autowired
    public ContactDetailsController(ContactDetailsServiceImpl contactDetailsService){
        this.contactDetailsService = contactDetailsService;
    }

    @PostMapping("/create")
    public ContactDetails create(@RequestBody ContactDetails contactDetails){
        return contactDetailsService.create(contactDetails);
    }

    @GetMapping("/read/{contactDetailsId}")
    public ContactDetails read(@PathVariable String contactDetailsId){
        return contactDetailsService.read(contactDetailsId);
    }

    @PutMapping("/update")
    public ContactDetails update(@RequestBody ContactDetails contactDetails){
        return contactDetailsService.update(contactDetails);
    }

    @DeleteMapping("/delete")
    public boolean delete(@PathVariable String contactDetailsId){
        if(contactDetailsService.delete(contactDetailsId)){
            return true;
        }
        return false;
    }

    @GetMapping("/getAll")
    public List<ContactDetails> getAll(){
        return contactDetailsService.getAll();
    }
}
