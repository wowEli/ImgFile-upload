package test;

import java.io.IOException;
import java.util.Enumeration; // util에 임포트 됨 컬렉션에 한 종류

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class test
 */
@WebServlet("/test.do")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path="/Users/dongwook/0607jang/eclipse-workspace/day54img/src/main/webapp/images";
		int maxSize=100000;
		
		MultipartRequest mr = new MultipartRequest(request, path, maxSize,"UTF-8",new DefaultFileRenamePolicy() );
		// MultipartRequest는 기본생성자가 없어서 인자를 넣어줘야함
		// request(요청정보) 
		// path(사용자가 업로드 시도한 이미지를 어디에 저장되어야 하는 지에 대한 정보)
		// maxSize(최대 사이즈를 지정 byte단위)
		// "UTF-8" 인코딩 타입
		// new DefaultFileRenamePolicy() (이미지를 복붙해내는 방식으로 동작내부에서 이를 수행할려면 필요한 객체)
		
		/*
		String mid = request.getParameter("mid");
		String fileName = request.getParameter("fileName");
		System.out.println("로그1 ["+mid+"]");
		System.out.println("로그1 ["+fileName+"]");
		*/
		// 위 MultipartRequest mr 에 request정보가 다 들어가서 이후에 request확인이 불가능 해짐 ( 로그 null 뜸)
		
		String mid = mr.getParameter("mid"); // request -> mr로 주체 변경
		Enumeration<?> file = mr.getFileNames(); 
		// Enumeraion<> util에 임포트 됨 컬렉션에 한 종류
		
		if(file.hasMoreElements()) { // 한 장을 올릴 수도 있고 아닐 수도 있고 여서 if 여러 개는 for
			String paramName = (String)file.nextElement(); // 를 통해 jsp단에서 name속석의 값을 알 수 있다 이를 이용해 value를 뽑아낼 수 있음
			System.out.println("파라미터명: ["+paramName+"]");
			
			String serverFileName = mr.getFilesystemName(paramName);
			System.out.println("서버에 업로드된 파일명 ["+serverFileName+"]"); 
			
			request.getSession().setAttribute("file", serverFileName);
			
			String clientFileName = mr.getOriginalFileName(paramName);
			System.out.println("사용자가 업로드한 파일명 ["+clientFileName+"]");
			
			String fileType = mr.getContentType(paramName);
			System.out.println("파일의 타입 ["+fileType+"]");
			
			long length = mr.getFile(paramName).length();
			System.out.println("파일의 크기 ["+length+"]");
			
		}
		
		response.sendRedirect("NewFile.jsp");
	
	}

}
