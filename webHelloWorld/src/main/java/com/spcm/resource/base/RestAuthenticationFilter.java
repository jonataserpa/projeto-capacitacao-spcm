/**
 * 
 */
package com.spcm.resource.base;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Classe para fazer um filtro base da chamada ao WS do LogPRO.<br/>
 * Alem do usuario e senha, o sistema guarda o IP origem de quem chamou. No
 * futuro pode ser usado para averiguacao de IP.
 * 
 * @author fuji
 * @since 06/2017
 */
public class RestAuthenticationFilter implements javax.servlet.Filter {

	public static final String AUTHENTICATION_HEADER = "Authorization";
	private static final Logger logger = LogManager.getLogger(RestAuthenticationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {

		// Caso seja necessario um controle via endereco IP do requisitante
		String ipAddress = request.getRemoteAddr();

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			
			String path = ((HttpServletRequest) request).getPathInfo();
			 			
			String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

			// Pega o IP caso o requisitante esteja atras de um proxy ou
			// cloudfare
			String ipAddress2 = getClientIp(httpServletRequest);
						
			Map<String, String> ipAddress3 = getRequestHeadersInMap(httpServletRequest);			
			
			// You can implement dependancy injection here
			AuthenticationService authenticationService = new AuthenticationService();
						
			boolean authenticationStatus = authenticationService.authenticate(authCredentials);
			
			if (authenticationStatus) {
				filter.doFilter(request, response);
			} else {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			} 
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	/**
	 * Obtem o endereco ip do cliente caso esteja atras de um<br/>
	 * proxy ou do cloudflare.
	 * 
	 * @param request
	 * @return
	 */
	private static String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

	/**
	 * Metodo privado para obter o header da requisicao http<br/>
	 * com isso e possivel inspecionar o header e verificar aonde esta o ip.
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

		Map<String, String> result = new HashMap<>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = request.getHeader(key);
			result.put(key, value);
		}

		return result;
	}

}
