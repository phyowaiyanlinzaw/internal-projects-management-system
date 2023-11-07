package team.placeholder.internalprojectsmanagementsystem.service;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.*;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FakerService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final ArchitectureRepository architectureRepository;
    private final DeliverableRepository deliverableRepository;
    private final DeliverableTypeRepo deliverableTypeRepo;
    private final ReviewRepo reviewRepository;
    private final SystemOutlineRepository systemOutlineRepository;
    private final IssueRepository issueRepository;
    private final Faker faker = new Faker();

    //generate fake clients
    public void generateAndSaveFakeClients(int numberOfClients) {
        for (int i = 0; i < numberOfClients; i++) {
            Client client = new Client();
            client.setName(faker.name().fullName());
            client.setEmail(faker.internet().emailAddress());
            client.setPhone(faker.phoneNumber().phoneNumber());
            clientRepository.save(client);
        }
    }

    //generate fake projects
    public void generateAndSaveFakeProjects(int numberOfProjects) {
        for (int i = 0; i < numberOfProjects; i++) {
            Project project = new Project();
            project.setName(faker.job().field());
            project.setObjective(faker.lorem().paragraph());
            //long value for date
            project.setStart_date(faker.number().numberBetween(System.currentTimeMillis() - 31536000000L, System.currentTimeMillis()));
            project.setEnd_date(faker.number().numberBetween(System.currentTimeMillis(), System.currentTimeMillis() + 31536000000L));
            project.setBackground(faker.lorem().paragraph());
            project.setCurrent_phase(DevelopmentPhase.values()[faker.random().nextInt(DevelopmentPhase.values().length)]);
            project.setProjectManager(userRepository.findAllByRole(Role.PROJECT_MANAGER).get(faker.random().nextInt(userRepository.findAll().size())));
            project.setDuration(faker.number().numberBetween(1, 12));
            project.setClient(clientRepository.findAll().get(faker.random().nextInt(clientRepository.findAll().size())));
            projectRepository.save(project);
        }
    }

    //generate fake amount
    public void generateAndSaveFakeAmount(int numberOfAmount) {
        for (int i = 0; i < numberOfAmount; i++) {
            Amount amount = new Amount();
            amount.setBasic_design(faker.number().numberBetween(1000, 100000));
            amount.setDetail_design(faker.number().numberBetween(1000, 100000));
            amount.setCoding(faker.number().numberBetween(1000, 100000));
            amount.setIntegrated_testing(faker.number().numberBetween(1000, 100000));
            amount.setUnit_testing(faker.number().numberBetween(1000, 100000));
        }
    }

    //generate fake architecture
    public void generateAndSaveFakeArchitecture(int numberOfArchitecture) {
        for (int i = 0; i < numberOfArchitecture; i++) {
            Architecture architecture = new Architecture();
            architecture.setTech_name(faker.job().field());
            architectureRepository.save(architecture);
        }
    }

    //generate fake deliverable
    public void generateAndSaveFakeDeliverable(int numberOfDeliverable) {
        for (int i = 0; i < numberOfDeliverable; i++) {
            Deliverable deliverable = new Deliverable();
            //boolean
            deliverable.setStatus(faker.bool().bool());
            deliverable.setDeliverableTypes(deliverableTypeRepo.findAll().get(faker.random().nextInt(deliverableTypeRepo.findAll().size())));
            deliverableRepository.save(deliverable);
        }
    }

    //generate fake DeliverableType
    public void generateAndSaveDeliverableTypes(int numberOfDeliverableTypes) {
        for (int i = 0; i < numberOfDeliverableTypes; i++) {
            DeliverableType deliverableType = new DeliverableType();
            deliverableType.setType(faker.job().field());
            deliverableTypeRepo.save(deliverableType);
        }
    }

    //generate fake review
    public void generateAndSaveFakeReview(int numberOfReview) {
        for (int i = 0; i < numberOfReview; i++) {
            Review review = new Review();
            review.setInternal_review_count(faker.number().numberBetween(1, 10));
            review.setExternal_review_count(faker.number().numberBetween(1, 10));
            review.setUser(userRepository.findAll().get(faker.random().nextInt(userRepository.findAll().size())));
            reviewRepository.save(review);
        }
    }

    //generate fake SystemOutline
    public void generateAndSaveFakeSystemOutline(int numberOfSystemOutline) {
        for (int i = 0; i < numberOfSystemOutline; i++) {
            SystemOutLine systemOutLine = new SystemOutLine();
            systemOutLine.setAnalysis(faker.bool().bool());
            systemOutLine.setSys_design(faker.bool().bool());
            systemOutLine.setCoding(faker.bool().bool());
            systemOutLine.setTesting(faker.bool().bool());
            systemOutLine.setDeploy(faker.bool().bool());
            systemOutLine.setMaintenance(faker.bool().bool());
            systemOutLine.setDocument(faker.bool().bool());
            systemOutlineRepository.save(systemOutLine);
        }
    }

    //generate fake issue
    public void generateAndSaveFakeIssue(int numberOfIssue) {
        for (int i = 0; i < numberOfIssue; i++) {
            Issue issue = new Issue();
            issue.setProject(projectRepository.findAll().get(faker.random().nextInt(projectRepository.findAll().size())));
            issue.setImpact(faker.lorem().paragraph());
            issue.setIssue_category(Category.values()[faker.random().nextInt(Category.values().length)]);
            issue.setDescription(faker.lorem().paragraph());
            issue.setCorrective_action(faker.lorem().paragraph());
            issue.setRoot_cause(faker.lorem().paragraph());
            issue.setPreventive_action(faker.lorem().paragraph());
            issue.setSolved(faker.bool().bool());
            issue.setSolved_date(faker.number().numberBetween(System.currentTimeMillis() - 31536000000L, System.currentTimeMillis()));
            issue.setTitle(faker.job().field());
            issue.setUpdated_date(faker.number().numberBetween(System.currentTimeMillis() - 31536000000L, System.currentTimeMillis()));
            issue.setUser_pic(userRepository.findAll().get(faker.random().nextInt(userRepository.findAll().size())));
            issue.setUser_uploader(userRepository.findAll().get(faker.random().nextInt(userRepository.findAll().size())));
            issueRepository.save(issue);
        }
    }

    public void generateAndSaveFakeDepartments(int numberOfDepartments) {
        for (int i = 0; i < numberOfDepartments; i++) {
            Department department = new Department();
            //check for duplicate department name and generate new one
            String departmentName = faker.job().field();
            while (departmentRepository.findByName(departmentName) != null) {
                departmentName = faker.job().field();
            }
            department.setName(departmentName);
            departmentRepository.save(department);
        }
    }

    public void generateAndSaveFakeUsers(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(new BCryptPasswordEncoder().encode("Abc!@123"));
            user.setRole(Role.values()[faker.random().nextInt(Role.values().length)]);
//            user.setProject();
            //except user role for PMO,SDQC , set department for other roles
            if (!user.getRole().equals(Role.PMO) && !user.getRole().equals(Role.SDQC)) {
                user.setDepartment(departmentRepository.findAll().get(faker.random().nextInt(departmentRepository.findAll().size())));
            }else {
                user.setDepartment(null);
            }

            if (!user.getRole().equals(Role.PMO) && !user.getRole().equals(Role.SDQC) && !user.getRole().equals(Role.PROJECT_MANAGER)) {
                List<User> projectManagers = userRepository.findAllByRole(Role.PROJECT_MANAGER);
                if (!projectManagers.isEmpty()) {
                    // Generate a random index within the range of project managers
                    int randomIndex = faker.random().nextInt(projectManagers.size());

                    // Set the projectManager for the user
                    user.setProjectManager(projectManagers.get(randomIndex));
                } else {
                    // Handle the case where there are no project managers in the database
                    // You can choose to set it to null or handle it as needed.
                    user.setProjectManager(null);
                }
            } else {
                user.setProjectManager(null);
            }
            userRepository.save(user);
        }
    }
}
