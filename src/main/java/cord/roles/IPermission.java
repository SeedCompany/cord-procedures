package cord.roles;

import cord.common.BaseNodeLabels;
import cord.model.Permission;

public interface IPermission {
  Permission permission(BaseNodeLabels label, String property);
}
