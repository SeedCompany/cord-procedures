package cord;

import org.neo4j.internal.kernel.api.exceptions.ProcedureException;
import org.neo4j.kernel.api.exceptions.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.cypher.result.QueryResult.Record;
import org.neo4j.graphdb.*;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.roles.Administrator;

public class Utility {

  public static BaseNodeLabels baseNodeClassStringToEnum(String className) throws RuntimeException {
    switch (className){
      case "BaseNode":                return BaseNodeLabels.BaseNode;
      case "Budget":                  return BaseNodeLabels.Budget;
      case "BudgetRecord":            return BaseNodeLabels.BudgetRecord;
      case "Ceremony":                return BaseNodeLabels.Ceremony;
      case "Directory":               return BaseNodeLabels.Directory;
      case "Education":               return BaseNodeLabels.Education;
      case "EthnologueLanguage":      return BaseNodeLabels.EthnologueLanguage;
      case "Engagement":              return BaseNodeLabels.Engagement;
      case "FieldRegion":             return BaseNodeLabels.FieldRegion;
      case "FieldZone":               return BaseNodeLabels.FieldZone;
      case "File":                    return BaseNodeLabels.File;
      case "FileVersion":             return BaseNodeLabels.FileVersion;
      case "Film":                    return BaseNodeLabels.Film;
      case "FundingAccount":          return BaseNodeLabels.FundingAccount;
      case "InternshipEngagement":    return BaseNodeLabels.InternshipEngagement;
      case "Language":                return BaseNodeLabels.Language;
      case "LanguageEngagement":      return BaseNodeLabels.LanguageEngagement;
      case "LiteracyMaterial":        return BaseNodeLabels.LiteracyMaterial;
      case "Location":                return BaseNodeLabels.Location;
      case "Organization":            return BaseNodeLabels.Organization;
      case "Partnership":             return BaseNodeLabels.Partnership;
      case "Product":                 return BaseNodeLabels.Product;
      case "Project":                 return BaseNodeLabels.Project;
      case "ProjectMember":           return BaseNodeLabels.ProjectMember;
      case "Song":                    return BaseNodeLabels.Song;
      case "Story":                   return BaseNodeLabels.Story;
      case "Unavailability":          return BaseNodeLabels.Unavailability;
      case "User":                    return BaseNodeLabels.User;

      default:                        
      throw new RuntimeException("BaseNodeLabels class not found: " + className);
    }
  }

  public static ArrayList<BaseNodeLabels> getBaseNodeLabels(GraphDatabaseService db, String id) throws RuntimeException {
    try ( Transaction tx = db.beginTx() )
    {
        Node BaseNodeLabels = tx.findNode(Label.label("BaseNodeLabels"), "id", id);
        Iterable<Label> labels = BaseNodeLabels.getLabels();
        ArrayList<BaseNodeLabels> labelArray = new ArrayList<BaseNodeLabels>();
        labels.forEach((label) -> {
          labelArray.add(baseNodeClassStringToEnum(label.name() ));
        });
        tx.commit();
        return labelArray;
    } catch(Exception e){
      throw new RuntimeException("error in finding base node lables: " + id);
    }
  }

  public static RoleNames getRoleFromString(String roleName, Boolean isMember){

    switch (roleName){
      case "Administrator":                                           return RoleNames.Administrator;
      case "Consultant":                                              return RoleNames.Consultant;
      case "ConsultantManager":                                       return RoleNames.ConsultantManager;
      case "Controller":                                              return RoleNames.Controller;
      case "FieldOperationsDirector":                                 return RoleNames.FieldOperationsDirector;
      case "FinancialAnalyst":                    if (isMember)       return RoleNames.FinancialAnalystGlobal;
                                                                      return RoleNames.FinancialAnalystOnProject;
      case "Fundraising":                                             return RoleNames.Fundraising;
      case "Intern":                                                  return RoleNames.Intern;
      case "Leadership":                                              return RoleNames.Leadership;
      case "Liason":                                                  return RoleNames.Liason;
      case "Marketing":                                               return RoleNames.Marketing;
      case "ProjectManager":                      if (isMember)       return RoleNames.ProjectManagerGlobal;
                                                                      return RoleNames.ProjectManagerOnProject;
      case "RegionalCommunicationCoordinator":                        return RoleNames.RegionalCommunicationCoordinator;
      case "RegionalDirector":                    if (isMember)       return RoleNames.RegionalDirectorGlobal;
                                                  if (isMember)       return RoleNames.RegionalDirectorOnProject;       
      case "StaffMember":                                             return RoleNames.StaffMember;
      case "Translator":                                              return RoleNames.Translator;
    }

    return null;
  }

  public static Boolean isProjectChildNode(BaseNodeLabels label){
    switch (label){   
      case Budget:                return true;
      case BudgetRecord:          return true;
      case Ceremony:              return true;
      case Directory:             return true;
      case Engagement:            return true;
      case EthnologueLanguage:    return true;
      case File:                  return true;
      case FileVersion:           return true;
      case Film:                  return true;
      case FundingAccount:        return true;
      case InternshipEngagement:  return true;
      case LanguageEngagement:    return true;
      case LiteracyMaterial:      return true;
      case Partner:               return true;
      case Partnership:           return true;
      case Producible:            return true;
      case Product:               return true;
      case ProjectMember:         return true;
      case Song:                  return true;
      case Story:                 return true;
      default:                    return false;
    }
  }

  public static Long getSecurityGroupNode(GraphDatabaseService db, RoleNames role, String baseNodeId, BaseNodeLabels label) throws RuntimeException {

    Map<String, Object> params = new HashMap<>();
    params.put("role", role.name());
    params.put("baseNodeId", baseNodeId);

    Long sgId = null;

    try ( 
      Transaction tx = db.beginTx();
      Result result = tx.execute(
        "MATCH (sg:SecurityGroup {role: $role})-[:baseNode]->(baseNode:"+label.name()+" {id:$baseNodeId}) " +
        "RETURN id(sg) as id",
        params
      )
    ) {
      while (result.hasNext()){
        Map<String, Object> row = result.next();
        sgId = (Long) row.get("id");
      }

      return sgId;

    } catch(Exception e){
      e.printStackTrace();
      throw new RuntimeException("error in finding security group for base node: " + baseNodeId + " with role " + role + "Error " + e.getMessage());
    }
  }


}
