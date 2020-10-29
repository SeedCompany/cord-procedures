package cord.roles;
import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.*;

public class Leadership extends BaseRole {
  public Leadership(){
    super(RoleNames.LeadershipRole, Leadership.permission);
  }package cord.roles;
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
      case degree:                     return Permission.ReadWrite;
      case institution:                return Permission.ReadWrite;
      case major:                      return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission EthnologueLanguage(EthnologueLanguage property){
    switch(property){
      case code:                       return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
      case population:                 return Permission.ReadWrite;
      case provisionalCode:            return Permission.ReadWrite;
      }
return Permission.None;
  }

  private static Permission FieldRegion(FieldRegion property){
    switch(property){
      case director:                   return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
      case fieldZone:                  return Permission.ReadWrite;
      }
return Permission.None;
  }  
  
  private static Permission FieldZone(FieldZone property){
    switch(property){
      case director:                   return Permission.ReadWrite;
      case name:                       return Permission.ReadWrite;
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
      case name:                       return Permission.ReadWrite;
      case accountNumber:              return Permission.ReadWrite;
      }
return Permission.None;
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
      }
return Permission.None;
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
case tags:                       return Permission.ReadWrite;
      }
return Permission.None;
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
      case name:                       return Permission.ReadWrite;
      case type:                       return Permission.ReadWrite;
      case sensitivity:                return Permission.ReadWrite;
      case isoAlpha3:                  return Permission.ReadWrite;
      case fundingAccount:             return Permission.ReadWrite;
      }
return Permission.None;
  }
  
  private static Permission Organization(Organization property){
    switch(property){
      case name:                       return Permission.ReadWrite;
      case address:                    return Permission.ReadWrite;
      case locations:                  return Permission.ReadWrite;
      }
return Permission.None;
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
      }
return Permission.None;
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
      case modifiedAt:                 return Permission.ReadWrite;
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
      case description:                return Permission.ReadWrite;
      case end:                        return Permission.ReadWrite;
      case start:                      return Permission.ReadWrite;
      }
return Permission.None;
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
      }
return Permission.None;
  }

}
