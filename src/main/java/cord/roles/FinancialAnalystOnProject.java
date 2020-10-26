package cord.roles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;

public class FinancialAnalystOnProject extends BaseRole {
  public FinancialAnalystOnProject(){
    super(RoleNames.FinancialAnalystOnProjectRole, FinancialAnalystOnProject.permission);
  }

  public static IPermission permission = (BaseNodeLabels label, String property) -> {
    switch(label){
      case Budget:                return FinancialAnalystOnProject.Budget(                 Budget.valueOf(property));
      case BudgetRecord:          return FinancialAnalystOnProject.BudgetRecord(           BudgetRecord.valueOf(property));
      case Ceremony:              return FinancialAnalystOnProject.Ceremony(               Ceremony.valueOf(property));
      case Directory:             return FinancialAnalystOnProject.Directory(              Directory.valueOf(property));
      case Education:             return FinancialAnalystOnProject.Education(              Education.valueOf(property));
      case EthnologueLanguage:    return FinancialAnalystOnProject.EthnologueLanguage(     EthnologueLanguage.valueOf(property));
      case FieldRegion:           return FinancialAnalystOnProject.FieldRegion(            FieldRegion.valueOf(property));
      case FieldZone:             return FinancialAnalystOnProject.FieldZone(              FieldZone.valueOf(property));
      case File:                  return FinancialAnalystOnProject.File(                   File.valueOf(property));
      case FileVersion:           return FinancialAnalystOnProject.FileVersion(            FileVersion.valueOf(property));
      case Film:                  return FinancialAnalystOnProject.Film(                   Film.valueOf(property));
      case FundingAccount:        return FinancialAnalystOnProject.FundingAccount(         FundingAccount.valueOf(property));
      case InternshipEngagement:  return FinancialAnalystOnProject.InternshipEngagement(   InternshipEngagement.valueOf(property));
      case Language:              return FinancialAnalystOnProject.Language(               Language.valueOf(property));
      case LanguageEngagement:    return FinancialAnalystOnProject.LanguageEngagement(     LanguageEngagement.valueOf(property));
      case LiteracyMaterial:      return FinancialAnalystOnProject.LiteracyMaterial(       LiteracyMaterial.valueOf(property));
      case Location:              return FinancialAnalystOnProject.Location(               Location.valueOf(property));
      case Organization:          return FinancialAnalystOnProject.Organization(           Organization.valueOf(property));
      case Partner:               return FinancialAnalystOnProject.Partner(                Partner.valueOf(property));
      case Partnership:           return FinancialAnalystOnProject.Partnership(            Partnership.valueOf(property));
      case Project:               return FinancialAnalystOnProject.Project(                Project.valueOf(property));
      case ProjectMember:         return FinancialAnalystOnProject.ProjectMember(          ProjectMember.valueOf(property));
      case Product:               return FinancialAnalystOnProject.Product(                Product.valueOf(property));
      case Song:                  return FinancialAnalystOnProject.Song(                   Song.valueOf(property));
      case Story:                 return FinancialAnalystOnProject.Story(                  Story.valueOf(property));
      case Unavailability:        return FinancialAnalystOnProject.Unavailability(         Unavailability.valueOf(property));
      case User:                  return FinancialAnalystOnProject.User(                   User.valueOf(property));

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
