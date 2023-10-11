package team.placeholder.internalprojectsmanagementsystem.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
