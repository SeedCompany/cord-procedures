package cord.common;

import java.util.Arrays;
import java.util.List;

import cord.model.*;

public class AllBaseNodes {
  public static Budget Budget;
  public static BudgetRecord BudgetRecord;
  public static Ceremony Ceremony;
  public static Directory Directory;
  public static Education Education;
  public static EthnologueLanguage EthnologueLanguage;
  public static FieldRegion FieldRegion;
  public static FieldZone FieldZone;
  public static File File;
  public static FileVersion FileVersion;
  public static Film Film;
  public static FundingAccount FundingAccount;
  public static InternshipEngagement InternshipEngagement;
  public static Language Language;
  public static LanguageEngagement LanguageEngagement;
  public static LiteracyMaterial LiteracyMaterial;
  public static Location Location;
  public static Organization Organization;
  public static Partner Partner;
  public static Partnership Partnership;
  public static Project Project;
  public static ProjectMember ProjectMember;
  public static Product Product;
  public static Song Song;
  public static Story Story;
  public static Unavailability Unavailability;
  public static User User;

  public static Object getBaseNodeByName(String name){
    switch (name){
      case "Budget":                return AllBaseNodes.Budget;
      case "BudgetRecord":          return AllBaseNodes.BudgetRecord;
      case "Ceremony":              return AllBaseNodes.Ceremony;
      case "Directory":             return AllBaseNodes.Directory;
      case "Education":             return AllBaseNodes.Education;
      case "EthnologueLanguage":    return AllBaseNodes.EthnologueLanguage;
      case "FieldRegion":           return AllBaseNodes.FieldRegion;
      case "FieldZone":             return AllBaseNodes.FieldZone;
      case "File":                  return AllBaseNodes.File;
      case "FileVersion":           return AllBaseNodes.FileVersion;
      case "Film":                  return AllBaseNodes.Film;
      case "FundingAccount":        return AllBaseNodes.FundingAccount;
      case "InternshipEngagement":  return AllBaseNodes.InternshipEngagement;
      case "Language":              return AllBaseNodes.Language;
      case "LanguageEngagement":    return AllBaseNodes.LanguageEngagement;
      case "LiteracyMaterial":      return AllBaseNodes.LiteracyMaterial;
      case "Location":              return AllBaseNodes.Location;
      case "Organization":          return AllBaseNodes.Organization;
      case "Partner":               return AllBaseNodes.Partner;
      case "Partnership":           return AllBaseNodes.Partnership;
      case "Project":               return AllBaseNodes.Project;
      case "ProjectMember":         return AllBaseNodes.ProjectMember;
      case "Product":               return AllBaseNodes.Product;
      case "Song":                  return AllBaseNodes.Song;
      case "Story":                 return AllBaseNodes.Story;
      case "Unavailability":        return AllBaseNodes.Unavailability;
      case "User":                  return AllBaseNodes.User;

      default:                      return null;
    }
  }
  
  public static List<String> baseNodeList(){
    return Arrays.asList(
      "Budget",
      "BudgetRecord",
      "Ceremony", 
      "Directory",
      "Education",
      "EthnologueLanguage",
      "FieldRegion",
      "FieldZone",
      "File",
      "FileVersion",
      "Film",
      "FundingAccount",
      "InternshipEngagement",
      "Language",
      "LanguageEngagement",
      "LiteracyMaterial",
      "Location",
      "Organization",
      "Partner",
      "Partnership",
      "Project",
      "ProjectMember",
      "Product",
      "Song",
      "Story",
      "Unavailability",
      "User"
    );
  }
}

