package com.clippings.test;
import com.pholser.util.properties.BoundProperty;

public interface ClippingsAppConfig {

    @BoundProperty("base_url")
    String getBaseUrl();

}
