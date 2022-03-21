package com.ljm.factory;

import com.ljm.factory.config.BeanDefinition;
import com.ljm.factory.config.BeanFactoryPostProcess;
import com.ljm.io.DefaultResourceLoader;
import com.ljm.io.Resource;
import com.ljm.props.PropertyValue;
import com.ljm.props.PropertyValues;

import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

/**
 * @Author jmle
 * @Date 2022/3/17 13:46
 * @Version 1.0
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcess {

    public static final String DEFAULT_PLACEHOLDER_PREFIX="${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX="}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                Object value = propertyValue.getValue();
                if(!(value instanceof String)){
                    continue;
                }
                String strVal= (String) value;
                StringBuilder buffer = new StringBuilder(strVal);
                int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                int stopIdx=strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                if(startIdx!=-1 && stopIdx!=-1 && startIdx<stopIdx){
                    String propKey=strVal.substring(startIdx+2,stopIdx);
                    String propVal=properties.getProperty(propKey);
                    buffer.replace(startIdx,stopIdx+1,propVal);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(),buffer.toString()));
                }
            }
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
