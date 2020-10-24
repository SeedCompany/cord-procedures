package cord.roles;

import cord.common.BaseNodeLabels;
import cord.model.Permission;

public abstract class BaseRole {
  public static Permission permission(BaseNodeLabels label, Object property){
    return Permission.None;
  }
}
