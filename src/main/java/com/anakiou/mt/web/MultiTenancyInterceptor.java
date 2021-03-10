package com.anakiou.mt.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MultiTenancyInterceptor extends HandlerInterceptorAdapter {

	List<String> tenants = List.of("tenant_1", "tenant_2", "tenant_3");

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		Map<String, Object> pathVars = (Map<String, Object>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		if (pathVars.containsKey("tenantid")) {
			String tenantId = (String) pathVars.get("tenantid");
			if (tenants.contains(tenantId)) req.setAttribute("CURRENT_TENANT_IDENTIFIER", tenantId);
		}
		return true;
	}
}
