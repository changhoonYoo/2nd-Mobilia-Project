package net.mobilia.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import net.mobilia.dao.ProductDAOImpl;
import net.mobilia.vo.ProductVO;

public class AdminProductOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String saveFolder=request.getRealPath("upload");
		//이진파일 업로드 실제 경로
		
		int fileSize=5*1024*1024;//이진파일 업로드 최대 크기 => 5메가 바이트
		
		MultipartRequest multi=null;
		//cos.jar에 있는 파일 첨부 라이브러리 api
		
		multi=new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		
		ProductVO p=new ProductVO();
		
		String p_name=multi.getParameter("p_name");
		int p_before_price=Integer.parseInt(multi.getParameter("p_before_price"));
		int p_price=Integer.parseInt(multi.getParameter("p_price"));
		int p_amount=Integer.parseInt(multi.getParameter("p_amount"));
		File upFile1=multi.getFile("p_img1");
		File upFile2=multi.getFile("p_img2");
		String p_class=multi.getParameter("p_class");
		String p_category=multi.getParameter("p_category");
		String p_info=multi.getParameter("p_info");
		
		int color_count=Integer.parseInt(multi.getParameter("color_count"));
		int size_count=Integer.parseInt(multi.getParameter("size_count"));
		
		String p_color =multi.getParameter("p_color0");
		p.setP_color(p_color);
		if(multi.getParameter("p_color1") != null) {
			for(int i=1;i<color_count;i++) {
				if(multi.getParameter("p_color"+i)!= null) {
					p_color+=","+multi.getParameter("p_color"+i);
				}
				p.setP_color(p_color);
			}
		}
		String p_size=multi.getParameter("p_size0");
		p.setP_size(p_size);
		if(multi.getParameter("p_size1") != null) {
			for(int i=1;i<size_count;i++) {
				if(multi.getParameter("p_size"+i) != null) {
					p_size=p_size+","+multi.getParameter("p_size"+i);
				}
				p.setP_size(p_size);
			}
		}
		if(multi.getParameter("p_choice") != null) {
			int p_choice=Integer.parseInt(multi.getParameter("p_choice"));
			 p.setP_choice(p_choice);
		}
		//첨부된 파일을 가져옴.
		
		if(upFile1 != null && upFile2 != null) {//첨부된 파일이 있는 경우
			String fileName1=upFile1.getName();//첨부된 파일명을 가져온다.
			String fileName2=upFile2.getName();//첨부된 파일명을 가져온다.
			
			Calendar cal=Calendar.getInstance();
			int year=cal.get(Calendar.YEAR);
			int month=cal.get(Calendar.MONTH)+1;
			//+1을 한 이유는 1월이 0으로 반환되기 때문에
			int date=cal.get(Calendar.DATE);
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
			//오늘 날짜 폴더 경로를 저장
			
			File path01=new File(homedir);
			if(!(path01.exists())) {//오늘 날짜 폴더 경로가 없다면
				path01.mkdir();//오늘 날짜 폴더를 생성
			}
			
			Random r=new Random();
			int random=r.nextInt(100000000);//0이상 1억 미만 사이의 정수 숫자 난수 발생
			
			/* 첨부된 파일에서 파일 확장자만 구하기 */
			int index1=fileName1.lastIndexOf(".");
			int index2=fileName2.lastIndexOf(".");
			//첨부된 파일에서 .를 맨 오른쪽 부터 찾아서 위치번호를 왼쪽부터 카운터 해서 구함. 첫 문자를 0부터 시작
			
			String fileExtenSion1=fileName1.substring(index1+1);
			String fileExtenSion2=fileName2.substring(index2+1);
			//첨부파일에서 .이후부터 마지막 문자까지 구함. 즉 첨부파일 확장자를 구함
			
			String refileName1="product"+year+month+date+random+"."+fileExtenSion1;
			String refileName2="product"+year+month+date+random+"_on."+fileExtenSion2;
			//새로운 첨부파일명을 저장
			
			String fileDBName1="/"+year+"-"+month+"-"+date+"/"+refileName1;
			String fileDBName2="/"+year+"-"+month+"-"+date+"/"+refileName2;
			//DB에 저장될 레코드 값
			
			upFile1.renameTo(new File(homedir+"/"+refileName1));
			upFile2.renameTo(new File(homedir+"/"+refileName2));
			//오늘 날짜 생성된 폴더 경로에 변경된 첨부파일로 실제 업로드 한다.
			
			p.setP_img1(fileDBName1);
			p.setP_img2(fileDBName2);
			
		}
		p.setP_name(p_name); p.setP_price(p_price); p.setP_before_price(p_before_price);
		p.setP_amount(p_amount);
		p.setP_class(p_class); p.setP_category(p_category); 
		p.setP_info(p_info);
		ProductDAOImpl pdao=new ProductDAOImpl();
		
		int re=pdao.insertProduct(p);//상품 저장
		
		if(re==1) {
			
			out.println("<script>");
			out.println("alert('상품 등록에 성공했습니다!');");
			out.println("location='admin_product.net'");
			out.println("</script>");
		}
		return null;
	}
}
