package cord.roles;

import cord.model.Budget;
import cord.model.BudgetRecord;
import cord.model.Permission;

public enum Administrator {
  Role;

  public Permission Budget(Budget property){
    switch(property){
      case records:         return Permission.Read;
      default:              return Permission.None;
    }
  }

  public Permission BudgetRecord(BudgetRecord property){
    switch(property){
      case Asdf:            return Permission.Read;
      default:              return Permission.None;
    }
  }

}
