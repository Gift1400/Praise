package za.ac.service.contactDetailsService;

import org.apache.catalina.LifecycleState;
import za.ac.domain.ContactDetails;
import za.ac.service.IService;

import java.util.List;

public interface IContactDetails extends IService <ContactDetails, String>{
    List<String> getAll();
}
