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
      }
return Permission.None;
  }

  private static Permission BudgetRecord(BudgetRecord property){
    switch(property){
      case amount:                     return Permission.ReadWrite;
      case fiscalYear:                 return Permission.ReadWrite;
      case organization:               return Permission.ReadWrite;
      }
return Permission.None;
  }  
  
  private static Permission Ceremony(Ceremony property){
    switch(property){
      case actualDate:                 return Permission.ReadWrite;
      case estimatedDate:              return Permission.ReadWrite;
      case planned:                    return Permission.ReadWrite;
      case type:                       return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission Directory(Directory property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case createdBy:                  return Permission.ReadWrite;
      case parent:                     return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission Education(Education property){
    switch(property){
      case degree:                     return Permission.Read;
      case institution:                return Permission.Read;
      case major:                      return Permission.Read;
      }
return Permission.None;
  }

  private static Permission EthnologueLanguage(EthnologueLanguage property){
    switch(property){
      case code:                       return Permission.Read;
      case name:                       return Permission.Read;
      case population:                 return Permission.Read;
      case provisionalCode:            return Permission.Read;
      }
return Permission.None;
  }

  private static Permission FieldRegion(FieldRegion property){
    switch(property){
      case director:                   return Permission.Read;
      case name:                       return Permission.Read;
      case fieldZone:                  return Permission.Read;
      }
return Permission.None;
  }  
  
  private static Permission FieldZone(FieldZone property){
    switch(property){
      case director:                   return Permission.Read;
      case name:                       return Permission.Read;
      }
return Permission.None;
  }

  private static Permission File(File property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case createdBy:                  return Permission.ReadWrite;
      case parent:                     return Permission.ReadWrite;
      case mimeType:                   return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission FileVersion(FileVersion property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case createdBy:                  return Permission.ReadWrite;
      case parent:                     return Permission.ReadWrite;
      case mimeType:                   return Permission.ReadWrite;
      case size:                       return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission Film(Film property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission FundingAccount(FundingAccount property){
    switch(property){
      case name:                       return Permission.Read;
      case accountNumber:              return Permission.Read;
      }
return Permission.None;
  }  
  
  private static Permission InternshipEngagement(InternshipEngagement property){
    switch(property){
      case ceremony:                   return Permission.ReadWrite;
      case communicationsCompleteDate: return Permission.Read;
      case completeDate:               return Permission.ReadWrite;
      case countryOfOrigin:            return Permission.ReadWrite;
      case disbursementCompleteDate:   return Permission.Read;
      case endDate:                    return Permission.ReadWrite;
      case endDateOverride:            return Permission.Read;
      case growthPlan:                 return Permission.ReadWrite;
      case initialEndDate:             return Permission.Read;
      case intern:                     return Permission.ReadWrite;
      case lastReactivatedAt:          return Permission.Read;
      case lastSuspendedAt:            return Permission.Read;
      case mentor:                     return Permission.ReadWrite;
      case methodologies:              return Permission.ReadWrite;
      case position:                   return Permission.ReadWrite;
      case startDate:                  return Permission.Read;
      case startDateOverride:          return Permission.Read;
      case statusModifiedAt:           return Permission.Read;
      case modifiedAt:                 return Permission.Read;
      case status:                     return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission Language(Language property){
    switch(property){
      case displayName:                return Permission.Read;
      case displayNamePronunciation:   return Permission.Read;
      case isDialect:                  return Permission.Read;
      case isSignLanguage:             return Permission.Read;
      case leastOfThese:               return Permission.Read;
      case name:                       return Permission.Read;
      case leastOfTheseReason:         return Permission.Read;
      case populationOverride:         return Permission.Read;
      case registryOfDialectsCode:     return Permission.Read;
      case signLanguageCode:           return Permission.Read;
      case sponsorEstimatedEndDate:    return Permission.Read;
      case ethnologue:                 return Permission.Read;
      case sensitivity:                return Permission.Read;
      case hasExternalFirstScripture:  return Permission.Read;
      case locations:                  return Permission.Read;
      case tags:                       return Permission.Read;
      }
return Permission.None;
  }

  private static Permission LanguageEngagement(LanguageEngagement property){
    switch(property){
      case ceremony:                   return Permission.ReadWrite;
      case communicationsCompleteDate: return Permission.Read;
      case completeDate:               return Permission.Read;
      case disbursementCompleteDate:   return Permission.Read;
      case endDate:                    return Permission.Read;
      case endDateOverride:            return Permission.Read;
      case firstScripture:             return Permission.ReadWrite;
      case initialEndDate:             return Permission.Read;
      case language:                   return Permission.ReadWrite;
      case lastReactivatedAt:          return Permission.Read;
      case lastSuspendedAt:            return Permission.Read;
      case lukePartnership:            return Permission.ReadWrite;
      case paraTextRegistryId:         return Permission.ReadWrite;
      case pnp:                        return Permission.ReadWrite;
      case sentPrintingDate:           return Permission.ReadWrite;
      case startDate:                  return Permission.Read;
      case startDateOverride:          return Permission.Read;
      case statusModifiedAt:           return Permission.Read;
      case modifiedAt:                 return Permission.Read;
      case product:                    return Permission.ReadWrite;
      case status:                     return Permission.Read;
      }
return Permission.None;
  }

  private static Permission LiteracyMaterial(LiteracyMaterial property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission Location(Location property){
    switch(property){
      case name:                       return Permission.Read;
      case type:                       return Permission.Read;
      case sensitivity:                return Permission.Read;
      case isoAlpha3:                  return Permission.Read;
      case fundingAccount:             return Permission.Read;
      }
return Permission.None;
  }
  
  private static Permission Organization(Organization property){
    switch(property){
      case name:                       return Permission.Read;
      case address:                    return Permission.Read;
      case locations:                  return Permission.Read;
      }
return Permission.None;
  }

  private static Permission Partner(Partner property){
    switch(property){
      case organization:               return Permission.Read;
      case pointOfContact:             return Permission.Read;
      case types:                      return Permission.Read;
      case financialReportingTypes:    return Permission.Read;
      case pmcEntityCode:              return Permission.Read;
      case globalInnovationsClient:    return Permission.Read;
      case active:                     return Permission.Read;
      case address:                    return Permission.Read;
      case modifiedAt:                 return Permission.Read;
      }
return Permission.None;
  }

  private static Permission Partnership(Partnership property){
    switch(property){
      case agreement:                  return Permission.ReadWrite;
      case agreementStatus:            return Permission.ReadWrite;
      case financialReportingType:     return Permission.Read;
      case mou:                        return Permission.Read;
      case mouEnd:                     return Permission.Read;
      case mouEndOverride:             return Permission.Read;
      case mouStart:                   return Permission.Read;
      case mouStartOverride:           return Permission.Read;
      case mouStatus:                  return Permission.Read;
      case types:                      return Permission.ReadWrite;
      case organization:               return Permission.ReadWrite;
      case partner:                    return Permission.ReadWrite;
      }
return Permission.None;
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
      }
return Permission.None;
  }

  private static Permission Project(Project property){
    switch(property){
      case estimatedSubmission:        return Permission.ReadWrite;
      case step:                       return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
      case status:                     return Permission.Read;
      case departmentId:               return Permission.Read;
      case mouStart:                   return Permission.ReadWrite;
      case mouEnd:                     return Permission.ReadWrite;
      case rootDirectory:              return Permission.ReadWrite;
      case member:                     return Permission.ReadWrite;
      case otherLocations:             return Permission.ReadWrite;
      case primaryLocation:            return Permission.ReadWrite;
      case marketingLocation:          return Permission.ReadWrite;
      case partnership:                return Permission.ReadWrite;
      case budget:                     return Permission.ReadWrite;
      case modifiedAt:                 return Permission.Read;
      case fieldRegion:                return Permission.ReadWrite;
      case engagement:                 return Permission.ReadWrite;
      case sensitivity:                return Permission.ReadWrite;
      case stepChangedAt:              return Permission.ReadWrite; 
      case owningOrganization:         return Permission.ReadWrite; 
      case initialMouEnd:              return Permission.ReadWrite; 
      case tags:                       return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission ProjectMember(ProjectMember property){
    switch(property){
      case roles:                      return Permission.ReadWrite;
      case user:                       return Permission.ReadWrite;
      case modifiedAt:                 return Permission.Read;
      }
return Permission.None;
  }

  private static Permission Song(Song property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission Story(Story property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case scriptureReferences:        return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission Unavailability(Unavailability property){
    switch(property){
      case description:                return Permission.Read;
      case end:                        return Permission.Read;
      case start:                      return Permission.Read;
      }
return Permission.None;
  }

  private static Permission User(User property){
    switch(property){
      case about:                      return Permission.Read;
      case displayFirstName:           return Permission.Read;
      case displayLastName:            return Permission.Read;
      case email:                      return Permission.Read;
      case phone:                      return Permission.Read;
      case realFirstName:              return Permission.Read;
      case realLastName:               return Permission.Read;
      case roles:                      return Permission.Read;
      case status:                     return Permission.Read;
      case timezone:                   return Permission.Read;
      case title:                      return Permission.Read;
      case education:                  return Permission.Read;
      case organization:               return Permission.Read;
      case unavailability:             return Permission.Read;
      case locations:                  return Permission.Read;
      }
return Permission.None;
  }

}
