package za.ac.service.tesimonyService;

import za.ac.domain.Testimony;
import za.ac.service.IService;
import java.util.*;

public interface ITestimony extends IService <Testimony, String>{
    List<Testimony> getAll();
}
