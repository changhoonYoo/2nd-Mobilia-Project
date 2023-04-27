package net.mobilia.vo;

public class QnaBoardVO {

	private int board_no;
	private String board_name;
	private String board_title;
	private String board_cont;
	private int board_hit;
	private int reply_hit;
	private String board_date;
	
	//페이징 관련변수
	private int startrow;//시작 번호
	private int endrow;//끝 번호
	
	//검색 기능 관련변수
	private String find_field; //검색필드
	private String find_name; //검색어
	
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_cont() {
		return board_cont;
	}
	public void setBoard_cont(String board_cont) {
		this.board_cont = board_cont;
	}
	public int getBoard_hit() {
		return board_hit;
	}
	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}
	public int getReply_hit() {
		return reply_hit;
	}
	public void setReply_hit(int reply_hit) {
		this.reply_hit = reply_hit;
	}
	public String getBoard_date() {
		return board_date.substring(0, 10);
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public int getEndrow() {
		return endrow;
	}
	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}
	public String getFind_field() {
		return find_field;
	}
	public void setFind_field(String find_field) {
		this.find_field = find_field;
	}
	public String getFind_name() {
		return find_name;
	}
	public void setFind_name(String fine_name) {
		this.find_name = fine_name;
	}
}
