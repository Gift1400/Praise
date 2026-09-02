package za.ac.service.contactDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.ContactDetails;
import za.ac.repository.IContactDetailsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContactDetailsServiceImpl implements IContactDetails{

    public final IContactDetailsRepository repository;

    @Autowired
    public ContactDetailsServiceImpl(IContactDetailsRepository repository){ this.repository = repository;}
    @Override
    public ContactDetails create(ContactDetails contactDetails) {
        return repository.save(contactDetails);
    }

    @Override
    public ContactDetails read(String contactDetailsId) {
        return repository.findById(contactDetailsId).orElse(null);
    }

    @Override
    public ContactDetails update(ContactDetails contactDetails) {
        return repository.save(contactDetails);
    }

    @Override
    public boolean delete(String contactDetailsId) {
        if(repository.existsById(contactDetailsId)){
            repository.deleteById(contactDetailsId);
            return true;
        }
        return false;
    }

    @Override
    public List<ContactDetails> getAll() {
        return repository.findAll();
    }

}
