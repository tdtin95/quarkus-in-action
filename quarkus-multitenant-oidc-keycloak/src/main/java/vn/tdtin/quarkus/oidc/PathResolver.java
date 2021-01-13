package vn.tdtin.quarkus.oidc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathResolver {
    public static String tenantRegex = "(http:\\/\\/|https:\\/\\/)*(.*:[0-9]*\\/api)(\\/tenants\\/)(\\w*)(\\/\\w*)*";
    public static final Pattern PATTERN = Pattern.compile(tenantRegex);

    public static String getTenant(String path) {
        Matcher matcher = PATTERN.matcher(path);
        if (matcher.find() && matcher.groupCount() > 4) {
            return matcher.group(4);
        }
        return null;
    }
}
