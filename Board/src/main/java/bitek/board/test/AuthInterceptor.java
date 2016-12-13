package bitek.board.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override   // 콘트롤러에 앞서 실행됨
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute("login")==null){
			response.sendRedirect("login");
			return false; // 다른 절차가 실행되지 않음
		}
		return true;          // 다른 인터셉터나 콘트롤러가 이후에 호출됨
	}
	
	@Override   // 콘트롤러 실행 후, 뷰 호출 전에 실행됨
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle():after Controller, before View rendering");
	}
	
	@Override   // 뷰가 실행된 후에 실행됨
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion():after View rendered");
	}
}