package br.ufpr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.ufpr.bean.Database;
import br.ufpr.bo.RdbToOntoBO;
import br.ufpr.form.RdbToOntoForm;

public class ProcessRdbToOntoAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RdbToOntoForm rdbToOntoForm = (RdbToOntoForm) form;
		Database database = new RdbToOntoBO().importFile(rdbToOntoForm);
		
		if (database == null) {
			addMessage(request, new ActionMessage("msg.0002"));
		}
		else {
			addMessage(request, new ActionMessage("msg.0001"));
		}
		
		return mapping.findForward("success");
	}
}