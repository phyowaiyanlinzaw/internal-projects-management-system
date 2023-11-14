package team.placeholder.internalprojectsmanagementsystem.service.project;

import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;

import java.util.List;

public interface AvailableEmployeeService {

    public void save(AvailableUser availableUser);

    public List<AvailableUser> getAllAvailableUser();

    public AvailableUser getAvailableUserByUserId(long userId);

}
