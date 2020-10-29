package cord.roles;

import cord.common.BaseNodeLabels;
import cord.model.Perm;

public interface IPermission {
  Perm permission(BaseNodeLabels label, String property);
}
