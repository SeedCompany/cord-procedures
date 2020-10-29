package cord.roles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;

public class Fundraising extends BaseRole {
  public Fundraising(){
    super(RoleNames.FundraisingRole, Fundraising.permission);
  }

  public static IPermission permission = (BaseNodeLabels label, String property) -> {
    switch(label){
      case Budget:                return Fundraising.Budget(                 Budget.valueOf(property));
      case BudgetRecord:          return Fundraising.BudgetRecord(           BudgetRecord.valueOf(property));
      case Ceremony:              return Fundraising.Ceremony(               Ceremony.valueOf(property));
      case Directory:             return Fundraising.Directory(              Directory.valueOf(property));
      case Education:             return Fundraising.Education(              Education.valueOf(property));
      case EthnologueLanguage:    return Fundraising.EthnologueLanguage(     EthnologueLanguage.valueOf(property));
      case FieldRegion:           return Fundraising.FieldRegion(            FieldRegion.valueOf(property));
      case FieldZone:             return Fundraising.FieldZone(              FieldZone.valueOf(property));
      case File:                  return Fundraising.File(                   File.valueOf(property));
      case FileVersion:           return Fundraising.FileVersion(            FileVersion.valueOf(property));
      case Film:                  return Fundraising.Film(                   Film.valueOf(property));
      case FundingAccount:        return Fundraising.FundingAccount(         FundingAccount.valueOf(property));
      case InternshipEngagement:  return Fundraising.InternshipEngagement(   InternshipEngagement.valueOf(property));
      case Language:              return Fundraising.Language(               Language.valueOf(property));
      case LanguageEngagement:    return Fundraising.LanguageEngagement(     LanguageEngagement.valueOf(property));
      case LiteracyMaterial:      return Fundraising.LiteracyMaterial(       LiteracyMaterial.valueOf(property));
      case Location:              return Fundraising.Location(               Location.valueOf(property));
      case Organization:          return Fundraising.Organization(           Organization.valueOf(property));
      case Partner:               return Fundraising.Partner(                Partner.valueOf(property));
      case Partnership:           return Fundraising.Partnership(            Partnership.valueOf(property));
      case Project:               return Fundraising.Project(                Project.valueOf(property));
      case ProjectMember:         return Fundraising.ProjectMember(          ProjectMember.valueOf(property));
      case Product:               return Fundraising.Product(                Product.valueOf(property));
      case Song:                  return Fundraising.Song(                   Song.valueOf(property));
      case Story:                 return Fundraising.Story(                  Story.valueOf(property));
      case Unavailability:        return Fundraising.Unavailability(         Unavailability.valueOf(property));
      case User:                  return Fundraising.User(                   User.valueOf(property));
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
