package za.ac.service.sermonService;

import za.ac.domain.Sermon;
import za.ac.service.IService;

import java.util.*;

public interface ISermon extends IService <Sermon, String>{
    List<Sermon> getAll();
    List<Sermon> getSermonsByLeader(String leaderId);
}
