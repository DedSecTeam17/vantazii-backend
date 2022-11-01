package com.example.vantazii;

import com.example.vantazii.CustomerRole.CustomerRoleRepo;
import com.example.vantazii.RolePermission.RolePermissionRepo;
import com.example.vantazii.core.config.TwillioConfig;
import com.example.vantazii.customer.CustomerRepo;
import com.example.vantazii.league.League;
import com.example.vantazii.league.LeagueRepo;
import com.example.vantazii.match.Match;
import com.example.vantazii.match.MatchRepo;
import com.example.vantazii.permission.AppPermissionRepo;
import com.example.vantazii.role.AppRoleRepo;
import com.example.vantazii.team.Team;
import com.example.vantazii.team.TeamRepo;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class VantaziiApplication {


    @Autowired
    private TwillioConfig twilioConfig;
    public static void main(String[] args) {
        SpringApplication.run(VantaziiApplication.class, args);
    }



    @PostConstruct
    public void initTwilio(){
        System.out.println("initTwilio-------------> {}{}{}");
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepo customerRepo, CustomerRoleRepo customerRoleRepo, AppRoleRepo appRoleRepo, AppPermissionRepo appPermissionRepo, RolePermissionRepo rolePermissionRepo, LeagueRepo leagueRepo, TeamRepo teamRepo, MatchRepo matchRepo){
        return args -> {


//            Optional<League> leagues = leagueRepo.findById(UUID.fromString("0bae5629-2d3a-4ecd-af01-0684069b80b7"));
//
//            League league = leagues.get();
//            Team barca = league.getTeams().get(0);
//            Team update = league.getTeams().get(1);
//
//
//            Match match = new Match();
//            match.setLeagune(league);
//            match.setAwayTeam(barca);
//            match.setHomeTeam(update);
//            match.setStartDate(LocalDateTime.now().plusHours(6));
//            match.setEndDate(LocalDateTime.now().plusHours(7));
//            match.setResult("0:0");
//
//            matchRepo.save(match);


//            League league = new League();
//            league.setLeagueLogo("path to logo");
//            league.setLeagueName("Premiuer league");
//            league.setLeagueDescription("desc");
//            leagueRepo.save(league);


//            League leagues = leagueRepo.findAll().iterator().next();


//            System.out.println(leagues.getTeams().stream().findFirst().get().getTeamName());
//            Team team = new Team();
//            team.setTeamDescription("desc");
//            team.setTeamName("name");
//            team.setTeamLogo("path");
//            team.setLeague(leagues);
//            teamRepo.save(team);


//            Customer customer = new Customer();
//
//            customer.setCreatedAt(LocalDateTime.now());
//            customer.setEmail("melamin100@yahoo.com");
//            customer.setSmsCode("2314");
//            customer.setVerified(true);
//            customer.setUserName("mohammed elamin");
//            customer.setPhoneNumber("0525221632");
//            customer.setPassword("mohamed1337");
//
////
//            customerRepo.save(customer);
//            AppRole adminRole = new AppRole();
//            adminRole.setRoleName(RoleName.ADMIN);
//            AppRole customerRole = new AppRole();
//            customerRole.setRoleName(RoleName.CUSTOMER);
//            appRoleRepo.saveAll(List.of(adminRole,customerRole));
//

//            AppPermission read = new AppPermission();
//            read.setPermissionName(PermissionName.READ);
//            AppPermission write = new AppPermission();
//            write.setPermissionName(PermissionName.WRITE);
//            AppPermission delete = new AppPermission();
//            delete.setPermissionName(PermissionName.DELETE);
//            AppPermission update = new AppPermission();
//            update.setPermissionName(PermissionName.UPDATE);

//            appPermissionRepo.saveAll(List.of(read,write,delete,update));

//            List<AppRole> roles  = appRoleRepo.findAll();
//            List<AppPermission> permissions = appPermissionRepo.findAll();
//
//            roles.forEach(appRole -> {
//                if (appRole.getRoleName().equals(RoleName.ADMIN)){
////                    admin
//                    permissions.forEach(permission ->{
//                        RolePermission rolePermission = new RolePermission();
//                        rolePermission.setAppRolePermission(appRole);
//                        rolePermission.setAppPermission(permission);
//                        RolePermissionID rolePermissionID = new RolePermissionID();
//                        rolePermissionID.setRoleID(appRole.getId());
//                        rolePermissionID.setPermissionID(permission.getId());
//                        rolePermission.setRolePermissionID(rolePermissionID);
//                        rolePermissionRepo.save(rolePermission);
//                    });
//                }else {
////                    customer
//                    List<AppPermission> customerPermissions = permissions.stream().filter(new Predicate<AppPermission>() {
//                        @Override
//                        public boolean test(AppPermission appPermission) {
//                            return appPermission.getPermissionName().equals(PermissionName.READ);
//                        }
//                    }).collect(Collectors.toList());
//                    customerPermissions.forEach(permission ->{
//                        RolePermission rolePermission = new RolePermission();
//                        rolePermission.setAppRolePermission(appRole);
//                        rolePermission.setAppPermission(permission);
//                        RolePermissionID rolePermissionID = new RolePermissionID();
//                        rolePermissionID.setRoleID(appRole.getId());
//                        rolePermissionID.setPermissionID(permission.getId());
//                        rolePermission.setRolePermissionID(rolePermissionID);
//                        rolePermissionRepo.save(rolePermission);
//                    });
//                }
//
//            });


//            AppPermission appPermission =new AppPermission();
//            appPermission.setPermissionName(PermissionName.READ);
//            appPermissionRepo.save(appPermission);
//            List<Customer> customers  = customerRepo.findAll();
//            List<AppRole> roles  = appRoleRepo.findAll();
//            List<AppPermission> permissions  = appPermissionRepo.findAll();

//            RolePermission rolePermission = new RolePermission();
//            RolePermissionID rolePermissionID = new RolePermissionID();
//            rolePermissionID.setPermissionID(permissions.stream().findFirst().get().getId());
//            rolePermissionID.setRoleID(roles.stream().findFirst().get().getId());
//
//            rolePermission.setRolePermissionID(rolePermissionID);
//            rolePermission.setAppPermission(permissions.stream().findFirst().get());
//            rolePermission.setAppRolePermission(roles.stream().findFirst().get());

//            rolePermissionRepo.save(rolePermission);



//            System.out.println(customers.stream().findFirst().get().getCustomerRoleIDS().stream().findFirst().get().getAppRole().getRolePermissions().stream().findFirst().get().getAppPermission().getPermissionName().name());
//
//
//            System.out.println(roles.stream().findFirst().get().getCustomerRoleIDS().stream().findFirst().get().getCustomer().getUserName());

//


//            CustomerRole customerRole = new CustomerRole();
//            CustomerRoleID customerRoleID =     new CustomerRoleID();
//            customerRoleID.setCustomerId(customers.stream().findFirst().get().getId());
//            customerRoleID.setAppRoleID(roles.stream().findFirst().get().getId());
//
//
//            customerRole.setCustomerRoleID(customerRoleID);
//            customerRole.setAppRole(roles.stream().findFirst().get());
//            customerRole.setCustomer(customers.stream().findFirst().get());
//
//            customerRoleRepo.save(customerRole);


//     customer has N Roles / roles belong to many customer




        };
    }

}
