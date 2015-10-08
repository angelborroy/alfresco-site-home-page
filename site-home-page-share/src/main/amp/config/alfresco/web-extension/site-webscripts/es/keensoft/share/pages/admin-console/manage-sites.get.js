var dropDownMenuConfig = widgetUtils.findObject(model.jsonModel, "name", "alfresco/menus/AlfMenuGroup");

if (dropDownMenuConfig != null) {
	
    model.jsonModel.services.push("sitehomepage/RestoreSiteHomePageService");
	
	dropDownMenuConfig.config.widgets.push({
        name: "alfresco/menus/AlfMenuItem",
        config: {
           label: msg.get("button.site.home.page.restore"),
           iconClass: "alf-fullscreen-icon",
           publishTopic: "ALF_RESTORE_SITE_HOME_PAGE",
           publishPayloadType: "BUILD",
           publishPayload: {
              site: {
                 alfType: "item",
                 alfProperty: "shortName"
              }
           }
        }
    });
}
