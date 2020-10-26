package cord.roles;

import cord.common.BaseNodeLabels;
import cord.common.RoleNames;
import cord.model.Permission;

public abstract class BaseRole {
  public RoleNames roleName;

  public BaseRole(RoleNames roleName){
    this.roleName = roleName;
  }
  public static Permission permission(BaseNodeLabels label, Object property){
    return Permission.None;
  }
}
