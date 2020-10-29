package cord.roles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;

public class Administrator extends BaseRole {
  public Administrator(){
    super(RoleNames.AdministratorRole, Administrator.permission);
  }

  public static IPermission permission = (BaseNodeLabels label, String property) -> {
    switch(label){
      case Budget:                return Administrator.Budget(                 Budget.valueOf(property));
      case BudgetRecord:          return Administrator.BudgetRecord(           BudgetRecord.valueOf(property));
      case Ceremony:              return Administrator.Ceremony(               Ceremony.valueOf(property));
      case Directory:             return Administrator.Directory(              Directory.valueOf(property));
      case Education:             return Administrator.Education(              Education.valueOf(property));
      case EthnologueLanguage:    return Administrator.EthnologueLanguage(     EthnologueLanguage.valueOf(property));
      case FieldRegion:           return Administrator.FieldRegion(            FieldRegion.valueOf(property));
      case FieldZone:             return Administrator.FieldZone(              FieldZone.valueOf(property));
      case File:                  return Administrator.File(                   File.valueOf(property));
      case FileVersion:           return Administrator.FileVersion(            FileVersion.valueOf(property));
      case Film:                  return Administrator.Film(                   Film.valueOf(property));
      case FundingAccount:        return Administrator.FundingAccount(         FundingAccount.valueOf(property));
      case InternshipEngagement:  return Administrator.InternshipEngagement(   InternshipEngagement.valueOf(property));
      case Language:              return Administrator.Language(               Language.valueOf(property));
      case LanguageEngagement:    return Administrator.LanguageEngagement(     LanguageEngagement.valueOf(property));
      case LiteracyMaterial:      return Administrator.LiteracyMaterial(       LiteracyMaterial.valueOf(property));
      case Location:              return Administrator.Location(               Location.valueOf(property));
      case Organization:          return Administrator.Organization(           Organization.valueOf(property));
      case Partner:               return Administrator.Partner(                Partner.valueOf(property));
      case Partnership:           return Administrator.Partnership(            Partnership.valueOf(property));
      case Project:               return Administrator.Project(                Project.valueOf(property));
      case ProjectMember:         return Administrator.ProjectMember(          ProjectMember.valueOf(property));
      case Product:               return Administrator.Product(                Product.valueOf(property));
      case Song:                  return Administrator.Song(                   Song.valueOf(property));
      case Story:                 return Administrator.Story(                  Story.valueOf(property));
      case Unavailability:        return Administrator.Unavailability(         Unavailability.valueOf(property));
      case User:                  return Administrator.User(                   User.valueOf(property));

      default: return Perm.NO;
    }
  };

  private static Perm Budget(Budget property){
    switch(property){
      case universalTemplateFile:      return Perm.RW;
      case records:                    return Perm.RW;
      case status:                     return Perm.RW;
      } 

      return Perm.NO;
  }

  private static Perm BudgetRecord(BudgetRecord property){
    switch(property){
      case amount:                     return Perm.RW;
      case fiscalYear:                 return Perm.RW;
      case organization:               return Perm.RW;
      } 

      return Perm.NO;
  }  
  
  private static Perm Ceremony(Ceremony property){
    switch(property){
      case actualDate:                 return Perm.RW;
      case estimatedDate:              return Perm.RW;
      case planned:                    return Perm.RW;
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
      case degree:                     return Perm.RW;
      case institution:                return Perm.RW;
      case major:                      return Perm.RW;
      } 

      return Perm.NO;
  }

  private static Perm EthnologueLanguage(EthnologueLanguage property){
    switch(property){
      case code:                       return Perm.RW;
      case name:                       return Perm.RW;
      case population:                 return Perm.RW;
      case provisionalCode:            return Perm.RW;
      } 

      return Perm.NO;
  }

  private static Perm FieldRegion(FieldRegion property){
    switch(property){
      case director:                   return Perm.RW;
      case name:                       return Perm.RW;
      case fieldZone:                  return Perm.RW;
      } 

      return Perm.NO;
  }  
  
  private static Perm FieldZone(FieldZone property){
    switch(property){
      case director:                   return Perm.RW;
      case name:                       return Perm.RW;
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
      case name:                       return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      } 

    return Perm.NO;
  }

  private static Perm FundingAccount(FundingAccount property){
    switch(property){
      case name:                       return Perm.RW;
      case accountNumber:              return Perm.RW;
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
      case displayName:                return Perm.RW;
      case displayNamePronunciation:   return Perm.RW;
      case isDialect:                  return Perm.RW;
      case isSignLanguage:             return Perm.RW;
      case leastOfThese:               return Perm.RW;
      case name:                       return Perm.RW;
      case leastOfTheseReason:         return Perm.RW;
      case populationOverride:         return Perm.RW;
      case registryOfDialectsCode:     return Perm.RW;
      case signLanguageCode:           return Perm.RW;
      case sponsorEstimatedEndDate:    return Perm.RW;
      case ethnologue:                 return Perm.RW;
      case sensitivity:                return Perm.RW;
      case hasExternalFirstScripture:  return Perm.RW;
      case locations:                  return Perm.RW;

      case tags:                       return Perm.RW;
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
      case name:                       return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      } 

return Perm.NO;
  }

  private static Perm Location(Location property){
    switch(property){
      case name:                       return Perm.RW;
      case type:                       return Perm.RW;
      case sensitivity:                return Perm.RW;
      case isoAlpha3:                  return Perm.RW;
      case fundingAccount:             return Perm.RW;
      } 

return Perm.NO;
  }
  
  private static Perm Organization(Organization property){
    switch(property){
      case name:                       return Perm.RW;
      case address:                    return Perm.RW;
      case locations:                  return Perm.RW;
      } 

return Perm.NO;
  }

  private static Perm Partner(Partner property){
    switch(property){
      case organization:               return Perm.RW;
      case pointOfContact:             return Perm.RW;
      case types:                      return Perm.RW;
      case financialReportingTypes:    return Perm.RW;
      case pmcEntityCode:              return Perm.RW;
      case globalInnovationsClient:    return Perm.RW;
      case active:                     return Perm.RW;
      case address:                    return Perm.RW;
      case modifiedAt:                 return Perm.RW;
      } 

return Perm.NO;
  }

  private static Perm Partnership(Partnership property){
    switch(property){
      case agreement:                  return Perm.RW;
      case agreementStatus:            return Perm.RW;
      case financialReportingType:     return Perm.RW;
      case mou:                        return Perm.RW;
      case mouEnd:                     return Perm.RW;
      case mouEndOverride:             return Perm.RW;
      case mouStart:                   return Perm.RW;
      case mouStartOverride:           return Perm.RW;
      case mouStatus:                  return Perm.RW;
      case types:                      return Perm.RW;
      case organization:               return Perm.RW;
      case partner:                    return Perm.RW;
      } 

return Perm.NO;
  }

  private static Perm Product(Product property){
    switch(property){
      case mediums:                    return Perm.RW;
      case methodology:                return Perm.RW;
      case purposes:                   return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      case produces:                   return Perm.RW;
      case scriptureReferencesOverride:return Perm.RW;
      case isOverriding:               return Perm.RW;
      } 

return Perm.NO;
  }

  private static Perm Project(Project property){
    switch(property){
      case estimatedSubmission:        return Perm.RW;
      case step:                       return Perm.RW;
      case name:                       return Perm.RW;
      case status:                     return Perm.RO;
      case departmentId:               return Perm.RO;
      case mouStart:                   return Perm.RW;
      case mouEnd:                     return Perm.RW;
      case rootDirectory:              return Perm.RW;
      case member:                     return Perm.RW;
      case otherLocations:             return Perm.RW;
      case primaryLocation:            return Perm.RW;
      case marketingLocation:          return Perm.RW;
      case partnership:                return Perm.RW;
      case budget:                     return Perm.RW;
      case modifiedAt:                 return Perm.RO;
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
      case modifiedAt:                 return Perm.RO;
      } 

return Perm.NO;
  }

  private static Perm Song(Song property){
    switch(property){
      case name:                       return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      } 

return Perm.NO;
  }

  private static Perm Story(Story property){
    switch(property){
      case name:                       return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      } 

return Perm.NO;
  }

  private static Perm Unavailability(Unavailability property){
    switch(property){
      case description:                return Perm.RW;
      case end:                        return Perm.RW;
      case start:                      return Perm.RW;
      } 

return Perm.NO;
  }

  private static Perm User(User property){
    switch(property){
      case about:                      return Perm.RW;
      case displayFirstName:           return Perm.RW;
      case displayLastName:            return Perm.RW;
      case email:                      return Perm.RW;
      case phone:                      return Perm.RW;
      case realFirstName:              return Perm.RW;
      case realLastName:               return Perm.RW;
      case roles:                      return Perm.RW;
      case status:                     return Perm.RW;
      case timezone:                   return Perm.RW;
      case title:                      return Perm.RW;
      case education:                  return Perm.RW;
      case organization:               return Perm.RW;
      case unavailability:             return Perm.RW;
      case locations:                  return Perm.RW;
      } 

return Perm.NO;
  }

}
