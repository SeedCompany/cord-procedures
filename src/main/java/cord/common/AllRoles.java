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
  public Liason Liason;
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
    this.Liason = new Liason();
    this.Marketing = new Marketing();
    this.ProjectManagerGlobal = new ProjectManagerGlobal();
    this.ProjectManagerOnProject = new ProjectManagerOnProject();
    this.RegionalCommunicationCoordinator = new RegionalCommunicationCoordinator();
    this.RegionalDirectorGlobal = new RegionalDirectorGlobal();
    this.RegionalDirectorOnProject = new RegionalDirectorOnProject();
    this.StaffMember = new StaffMember();
    this.Translator = new Translator();
  }

  public BaseRole getRoleByName(String name){
    switch (name){
      case "Administrator":                       return this.Administrator;
      case "Consultant":                          return this.Consultant;
      case "ConsultantManager":                   return this.ConsultantManager;
      case "Controller":                          return this.Controller;
      case "FieldOperationsDirector":             return this.FieldOperationsDirector;
      case "FinancialAnalyst":                    // must fall through to project
      case "FinancialAnalystOnProject":           return this.FinancialAnalystOnProject;
      case "FinancialAnalystGlobal":              return this.FinancialAnalystGlobal;
      case "Fundraising":                         return this.Fundraising;
      case "Intern":                              return this.Intern;
      case "Leadership":                          return this.Leadership;
      case "Liason":                              return this.Liason;
      case "Marketing":                           return this.Marketing;
      case "ProjectManager":                      // must fall through to project
      case "ProjectManagerOnProject":             return this.ProjectManagerOnProject;
      case "ProjectManagerGlobal":                return this.ProjectManagerGlobal;
      case "RegionalCommunicationCoordinator":    return this.RegionalCommunicationCoordinator;
      case "RegionalDirector":                    // must fall through to project
      case "RegionalDirectorOnProject":           return this.RegionalDirectorOnProject;
      case "RegionalDirectorGlobal":              return this.RegionalDirectorGlobal;
      case "StaffMember":                         return this.StaffMember;
      case "Translator":                          return this.Translator;

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
      this.Liason,
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
      this.Liason,
      this.ProjectManagerOnProject,
      this.RegionalCommunicationCoordinator,
      this.RegionalDirectorOnProject,
      this.Translator
    );
  }
}

