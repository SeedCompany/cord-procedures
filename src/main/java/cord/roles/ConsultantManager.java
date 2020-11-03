package cord.roles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;

public class ConsultantManager extends BaseRole {
  public ConsultantManager(){
    super(RoleNames.ConsultantManagerRole, ConsultantManager.permission);
  }

  public static IPermission permission = (BaseNodeLabels label, String property) -> {
    switch(label){
      case Budget:                return ConsultantManager.Budget(                 Budget.valueOf(property));
      case BudgetRecord:          return ConsultantManager.BudgetRecord(           BudgetRecord.valueOf(property));
      case Ceremony:              return ConsultantManager.Ceremony(               Ceremony.valueOf(property));
      case Directory:             return ConsultantManager.Directory(              Directory.valueOf(property));
      case Education:             return ConsultantManager.Education(              Education.valueOf(property));
      case EthnologueLanguage:    return ConsultantManager.EthnologueLanguage(     EthnologueLanguage.valueOf(property));
      case FieldRegion:           return ConsultantManager.FieldRegion(            FieldRegion.valueOf(property));
      case FieldZone:             return ConsultantManager.FieldZone(              FieldZone.valueOf(property));
      case File:                  return ConsultantManager.File(                   File.valueOf(property));
      case FileVersion:           return ConsultantManager.FileVersion(            FileVersion.valueOf(property));
      case Film:                  return ConsultantManager.Film(                   Film.valueOf(property));
      case FundingAccount:        return ConsultantManager.FundingAccount(         FundingAccount.valueOf(property));
      case InternshipEngagement:  return ConsultantManager.InternshipEngagement(   InternshipEngagement.valueOf(property));
      case Language:              return ConsultantManager.Language(               Language.valueOf(property));
      case LanguageEngagement:    return ConsultantManager.LanguageEngagement(     LanguageEngagement.valueOf(property));
      case LiteracyMaterial:      return ConsultantManager.LiteracyMaterial(       LiteracyMaterial.valueOf(property));
      case Location:              return ConsultantManager.Location(               Location.valueOf(property));
      case Organization:          return ConsultantManager.Organization(           Organization.valueOf(property));
      case Partner:               return ConsultantManager.Partner(                Partner.valueOf(property));
      case Partnership:           return ConsultantManager.Partnership(            Partnership.valueOf(property));
      case Project:               return ConsultantManager.Project(                Project.valueOf(property));
      case ProjectMember:         return ConsultantManager.ProjectMember(          ProjectMember.valueOf(property));
      case Product:               return ConsultantManager.Product(                Product.valueOf(property));
      case Song:                  return ConsultantManager.Song(                   Song.valueOf(property));
      case Story:                 return ConsultantManager.Story(                  Story.valueOf(property));
      case Unavailability:        return ConsultantManager.Unavailability(         Unavailability.valueOf(property));
      case User:                  return ConsultantManager.User(                   User.valueOf(property));
      default: return Perm.NO;
    }
  };

  private static Perm Budget(Budget property){
    switch(property){
      case universalTemplateFile:      return Perm.RO;
      case records:                    return Perm.RO;
      case status:                     return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm BudgetRecord(BudgetRecord property){
    switch(property){
      case amount:                     return Perm.RO;
      case fiscalYear:                 return Perm.RO;
      case organization:               return Perm.RO;
    }
    return Perm.NO;
  }  
  
  private static Perm Ceremony(Ceremony property){
    switch(property){
      case actualDate:                 return Perm.RO;
      case estimatedDate:              return Perm.RO;
      case planned:                    return Perm.RO;
      case type:                       return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm Directory(Directory property){
    switch(property){
      case name:                       return Perm.RW;
      case createdBy:                  return Perm.RW;
      case parent:                     return Perm.RW;
    }
    return Perm.NO;
  }

  private static Perm Education(Education property){
    switch(property){
      case degree:                     return Perm.RO;
      case institution:                return Perm.RO;
      case major:                      return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm EthnologueLanguage(EthnologueLanguage property){
    switch(property){
      case code:                       return Perm.RO;
      case name:                       return Perm.RO;
      case population:                 return Perm.RO;
      case provisionalCode:            return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm FieldRegion(FieldRegion property){
    switch(property){
      case director:                   return Perm.RO;
      case name:                       return Perm.RO;
      case fieldZone:                  return Perm.RO;
    }
    return Perm.NO;
  }  
  
  private static Perm FieldZone(FieldZone property){
    switch(property){
      case director:                   return Perm.RO;
      case name:                       return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm File(File property){
    switch(property){
      case name:                       return Perm.RW;
      case createdBy:                  return Perm.RW;
      case parent:                     return Perm.RW;
      case mimeType:                   return Perm.RW;
    }
    return Perm.NO;
  }

  private static Perm FileVersion(FileVersion property){
    switch(property){
      case name:                       return Perm.RW;
      case createdBy:                  return Perm.RW;
      case parent:                     return Perm.RW;
      case mimeType:                   return Perm.RW;
      case size:                       return Perm.RW;
    }
    return Perm.NO;
  }

  private static Perm Film(Film property){
    switch(property){
      case name:                       return Perm.RO;
      case scriptureReferences:        return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm FundingAccount(FundingAccount property){
    switch(property){
      case name:                       return Perm.RO;
      case accountNumber:              return Perm.RO;
    }
    return Perm.NO;
  }  
  
  private static Perm InternshipEngagement(InternshipEngagement property){
    switch(property){
      case ceremony:                   return Perm.RW;
      case communicationsCompleteDate: return Perm.RW;
      case completeDate:               return Perm.RW;
      case countryOfOrigin:            return Perm.RW;
      case disbursementCompleteDate:   return Perm.RW;
      case endDate:                    return Perm.RW;
      case endDateOverride:            return Perm.RW;
      case growthPlan:                 return Perm.RW;
      case initialEndDate:             return Perm.RW;
      case intern:                     return Perm.RW;
      case lastReactivatedAt:          return Perm.RW;
      case lastSuspendedAt:            return Perm.RW;
      case mentor:                     return Perm.RW;
      case methodologies:              return Perm.RW;
      case position:                   return Perm.RW;
      case startDate:                  return Perm.RW;
      case startDateOverride:          return Perm.RW;
      case statusModifiedAt:           return Perm.RW;
      case modifiedAt:                 return Perm.RW;
      case status:                     return Perm.RW;
    }
    return Perm.NO;
  }

  private static Perm Language(Language property){
    switch(property){
      case displayName:                return Perm.RO;
      case displayNamePronunciation:   return Perm.RO;
      case isDialect:                  return Perm.RO;
      case isSignLanguage:             return Perm.RO;
      case leastOfThese:               return Perm.RO;
      case name:                       return Perm.RO;
      case leastOfTheseReason:         return Perm.RO;
      case populationOverride:         return Perm.RO;
      case registryOfDialectsCode:     return Perm.RO;
      case signLanguageCode:           return Perm.RO;
      case sponsorEstimatedEndDate:    return Perm.RO;
      case ethnologue:                 return Perm.RO;
      case sensitivity:                return Perm.RO;
      case hasExternalFirstScripture:  return Perm.RO;
      case locations:                  return Perm.RO;
      case tags:                       return Perm.RO;
      }
    return Perm.NO;
  }

  private static Perm LanguageEngagement(LanguageEngagement property){
    switch(property){
      case ceremony:                   return Perm.RW;
      case communicationsCompleteDate: return Perm.RW;
      case completeDate:               return Perm.RW;
      case disbursementCompleteDate:   return Perm.RW;
      case endDate:                    return Perm.RW;
      case endDateOverride:            return Perm.RW;
      case firstScripture:             return Perm.RW;
      case initialEndDate:             return Perm.RW;
      case language:                   return Perm.RW;
      case lastReactivatedAt:          return Perm.RW;
      case lastSuspendedAt:            return Perm.RW;
      case lukePartnership:            return Perm.RW;
      case paraTextRegistryId:         return Perm.RW;
      case pnp:                        return Perm.RW;
      case sentPrintingDate:           return Perm.RW;
      case startDate:                  return Perm.RW;
      case startDateOverride:          return Perm.RW;
      case statusModifiedAt:           return Perm.RW;
      case modifiedAt:                 return Perm.RW;
      case product:                    return Perm.RW;
      case status:                     return Perm.RW;
    }
    return Perm.NO;
  }

  private static Perm LiteracyMaterial(LiteracyMaterial property){
    switch(property){
      case name:                       return Perm.RO;
      case scriptureReferences:        return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm Location(Location property){
    switch(property){
      case name:                       return Perm.RO;
      case type:                       return Perm.RO;
      case sensitivity:                return Perm.RO;
      case isoAlpha3:                  return Perm.RO;
      case fundingAccount:             return Perm.RO;
    }
    return Perm.NO;
  }
  
  private static Perm Organization(Organization property){
    switch(property){
      case name:                       return Perm.RO;
      case address:                    return Perm.RO;
      case locations:                  return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm Partner(Partner property){
    switch(property){
      case organization:               return Perm.RO;
      case pointOfContact:             return Perm.RO;
      case types:                      return Perm.RO;
      case financialReportingTypes:    return Perm.RO;
      case pmcEntityCode:              return Perm.RO;
      case globalInnovationsClient:    return Perm.RO;
      case active:                     return Perm.RO;
      case address:                    return Perm.RO;
      case modifiedAt:                 return Perm.RW;
    }
    return Perm.NO;
  }

  private static Perm Partnership(Partnership property){
    switch(property){
      case agreement:                  return Perm.RO;
      case agreementStatus:            return Perm.RO;
      case financialReportingType:     return Perm.RO;
      case mou:                        return Perm.RO;
      case mouEnd:                     return Perm.RO;
      case mouEndOverride:             return Perm.RO;
      case mouStart:                   return Perm.RO;
      case mouStartOverride:           return Perm.RO;
      case mouStatus:                  return Perm.RO;
      case types:                      return Perm.RO;
      case organization:               return Perm.RO;
      case partner:                    return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm Product(Product property){
    switch(property){
      case mediums:                    return Perm.RO;
      case methodology:                return Perm.RO;
      case purposes:                   return Perm.RO;
      case scriptureReferences:        return Perm.RO;
      case produces:                   return Perm.RO;
      case scriptureReferencesOverride:return Perm.RO;
      case isOverriding:               return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm Project(Project property){
    switch(property){
      case estimatedSubmission:        return Perm.RW;
      case step:                       return Perm.RW;
      case name:                       return Perm.RW;
      case status:                     return Perm.RW;
      case departmentId:               return Perm.RW;
      case mouStart:                   return Perm.RW;
      case mouEnd:                     return Perm.RW;
      case rootDirectory:              return Perm.RW;
      case member:                     return Perm.RW;
      case otherLocations:             return Perm.RW;
      case primaryLocation:            return Perm.RW;
      case marketingLocation:          return Perm.RW;
      case partnership:                return Perm.RW;
      case budget:                     return Perm.RW;
      case modifiedAt:                 return Perm.RW;
      case fieldRegion:                return Perm.RW;
      case engagement:                 return Perm.RW;
      case sensitivity:                return Perm.RW;
      case stepChangedAt:              return Perm.RW;
      case owningOrganization:         return Perm.RW;
      case initialMouEnd:              return Perm.RW; 
      case tags:                       return Perm.RW;
    }
    return Perm.NO;
  }

  private static Perm ProjectMember(ProjectMember property){
    switch(property){
      case roles:                      return Perm.RW;
      case user:                       return Perm.RW;
      case modifiedAt:                 return Perm.RW;
    }
    return Perm.NO;
  }

  private static Perm Song(Song property){
    switch(property){
      case name:                       return Perm.RO;
      case scriptureReferences:        return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm Story(Story property){
    switch(property){
      case name:                       return Perm.RO;
      case scriptureReferences:        return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm Unavailability(Unavailability property){
    switch(property){
      case description:                return Perm.RO;
      case end:                        return Perm.RO;
      case start:                      return Perm.RO;
    }
    return Perm.NO;
  }

  private static Perm User(User property){
    switch(property){
      case about:                      return Perm.RO;
      case displayFirstName:           return Perm.RO;
      case displayLastName:            return Perm.RO;
      case email:                      return Perm.RO;
      case phone:                      return Perm.RO;
      case realFirstName:              return Perm.RO;
      case realLastName:               return Perm.RO;
      case roles:                      return Perm.RO;
      case status:                     return Perm.RO;
      case timezone:                   return Perm.RO;
      case title:                      return Perm.RO;
      case education:                  return Perm.RO;
      case organization:               return Perm.RO;
      case unavailability:             return Perm.RO;
      case locations:                  return Perm.RO;
      case partners:                   return Perm.RO;
    }
    return Perm.NO;
  }

}
