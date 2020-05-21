package com.messy.token;

import com.messy.dao.SysClientDao;
import com.messy.dao.SysServiceDao;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("all")
public class AuthorizeConfigManager implements ReactiveAuthorizationManager<AuthorizationContext> {

	@Resource
	private SysServiceDao sysServiceDao;

	@Resource
	private SysClientDao sysClientDao;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public Mono<AuthorizationDecision> check(Mono<Authentication> authentication,
			AuthorizationContext authorizationContext) {
		return authentication.map(auth -> {

			// TODO 目前都是true
			boolean hasPermission = false;

			ServerWebExchange exchange = authorizationContext.getExchange();
			ServerHttpRequest request = exchange.getRequest();

			if (auth instanceof OAuth2Authentication) {

				OAuth2Authentication athentication = (OAuth2Authentication) auth;

				String clientId = athentication.getOAuth2Request().getClientId();

				Map map = sysClientDao.getClient(clientId);

				if (map == null) {
					return new AuthorizationDecision(false);
				} else {
					List<Map> list = sysServiceDao.listByClientId(Long.valueOf(String.valueOf(map.get("id"))));

					for (Iterator<Map> it = list.iterator(); it.hasNext();) {
						Map temp = it.next();

						if (antPathMatcher.match(String.valueOf(temp.get("path")), request.getURI().getPath())) {
							return new AuthorizationDecision(true);
						}
					}
					return new AuthorizationDecision(false);
				}

			}

			// boolean isPermission = super.hasPermission(auth,
			// request.getMethodValue(), request.getURI().getPath());

			return new AuthorizationDecision(hasPermission);
		}).defaultIfEmpty(new AuthorizationDecision(false));
	}

}
