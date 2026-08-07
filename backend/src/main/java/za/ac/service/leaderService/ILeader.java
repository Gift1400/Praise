package za.ac.service.leaderService;

import za.ac.domain.Leader;
import za.ac.service.IService;
import java.util.List;


public interface ILeader extends IService<Leader, String> {
    List<String> getAll();
}
