package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.repository.project.AvailableUserRepo;
import team.placeholder.internalprojectsmanagementsystem.service.project.AvailableEmployeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AESImpl implements AvailableEmployeeService {

    private final AvailableUserRepo availableUserRepo;

    @Override
    public void save(AvailableUser availableUser) {
        availableUserRepo.save(availableUser);
    }

    @Override
    public List<AvailableUser> getAllAvailableUser() {
        return availableUserRepo.findAllByAvaliableIsTrue();
    }

    @Override
    public AvailableUser getAvailableUserByUserId(long userId) {
        return availableUserRepo.findByUserId(userId);
    }
}
