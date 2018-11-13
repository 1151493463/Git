package com.tarena.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class SpringContext {
	//此集合中存储的是xml中的数据，并把数据存储给此集合
	private Map<String,BeanDefinition> beanDefinitions = new HashMap<String,BeanDefinition>();
	//把实例化后的对象存储给此集合，模拟spring容器
	private Map<String,Object> singletons = new HashMap<String,Object>();
	//通过构造函数传递xml文件
	public SpringContext(String fileName){
		readXML(fileName);
		instanceObject();
		injectObject();
	}
	/**
	 * 此方法用来寻找bean节点中是否有property节点，如果有就取出name属性的值
	 * 第一个字母大写，加上set,拿组合完的字符串去类中寻找是否有set方法
	 * 如果有就调用并注入数据
	 */
	private void injectObject() {
		// TODO Auto-generated method stub
		for(BeanDefinition bd:beanDefinitions.values()){
			Object obj = singletons.get(bd.getId());
			Class clazz = obj.getClass();
			for(PropertyDefinition pd:bd.getProperties().values()){
				String name = pd.getName();
				String ref = pd.getRef();
				//userDao--setUserDao
				String setterName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1);
				Method[] methods = clazz.getDeclaredMethods();
				for(Method method:methods){
					if(setterName.equals(method.getName())){
						Object args = singletons.get(ref);
						try {
							method.invoke(obj, args);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	/**
	 * 解析beanDefinitions,取出class属性的值反射实例化对象
	 * 并把反射完的对象存储singletons
	 */
	private void instanceObject() {
		// TODO Auto-generated method stub
try {
		for(BeanDefinition bd:beanDefinitions.values()){
			String id = bd.getId();
			String clazz = bd.getClazz();
			Object obj = getClass().forName(clazz).newInstance();
			singletons.put(id, obj);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 此方法用来解析xml文件
	 * 把xml中的所有数据解析后存储给beanDefinitions
	 * @param fileName
	 */
	private void readXML(String fileName) {
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try{
			URL xmlPath = SpringContext.class.getClassLoader().getResource(fileName);
			document = saxReader.read(xmlPath);
			HashMap<String, String> nsMap = new HashMap<String,String>();
			/**
			 * 在解析带有命名空间的xml文件时，会出项无法获取节点数据的情况
			 * xmlns="http://www.springframework.org/schema/beans"
			 * xmlns xml namespace xml命名空间
			 * 标准写法：xmlns:namespace-prefix="namespaceURI"
			 * 默认写法即上：http://www.springframework.org/schema/beans 为xml文档的命名空间
			 */
			nsMap.put("ns", "http://www.springframework.org/schema/beans");
			XPath xsub = document.createXPath("//ns:beans/ns:bean");
			xsub.setNamespaceURIs(nsMap);
			//从xpath路径中解析出所有的bean节点
			List<Element> beans = xsub.selectNodes(document);
			for(Element element:beans){
				String id = element.attributeValue("id");
				String className = element.attributeValue("class");
				BeanDefinition beanDefinition = new BeanDefinition();
				beanDefinition.setId(id);
				beanDefinition.setClazz(className);
				//构建xpath路径，解析/beans/bean/property
				XPath propertySub = element.createXPath("ns:property");
				propertySub.setNamespaceURIs(nsMap);
				//开始解析property节点
				List<Element> properties = propertySub.selectNodes(element);
				for(Element property:properties){
					String name = property.attributeValue("name");
					String ref = property.attributeValue("ref");
					PropertyDefinition propertyDefinition = new PropertyDefinition();
					propertyDefinition.setName(name);
					propertyDefinition.setRef(ref);
					beanDefinition.getProperties().put(name, propertyDefinition);
				}
				beanDefinitions.put(id, beanDefinition);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Object getBean(String id){
		return singletons.get(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
