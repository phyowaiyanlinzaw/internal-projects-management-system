package team.placeholder.internalprojectsmanagementsystem.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {


    List<Client> selectAllUser();
    Client findById(long id);

    Client findByName(String name);





}
