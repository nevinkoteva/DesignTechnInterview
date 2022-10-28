package com.clippings.test.base;

import com.clippings.test.ClippingsAppConfig;
import gherkin.StringUtils;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String ENVIRONMENT_KEY = "environment";
    private static final String PROP_FILE_KEY = "fileName";

    public static ClippingsAppConfig getConfiguration() {
        String environmentPropertiesPath = createPath(ENVIRONMENT_KEY, getCurrentEnvironmentName(),
                getPropsFile());
        return DataLoader.fetchDataFromPropertiesFile(environmentPropertiesPath, false,
                ClippingsAppConfig.class);
    }

    public static String getCurrentEnvironmentName() {
        return getProgramArgument(ENVIRONMENT_KEY);
    }

    public static String getPropsFile() {
        return getProgramArgument(PROP_FILE_KEY);
    }

    /**
     * Gets the value of the program argument by the provided key.
     *
     * @return t null)
     */
    private static String getProgramArgument(String argumentKey) {
        // logger.debug("Getting program argument with key {}", argumentKey);
        String data = System.getProperty(argumentKey);
        Asserts.notNull(data, String.format("The program argument '%s' is missing", argumentKey));
        return data;
    }

    /**
     * Creates paths by provided parts. The separator is the current file system separator.
     *
     * @param strings varargs parameter to provide the parts of the path
     * @return the whole path, concatenated with the current file system separator
     */
    public static String createPath(String... strings) {
        return StringUtils.join(File.separator, Arrays.asList(strings));
    }

}
