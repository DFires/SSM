package com.maven.ssm.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

public class MyRelam extends AuthorizingRealm{

	/**
	 *授权 
	 *先获取登录用户名,使用登录用户查询数据库获取用户信息,再使用SimpleAuthorizationInfo把用户把用户与角色权限进行绑定
	 **/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		System.out.println(principal+"授权");
		System.out.println(principal.fromRealm(getName()));
		String username = (String) principal.getPrimaryPrincipal();
		Set<String> roles = new HashSet();
		roles.add("admin");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		return info;
	}

	/**
	 *认证 
	 *
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String algorith = "MD5"; // 加密方式
		int hashIter = 1024; // 加密次数
		String username = userToken.getUsername();
//		System.out.println(userToken.getPassword()+"md5");
		/**
		 *模拟从数据库查询用户信息,密码 
		 **/
		String password = "1q2w3e4r";
		String saltpassword="78ac3091040c7eaa96f649b5288bff5a";
		
		SimpleAuthenticationInfo authentic = null;
		if(!StringUtils.isEmpty(username)){
			/**
			 * 通过AuthenticatingRealm 类中的info = doGetAuthenticationInfo(token) 调用自定义实现的realm的认证方法
			 * 并使用 assertCredentialsMatch(token, info);对密码进行验证
			 * tips:如果使用md5 salt盐值加密 ,则需要先把传入的密码进行加密 再和数据库中的密码进行匹配。因为保存到数据库中的数据是经过md5加密之后的密文。shiro进行对密码加密需要在shiro配置文件中的realm的bean中配置如下属性
			 * <property name="credentialsMatcher">
					<bean name="hashedCredentialsMatcher"
					class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
					<property name="hashAlgorithmName" value="MD5" />
					<property name="hashIterations" value="1024" />
					</bean>
				</property>
			 **/
			authentic = new SimpleAuthenticationInfo(username, saltpassword, getName());
		}
		return authentic;
	}

}
