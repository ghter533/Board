package bitek.board.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override   // ��Ʈ�ѷ��� �ռ� �����
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute("login")==null){
			response.sendRedirect("login");
			return false; // �ٸ� ������ ������� ����
		}
		return true;          // �ٸ� ���ͼ��ͳ� ��Ʈ�ѷ��� ���Ŀ� ȣ���
	}
	
	@Override   // ��Ʈ�ѷ� ���� ��, �� ȣ�� ���� �����
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle():after Controller, before View rendering");
	}
	
	@Override   // �䰡 ����� �Ŀ� �����
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion():after View rendered");
	}
}