package za.ac.service.contactDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.ContactDetails;
import za.ac.repository.IContactDetailsRepository;

import java.util.List;
@Service
public class ContactDetailsServiceImpl implements IContactDetails{

    public final IContactDetailsRepository repository;

    @Autowired
    public ContactDetailsServiceImpl(IContactDetailsRepository repository){ this.repository = repository;}
    @Override
    public ContactDetails create(ContactDetails contactDetails) {
        return null;
    }

    @Override
    public ContactDetails read(String s) {
        return null;
    }

    @Override
    public ContactDetails update(ContactDetails contactDetails) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<String> getAll() {
        return List.of();
    }

}
