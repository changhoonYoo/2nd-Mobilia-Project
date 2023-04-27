package net.mobilia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.mobilia.vo.QnaBoardVO;

public class QnaBoardDAOImpl {

	Connection con=null;//db연결 con
	PreparedStatement pt=null;//쿼리문 수행
	Statement st=null;//쿼리문 수행
	ResultSet rs=null;//검색 결과 레코드를 저장할 rs
	DataSource ds=null;//DBCP 커넥션 풀 관리 ds
	String sql=null;//쿼리문 저장변수

	public QnaBoardDAOImpl() {

		try {

			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/xe");
			//커넥션 풀 관리 ds 생성

		}catch(Exception e) {e.printStackTrace();}
	}//생성자


	public int insertBoard(QnaBoardVO qnavo) {
		int re = -1;
		try {
			con = ds.getConnection();
			sql = "insert into qna_board (board_no, board_name, board_title, board_cont, board_date) "
					+ "values(qna_board_no_seq.nextval,?,?,?,sysdate)";

			pt = con.prepareStatement(sql);
			pt.setString(1, qnavo.getBoard_name());
			pt.setString(2, qnavo.getBoard_title());
			pt.setString(3, qnavo.getBoard_cont());

			re = pt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return re;
	}

	public int getListCount(QnaBoardVO findqna) {
		int count = 0;

		try {
			con = ds.getConnection();
			sql ="select count(board_no) from qna_board";
			if(findqna.getFind_field() == null) {
				sql+="";
			}else if(findqna.getFind_field().equals("board_name")) {
				sql+=" where board_name like ? ";
			}else if(findqna.getFind_field().equals("board_title")) {
				sql+=" where board_title like ? ";
			}else if(findqna.getFind_field().equals("board_cont")) {
				sql+=" where board_cont like ? ";
			}

			pt = con.prepareStatement(sql);

			if(findqna.getFind_field() != null) {
				pt.setString(1, findqna.getFind_name());
			}

			rs = pt.executeQuery();

			if(rs.next()) {
				count  = rs.getInt(1);
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
	}

	public List<QnaBoardVO> getBoardData(int page, int maxview, QnaBoardVO findqna) {//보드 리스트 가져오기
		List<QnaBoardVO> qlist = new ArrayList<>();

		try {
			con = ds.getConnection();
			sql = "select * from (select rowNum rNum,board_no,board_name,board_title,"
					+ "board_cont,board_hit,reply_hit,board_date from(select * from qna_board";
			if(findqna.getFind_field() == null) {
				sql+="";
			}else if(findqna.getFind_field().equals("board_name")) {
				sql+=" where board_name like ?";
			}else if(findqna.getFind_field().equals("board_title")) {
				sql+=" where board_title like ?";
			}else if(findqna.getFind_field().equals("board_cont")) {
				sql+=" where board_cont like ?";
			}
			sql+=" order by board_no desc)) where rNum>=? and rNum<=?";

			pt = con.prepareStatement(sql);

			int startrow=(page-1)*10+1; //읽기 시작할 행번호. 10은 한페이지 에서 보여지는 목록 개수
			int endrow = startrow+maxview-1;//읽을 마지막 행번호

			if(findqna.getFind_field() != null) {//사용자가 검색을 한 경우
				pt.setString(1, findqna.getFind_name());
				pt.setInt(2, startrow);
				pt.setInt(3, endrow);
			}else { //사용자가 검색을 하지 않은 경우
				pt.setInt(1, startrow);
				pt.setInt(2, endrow);
			}

			rs = pt.executeQuery();

			while(rs.next()) {
				QnaBoardVO qvo = new QnaBoardVO();
				qvo.setBoard_no(rs.getInt("board_no"));
				qvo.setBoard_title(rs.getString("board_title"));
				qvo.setBoard_name(rs.getString("board_name"));
				qvo.setBoard_hit(rs.getInt("board_hit"));
				qvo.setReply_hit(rs.getInt("reply_hit"));
				qvo.setBoard_date(rs.getString("board_date"));

				qlist.add(qvo);
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return qlist;
	}


	public void updatetHit(int board_no) {

		try {
			con=ds.getConnection();
			sql="update qna_board set board_hit=board_hit+1 where board_no=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1,board_no);
			pt.executeUpdate();

		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {				
				if(pt != null) pt.close();
				if(con != null) con.close();				
			}catch(Exception e) {e.printStackTrace();}
		}
	}


	public QnaBoardVO getBoardCont(int board_no) {
		QnaBoardVO qvo=null;

		try {
			con=ds.getConnection();
			sql="select * from qna_board where board_no=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1,board_no);
			rs=pt.executeQuery();

			if(rs.next()) {
				qvo = new QnaBoardVO();
				qvo.setBoard_no(rs.getInt("board_no"));
				qvo.setBoard_name(rs.getString("board_name"));
				qvo.setBoard_title(rs.getString("board_title"));
				qvo.setBoard_cont(rs.getString("board_cont"));
				qvo.setBoard_hit(rs.getInt("board_hit"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();				
			}catch(Exception e) {e.printStackTrace();}
		}
		return qvo;
	}


	public void delBoard(int board_no) {
		
		try {
			con=ds.getConnection();
			sql="delete qna_board where board_no=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1,board_no);
			pt.executeUpdate();
					
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}


	//QnA 게시물 수정
	public int editQnaBoard(QnaBoardVO qvo) {
		int re=-1;
		
		try {
			
			con=ds.getConnection();
			sql="update qna_board set board_name=?,board_title=?,board_cont=? "
					+ "where board_no=?";
			pt=con.prepareStatement(sql);
			
			pt.setString(1, qvo.getBoard_name());
			pt.setString(2, qvo.getBoard_title());
			pt.setString(3, qvo.getBoard_cont());
			pt.setInt(4, qvo.getBoard_no());
			
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
