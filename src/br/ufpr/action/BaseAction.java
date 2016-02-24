package br.ufpr.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public abstract class BaseAction extends Action {
	
	protected static final String SUCCESS = "success";
	protected static final String FAILURE = "failure";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.ENGLISH);
		
		response.setHeader("Cache-Control", "no-store");
		response.setContentType("text/html; charset=UTF-8");
		
		return execute(mapping, form, request, response);
	}
	
	protected void addMessage(HttpServletRequest request, ActionMessage msg){
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
		saveErrors(request, messages);
	}
	
	protected void addMessage(HttpServletRequest request, String key) {
		addMessage(request, new ActionMessage(key,true));
	}

}
