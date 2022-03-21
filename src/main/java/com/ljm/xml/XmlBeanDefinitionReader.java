package com.ljm.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.ljm.context.annotation.ClassPathBeanDefinitionScanner;
import com.ljm.factory.config.BeanDefinition;
import com.ljm.factory.config.BeanReference;
import com.ljm.factory.support.AbstractBeanDefinitionReader;
import com.ljm.factory.support.BeanDefinitionRegistry;
import com.ljm.io.Resource;
import com.ljm.io.ResourceLoader;
import com.ljm.props.PropertyValue;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author jmle
 * @Date 2022/2/11 17:09
 * @Version 1.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws IOException {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (ClassNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            try {
                loadBeanDefinitions(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void loadBeanDefinitions(String locations) {
        try {
            ResourceLoader resourceLoader = getResourceLoader();
            Resource resource = resourceLoader.getResource(locations);
            loadBeanDefinitions(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        Element componentScan = root.element("component-scan");
        if(null!=componentScan){
            String scanPath = componentScan.attributeValue("base-package");
            scanPackage(scanPath);
        }
        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod=bean.attributeValue("init-method");
            String destroyMethodName=bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");
            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            if(StrUtil.isNotEmpty(beanScope)){
                beanDefinition.setScope(beanScope);
            }
            List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList) {
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {

            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
            }
    }

    private void scanPackage(String scanPath){
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);

    }
}
