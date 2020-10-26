package cord.roles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;

public class RegionalDirectorOnProject extends BaseRole {
  public RegionalDirectorOnProject(){
    super(RoleNames.RegionalDirectorOnProjectRole, RegionalDirectorOnProject.permission);
  }

  public static IPermission permission = (BaseNodeLabels label, String property) -> {
    switch(label){
      case Budget:                return RegionalDirectorOnProject.Budget(                 Budget.valueOf(property));
      case BudgetRecord:          return RegionalDirectorOnProject.BudgetRecord(           BudgetRecord.valueOf(property));
      case Ceremony:              return RegionalDirectorOnProject.Ceremony(               Ceremony.valueOf(property));
      case Directory:             return RegionalDirectorOnProject.Directory(              Directory.valueOf(property));
      case Education:             return RegionalDirectorOnProject.Education(              Education.valueOf(property));
      case EthnologueLanguage:    return RegionalDirectorOnProject.EthnologueLanguage(     EthnologueLanguage.valueOf(property));
      case FieldRegion:           return RegionalDirectorOnProject.FieldRegion(            FieldRegion.valueOf(property));
      case FieldZone:             return RegionalDirectorOnProject.FieldZone(              FieldZone.valueOf(property));
      case File:                  return RegionalDirectorOnProject.File(                   File.valueOf(property));
      case FileVersion:           return RegionalDirectorOnProject.FileVersion(            FileVersion.valueOf(property));
      case Film:                  return RegionalDirectorOnProject.Film(                   Film.valueOf(property));
      case FundingAccount:        return RegionalDirectorOnProject.FundingAccount(         FundingAccount.valueOf(property));
      case InternshipEngagement:  return RegionalDirectorOnProject.InternshipEngagement(   InternshipEngagement.valueOf(property));
      case Language:              return RegionalDirectorOnProject.Language(               Language.valueOf(property));
      case LanguageEngagement:    return RegionalDirectorOnProject.LanguageEngagement(     LanguageEngagement.valueOf(property));
      case LiteracyMaterial:      return RegionalDirectorOnProject.LiteracyMaterial(       LiteracyMaterial.valueOf(property));
      case Location:              return RegionalDirectorOnProject.Location(               Location.valueOf(property));
      case Organization:          return RegionalDirectorOnProject.Organization(           Organization.valueOf(property));
      case Partner:               return RegionalDirectorOnProject.Partner(                Partner.valueOf(property));
      case Partnership:           return RegionalDirectorOnProject.Partnership(            Partnership.valueOf(property));
      case Project:               return RegionalDirectorOnProject.Project(                Project.valueOf(property));
      case ProjectMember:         return RegionalDirectorOnProject.ProjectMember(          ProjectMember.valueOf(property));
      case Product:               return RegionalDirectorOnProject.Product(                Product.valueOf(property));
      case Song:                  return RegionalDirectorOnProject.Song(                   Song.valueOf(property));
      case Story:                 return RegionalDirectorOnProject.Story(                  Story.valueOf(property));
      case Unavailability:        return RegionalDirectorOnProject.Unavailability(         Unavailability.valueOf(property));
      case User:                  return RegionalDirectorOnProject.User(                   User.valueOf(property));

      default: return Permission.None;
    }
  };

  private static Permission Budget(Budget property){
    switch(property){
      case universalTemplateFile:      return Permission.ReadWrite;
      case records:                    return Permission.ReadWrite;
      case status:                     return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission BudgetRecord(BudgetRecord property){
    switch(property){
      case amount:                     return Permission.ReadWrite;
      case fiscalYear:                 return Permission.ReadWrite;
      case organization:               return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }  
  
  private static Permission Ceremony(Ceremony property){
    switch(property){
      case actualDate:                 return Permission.ReadWrite;
      case estimatedDate:              return Permission.ReadWrite;
      case planned:                    return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Directory(Directory property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case createdBy:                  return Permission.ReadWrite;
      case parent:                     return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Education(Education property){
    switch(property){
      case degree:                     return Permission.ReadWrite;
      case institution:                return Permission.ReadWrite;
      case major:                      return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission EthnologueLanguage(EthnologueLanguage property){
    switch(property){
      case code:                       return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
      case population:                 return Permission.ReadWrite;
      case provisionalCode:            return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission FieldRegion(FieldRegion property){
    switch(property){
      case director:                   return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
      case fieldZone:                  return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }  
  
  private static Permission FieldZone(FieldZone property){
    switch(property){
      case director:                   return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission File(File property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case createdBy:                  return Permission.ReadWrite;
      case parent:                     return Permission.ReadWrite;
      case mimeType:                   return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission FileVersion(FileVersion property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case createdBy:                  return Permission.ReadWrite;
      case parent:                     return Permission.ReadWrite;
      case mimeType:                   return Permission.ReadWrite;
      case size:                       return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Film(Film property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission FundingAccount(FundingAccount property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case accountNumber:              return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }  
  
  private static Permission InternshipEngagement(InternshipEngagement property){
    switch(property){
      case ceremony:                   return Permission.ReadWrite;
      case communicationsCompleteDate: return Permission.ReadWrite;
      case completeDate:               return Permission.ReadWrite;
      case countryOfOrigin:            return Permission.ReadWrite;
      case disbursementCompleteDate:   return Permission.ReadWrite;
      case endDate:                    return Permission.ReadWrite;
      case endDateOverride:            return Permission.ReadWrite;
      case growthPlan:                 return Permission.ReadWrite;
      case initialEndDate:             return Permission.ReadWrite;
      case intern:                     return Permission.ReadWrite;
      case lastReactivatedAt:          return Permission.ReadWrite;
      case lastSuspendedAt:            return Permission.ReadWrite;
      case mentor:                     return Permission.ReadWrite;
      case methodologies:              return Permission.ReadWrite;
      case position:                   return Permission.ReadWrite;
      case startDate:                  return Permission.ReadWrite;
      case startDateOverride:          return Permission.ReadWrite;
      case statusModifiedAt:           return Permission.ReadWrite;
      case modifiedAt:                 return Permission.ReadWrite;
      case status:                     return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Language(Language property){
    switch(property){
      case displayName:                return Permission.ReadWrite;
      case displayNamePronunciation:   return Permission.ReadWrite;
      case isDialect:                  return Permission.ReadWrite;
      case isSignLanguage:             return Permission.ReadWrite;
      case leastOfThese:               return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
      case leastOfTheseReason:         return Permission.ReadWrite;
      case populationOverride:         return Permission.ReadWrite;
      case registryOfDialectsCode:     return Permission.ReadWrite;
      case signLanguageCode:           return Permission.ReadWrite;
      case sponsorEstimatedEndDate:    return Permission.ReadWrite;
      case ethnologue:                 return Permission.ReadWrite;
      case sensitivity:                return Permission.ReadWrite;
      case hasExternalFirstScripture:  return Permission.ReadWrite;
      case locations:                  return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission LanguageEngagement(LanguageEngagement property){
    switch(property){
      case ceremony:                   return Permission.ReadWrite;
      case communicationsCompleteDate: return Permission.ReadWrite;
      case completeDate:               return Permission.ReadWrite;
      case disbursementCompleteDate:   return Permission.ReadWrite;
      case endDate:                    return Permission.ReadWrite;
      case endDateOverride:            return Permission.ReadWrite;
      case firstScripture:             return Permission.ReadWrite;
      case initialEndDate:             return Permission.ReadWrite;
      case language:                   return Permission.ReadWrite;
      case lastReactivatedAt:          return Permission.ReadWrite;
      case lastSuspendedAt:            return Permission.ReadWrite;
      case lukePartnership:            return Permission.ReadWrite;
      case paraTextRegistryId:         return Permission.ReadWrite;
      case pnp:                        return Permission.ReadWrite;
      case sentPrintingDate:           return Permission.ReadWrite;
      case startDate:                  return Permission.ReadWrite;
      case startDateOverride:          return Permission.ReadWrite;
      case statusModifiedAt:           return Permission.ReadWrite;
      case modifiedAt:                 return Permission.ReadWrite;
      case product:                    return Permission.ReadWrite;
      case status:                     return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission LiteracyMaterial(LiteracyMaterial property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Location(Location property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case type:                       return Permission.ReadWrite;
      case sensitivity:                return Permission.ReadWrite;
      case isoAlpha3:                  return Permission.ReadWrite;
      case fundingAccount:             return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }
  
  private static Permission Organization(Organization property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case address:                    return Permission.ReadWrite;
      case locations:                  return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Partner(Partner property){
    switch(property){
      case organization:               return Permission.ReadWrite;
      case pointOfContact:             return Permission.ReadWrite;
      case types:                      return Permission.ReadWrite;
      case financialReportingTypes:    return Permission.ReadWrite;
      case pmcEntityCode:              return Permission.ReadWrite;
      case globalInnovationsClient:    return Permission.ReadWrite;
      case active:                     return Permission.ReadWrite;
      case address:                    return Permission.ReadWrite;
      case modifiedAt:                 return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Partnership(Partnership property){
    switch(property){
      case agreement:                  return Permission.ReadWrite;
      case agreementStatus:            return Permission.ReadWrite;
      case financialReportingType:     return Permission.ReadWrite;
      case mou:                        return Permission.ReadWrite;
      case mouEnd:                     return Permission.ReadWrite;
      case mouEndOverride:             return Permission.ReadWrite;
      case mouStart:                   return Permission.ReadWrite;
      case mouStartOverride:           return Permission.ReadWrite;
      case mouStatus:                  return Permission.ReadWrite;
      case types:                      return Permission.ReadWrite;
      case organization:               return Permission.ReadWrite;
      case partner:                    return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Product(Product property){
    switch(property){
      case mediums:                    return Permission.ReadWrite;
      case methodology:                return Permission.ReadWrite;
      case purposes:                   return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      case produces:                   return Permission.ReadWrite;
      case scriptureReferencesOverride:return Permission.ReadWrite;
      case isOverriding:               return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Project(Project property){
    switch(property){
      case estimatedSubmission:        return Permission.ReadWrite;
      case step:                       return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
      case status:                     return Permission.ReadWrite;
      case departmentId:               return Permission.ReadWrite;
      case mouStart:                   return Permission.ReadWrite;
      case mouEnd:                     return Permission.ReadWrite;
      case rootDirectory:              return Permission.ReadWrite;
      case member:                     return Permission.ReadWrite;
      case otherLocations:             return Permission.ReadWrite;
      case primaryLocation:            return Permission.ReadWrite;
      case marketingLocation:          return Permission.ReadWrite;
      case partnership:                return Permission.ReadWrite;
      case budget:                     return Permission.ReadWrite;
      case modifiedAt:                 return Permission.ReadWrite;
      case fieldRegion:                return Permission.ReadWrite;
      case engagement:                 return Permission.ReadWrite;
      case sensitivity:                return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission ProjectMember(ProjectMember property){
    switch(property){
      case roles:                      return Permission.ReadWrite;
      case user:                       return Permission.ReadWrite;
      case modifiedAt:                 return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Song(Song property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Story(Story property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission Unavailability(Unavailability property){
    switch(property){
      case description:                return Permission.ReadWrite;
      case end:                        return Permission.ReadWrite;
      case start:                      return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

  private static Permission User(User property){
    switch(property){
      case about:                      return Permission.ReadWrite;
      case displayFirstName:           return Permission.ReadWrite;
      case displayLastName:            return Permission.ReadWrite;
      case email:                      return Permission.ReadWrite;
      case phone:                      return Permission.ReadWrite;
      case realFirstName:              return Permission.ReadWrite;
      case realLastName:               return Permission.ReadWrite;
      case roles:                      return Permission.ReadWrite;
      case status:                     return Permission.ReadWrite;
      case timezone:                   return Permission.ReadWrite;
      case title:                      return Permission.ReadWrite;
      case education:                  return Permission.ReadWrite;
      case organization:               return Permission.ReadWrite;
      case unavailability:             return Permission.ReadWrite;
      case locations:                  return Permission.ReadWrite;
      default:                         return Permission.None;
    }
  }

}
