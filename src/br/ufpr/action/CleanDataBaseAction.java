package br.ufpr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.ufpr.bo.CleanDataBaseBO;

public class CleanDataBaseAction extends BaseAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			new CleanDataBaseBO().cleanDataBase();
			addMessage(request, new ActionMessage("msg.0001"));
			return mapping.findForward("success");
		}
		catch(Exception e) {
			addMessage(request, new ActionMessage("msg.0002"));
			return mapping.findForward("success");
		}
	}
}