package cord.roles;

import cord.common.RoleNames;
public abstract class BaseRole {
  public RoleNames roleName;
  public IPermission permission;

  public BaseRole(RoleNames roleName, IPermission permission){
    this.roleName = roleName;
    this.permission = permission;
  }
}
