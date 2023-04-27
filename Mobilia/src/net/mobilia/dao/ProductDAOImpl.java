package net.mobilia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.mobilia.vo.ProductVO;
import net.mobilia.vo.QnaBoardVO;

public class ProductDAOImpl {
	DataSource ds=null;//커넥션 풀 관리 ds
	Connection con=null;//데이터 베이스 연결
	PreparedStatement pt=null;//쿼리문 수행
	ResultSet rs=null;//검색 결과 레코드를 저장할 rs
	String sql=null;//쿼리문 저장변수
	
	public ProductDAOImpl() {
		
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/xe");
			//JNDI 커넥션 풀 관리 ds생성
		}catch(Exception e) {
			System.out.println("DB 연결 실패! : "+ e);
			return;//종료
		}//try~catch
	}//기본생성자

	//상품 등록
	public int insertProduct(ProductVO p) {
		int re=-1;
		try {
			con=ds.getConnection();
			
			if(p.getP_choice() == 1) {
				sql="insert into product_list(p_no,p_name,p_before_price,p_price,p_amount,p_img1,p_img2"
						+ ",p_choice,p_class,p_category,p_info,p_color,p_size,p_date) "
						+ "values(product_no_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
					pt=con.prepareStatement(sql);
				
					pt.setString(1, p.getP_name());
					pt.setInt(2, p.getP_before_price());
					pt.setInt(3, p.getP_price());
					pt.setInt(4, p.getP_amount());
					pt.setString(5, p.getP_img1());
					pt.setString(6, p.getP_img2());
					pt.setInt(7, p.getP_choice());
					pt.setString(8, p.getP_class());
					pt.setString(9, p.getP_category());
					pt.setString(10, p.getP_info());
					pt.setString(11, p.getP_color());
					pt.setString(12, p.getP_size());
			}else {
					sql="insert into product_list(p_no,p_name,p_before_price,p_price,p_amount,p_img1,p_img2"
						+ ",p_class,p_category,p_info,p_color,p_size,p_date) "
						+ "values(product_no_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
					pt=con.prepareStatement(sql);
				
					pt.setString(1, p.getP_name());
					pt.setInt(2, p.getP_before_price());
					pt.setInt(3, p.getP_price());
					pt.setInt(4, p.getP_amount());
					pt.setString(5, p.getP_img1());
					pt.setString(6, p.getP_img2());
					pt.setString(7, p.getP_class());
					pt.setString(8, p.getP_category());
					pt.setString(9, p.getP_info());
					pt.setString(10, p.getP_color());
					pt.setString(11, p.getP_size());
			}
			
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
	
	//전체 상품 개수
	public int getListCount(ProductVO p,String c) {
		int count=0;
		
		try {
			
			con=ds.getConnection();//커넥션 풀 관리 ds로 DB연결 con 생성
			sql="select count(p_no) from product_list where p_class=?";
			
			pt=con.prepareStatement(sql);//쿼리문을 미리 컴파일하여 수행할 pt생성
			pt.setString(1, c);
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
	}
	
	//선택 상품 개수
	public int getListCount(ProductVO p, String c, String state) {
		int count=0;
		
		try {
			
			con=ds.getConnection();//커넥션 풀 관리 ds로 DB연결 con 생성
			sql="select count(p_no) from product_list where p_class=? and p_category=?";
			
			pt=con.prepareStatement(sql);//쿼리문을 미리 컴파일하여 수행할 pt생성
			pt.setString(1, c);
			pt.setString(2, state);
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
	}
	
	//전체 상품 리스트
	public List<ProductVO> getProductList(ProductVO p, String c) {
		List<ProductVO> plist=new ArrayList<>();
		
		try {
			
			con=ds.getConnection();
			sql="select * from product_list where p_class=? order by p_no desc";
			pt=con.prepareStatement(sql);
			pt.setString(1, c);
			rs=pt.executeQuery();
			
			while(rs.next()) {
				//복수개의 레코드가 검색되는 경우는 while 반복문으로 반복
				ProductVO pv=new ProductVO();
				pv.setP_no(rs.getInt("p_no"));
				pv.setP_name(rs.getString("p_name"));
				pv.setP_before_price(rs.getInt("p_before_price"));
				pv.setP_price(rs.getInt("p_price"));
				pv.setP_amount(rs.getInt("p_amount"));
				pv.setP_sold(rs.getInt("p_sold"));
				pv.setP_img1(rs.getString("p_img1"));
				pv.setP_img2(rs.getString("p_img2"));
				pv.setP_choice(rs.getInt("p_choice"));
				pv.setP_class(rs.getString("p_class"));
				pv.setP_category(rs.getString("p_category"));
				pv.setP_date(rs.getString("p_date"));
				pv.setP_info(rs.getString("p_info"));
				pv.setP_color(rs.getString("p_color"));
				pv.setP_size(rs.getString("p_size"));
				
				int rate=((rs.getInt("p_before_price") - rs.getInt("p_price")) *100 )/ rs.getInt("p_before_price");
				pv.setP_rate(rate);
				plist.add(pv);//컬렉션에 추가
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
		return plist;
	}

	
	//선택 상품 리스트
	public List<ProductVO> getProductList(ProductVO p, String c, String state) {
		List<ProductVO> plist=new ArrayList<>();
		
		try {
			
			con=ds.getConnection();
			sql="select * from product_list "
					+ "where p_class=? and p_category=? order by p_no desc";
			pt=con.prepareStatement(sql);
			pt.setString(1, c);
			pt.setString(2, state);
			rs=pt.executeQuery();
			
			while(rs.next()) {
				//복수개의 레코드가 검색되는 경우는 while 반복문으로 반복
				ProductVO pv=new ProductVO();
				pv.setP_no(rs.getInt("p_no"));
				pv.setP_name(rs.getString("p_name"));
				pv.setP_before_price(rs.getInt("p_before_price"));
				pv.setP_price(rs.getInt("p_price"));
				pv.setP_amount(rs.getInt("p_amount"));
				pv.setP_sold(rs.getInt("p_sold"));
				pv.setP_img1(rs.getString("p_img1"));
				pv.setP_img2(rs.getString("p_img2"));
				pv.setP_choice(rs.getInt("p_choice"));
				pv.setP_class(rs.getString("p_class"));
				pv.setP_category(rs.getString("p_category"));
				pv.setP_date(rs.getString("p_date"));
				pv.setP_info(rs.getString("p_info"));
				pv.setP_color(rs.getString("p_color"));
				pv.setP_size(rs.getString("p_size"));
				
				int rate=((rs.getInt("p_before_price") - rs.getInt("p_price")) *100 )/ rs.getInt("p_before_price");
				pv.setP_rate(rate);
				plist.add(pv);//컬렉션에 추가
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
		return plist;
	}
	
	//전체 상품에서 상품 정렬 방법 선택 시
	public List<ProductVO> getProductListMethod(ProductVO p, String c, String m) {
		List<ProductVO> plist=new ArrayList<>();
		
		try {
			
			con=ds.getConnection();
			sql="select * from product_list where p_class=? ";
			if(m.equals("new")) {
				sql+="order by p_no desc";
			}else if(m.equals("low")) {
				sql+="order by p_price asc";
			}else if(m.equals("high")) {
				sql+="order by p_price desc";
			}else if(m.equals("best")) {
				sql+="order by p_sold asc";
			}
			pt=con.prepareStatement(sql);
			pt.setString(1, c);
			
			
			rs=pt.executeQuery();
			
			while(rs.next()) {
				//복수개의 레코드가 검색되는 경우는 while 반복문으로 반복
				ProductVO pv=new ProductVO();
				pv.setP_no(rs.getInt("p_no"));
				pv.setP_name(rs.getString("p_name"));
				pv.setP_before_price(rs.getInt("p_before_price"));
				pv.setP_price(rs.getInt("p_price"));
				pv.setP_amount(rs.getInt("p_amount"));
				pv.setP_sold(rs.getInt("p_sold"));
				pv.setP_img1(rs.getString("p_img1"));
				pv.setP_img2(rs.getString("p_img2"));
				pv.setP_choice(rs.getInt("p_choice"));
				pv.setP_class(rs.getString("p_class"));
				pv.setP_category(rs.getString("p_category"));
				pv.setP_date(rs.getString("p_date"));
				pv.setP_info(rs.getString("p_info"));
				pv.setP_color(rs.getString("p_color"));
				pv.setP_size(rs.getString("p_size"));
				
				int rate=((rs.getInt("p_before_price") - rs.getInt("p_price")) *100 )/ rs.getInt("p_before_price");
				pv.setP_rate(rate);
				plist.add(pv);//컬렉션에 추가
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
		return plist;
	}

	//선택 상품에서 정렬방법 선택 시
	public List<ProductVO> getProductListMethod(ProductVO p, String c, String state, String m) {
		List<ProductVO> plist=new ArrayList<>();
		
		try {
			
			con=ds.getConnection();
			sql="select * from product_list "
					+ "where p_class=? and p_category=? ";
			if(m.equals("new")) {
				sql+="order by p_no desc";
			}else if(m.equals("low")) {
				sql+="order by p_price asc";
			}else if(m.equals("high")) {
				sql+="order by p_price desc";
			}else if(m.equals("best")) {
				sql+="order by p_sold asc";
			}
			pt=con.prepareStatement(sql);
			pt.setString(1, c);
			pt.setString(2, state);
			
			
			rs=pt.executeQuery();
			
			while(rs.next()) {
				//복수개의 레코드가 검색되는 경우는 while 반복문으로 반복
				ProductVO pv=new ProductVO();
				pv.setP_no(rs.getInt("p_no"));
				pv.setP_name(rs.getString("p_name"));
				pv.setP_before_price(rs.getInt("p_before_price"));
				pv.setP_price(rs.getInt("p_price"));
				pv.setP_amount(rs.getInt("p_amount"));
				pv.setP_sold(rs.getInt("p_sold"));
				pv.setP_img1(rs.getString("p_img1"));
				pv.setP_img2(rs.getString("p_img2"));
				pv.setP_choice(rs.getInt("p_choice"));
				pv.setP_class(rs.getString("p_class"));
				pv.setP_category(rs.getString("p_category"));
				pv.setP_date(rs.getString("p_date"));
				pv.setP_info(rs.getString("p_info"));
				pv.setP_color(rs.getString("p_color"));
				pv.setP_size(rs.getString("p_size"));
				
				int rate=((rs.getInt("p_before_price") - rs.getInt("p_price")) *100 )/ rs.getInt("p_before_price");
				pv.setP_rate(rate);
				plist.add(pv);//컬렉션에 추가
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
		return plist;
	}
	
	//번호 기준 상품정보 가져오기
	public ProductVO getProductInfo(int p_no) {
		ProductVO pv=null;
		try {
			
			con=ds.getConnection();
			sql="select * from product_list where p_no=?";
			pt=con.prepareStatement(sql);
			
			pt.setInt(1, p_no);
			
			rs=pt.executeQuery();
			
			if(rs.next()) {
				pv=new ProductVO();
				
				pv.setP_no(rs.getInt("p_no"));
				pv.setP_name(rs.getString("p_name"));
				pv.setP_before_price(rs.getInt("p_before_price"));
				pv.setP_price(rs.getInt("p_price"));
				pv.setP_amount(rs.getInt("p_amount"));
				pv.setP_sold(rs.getInt("p_sold"));
				pv.setP_img1(rs.getString("p_img1"));
				pv.setP_img2(rs.getString("p_img2"));
				pv.setP_choice(rs.getInt("p_choice"));
				pv.setP_class(rs.getString("p_class"));
				pv.setP_category(rs.getString("p_category"));
				pv.setP_date(rs.getString("p_date"));
				pv.setP_info(rs.getString("p_info"));
				pv.setP_color(rs.getString("p_color"));
				pv.setP_size(rs.getString("p_size"));
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
		return pv;
	}//getBoardCont()


	public List<ProductVO> findProductList(ProductVO pvo) {//검색한 상품 가져오기
		
		List<ProductVO> plist = new ArrayList<>();

		try {
			con = ds.getConnection();
			sql = "select * from product_list where p_name like ?";

			pt = con.prepareStatement(sql);
			pt.setString(1, pvo.getP_name());

			rs = pt.executeQuery();

			while(rs.next()) {
				pvo = new ProductVO();
				
				pvo.setP_no(rs.getInt("p_no"));
				pvo.setP_name(rs.getString("p_name"));
				pvo.setP_before_price(rs.getInt("p_before_price"));
				pvo.setP_price(rs.getInt("p_price"));
				pvo.setP_img1(rs.getString("p_img1"));
				pvo.setP_img2(rs.getString("p_img2"));
				pvo.setP_class(rs.getString("p_class"));
				pvo.setP_category(rs.getString("p_category"));
				int rate=((rs.getInt("p_before_price") - rs.getInt("p_price")) *100 )/ rs.getInt("p_before_price");
				pvo.setP_rate(rate);
				
				plist.add(pvo);
			}
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {e.printStackTrace();}
		}
		return plist;
	}

	//New Item 가져오기
	public List<ProductVO> getNewItem() {
		
		List<ProductVO> nlist=new ArrayList<>();
		
		try {
			
			con=ds.getConnection();
			sql="select * from (select * from product_list order by p_no desc) where rownum <=8";
			pt=con.prepareStatement(sql);
			rs=pt.executeQuery();
			
			while(rs.next()) {
				//복수개의 레코드가 검색되는 경우는 while 반복문으로 반복
				ProductVO pv=new ProductVO();
				pv.setP_no(rs.getInt("p_no"));
				pv.setP_name(rs.getString("p_name"));
				pv.setP_before_price(rs.getInt("p_before_price"));
				pv.setP_price(rs.getInt("p_price"));
				pv.setP_amount(rs.getInt("p_amount"));
				pv.setP_sold(rs.getInt("p_sold"));
				pv.setP_img1(rs.getString("p_img1"));
				pv.setP_img2(rs.getString("p_img2"));
				pv.setP_choice(rs.getInt("p_choice"));
				pv.setP_class(rs.getString("p_class"));
				pv.setP_category(rs.getString("p_category"));
				pv.setP_date(rs.getString("p_date"));
				pv.setP_info(rs.getString("p_info"));
				pv.setP_color(rs.getString("p_color"));
				pv.setP_size(rs.getString("p_size"));
				
				int rate=((rs.getInt("p_before_price") - rs.getInt("p_price")) *100 )/ rs.getInt("p_before_price");
				pv.setP_rate(rate);
				nlist.add(pv);//컬렉션에 추가
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
		return nlist;
	}

	//Md's Choice 가져오기
	public List<ProductVO> getMdChoice() {
		
		List<ProductVO> mlist=new ArrayList<>();
		
		try {
			
			con=ds.getConnection();
			sql="select * from (select * from product_list where p_choice=1 order by p_no desc) where ROWNUM <= 8";
			pt=con.prepareStatement(sql);
			rs=pt.executeQuery();
			
			while(rs.next()) {
				//복수개의 레코드가 검색되는 경우는 while 반복문으로 반복
				ProductVO pv=new ProductVO();
				pv.setP_no(rs.getInt("p_no"));
				pv.setP_name(rs.getString("p_name"));
				pv.setP_before_price(rs.getInt("p_before_price"));
				pv.setP_price(rs.getInt("p_price"));
				pv.setP_amount(rs.getInt("p_amount"));
				pv.setP_sold(rs.getInt("p_sold"));
				pv.setP_img1(rs.getString("p_img1"));
				pv.setP_img2(rs.getString("p_img2"));
				pv.setP_choice(rs.getInt("p_choice"));
				pv.setP_class(rs.getString("p_class"));
				pv.setP_category(rs.getString("p_category"));
				pv.setP_date(rs.getString("p_date"));
				pv.setP_info(rs.getString("p_info"));
				pv.setP_color(rs.getString("p_color"));
				pv.setP_size(rs.getString("p_size"));
				
				int rate=((rs.getInt("p_before_price") - rs.getInt("p_price")) *100 )/ rs.getInt("p_before_price");
				pv.setP_rate(rate);
				mlist.add(pv);//컬렉션에 추가
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {e.printStackTrace();}
		}
		return mlist;
	}

}