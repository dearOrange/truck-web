package cn.coolink.filters;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "jsonPFilter", urlPatterns = {"/*"})
public class JsonPFilter extends OncePerRequestFilter {

	public void destroy() {
	}

	/**
	 * 跨域
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Version,User-agent,Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		filterChain.doFilter(request, response);
	}

}
