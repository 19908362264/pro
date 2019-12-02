package com.benwunet.mws.model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * spring security当前登录对象 *
 * @author xiangkaihong
 * @date 2019-04-27 10:13
 */

@Getter
@Setter
public class LoginPubSysUser extends PubSysUser implements UserDetails{

	private static final long serialVersionUID = -6810155155115800131L;

	private Set<PubSysRole> pubSysRoles;

	private Set<String> menuIds;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new HashSet<>();
		if (!CollectionUtils.isEmpty(pubSysRoles)) {
			pubSysRoles.forEach(role -> {
				if (role.getRoleId().startsWith("ROLE_")) {
					collection.add(new SimpleGrantedAuthority(role.getRoleId()));
				} else {
					collection.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleId()));
				}
			});
		}

		if (!CollectionUtils.isEmpty(menuIds)) {
			menuIds.forEach(per -> {
				collection.add(new SimpleGrantedAuthority(per));
			});
		}

		return collection;
	}

	@Override
	public String getUsername() { return getUserName();	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() { return getIsUse();}
}
