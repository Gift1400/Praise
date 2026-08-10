package za.ac.service.churchSiteService;

import za.ac.domain.ChurchSite;
import za.ac.service.IService;

import java.util.List;


public interface IChurchSite extends IService <ChurchSite, String>{
    List<String> getAll();
}
