package es.keensoft.alfresco.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteUtil {
	
	private static final String REGEX_SITE_NAME = "\\/site\\/(.*)\\/(.*)";
    public static String getSiteName(String shareUrl) {
        Pattern p1 = Pattern.compile(REGEX_SITE_NAME);
        Matcher m1 = p1.matcher(shareUrl);
        if (m1.find()) {
            return m1.group(1);
        } else {
            return null;
        }
    }

}
