package cord.roles;

import java.util.HashMap;

import cord.common.AllProperties;
import cord.common.RoleNames;
import cord.common.BaseNodeLabels;
import cord.model.Perm;

public class Roles {

  class RoleToPerm{
    private HashMap<RoleNames, Perm> map;
    public RoleToPerm(RoleNames role, Perm[] perms){
      this.map = new HashMap<RoleNames, Perm>();
      for (Perm perm : perms){
        map.put(role, perm);
      }
    }
    public Perm get(RoleNames role){
      return this.map.get(role);
    }
  }

  class PropertyToRole{
    private HashMap<AllProperties, RoleToPerm> map;
    public PropertyToRole(AllProperties property, RoleToPerm[] roles){
      this.map = new HashMap<AllProperties, RoleToPerm>();
      for (RoleToPerm role : roles){
        map.put(property, role);
      }
    }
    public RoleToPerm get(AllProperties property){
      return this.map.get(property);
    }
  }

  public class BaseNodeToProperty{
    private HashMap<BaseNodeLabels, PropertyToRole> map;
    public BaseNodeToProperty(BaseNodeLabels baseNode, PropertyToRole[] propertyRows){
      this.map = new HashMap<BaseNodeLabels, PropertyToRole>();
      for (PropertyToRole row: propertyRows){
        this.map.put(baseNode, row);
      }
    }
    public PropertyToRole get(BaseNodeLabels baseNode){
      return this.map.get(baseNode);
    }
  }

  private BaseNodeToProperty map;

  public Roles() {
    this.map = new BaseNodeToProperty(
      BaseNodeLabels.Budget,            new PropertyToRole[]{ new PropertyToRole( AllProperties.about,          new RoleToPerm[]{ new RoleToPerm( RoleNames.AdministratorRole,            new Perm[]{Perm.NO} ) } ) } );
  }
  
  public Perm permission(BaseNodeLabels baseNode, AllProperties property, RoleNames role){
    return this.map.get(baseNode).get(property).get(role);
  }
  
}
