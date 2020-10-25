package cord.common;

import java.util.Arrays;
import java.util.List;

import cord.roles.*;

public class AllRoles {
  public static Administrator Administrator;
  public static Consultant Consultant;
  public static ConsultantManager ConsultantManager;
  public static Controller Controller;
  public static FieldOperationsDirector FieldOperationsDirector;
  public static FinancialAnalystGlobal FinancialAnalystGlobal;
  public static FinancialAnalystOnProject FinancialAnalystOnProject;
  public static Fundraising Fundraising;
  public static Intern Intern;
  public static Leadership Leadership;
  public static Liason Liason;
  public static Marketing Marketing;
  public static ProjectManagerGlobal ProjectManagerGlobal;
  public static ProjectManagerOnProject ProjectManagerOnProject;
  public static RegionalCommunicationCoordinator RegionalCommunicationCoordinator;
  public static RegionalDirectorGlobal RegionalDirectorGlobal;
  public static RegionalDirectorOnProject RegionalDirectorOnProject;
  public static StaffMember StaffMember;
  public static Translator Translator;

  public static Object getRoleByName(String name){
    switch (name){
      case "Administrator":                       return AllRoles.Administrator;
      case "Consultant":                          return AllRoles.Consultant;
      case "ConsultantManager":                   return AllRoles.ConsultantManager;
      case "Controller":                          return AllRoles.Controller;
      case "FieldOperationsDirector":             return AllRoles.FieldOperationsDirector;
      case "FinancialAnalystGlobal":              return AllRoles.FinancialAnalystGlobal;
      case "FinancialAnalystOnProject":           return AllRoles.FinancialAnalystOnProject;
      case "Fundraising":                         return AllRoles.Fundraising;
      case "Intern":                              return AllRoles.Intern;
      case "Leadership":                          return AllRoles.Leadership;
      case "Liason":                              return AllRoles.Liason;
      case "Marketing":                           return AllRoles.Marketing;
      case "ProjectManagerGlobal":                return AllRoles.ProjectManagerGlobal;
      case "ProjectManagerOnProject":             return AllRoles.ProjectManagerOnProject;
      case "RegionalCommunicationCoordinator":    return AllRoles.RegionalCommunicationCoordinator;
      case "RegionalDirectorGlobal":              return AllRoles.RegionalDirectorGlobal;
      case "RegionalDirectorOnProject":           return AllRoles.RegionalDirectorOnProject;
      case "StaffMember":                         return AllRoles.StaffMember;
      case "Translator":                          return AllRoles.Translator;

      default: return null;
    }
  }

  public static List<String> rolesList(){
    return Arrays.asList(
      "Administrator",
      "Consultant",
      "ConsultantManager",
      "Controller",
      "FieldOperationsDirector",
      "FinancialAnalystGlobal",
      "FinancialAnalystOnProject",
      "Fundraising",
      "Intern",
      "Leadership",
      "Liason",
      "Marketing",
      "ProjectManagerGlobal",
      "ProjectManagerOnProject",
      "RegionalCommunicationCoordinator",
      "RegionalDirectorGlobal",
      "RegionalDirectorOnProject",
      "StaffMember",
      "Translator"
    );
  }
}

