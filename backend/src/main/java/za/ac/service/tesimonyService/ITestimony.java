package za.ac.service.tesimonyService;

import jakarta.persistence.SecondaryTable;
import za.ac.domain.Testimony;
import za.ac.service.IService;
import java.util.*;

public interface ITestimony extends IService <Testimony, String>{
    Set<Testimony> getAll();
}
