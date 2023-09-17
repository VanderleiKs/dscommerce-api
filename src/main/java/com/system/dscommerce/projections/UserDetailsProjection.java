package com.system.dscommerce.projections;

public interface UserDetailsProjection {
    String getUsername();
    String getPassWord();
    Long getRoleId();
    String getAuthority();
}
