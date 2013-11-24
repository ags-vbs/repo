package com.mycompany.vgtu.utils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import org.apache.wicket.Component;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

public class MyWicketMessages implements Serializable {
//FIX ME. This class should be checked and code improved. Nicer exceptions and so on. When no property found exception should be clear.

    private static final long serialVersionUID = 1L;
    private Component relativeComponentToPropertiesFile;
    private Class<?> relativeClazzToPropertiesFile;
    public static final String PROPERTIES_FILE_NAME_END = ".properties";

    private MyWicketMessages(Component relativeComponentToPropertiesFiled) {
        this.relativeComponentToPropertiesFile = relativeComponentToPropertiesFiled;
    }

    private MyWicketMessages(Class<?> clazz) {
        this.relativeClazzToPropertiesFile = clazz;
    }

    public static MyWicketMessages from(Component relativeComponentToPropertiesFiled) {
        return new MyWicketMessages(relativeComponentToPropertiesFiled);
    }

    public static MyWicketMessages from(Class<?> clazz) {
        return new MyWicketMessages(clazz);
    }

    public IModel<String> txtModel(String propertyKey) {
        if (relativeComponentToPropertiesFile == null) {
            try {
                return readPropertyFromClazzProperties(propertyKey);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to read property file for class: "
                        + relativeClazzToPropertiesFile.getName()
                        + " or property not found by key: "
                        + propertyKey);
            }
        } else {
            return readPropertyFromComponentProperties(propertyKey);
        }
    }

    public IModel<String> readPropertyFromComponentProperties(String propertyKey) {
        return new StringResourceModel(propertyKey, relativeComponentToPropertiesFile, null);
    }

    private IModel<String> readPropertyFromClazzProperties(final String propertyKey) throws Exception {
        final Properties p = new Properties();
        InputStream inputStream = MyWicketMessages.class.getResourceAsStream(
                classpathResourcePathWithoutExtension(relativeClazzToPropertiesFile) + PROPERTIES_FILE_NAME_END);
        p.load(inputStream);
        return new AbstractReadOnlyModel<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                return p.getProperty(propertyKey);
            }
        };
    }

    public String classpathResourcePathWithoutExtension(Class<?> clazz) {
        return "/" + clazz.getName().replace(".", "/");
    }
}
