define(["dojo/_base/declare",
        "alfresco/core/Core",
        "alfresco/core/CoreXhr",
        "dojo/request/xhr",
        "dojo/json",
        "dojo/_base/lang",
        "dojo/dom",
        "dojo/dom-attr",
        "dojo/dom-class",
        "service/constants/Default"],
        function(declare, AlfCore, AlfXhr, xhr, JSON, lang, dom, domAttr, domClass, AlfConstants) {

   return declare([AlfCore, AlfXhr], {
     
     TOPIC_SET_CURRENT_PAGE_AS_SITE_HOME: "ALF_SET_CURRENT_PAGE_AS_SITE_HOME",
     
     _lastSeenHomePageRequest: null,
     
     constructor: function share_services_SiteHomePageService__constructor(args) {
        this.alfSubscribe(this.TOPIC_SET_CURRENT_PAGE_AS_SITE_HOME, lang.hitch(this, this.onSetCurrentPageAsHome));
        this.alfSubscribe(this.setSiteHomePageTopic, lang.hitch(this, this.onSetHomePage));
        this.alfSubscribe(this.setSiteHomePageSuccessTopic, lang.hitch(this, this.onSetHomePageSuccess));
        this.alfSubscribe(this.setSiteHomePageFailureTopic, lang.hitch(this, this.onSetHomePageFailure));
      },
      
      onSetCurrentPageAsHome:  function share_services_SiteHomePageService__onSetCurrentPageAsHome(publishPayload) {
         if (publishPayload && publishPayload.servletContext) {
             
            var currentPage = window.location.href;
            currentPage = currentPage.replace(window.location.origin + publishPayload.servletContext, "/page");
            
            this.serviceXhr({
               url: AlfConstants.PROXY_URI + "keensoft/site/home/" + currentPage,
               method: "POST",
               data: {},
               successCallback: this.onSetHomePageSucess,
               callbackScope: this
            });            
            
           this.alfPublish(this.setSiteHomePageTopic, { homePage: currentPage });
           
         }
      },
      
      onSetHomePage:  function share_services_SiteHomePageService__onSetHomePage(publishPayload) {
         if (publishPayload && publishPayload.homePage) {
            this._lastSeenHomePageRequest = publishPayload.homePage;
         }
      },
      
      onSetHomePageSuccess:  function share_services_SiteHomePageService__onSetHomePageSuccess(publishPayload) {
         if (this._lastSeenHomePageRequest)
         {
            var homeLinkParent = dom.byId("HEADER_HOME_text");
            if (homeLinkParent) {
               location.reload();
            }
         }
      },
      
      onSetHomePageFailure:  function share_services_SiteHomePageService__onSetHomePageFailure(publishPayload) {
         this._lastSeenHomePageRequest = null;
      }
  });
});