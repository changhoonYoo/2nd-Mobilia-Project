package net.mobilia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.mobilia.vo.MemberVO;
import net.mobilia.vo.ReviewVO;

public class ReviewDAOImpl {

	DataSource ds=null;//커넥션 풀 관리 ds
	Connection con=null;//데이터 베이스 연결
	PreparedStatement pt=null;//쿼리문 수행
	ResultSet rs=null;//검색 결과 레코드를 저장할 rs
	String sql=null;//쿼리문 저장변수

	public ReviewDAOImpl() {

		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/xe");
			//JNDI 커넥션 풀 관리 ds생성
		}catch(Exception e) {
			System.out.println("DB 연결 실패! : "+ e);
			return;//종료
		}//try~catch
	}//기본생성자

	//상품후기 등록
	public int insertReview(ReviewVO rv) {
		int re=-1;
		try {
			con=ds.getConnection();
			sql="insert into review_p (p_no,m_id,p_img1,p_name,re_no,re_cont,re_star,re_date) "
					+"values(?,?,?,?,re_no_seq.nextval,?,?,sysdate)";

			pt=con.prepareStatement(sql);

			pt.setInt(1, rv.getP_no());
			pt.setString(2, rv.getM_id());
			pt.setString(3, rv.getP_img1());
			pt.setString(4, rv.getP_name());
			pt.setString(5, rv.getRe_cont());
			pt.setInt(6, rv.getRe_star());

			re=pt.executeUpdate();

		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}

	public int getReviewCount(int p_no) {
		int count=0;

		try {

			con=ds.getConnection();//커넥션 풀 관리 ds로 DB연결 con 생성
			sql="select count(p_no) from review_p where p_no=?";

			pt=con.prepareStatement(sql);//쿼리문을 미리 컴파일하여 수행할 pt생성
			pt.setInt(1, p_no);

			rs=pt.executeQuery();//검색 쿼리문 수행후 결과 레코드를 rs에 저장

			if(rs.next()) {
				count=rs.getInt(1);//1의 뜻은 select문 다음에 나오는 컬럼 순번
			}

		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {

				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();

			}catch(Exception e) {e.printStackTrace();}
		}//finally

		return count;
	}//getListCount()

	public List<ReviewVO> getReviewList(int page, int limit, int p_no) {

		List<ReviewVO> rlist=new ArrayList<>();

		try {

			con=ds.getConnection();
			sql="select * from (select rownum rNum,p_no,m_id,p_img1,p_name,re_no,re_cont,re_star,re_date "
					+ "from (select * from review_p order by re_no desc)) where rNum>=? and rNum<=? and p_no=?";
			/* 페이징과 검색 관련 쿼리문
			 * rowNum 컬럼은 오라클에서 페이징 생성시 추가되는 컬럼으로 최초 레코드 저장시
			 * 일련 번호값이 알아서 저장된다.
			 * rNum은 rowNum 컬럼의 별칭명이다.
			 */

			pt=con.prepareStatement(sql);

			int startrow=(page-1)*5+1;//읽기 시작할 행번호.
			//10이 한페이지 보여지는 목록개수
			int endrow=startrow+limit-1;//읽을 마지막 행번호.

			pt.setInt(1, startrow);
			pt.setInt(2, endrow);
			pt.setInt(3, p_no);

			rs=pt.executeQuery();

			while(rs.next()) {
				//복수개의 레코드가 검색되는 경우는 while 반복문으로 반복
				ReviewVO rv=new ReviewVO();

				rv.setP_no(rs.getInt("p_no"));
				rv.setM_id(rs.getString("m_id"));
				rv.setP_img1(rs.getString("p_img1"));
				rv.setP_name(rs.getString("p_name"));
				rv.setRe_no(rs.getInt("re_no"));
				rv.setRe_cont(rs.getNString("re_cont"));
				rv.setRe_star(rs.getInt("re_star"));
				rv.setRe_date(rs.getString("re_date"));

				rlist.add(rv);//컬렉션에 추가
			}

		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {

				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();

			}catch(Exception e) {e.printStackTrace();}
		}
		return rlist;
	}

	public ReviewVO getReviewCont(int re_no) {

		ReviewVO rvo=null;
		
		try {
			con=ds.getConnection();
			sql="select * from review_p where re_no=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1,re_no);
			rs=pt.executeQuery();
			if(rs.next()){
				rvo=new ReviewVO();
				
				rvo.setP_no(rs.getInt("p_no"));
				rvo.setM_id(rs.getString("m_id"));
				rvo.setP_img1(rs.getString("p_img1"));
				rvo.setP_name(rs.getString("p_name"));
				rvo.setRe_no(rs.getInt("re_no"));
				rvo.setRe_cont(rs.getString("re_cont"));
				rvo.setRe_star(rs.getInt("re_star"));
				rvo.setRe_date(rs.getString("re_date"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try{
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}//finally
		return rvo;
	}

	public int updateReview(ReviewVO rv) {
		int re=0;
		
		try {
			con=ds.getConnection();
			sql="update review_p set re_star=?,re_cont=? where re_no=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1,rv.getRe_star());
			pt.setString(2, rv.getRe_cont());
			pt.setInt(3, rv.getRe_no());

			re=pt.executeUpdate();
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {				
				if(pt != null) pt.close();
				if(con != null) con.close();				
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}

	public int delReview(int re_no) {
		int re=0;
		
		try {
			con=ds.getConnection();
			sql="delete review_p where re_no=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1,re_no);
			
			re=pt.executeUpdate();
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}
}
