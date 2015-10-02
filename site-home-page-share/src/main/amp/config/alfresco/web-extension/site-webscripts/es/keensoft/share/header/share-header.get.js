var siteConfig = widgetUtils.findObject(model.jsonModel, "id", "HEADER_SITE_CONFIGURATION_DROPDOWN");

if (siteConfig != null) {
	
    var userIsSiteManager = false, obj = null;
    json = remote.call("/api/sites/" + page.url.templateArgs.site + "/memberships/" + encodeURIComponent(user.name));
    if (json.status == 200) {
       obj = JSON.parse(json);
    }
    if (obj) {
       userIsSiteManager = obj.role == "SiteManager";
    }

    if (userIsSiteManager) {
    	
    	model.jsonModel.services.push("sitehomepage/SiteHomePageService");
    	
		siteConfig.config.widgets.push({
			
			id : "HEADER_SITE_MENU_HOME_PAGE_GROUP",
			name : "alfresco/menus/AlfMenuGroup",
			config :
			{
				label: "group.homepage.label",
				widgets:
				[
				    {
						id : "HEADER_SITE_MENU_HOME_PAGE",
						name : "alfresco/menus/AlfMenuItem",
						config : {
							id : "HEADER_SITE_MENU_SET_CURRENT_PAGE_AS_HOME",
							label : "link.setCurrentPageAsHome",
							title: "link.setCurrentPageAsHome",
				            iconAltText: "link.setCurrentPageAsHome",
							iconClass: "alf-edit-icon",
		                    publishTopic: "ALF_SET_CURRENT_PAGE_AS_SITE_HOME",
		                    publishPayload: {
		                       servletContext: page.url.servletContext
		                    }
						}
				    }
		        ]
			}
		
		});
		
    }
    
}