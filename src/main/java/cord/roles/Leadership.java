package cord.roles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;

public class Leadership extends BaseRole {
  public Leadership(){
    super(RoleNames.LeadershipRole, Leadership.permission);
  }

  public static IPermission permission = (BaseNodeLabels label, String property) -> {
    switch(label){
      case Budget:                return Leadership.Budget(                 Budget.valueOf(property));
      case BudgetRecord:          return Leadership.BudgetRecord(           BudgetRecord.valueOf(property));
      case Ceremony:              return Leadership.Ceremony(               Ceremony.valueOf(property));
      case Directory:             return Leadership.Directory(              Directory.valueOf(property));
      case Education:             return Leadership.Education(              Education.valueOf(property));
      case EthnologueLanguage:    return Leadership.EthnologueLanguage(     EthnologueLanguage.valueOf(property));
      case FieldRegion:           return Leadership.FieldRegion(            FieldRegion.valueOf(property));
      case FieldZone:             return Leadership.FieldZone(              FieldZone.valueOf(property));
      case File:                  return Leadership.File(                   File.valueOf(property));
      case FileVersion:           return Leadership.FileVersion(            FileVersion.valueOf(property));
      case Film:                  return Leadership.Film(                   Film.valueOf(property));
      case FundingAccount:        return Leadership.FundingAccount(         FundingAccount.valueOf(property));
      case InternshipEngagement:  return Leadership.InternshipEngagement(   InternshipEngagement.valueOf(property));
      case Language:              return Leadership.Language(               Language.valueOf(property));
      case LanguageEngagement:    return Leadership.LanguageEngagement(     LanguageEngagement.valueOf(property));
      case LiteracyMaterial:      return Leadership.LiteracyMaterial(       LiteracyMaterial.valueOf(property));
      case Location:              return Leadership.Location(               Location.valueOf(property));
      case Organization:          return Leadership.Organization(           Organization.valueOf(property));
      case Partner:               return Leadership.Partner(                Partner.valueOf(property));
      case Partnership:           return Leadership.Partnership(            Partnership.valueOf(property));
      case Project:               return Leadership.Project(                Project.valueOf(property));
      case ProjectMember:         return Leadership.ProjectMember(          ProjectMember.valueOf(property));
      case Product:               return Leadership.Product(                Product.valueOf(property));
      case Song:                  return Leadership.Song(                   Song.valueOf(property));
      case Story:                 return Leadership.Story(                  Story.valueOf(property));
      case Unavailability:        return Leadership.Unavailability(         Unavailability.valueOf(property));
      case User:                  return Leadership.User(                   User.valueOf(property));
      default: return Perm.NO;
    }
  };

  private static Perm Budget(Budget property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case universalTemplateFile:      return Perm.RW;
      case records:                    return Perm.RW;
      case status:                     return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm BudgetRecord(BudgetRecord property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case amount:                     return Perm.RW;
      case fiscalYear:                 return Perm.RW;
      case organization:               return Perm.RW;
      }
return Perm.NO;
  }  
  
  private static Perm Ceremony(Ceremony property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case actualDate:                 return Perm.RW;
      case estimatedDate:              return Perm.RW;
      case planned:                    return Perm.RW;
      case type:                       return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm Directory(Directory property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RW;
      case createdBy:                  return Perm.RW;
      case parent:                     return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm Education(Education property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case degree:                     return Perm.RO;
      case institution:                return Perm.RO;
      case major:                      return Perm.RO;
      }
return Perm.NO;
  }

  private static Perm EthnologueLanguage(EthnologueLanguage property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case code:                       return Perm.RO;
      case name:                       return Perm.RO;
      case population:                 return Perm.RO;
      case provisionalCode:            return Perm.RO;
      }
return Perm.NO;
  }

  private static Perm FieldRegion(FieldRegion property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case director:                   return Perm.RO;
      case name:                       return Perm.RO;
      case fieldZone:                  return Perm.RO;
      }
return Perm.NO;
  }  
  
  private static Perm FieldZone(FieldZone property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case director:                   return Perm.RO;
      case name:                       return Perm.RO;
      }
return Perm.NO;
  }

  private static Perm File(File property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RW;
      case createdBy:                  return Perm.RW;
      case parent:                     return Perm.RW;
      case mimeType:                   return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm FileVersion(FileVersion property){
        switch(property){
      case canDelete:                  return Perm.RO;
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
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm FundingAccount(FundingAccount property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RO;
      case accountNumber:              return Perm.RO;
      }
return Perm.NO;
  }  
  
  private static Perm InternshipEngagement(InternshipEngagement property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case ceremony:                   return Perm.RW;
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
      case canDelete:                  return Perm.RO;
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
case tags:                             return Perm.RO;
      }
return Perm.NO;
  }

  private static Perm LanguageEngagement(LanguageEngagement property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case ceremony:                   return Perm.RW;
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
      case paratextRegistryId:         return Perm.RW;
      case paraTextRegistryId:         return Perm.RW;
      case pnp:                        return Perm.RW;
      case sentPrintingDate:           return Perm.RW;
      case startDate:                  return Perm.RW;
      case startDateOverride:          return Perm.RW;
      case statusModifiedAt:           return Perm.RW;
      case modifiedAt:                 return Perm.RW;
      case product:                    return Perm.RW;
      case status:                     return Perm.RW;
      case historicGoal:               return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm LiteracyMaterial(LiteracyMaterial property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm Location(Location property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RO;
      case type:                       return Perm.RO;
      case sensitivity:                return Perm.RO;
      case isoAlpha3:                  return Perm.RO;
      case fundingAccount:             return Perm.RO;
      case defaultFieldRegion:         return Perm.RO;
      }
return Perm.NO;
  }
  
  private static Perm Organization(Organization property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RO;
      case address:                    return Perm.RO;
      case locations:                  return Perm.RO;
      }
return Perm.NO;
  }

  private static Perm Partner(Partner property){
        switch(property){
      case canDelete:                  return Perm.RO;
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
      case canDelete:                  return Perm.RO;
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
      case canDelete:                  return Perm.RO;
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
      case canDelete:                  return Perm.RO;
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
      case financialReportReceivedAt:  return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm ProjectMember(ProjectMember property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case roles:                      return Perm.RW;
      case user:                       return Perm.RW;
      case modifiedAt:                 return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm Song(Song property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm Story(Story property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case name:                       return Perm.RW;
      case scriptureReferences:        return Perm.RW;
      }
return Perm.NO;
  }

  private static Perm Unavailability(Unavailability property){
        switch(property){
      case canDelete:                  return Perm.RO;
      case description:                return Perm.RO;
      case end:                        return Perm.RO;
      case start:                      return Perm.RO;
      }
return Perm.NO;
  }

  private static Perm User(User property){
        switch(property){
      case canDelete:                  return Perm.RO;
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
