package com.project.thein.vo;

import java.util.Date;

public class KakaoPayReadyVO {
	  public KakaoPayReadyVO(String tid, String next_redirect_pc_url, String next_redirect_mobile_url,
			String next_redirect_app_url, String android_app_scheme, String ios_app_scheme, boolean tms_result,
			Date created_at) {
		super();
		this.tid = tid;
		this.next_redirect_pc_url = next_redirect_pc_url;
		this.next_redirect_mobile_url = next_redirect_mobile_url;
		this.next_redirect_app_url = next_redirect_app_url;
		this.android_app_scheme = android_app_scheme;
		this.ios_app_scheme = ios_app_scheme;
		this.tms_result = tms_result;
		this.created_at = created_at;
	}
	public KakaoPayReadyVO() {
			super();
			// TODO Auto-generated constructor stub
		}
    private String tid, next_redirect_pc_url,next_redirect_mobile_url,next_redirect_app_url;
    private String android_app_scheme,ios_app_scheme;
    private boolean tms_result;
	private Date created_at;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getNext_redirect_pc_url() {
		return next_redirect_pc_url;
	}
	public void setNext_redirect_pc_url(String next_redirect_pc_url) {
		this.next_redirect_pc_url = next_redirect_pc_url;
	}
	public String getNext_redirect_mobile_url() {
		return next_redirect_mobile_url;
	}
	public void setNext_redirect_mobile_url(String next_redirect_mobile_url) {
		this.next_redirect_mobile_url = next_redirect_mobile_url;
	}
	public String getNext_redirect_app_url() {
		return next_redirect_app_url;
	}
	public void setNext_redirect_app_url(String next_redirect_app_url) {
		this.next_redirect_app_url = next_redirect_app_url;
	}
	public String getAndroid_app_scheme() {
		return android_app_scheme;
	}
	public void setAndroid_app_scheme(String android_app_scheme) {
		this.android_app_scheme = android_app_scheme;
	}
	public String getIos_app_scheme() {
		return ios_app_scheme;
	}
	public void setIos_app_scheme(String ios_app_scheme) {
		this.ios_app_scheme = ios_app_scheme;
	}
	public boolean isTms_result() {
		return tms_result;
	}
	public void setTms_result(boolean tms_result) {
		this.tms_result = tms_result;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	@Override
	public String toString() {
		return "KakaoPayReadyVO [tid=" + tid + ", next_redirect_pc_url=" + next_redirect_pc_url
				+ ", next_redirect_mobile_url=" + next_redirect_mobile_url + ", next_redirect_app_url="
				+ next_redirect_app_url + ", android_app_scheme=" + android_app_scheme + ", ios_app_scheme="
				+ ios_app_scheme + ", tms_result=" + tms_result + ", created_at=" + created_at + "]";
	}
	
}

