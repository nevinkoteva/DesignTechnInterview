package com.clippings.test.base;

import com.pholser.util.properties.PropertyBinder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertNotNull;

public class DataLoader {

    /**
     * Reads from the specified properties file and returns the corresponding object.
     *
     * @param propertyFileName - the location of the file to be read (includes the name)
     * @param inExternalFile   - denotes whether the file is external (not added into the classpath) or
     *                         internal (in the classpath)
     * @param outputClazz      - the type of the final results to be returned
     * @return the fetched data
     */
    public static <T> T fetchDataFromPropertiesFile(String propertyFileName, boolean inExternalFile,
                                                    Class<T> outputClazz) {
        T data = null;
        assertNotNull("No property present to fetch data from ", propertyFileName);
        assertNotNull("No output class present to fetch data", outputClazz);

        if (inExternalFile) {
            data = readPropertiesFromFile(propertyFileName, outputClazz, data);
        } else {
            data = readPropertiesFromClassPath(propertyFileName, outputClazz, data);
        }
        return data;
    }

    private static <T> T readPropertiesFromFile(String filePath, Class<T> outputClazz, T data) {
        try (InputStream propertiesStream = FileUtils.openInputStream(new File(filePath))) {
            data = readDataFromStream(filePath, outputClazz, propertiesStream);
        } catch (IOException e) {
            fail(String.format(
                    "Error occurred while fetching data from external properties file %s with output class %s",
                    filePath,
                    outputClazz), e);
        }
        return data;
    }


    private static <T> T readPropertiesFromClassPath(String propertyFileName, Class<T> outputClazz,
                                                     T data) {
        try (InputStream propertiesStream = DataLoader.class.getClassLoader()
                .getResourceAsStream(propertyFileName + ".properties")) {
            data = readDataFromStream(propertyFileName, outputClazz, propertiesStream);
        } catch (IOException e) {
            fail(String.format(
                    "Error occurred while fetching data from classpath properties file %s with output class %s",
                    propertyFileName, outputClazz), e);
        }
        return data;
    }

    private static <T> T readDataFromStream(String fileLocation, Class<T> outputClazz,
                                            InputStream propertiesStream)
            throws IOException {
        assertNotNull("No Properties for " + fileLocation + " found", propertiesStream);
        PropertyBinder<T> binder = PropertyBinder.forType(outputClazz);
        return binder.bind(propertiesStream);
    }

}