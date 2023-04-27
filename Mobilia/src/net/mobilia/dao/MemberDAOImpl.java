package net.mobilia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.mobilia.vo.MemberVO;

public class MemberDAOImpl {

	Connection con=null;//db연결 con
	PreparedStatement pt=null;//쿼리문 수행
	Statement st=null;//쿼리문 수행
	ResultSet rs=null;//검색 결과 레코드를 저장할 rs
	DataSource ds=null;//DBCP 커넥션 풀 관리 ds
	String sql=null;//쿼리문 저장변수

	public MemberDAOImpl() {

		try {

			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/xe");
			//커넥션 풀 관리 ds 생성

		}catch(Exception e) {e.printStackTrace();}
	}//생성자


	//회원 가입
	public int insertMember(MemberVO m) {
		int re=-1;
		try {
			con=ds.getConnection();
			sql="insert into m_member(m_id,m_pwd,m_name,m_post,m_roadAddr,m_jibunAddr,m_detailAddr,"
					+ "m_phone01,m_phone02,m_phone03,m_birth01,m_birth02,m_birth03,mail_id,mail_domain,m_state,m_date)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,sysdate)";
			
			pt=con.prepareStatement(sql);
			
			pt.setString(1, m.getM_id());
			pt.setString(2, m.getM_pwd());
			pt.setString(3, m.getM_name());
			pt.setString(4, m.getM_post());
			pt.setString(5, m.getM_roadAddr());
			pt.setString(6, m.getM_jibunAddr());
			pt.setString(7, m.getM_detailAddr());
			pt.setString(8, m.getM_phone01());
			pt.setString(9, m.getM_phone02());
			pt.setString(10, m.getM_phone03());
			pt.setString(11, m.getM_birth01());
			pt.setString(12, m.getM_birth02());
			pt.setString(13, m.getM_birth03());
			pt.setString(14, m.getMail_id());
			pt.setString(15, m.getMail_domain());
			
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


	//아이디 중복검색
	public MemberVO idCheck(String id) {

		MemberVO m=null;

		try {
			con=ds.getConnection();//커넥션 풀로 관리 ds로 db연결 con 생성
			st=con.createStatement();
			sql="select * from m_member where m_id='"+id+"'";
			rs=st.executeQuery(sql);//검색 쿼리문 수행해서 결과 회원정보를 rs에 저장

			if(rs.next()) {
				m=new MemberVO();
				m.setM_id(rs.getString("m_id"));
			}

		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}

		return m;
	}

	//회원정보 수정
	public int updateMember(MemberVO m) {

		int re = -1;
		try {
			con = ds.getConnection();
			sql = "update m_member set m_pwd=?, m_name=?, m_post=?, m_roadAddr=?, m_jibunAddr=?,"
					+ "m_detailAddr=?, m_phone01=?, m_phone02=?, m_phone03=?, mail_id=?, mail_domain=?,"
					+ "m_birth01=?, m_birth02=?, m_birth03=? where m_id=?";
			pt = con.prepareStatement(sql);
			pt.setString(1, m.getM_pwd());
			pt.setString(2, m.getM_name());
			pt.setString(3, m.getM_post());
			pt.setString(4, m.getM_roadAddr());
			pt.setString(5, m.getM_jibunAddr());
			pt.setString(6, m.getM_detailAddr());
			pt.setString(7, m.getM_phone01());
			pt.setString(8, m.getM_phone02());
			pt.setString(9, m.getM_phone03());
			pt.setString(10, m.getMail_id());
			pt.setString(11, m.getMail_domain());
			pt.setString(12, m.getM_birth01());
			pt.setString(13, m.getM_birth02());
			pt.setString(14, m.getM_birth03());
			pt.setString(15, m.getM_id());
			
			re = pt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}//updateMember()

	//아이디를 기준으로 비밀번호를 가져옴
	public MemberVO loginCheck(String m_id) {
		MemberVO m=null;

		try {
			con=ds.getConnection();
			sql="select * from m_member where m_id=? and m_state=1";//가입회원 1인경우만
			//로그인 인증 처리
			pt=con.prepareStatement(sql);
			pt.setString(1,m_id);
			rs=pt.executeQuery();//검색 쿼리문 수행해서 결과 레코드를 rs에 저장
			if(rs.next()) {
				m = new MemberVO();
				m.setM_pwd(rs.getString("m_pwd"));
				
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return m;
	}

	//회원정보 가져오기
	public MemberVO getMember(String id) {
		
		MemberVO mvo=null;
		
		try {
			con=ds.getConnection();
			sql="select * from m_member where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1,id);
			rs=pt.executeQuery();
			if(rs.next()){
				mvo=new MemberVO();
				mvo.setM_id(rs.getString("m_id"));
				mvo.setM_pwd(rs.getString("m_pwd"));
				mvo.setM_name(rs.getString("m_name"));
				mvo.setM_post(rs.getString("m_post"));
				mvo.setM_roadAddr(rs.getString("m_roadAddr"));
				mvo.setM_jibunAddr(rs.getString("m_jibunAddr"));
				mvo.setM_detailAddr(rs.getString("m_detailAddr"));
				mvo.setM_phone01(rs.getString("m_phone01"));
				mvo.setM_phone02(rs.getString("m_phone02"));
				mvo.setM_phone03(rs.getString("m_phone03"));
				mvo.setMail_id(rs.getString("mail_id"));
				mvo.setMail_domain(rs.getString("mail_domain"));
				mvo.setM_birth01(rs.getString("m_birth01"));
				mvo.setM_birth02(rs.getString("m_birth02"));
				mvo.setM_birth03(rs.getString("m_birth03"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try{
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}//finally
		return mvo;
	}

	//회원탈퇴
	public void delMem(MemberVO mvo) {
		
		try {
			con=ds.getConnection();
			sql="update m_member set m_delcont=?, m_deldate=sysdate, m_state=2 "
			+" where m_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1,mvo.getM_delcont());
			pt.setString(2,mvo.getM_id());
			pt.executeUpdate();//수정 쿼리문 성공후 성공한 레코드 행의 개수를 반환
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}


	//비밀번호 찾기
	public MemberVO pwdMember(MemberVO m) {
		MemberVO pm=null;
		
		try {
			con=ds.getConnection();
			st=con.createStatement();
			sql="select * from m_member where m_id='"+m.getM_id()+"' and m_name='"
					+m.getM_name()+"' and mail_id='"+m.getMail_id()+ "'and mail_domain='"+m.getMail_domain()+"'";
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				pm=new MemberVO();
				pm.setM_pwd(rs.getString("m_pwd"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close(); 
			}catch(Exception e) {e.printStackTrace();}
		}
		return pm;
	}

	//아이디찾기
	public MemberVO idMember(MemberVO m) {
		MemberVO pm2=null;
		
		try {
			con=ds.getConnection();
			st=con.createStatement();
			sql="select * from m_member where m_name='"+m.getM_name()+"'"
					+" and mail_id='"+m.getMail_id()+ "'and mail_domain='"+m.getMail_domain()+"'";
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				pm2=new MemberVO();
				pm2.setM_id(rs.getString("m_id"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close(); 
			}catch(Exception e) {e.printStackTrace();}
		}
		return pm2;
	}
	
	
	
	
	
	
	
}
