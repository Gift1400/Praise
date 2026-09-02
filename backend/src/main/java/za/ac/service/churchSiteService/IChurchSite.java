package za.ac.service.churchSiteService;

import za.ac.domain.ChurchSite;
import za.ac.service.IService;

import java.util.*;


public interface IChurchSite extends IService <ChurchSite, String>{
    List<ChurchSite> getAll();
}
