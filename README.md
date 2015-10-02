Alfresco: Site Home Page (plus)
===============================
From Alfresco 5.1 EE, a user can define the start page after login to Share.

This addon provides an extension to use any arbitrary page as home for every Site (instead of Dashboard page). Site Administrator can define the home page for the Site similarly to related start page definition introduced by Alfresco 5.1 EE.

![alfresco-site-home-page-demo](https://cloud.githubusercontent.com/assets/1818300/10258849/6303c5e4-6962-11e5-997d-a0311d7035f5.png)

Current addon status is ***BETA***.

The plugin is licensed under the [LGPL v3.0](http://www.gnu.org/licenses/lgpl-3.0.html). The current version is 1.0.0, which has been developed using Alfresco 5.1-EA, although it should run in Alfresco 5.0.d.

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
