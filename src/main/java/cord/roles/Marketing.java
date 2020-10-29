package cord.roles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;

public class Marketing extends BaseRole {
  public Marketing(){
    super(RoleNames.MarketingRole, Marketing.permission);
  }

  public static IPermission permission = (BaseNodeLabels label, String property) -> {
    switch(label){
      case Budget:                return Marketing.Budget(                 Budget.valueOf(property));
      case BudgetRecord:          return Marketing.BudgetRecord(           BudgetRecord.valueOf(property));
      case Ceremony:              return Marketing.Ceremony(               Ceremony.valueOf(property));
      case Directory:             return Marketing.Directory(              Directory.valueOf(property));
      case Education:             return Marketing.Education(              Education.valueOf(property));
      case EthnologueLanguage:    return Marketing.EthnologueLanguage(     EthnologueLanguage.valueOf(property));
      case FieldRegion:           return Marketing.FieldRegion(            FieldRegion.valueOf(property));
      case FieldZone:             return Marketing.FieldZone(              FieldZone.valueOf(property));
      case File:                  return Marketing.File(                   File.valueOf(property));
      case FileVersion:           return Marketing.FileVersion(            FileVersion.valueOf(property));
      case Film:                  return Marketing.Film(                   Film.valueOf(property));
      case FundingAccount:        return Marketing.FundingAccount(         FundingAccount.valueOf(property));
      case InternshipEngagement:  return Marketing.InternshipEngagement(   InternshipEngagement.valueOf(property));
      case Language:              return Marketing.Language(               Language.valueOf(property));
      case LanguageEngagement:    return Marketing.LanguageEngagement(     LanguageEngagement.valueOf(property));
      case LiteracyMaterial:      return Marketing.LiteracyMaterial(       LiteracyMaterial.valueOf(property));
      case Location:              return Marketing.Location(               Location.valueOf(property));
      case Organization:          return Marketing.Organization(           Organization.valueOf(property));
      case Partner:               return Marketing.Partner(                Partner.valueOf(property));
      case Partnership:           return Marketing.Partnership(            Partnership.valueOf(property));
      case Project:               return Marketing.Project(                Project.valueOf(property));
      case ProjectMember:         return Marketing.ProjectMember(          ProjectMember.valueOf(property));
      case Product:               return Marketing.Product(                Product.valueOf(property));
      case Song:                  return Marketing.Song(                   Song.valueOf(property));
      case Story:                 return Marketing.Story(                  Story.valueOf(property));
      case Unavailability:        return Marketing.Unavailability(         Unavailability.valueOf(property));
      case User:                  return Marketing.User(                   User.valueOf(property));
      default: return Permission.None;
    }
  };

  private static Permission Budget(Budget property){
    switch(property){
      case universalTemplateFile:      return Permission.Read;
      case records:                    return Permission.Read;
      case status:                     return Permission.Read;
      }
return Permission.None;
  }

  private static Permission BudgetRecord(BudgetRecord property){
    switch(property){
      case amount:                     return Permission.Read;
      case fiscalYear:                 return Permission.Read;
      case organization:               return Permission.Read;
      }
return Permission.None;
  }  
  
  private static Permission Ceremony(Ceremony property){
    switch(property){
      case actualDate:                 return Permission.Read;
      case estimatedDate:              return Permission.Read;
      case planned:                    return Permission.Read;
      case type:                       return Permission.Read;
      }
return Permission.None;
  }

  private static Permission Directory(Directory property){
    switch(property){
      case name:                       return Permission.Read;
      case createdBy:                  return Permission.Read;
      case parent:                     return Permission.Read;
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
      case name:                       return Permission.Read;
      case scriptureReferences:        return Permission.Read;
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
      case ceremony:                   return Permission.Read;
      case communicationsCompleteDate: return Permission.Read;
      case completeDate:               return Permission.Read;
      case countryOfOrigin:            return Permission.Read;
      case disbursementCompleteDate:   return Permission.Read;
      case endDate:                    return Permission.Read;
      case endDateOverride:            return Permission.Read;
      case growthPlan:                 return Permission.Read;
      case initialEndDate:             return Permission.Read;
      case intern:                     return Permission.Read;
      case lastReactivatedAt:          return Permission.Read;
      case lastSuspendedAt:            return Permission.Read;
      case mentor:                     return Permission.Read;
      case methodologies:              return Permission.Read;
      case position:                   return Permission.Read;
      case startDate:                  return Permission.Read;
      case startDateOverride:          return Permission.Read;
      case statusModifiedAt:           return Permission.Read;
      case modifiedAt:                 return Permission.Read;
      case status:                     return Permission.Read;
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
case tags:                             return Permission.Read;
      }
return Permission.None;
  }

  private static Permission LanguageEngagement(LanguageEngagement property){
    switch(property){
      case ceremony:                   return Permission.Read;
      case communicationsCompleteDate: return Permission.Read;
      case completeDate:               return Permission.Read;
      case disbursementCompleteDate:   return Permission.Read;
      case endDate:                    return Permission.Read;
      case endDateOverride:            return Permission.Read;
      case firstScripture:             return Permission.Read;
      case initialEndDate:             return Permission.Read;
      case language:                   return Permission.Read;
      case lastReactivatedAt:          return Permission.Read;
      case lastSuspendedAt:            return Permission.Read;
      case lukePartnership:            return Permission.Read;
      case paraTextRegistryId:         return Permission.Read;
      case pnp:                        return Permission.Read;
      case sentPrintingDate:           return Permission.Read;
      case startDate:                  return Permission.Read;
      case startDateOverride:          return Permission.Read;
      case statusModifiedAt:           return Permission.Read;
      case modifiedAt:                 return Permission.Read;
      case product:                    return Permission.Read;
      case status:                     return Permission.Read;
      }
return Permission.None;
  }

  private static Permission LiteracyMaterial(LiteracyMaterial property){
    switch(property){
      case name:                       return Permission.Read;
      case scriptureReferences:        return Permission.Read;
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
      case agreement:                  return Permission.Read;
      case agreementStatus:            return Permission.Read;
      case financialReportingType:     return Permission.Read;
      case mou:                        return Permission.Read;
      case mouEnd:                     return Permission.Read;
      case mouEndOverride:             return Permission.Read;
      case mouStart:                   return Permission.Read;
      case mouStartOverride:           return Permission.Read;
      case mouStatus:                  return Permission.Read;
      case types:                      return Permission.Read;
      case organization:               return Permission.Read;
      case partner:                    return Permission.Read;
      }
return Permission.None;
  }

  private static Permission Product(Product property){
    switch(property){
      case mediums:                    return Permission.Read;
      case methodology:                return Permission.Read;
      case purposes:                   return Permission.Read;
      case scriptureReferences:        return Permission.Read;
      case produces:                   return Permission.Read;
      case scriptureReferencesOverride:return Permission.Read;
      case isOverriding:               return Permission.Read;
      }
return Permission.None;
  }

  private static Permission Project(Project property){
    switch(property){
      case estimatedSubmission:        return Permission.Read;
      case step:                       return Permission.Read;
      case name:                       return Permission.Read;
      case status:                     return Permission.Read;
      case departmentId:               return Permission.Read;
      case mouStart:                   return Permission.Read;
      case mouEnd:                     return Permission.Read;
      case rootDirectory:              return Permission.Read;
      case member:                     return Permission.Read;
      case otherLocations:             return Permission.Read;
      case primaryLocation:            return Permission.Read;
      case marketingLocation:          return Permission.Read;
      case partnership:                return Permission.Read;
      case budget:                     return Permission.Read;
      case modifiedAt:                 return Permission.Read;
      case fieldRegion:                return Permission.Read;
      case engagement:                 return Permission.Read;
      case sensitivity:                return Permission.Read;
      case stepChangedAt:              return Permission.Read; 
      case owningOrganization:         return Permission.Read; 
      case initialMouEnd:              return Permission.Read; 
      case tags:                       return Permission.Read;
      }
return Permission.None;
  }

  private static Permission ProjectMember(ProjectMember property){
    switch(property){
      case roles:                      return Permission.Read;
      case user:                       return Permission.Read;
      case modifiedAt:                 return Permission.Read;
      }
return Permission.None;
  }

  private static Permission Song(Song property){
    switch(property){
      case name:                       return Permission.Read;
      case scriptureReferences:        return Permission.Read;
      }
return Permission.None;
  }

  private static Permission Story(Story property){
    switch(property){
      case name:                       return Permission.Read;
      case scriptureReferences:        return Permission.Read;
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
