define(["dojo/_base/declare",
        "alfresco/core/Core",
        "alfresco/core/CoreXhr",
        "alfresco/core/NotificationUtils",
        "dojo/request/xhr",
        "dojo/json",
        "dojo/_base/lang",
        "dojo/dom",
        "dojo/dom-attr",
        "dojo/dom-class",
        "service/constants/Default"],
        function(declare, AlfCore, AlfXhr, NotificationUtils, xhr, JSON, lang, dom, domAttr, domClass, AlfConstants) {

   return declare([AlfCore, AlfXhr, NotificationUtils], {
     
     TOPIC_RESTORE_HOME_PAGE_CURRENT_SITE: "MANAGE_SITES_SITE_SERVICE_ALF_RESTORE_SITE_HOME_PAGE",
     
     constructor: function share_services_RestoreSiteHomePageService__constructor(args) {
        this.alfSubscribe(this.TOPIC_RESTORE_HOME_PAGE_CURRENT_SITE, lang.hitch(this, this.onRestoreCurrentSite));
        this.alfSubscribe(this.setSiteHomePageSuccessTopic, lang.hitch(this, this.onSetHomePageSuccess));
      },
      
      onRestoreCurrentSite:  function share_services_RestoreSiteHomePageService__onRestoreCurrentSite(publishPayload) {
    	  
         if (publishPayload) {
             
            this.serviceXhr({
               url: AlfConstants.PROXY_URI + "keensoft/site/home/restore/" + encodeURIComponent(publishPayload.site),
               method: "POST",
               data: {},
               successCallback: this.onSetHomePageSucess,
               callbackScope: this
            });            
            
            this.alfPublish(this.setSiteHomePageSuccessTopic, publishPayload);
            
         }
      },
      
      onSetHomePageSuccess:  function share_services_RestoreSiteHomePageService__onSetHomePageSuccess(publishPayload) {
          this.displayMessage(this.message("message.site.home.page.restore.ok"));
      },
      
  });
});