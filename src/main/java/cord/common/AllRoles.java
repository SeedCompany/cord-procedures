package cord.common;

import java.util.Arrays;
import java.util.List;

import cord.roles.*;

public class AllRoles {
  public Administrator Administrator;
  public Consultant Consultant;
  public ConsultantManager ConsultantManager;
  public Controller Controller;
  public FieldOperationsDirector FieldOperationsDirector;
  public FinancialAnalystGlobal FinancialAnalystGlobal;
  public FinancialAnalystOnProject FinancialAnalystOnProject;
  public Fundraising Fundraising;
  public Intern Intern;
  public Leadership Leadership;
  public Liaison Liaison;
  public Marketing Marketing;
  public ProjectManagerGlobal ProjectManagerGlobal;
  public ProjectManagerOnProject ProjectManagerOnProject;
  public RegionalCommunicationCoordinator RegionalCommunicationCoordinator;
  public RegionalDirectorGlobal RegionalDirectorGlobal;
  public RegionalDirectorOnProject RegionalDirectorOnProject;
  public StaffMember StaffMember;
  public Translator Translator;

  public AllRoles(){
    this.Administrator = new Administrator();
    this.Consultant = new Consultant();
    this.ConsultantManager = new ConsultantManager();
    this.Controller = new Controller();
    this.FieldOperationsDirector = new FieldOperationsDirector();
    this.FinancialAnalystGlobal = new FinancialAnalystGlobal();
    this.FinancialAnalystOnProject = new FinancialAnalystOnProject();
    this.Fundraising = new Fundraising();
    this.Intern = new Intern();
    this.Leadership = new Leadership();
    this.Liaison = new Liaison();
    this.Marketing = new Marketing();
    this.ProjectManagerGlobal = new ProjectManagerGlobal();
    this.ProjectManagerOnProject = new ProjectManagerOnProject();
    this.RegionalCommunicationCoordinator = new RegionalCommunicationCoordinator();
    this.RegionalDirectorGlobal = new RegionalDirectorGlobal();
    this.RegionalDirectorOnProject = new RegionalDirectorOnProject();
    this.StaffMember = new StaffMember();
    this.Translator = new Translator();
  }

  public BaseRole getRoleByStringName(String name){
    switch (name){
      case "AdministratorRole":                       return this.Administrator;
      case "ConsultantRole":                          return this.Consultant;
      case "ConsultantManagerRole":                   return this.ConsultantManager;
      case "ControllerRole":                          return this.Controller;
      case "FieldOperationsDirectorRole":             return this.FieldOperationsDirector;
      case "FinancialAnalystOnProjectRole":           return this.FinancialAnalystOnProject;
      case "FinancialAnalystGlobalRole":              return this.FinancialAnalystGlobal;
      case "FundraisingRole":                         return this.Fundraising;
      case "InternRole":                              return this.Intern;
      case "LeadershipRole":                          return this.Leadership;
      case "LiaisonRole":                              return this.Liaison;
      case "MarketingRole":                           return this.Marketing;
      case "ProjectManagerOnProjectRole":             return this.ProjectManagerOnProject;
      case "ProjectManagerGlobalRole":                return this.ProjectManagerGlobal;
      case "RegionalCommunicationCoordinatorRole":    return this.RegionalCommunicationCoordinator;
      case "RegionalDirectorOnProjectRole":           return this.RegionalDirectorOnProject;
      case "RegionalDirectorGlobalRole":              return this.RegionalDirectorGlobal;
      case "StaffMemberRole":                         return this.StaffMember;
      case "TranslatorRole":                          return this.Translator;

      default:                                    return null;
    }
  }

  public List<BaseRole> allRolesList(){
    return Arrays.asList(
      this.Administrator,
      this.Consultant,
      this.ConsultantManager,
      this.Controller,
      this.FieldOperationsDirector,
      this.FinancialAnalystGlobal,
      this.FinancialAnalystOnProject,
      this.Fundraising,
      this.Intern,
      this.Leadership,
      this.Liaison,
      this.Marketing,
      this.ProjectManagerGlobal,
      this.ProjectManagerOnProject,
      this.RegionalCommunicationCoordinator,
      this.RegionalDirectorGlobal,
      this.RegionalDirectorOnProject,
      this.StaffMember,
      this.Translator
    );
  }

  public List<BaseRole> globalRolesList(){
    return Arrays.asList(
      this.Administrator,
      this.ConsultantManager,
      this.Controller,
      this.FieldOperationsDirector,
      this.FinancialAnalystGlobal,
      this.Fundraising,
      this.Leadership,
      this.Marketing,
      this.ProjectManagerGlobal,
      this.RegionalDirectorGlobal,
      this.RegionalDirectorOnProject,
      this.StaffMember
    );
  }

  public List<BaseRole> projectRolesList(){
    return Arrays.asList(
      this.Consultant,
      this.FinancialAnalystOnProject,
      this.Intern,
      this.Liaison,
      this.ProjectManagerOnProject,
      this.RegionalCommunicationCoordinator,
      this.RegionalDirectorOnProject,
      this.Translator
    );
  }

  public static String getFrontendRoleNameFromApiRoleName(RoleNames role){
    switch (role){
      case AdministratorRole:
      case ConsultantManagerRole:
      case ConsultantRole:
      case ControllerRole:
      case FieldOperationsDirectorRole:
      case FundraisingRole:
      case InternRole:
      case LeadershipRole:
      case LiaisonRole:
      case MarketingRole:
      case MentorRole:
      case RegionalCommunicationCoordinatorRole:
      case StaffMemberRole:
      case TranslatorRole:
        return role.name().replace("Role", "");
      case ProjectManagerGlobalRole:
      case RegionalDirectorGlobalRole:
      case FinancialAnalystGlobalRole:
        return role.name().replace("GlobalRole", "");
      case ProjectManagerOnProjectRole:
      case RegionalDirectorOnProjectRole:
      case FinancialAnalystOnProjectRole:
        return role.name().replace("OnProjectRole", "");
      default: 
        System.out.println("frontend role name not found");
        return null;
    }
  }

  public static RoleNames getRoleNameEnumFromFeString(String roleName, Boolean isMember){

    switch (roleName){
      case "Administrator":                                           return RoleNames.AdministratorRole;
      case "Consultant":                                              return RoleNames.ConsultantRole;
      case "ConsultantManager":                                       return RoleNames.ConsultantManagerRole;
      case "Controller":                                              return RoleNames.ControllerRole;
      case "FieldOperationsDirector":                                 return RoleNames.FieldOperationsDirectorRole;
      case "FinancialAnalyst":                    if (isMember)       return RoleNames.FinancialAnalystOnProjectRole;
                                                                      return RoleNames.FinancialAnalystGlobalRole;
      case "Fundraising":                                             return RoleNames.FundraisingRole;
      case "Intern":                                                  return RoleNames.InternRole;
      case "Leadership":                                              return RoleNames.LeadershipRole;
      case "Liaison":                                                  return RoleNames.LiaisonRole;
      case "Marketing":                                               return RoleNames.MarketingRole;
      case "ProjectManager":                      if (isMember)       return RoleNames.ProjectManagerOnProjectRole;
                                                                      return RoleNames.ProjectManagerGlobalRole;
      case "RegionalCommunicationCoordinator":                        return RoleNames.RegionalCommunicationCoordinatorRole;
      case "RegionalDirector":                    if (isMember)       return RoleNames.RegionalDirectorOnProjectRole;
                                                                      return RoleNames.RegionalDirectorGlobalRole;
      case "StaffMember":                                             return RoleNames.StaffMemberRole;
      case "Translator":                                              return RoleNames.TranslatorRole;
    }

    return null;
  }
}

