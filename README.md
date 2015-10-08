Alfresco: Site Home Page (plus)
===============================
From Alfresco 5.1 EE, a user can define the start page after login to Share.

This addon provides an extension to use any arbitrary page as home for every Site (instead of Dashboard page). 
Site Administrator can define the home page for the Site similarly to related start page definition introduced by Alfresco 5.1 EE.
Alfresco Administrator can restore home page for every site to Site Dashboard using Site Manager page at Admin Tools menu.

![alfresco-site-home-page-demo](https://youtu.be/QJnRbZG-TPw)

***License***
The plugin is licensed under the [LGPL v3.0](http://www.gnu.org/licenses/lgpl-3.0.html). 

***State***
Current addon release 1.0.0 is ***BETA***

**Compatibility**
The current version has been developed using Alfresco 5.1-EA and Alfresco SDK 2.1, although it should run in Alfresco 5.0.d and Alfresco 5.0.c
Browser compatibility: 95% supported (refer http://caniuse.com/#feat=atob-btoa)

**Languages**
Currently only provided in English

**No original Alfresco resources have been overwritten**

Downloading the ready-to-deploy-plugin
--------------------------------------
The binary distribution is made of one amp file to be deployed in Share:

* [repo AMP](https://github.com/angelborroy/alfresco-site-home-page/releases/download/1.0.0/site-home-page-repo.amp)
* [share AMP](https://github.com/angelborroy/alfresco-site-home-page/releases/download/1.0.0/site-home-page-share.amp)

You can install them by using standard [Alfresco deployment tools](http://docs.alfresco.com/community/tasks/dev-extensions-tutorials-simple-module-install-amp.html)

Building the artifacts
----------------------
If you are new to Alfresco and the Alfresco Maven SDK, you should start by reading [Jeff Potts' tutorial on the subject](http://ecmarchitect.com/alfresco-developer-series-tutorials/maven-sdk/tutorial/tutorial.html).

You can build the artifacts from source code using maven
```$ mvn clean package```
