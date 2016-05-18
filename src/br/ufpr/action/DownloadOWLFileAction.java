package br.ufpr.action;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.ufpr.bo.DownloadOWLFileBO;

public class DownloadOWLFileAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			StringBuffer file = new DownloadOWLFileBO().downloadOWLFile();
			String fileName = "arquivo.owl";
			
			byte[] bytes = file.toString().getBytes();
			
			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			
			return null;
		}
		catch(Exception e) {
			addMessage(request, new ActionMessage("msg.0002"));
			return mapping.findForward("success");
		}
	}
}