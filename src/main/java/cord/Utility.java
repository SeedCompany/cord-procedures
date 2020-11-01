package cord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.*;

import cord.common.AllProperties;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;
import cord.roles.BaseRole;

public class Utility {

  public static Long getNode(GraphDatabaseService db, String id, String label) throws RuntimeException {
    try ( Transaction tx = db.beginTx() )
    {
        Node node = tx.findNode(Label.label(label), "id", id);
        Long nodeNeoId = node.getId();
        tx.commit();
        return nodeNeoId;
    } catch(Exception e){
      throw new RuntimeException("error in finding base node: " + label + " id:" + id);
    }
  }

  public static Long getProjectNode(
    GraphDatabaseService db, 
    Long baseNodeNeoId, 
    BaseNodeLabels label) {

    Long projectNeoId = null;
    try ( Transaction tx = db.beginTx() )
    {
      Node baseNode = tx.getNodeById(baseNodeNeoId);
      Node nextNode = baseNode;
      BaseNodeLabels nextLabel = label;

      while (projectNeoId == null){
        switch (nextLabel){
          case Project:
            projectNeoId = nextNode.getId();
            break;
          case File:
          case FileNode:
          case FileVersion:
          case Directory:
            if(nextNode.hasRelationship(
              RelationshipType.withName(AllProperties.rootDirectory.name()))
            ){
              Relationship fromFileNodeRel = nextNode.getSingleRelationship(
                RelationshipType.withName(AllProperties.rootDirectory.name()), Direction.INCOMING); 
              nextNode = fromFileNodeRel.getStartNode();          
              nextLabel = BaseNodeLabels.Project;
              projectNeoId = nextNode.getId();
            } else if (
              nextNode.hasRelationship(
                RelationshipType.withName(AllProperties.pnpNode.name()))
            ){
              Relationship fromFileNodeRel = nextNode.getSingleRelationship(
                RelationshipType.withName(AllProperties.pnpNode.name()), Direction.INCOMING); 
              nextNode = fromFileNodeRel.getStartNode();          
              nextLabel = BaseNodeLabels.Engagement;
            } else if (
              nextNode.hasRelationship(
                RelationshipType.withName(AllProperties.growthPlanNode.name()))
            ){
              Relationship fromFileNodeRel = nextNode.getSingleRelationship(
                RelationshipType.withName(AllProperties.growthPlanNode.name()), Direction.INCOMING); 
              nextNode = fromFileNodeRel.getStartNode();          
              nextLabel = BaseNodeLabels.Engagement;
            } else if (
              nextNode.hasRelationship(
                RelationshipType.withName(AllProperties.universalTemplateFileNode.name()))
            ){
              Relationship fromFileNodeRel = nextNode.getSingleRelationship(
                RelationshipType.withName(AllProperties.universalTemplateFileNode.name()), Direction.INCOMING); 
              nextNode = fromFileNodeRel.getStartNode();          
              nextLabel = BaseNodeLabels.Budget;
            } else if (
              nextNode.hasRelationship(
                RelationshipType.withName(AllProperties.mouNode.name()))
            ){
              Relationship fromFileNodeRel = nextNode.getSingleRelationship(
                RelationshipType.withName(AllProperties.mouNode.name()), Direction.INCOMING); 
              nextNode = fromFileNodeRel.getStartNode();          
              nextLabel = BaseNodeLabels.Partnership;
            } else if (
              nextNode.hasRelationship(
                RelationshipType.withName(AllProperties.agreementNode.name()))
            ){
              Relationship fromFileNodeRel = nextNode.getSingleRelationship(
                RelationshipType.withName(AllProperties.agreementNode.name()), Direction.INCOMING); 
              nextNode = fromFileNodeRel.getStartNode();          
              nextLabel = BaseNodeLabels.Partnership;
            } else {
              Relationship fromFileNodeRel = nextNode.getSingleRelationship(
                RelationshipType.withName(AllProperties.parent.name()), Direction.OUTGOING); 
                if (fromFileNodeRel == null){
                  System.out.println("file node rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                  return null;
                }
              nextNode = fromFileNodeRel.getEndNode();            
              nextLabel = BaseNodeLabels.FileNode;
            }
            break;
          case Budget:
            Relationship fromBudgetRel = nextNode.getSingleRelationship(
              RelationshipType.withName(AllProperties.budget.name()), Direction.INCOMING); 
              if (fromBudgetRel == null){
                System.out.println("budget rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                return null;
              }
            nextNode = fromBudgetRel.getStartNode();
            nextLabel = BaseNodeLabels.Project;
            projectNeoId = nextNode.getId();
            break;
          case BudgetRecord:
            Relationship fromBudgetRecordRel = nextNode.getSingleRelationship(
              RelationshipType.withName(AllProperties.record.name()), Direction.INCOMING); 
              if (fromBudgetRecordRel == null){
                System.out.println("budget record rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                return null;
              }
            nextNode = fromBudgetRecordRel.getStartNode();
            nextLabel = BaseNodeLabels.Budget;
            break;
          case Ceremony:
            Relationship fromCeremonyRel = nextNode.getSingleRelationship(
              RelationshipType.withName(AllProperties.ceremony.name()), Direction.INCOMING); 
              if (fromCeremonyRel == null){
                System.out.println("ceremony rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                return null;
              }
            nextNode = fromCeremonyRel.getStartNode();
            nextLabel = BaseNodeLabels.Engagement;
            break;
          case Engagement:
          case InternshipEngagement:
          case LanguageEngagement:
            Relationship fromEngagementRel = nextNode.getSingleRelationship(
              RelationshipType.withName(AllProperties.engagement.name()), Direction.INCOMING); 
              if (fromEngagementRel == null){
                System.out.println("engagement rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                return null;
              }
            nextNode = fromEngagementRel.getStartNode();
            nextLabel = BaseNodeLabels.Project;
            projectNeoId = nextNode.getId();
            break;
          case Partner:
            Relationship fromPartnerRel = nextNode.getSingleRelationship(
              RelationshipType.withName(AllProperties.partner.name()), Direction.INCOMING); 
              if (fromPartnerRel == null){
                System.out.println("partner rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                return null;
              }
            nextNode = fromPartnerRel.getStartNode();
            nextLabel = BaseNodeLabels.Partnership;
            break;
          case Partnership:
            Relationship fromPartnershipRel = nextNode.getSingleRelationship(
              RelationshipType.withName(AllProperties.partnership.name()), Direction.INCOMING); 
              if (fromPartnershipRel == null){
                System.out.println("partnership rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                return null;
              }
            nextNode = fromPartnershipRel.getStartNode();
            nextLabel = BaseNodeLabels.Project;
            projectNeoId = nextNode.getId();
            break;
          case Product:
            Relationship fromProductRel = nextNode.getSingleRelationship(
              RelationshipType.withName(AllProperties.product.name()), Direction.INCOMING); 
              if (fromProductRel == null){
                System.out.println("product rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                return null;
              }
            nextNode = fromProductRel.getStartNode();
            nextLabel = BaseNodeLabels.Engagement;
            break;
          case ProjectMember:
            Relationship fromProjectMemberRel = nextNode.getSingleRelationship(
              RelationshipType.withName(AllProperties.member.name()), Direction.INCOMING); 
              if (fromProjectMemberRel == null){
                System.out.println("ceremony rel is null for label " + nextLabel + " nextNodeNeoId: " + nextNode.getId());
                return null;
              }
            nextNode = fromProjectMemberRel.getStartNode();
            nextLabel = BaseNodeLabels.Project;
            projectNeoId = nextNode.getId();
            break;
          default: 
            System.out.println("base node label not found in project nodes switch:" + nextLabel);
            return null;
        }
      }

      tx.commit();

    } catch(Exception e){
      e.printStackTrace();
      System.out.println(e.getMessage());
      throw new RuntimeException("error in finding project node");
    }

    return projectNeoId;
  }

  public static ArrayList<Long> getProjectMembers(GraphDatabaseService db, Long projectNodeNeoId) throws RuntimeException {
    ArrayList<Long> members = new ArrayList<Long>();
    try ( Transaction tx = db.beginTx() )
    {
      Node projectNode = tx.getNodeById(projectNodeNeoId);
      Iterable<Relationship> iter = projectNode.getRelationships(Direction.OUTGOING, 
        RelationshipType.withName(AllProperties.member.name()));
      iter.forEach(rel -> members.add(rel.getEndNode().getId()));
      tx.commit();
    } catch(Exception e){
      e.printStackTrace();
      throw new RuntimeException("error in finding project member: " + projectNodeNeoId);
    }
    return members;
  }

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
      case "Partner":                 return BaseNodeLabels.Partner;
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
      case "Administrator":                                           return RoleNames.AdministratorRole;
      case "Consultant":                                              return RoleNames.ConsultantRole;
      case "ConsultantManager":                                       return RoleNames.ConsultantManagerRole;
      case "Controller":                                              return RoleNames.ControllerRole;
      case "FieldOperationsDirector":                                 return RoleNames.FieldOperationsDirectorRole;
      case "FinancialAnalyst":                    if (isMember)       return RoleNames.FinancialAnalystGlobalRole;
                                                                      return RoleNames.FinancialAnalystOnProjectRole;
      case "Fundraising":                                             return RoleNames.FundraisingRole;
      case "Intern":                                                  return RoleNames.InternRole;
      case "Leadership":                                              return RoleNames.LeadershipRole;
      case "Liason":                                                  return RoleNames.LiasonRole;
      case "Marketing":                                               return RoleNames.MarketingRole;
      case "ProjectManager":                      if (isMember)       return RoleNames.ProjectManagerGlobalRole;
                                                                      return RoleNames.ProjectManagerOnProjectRole;
      case "RegionalCommunicationCoordinator":                        return RoleNames.RegionalCommunicationCoordinatorRole;
      case "RegionalDirector":                    if (isMember)       return RoleNames.RegionalDirectorGlobalRole;
                                                  if (isMember)       return RoleNames.RegionalDirectorOnProjectRole;
      case "StaffMember":                                             return RoleNames.StaffMemberRole;
      case "Translator":                                              return RoleNames.TranslatorRole;
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
      case File:                  return true;
      case FileNode:              return true;
      case FileVersion:           return true;
      case InternshipEngagement:  return true;
      case LanguageEngagement:    return true;
      case Partnership:           return true;
      case Product:               return true;
      case ProjectMember:         return true;
      default:                    return false;
    }
  }

  public static Long getSecurityGroupNode(GraphDatabaseService db, BaseRole role, String baseNodeId, BaseNodeLabels label) throws RuntimeException {

    Map<String, Object> params = new HashMap<>();
    // params.put("role", role);                      // todo
    params.put("baseNodeId", baseNodeId);
    params.put("role", role.roleName.name());

    Long sg = null;

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
        sg = (Long) row.get("id");
      }

      return sg;

    } catch(Exception e){
      e.printStackTrace();
      throw new RuntimeException("error in finding security group for base node: " + baseNodeId + " with role " + role + "Error " + e.getMessage());
    }
  }

  public static ArrayList<String> getBaseNodePropertiesByLabel(BaseNodeLabels label){
    ArrayList<String> list = new ArrayList<>();
    switch (label){
      case Budget:               
        Arrays.asList(Budget.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case BudgetRecord:          
        Arrays.asList(BudgetRecord.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Ceremony:              
        Arrays.asList(Ceremony.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Directory:             
        Arrays.asList(Directory.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Education:             
        Arrays.asList(Education.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case EthnologueLanguage:    
        Arrays.asList(EthnologueLanguage.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case FieldRegion:           
        Arrays.asList(FieldRegion.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case FieldZone:             
        Arrays.asList(FieldZone.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case File:                  
        Arrays.asList(File.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case FileVersion:           
        Arrays.asList(FileVersion.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Film:                  
        Arrays.asList(Film.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case FundingAccount:        
        Arrays.asList(FundingAccount.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case InternshipEngagement:  
        Arrays.asList(InternshipEngagement.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Language:              
        Arrays.asList(Language.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case LanguageEngagement:    
        Arrays.asList(LanguageEngagement.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case LiteracyMaterial:      
        Arrays.asList(LiteracyMaterial.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Location:              
        Arrays.asList(Location.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Organization:          
        Arrays.asList(Organization.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Partner:               
        Arrays.asList(Partner.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Partnership:           
        Arrays.asList(Partnership.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Project:               
        Arrays.asList(Project.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case ProjectMember:         
        Arrays.asList(ProjectMember.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Product:               
        Arrays.asList(Product.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Song:                  
        Arrays.asList(Song.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Story:                 
        Arrays.asList(Story.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case Unavailability:        
        Arrays.asList(Unavailability.values())
          .forEach(item -> list.add(item.name()));
          return list;
      case User:                  
        Arrays.asList(User.values())
          .forEach(item -> list.add(item.name()));
          return list;

      default: return null;
    }
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
      case LiasonRole:
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
}
