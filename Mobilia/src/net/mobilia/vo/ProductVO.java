package net.mobilia.vo;

public class ProductVO {
	private int p_no;
	private String p_name;
	private int p_before_price;
	private int p_price;
	private int p_amount;
	private int p_sold;
	private String p_img1;
	private String p_img2;
	private int p_choice;
	private String p_class;
	private String p_category;
	private String p_date;
	private String p_info;
	private String p_color;
	private String p_size;
	
	
	private int p_rate; //할인률
	
	
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_before_price() {
		return p_before_price;
	}
	public void setP_before_price(int p_before_price) {
		this.p_before_price = p_before_price;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_amount() {
		return p_amount;
	}
	public void setP_amount(int p_amount) {
		this.p_amount = p_amount;
	}
	public int getP_sold() {
		return p_sold;
	}
	public void setP_sold(int p_sold) {
		this.p_sold = p_sold;
	}
	public String getP_img1() {
		return p_img1;
	}
	public void setP_img1(String p_img1) {
		this.p_img1 = p_img1;
	}
	public String getP_img2() {
		return p_img2;
	}
	public void setP_img2(String p_img2) {
		this.p_img2 = p_img2;
	}
	public int getP_choice() {
		return p_choice;
	}
	public void setP_choice(int p_choice) {
		this.p_choice = p_choice;
	}
	public String getP_class() {
		return p_class;
	}
	public void setP_class(String p_class) {
		this.p_class = p_class;
	}
	public String getP_category() {
		return p_category;
	}
	public void setP_category(String p_category) {
		this.p_category = p_category;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date.substring(0, 10);
	}
	public String getP_info() {
		return p_info;
	}
	public void setP_info(String p_info) {
		this.p_info = p_info;
	}
	public String getP_color() {
		return p_color;
	}
	public void setP_color(String p_color) {
		this.p_color = p_color;
	}
	public String getP_size() {
		return p_size;
	}
	public void setP_size(String p_size) {
		this.p_size = p_size;
	}
	public int getP_rate() {
		return p_rate;
	}
	public void setP_rate(int p_rate) {
		this.p_rate = p_rate;
	}
}
