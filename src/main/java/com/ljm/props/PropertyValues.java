package com.ljm.props;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author jmle
 * @Date 2022/2/10 16:11
 * @Version 1.0
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList= new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }
    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue propertyValue : this.propertyValueList) {
            if(propertyValue.getName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }

}
