package cn.coolink.interceptor;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.LoginContext;
import cn.coolink.dto.LoginDTO;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.service.sys.LoginService;
import cn.coolink.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证拦截器
 * 
 * @author
 * @Date 2016年12月16日
 */
public class JwtAuthenticateInterceptor extends HandlerInterceptorAdapter {

	private static final String AUTHORIZATION_PREFIX = "Bearer ";

	private static final int AUTHORIZATION_PREFIX_LENGTH = AUTHORIZATION_PREFIX.length();

	@Autowired
	private LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//LogUtil.info("进入拦截器",request.getRequestURL().toString() + "---" + JSON.toJSONString(handler) + "---" + handler.getClass().toString() );
		if(handler instanceof HandlerMethod){
			//LogUtil.info("进入拦截器1",request.getRequestURL().toString());
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			EscapeLogin escapeLogin = handlerMethod.getMethodAnnotation(EscapeLogin.class);
			//LogUtil.info("进入拦截器2",JSON.toJSONString(escapeLogin));
			if(escapeLogin!=null){
				return true;
			}
		}
		
		String authorization = request.getHeader("Authorization");
		if (StringUtils.isEmpty(authorization)) {
			handleFailure("AUTH_FAIL","认证失败(token缺失)",401,response);
			return false;
		}

		ResultBean<LoginDTO> loginResult = loginService.getLoginByToken(authorization);
		if (200 != loginResult.getCode()) {
			LogUtil.error("认证失败","URL:{},errMsg:{},errCode:{}",request.getRequestURL().toString(),
					loginResult.getMsg(),loginResult.getAlertMsg());
			handleFailure(loginResult.getMsg(),"认证失败",401,response);
			return false;
		}
		LoginDTO loginInfo = loginResult.getData();
		ResultBean<Boolean> valid = loginService.validToken(authorization, loginInfo.getMobile());
		if (200!=valid.getCode() || !valid.getData()) {
			LogUtil.error("认证失败","URL:{}",request.getRequestURL().toString());
			handleFailure(loginResult.getMsg(),"认证失败",401,response);
			return false;
		}

		if(null== loginInfo.getExpireDuration() || new Date().getTime()-loginInfo.getLogintime()>=loginInfo.getExpireDuration()*1000){
			LogUtil.error("登录超时","URL:{}",request.getRequestURL().toString());
			handleFailure(loginResult.getMsg(),"登录超时",401,response);
			loginService.loginOut(authorization);
			//response.sendRedirect("/");
			return false;
		}
		//刷新超时时间
		loginService.refreshLogin(loginInfo);

	    LoginContext.put(loginInfo);

		return super.preHandle(request, response, handler);
	}

	private void handleFailure(String errorMsg, String alertMsg,int status, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		Map<String, Object> map = new HashMap<>();
		map.put("info",errorMsg);
		map.put("status",status);
		map.put("code",status);
		map.put("success", false);
		map.put("alertInfo", alertMsg);
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect));
		out.flush();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		LoginContext.remove();
		super.postHandle(request, response, handler, modelAndView);
	}

}
