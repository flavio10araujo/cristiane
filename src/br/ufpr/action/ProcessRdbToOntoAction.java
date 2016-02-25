package br.ufpr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.ufpr.bo.RdbToOntoBO;
import br.ufpr.form.RdbToOntoForm;

public class ProcessRdbToOntoAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RdbToOntoForm rdbToOntoForm = (RdbToOntoForm) form;
		new RdbToOntoBO().importFile(rdbToOntoForm);
		return null;
	}
}