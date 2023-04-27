package net.mobilia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	public abstract ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}