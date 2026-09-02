package za.ac.service.contactDetailsService;

import za.ac.domain.ContactDetails;
import za.ac.service.IService;
import java.util.*;

public interface IContactDetails extends IService <ContactDetails, String>{
    List<ContactDetails> getAll();
}
