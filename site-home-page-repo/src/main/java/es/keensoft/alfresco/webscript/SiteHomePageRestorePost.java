package es.keensoft.alfresco.webscript;

import java.io.IOException;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.site.SiteService;
import org.alfresco.service.namespace.QName;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.springframework.http.MediaType;

public class SiteHomePageRestorePost extends AbstractWebScript {
    
    private NodeService nodeService;
    private SiteService siteService;

    @SuppressWarnings("unchecked")
    @Override
    public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
        
        try {
        	
            String siteName = req.getExtensionPath();
            
            if (siteName == null || siteName.equals("")) {
                throw new WebScriptException("URL from a site is required!");
            }
            
            NodeRef siteNodeRef = siteService.getSite(siteName).getNodeRef();
            nodeService.removeProperty(siteNodeRef, QName.createQName("homePagePath"));
    
            JSONObject objProcess = new JSONObject();
            objProcess.put("result", "ok");
            
            String jsonString = objProcess.toString();
            res.setContentType(MediaType.APPLICATION_JSON.toString());
            res.setContentEncoding("UTF-8");
            res.getWriter().write(jsonString);
            
        } catch (Exception e) {
            throw new IOException(e);
        }
                
    }
    
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }
    
}