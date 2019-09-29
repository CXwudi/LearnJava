/**
 * 
 */
/**
 * @author CX无敌
 * You don't need to create the module by yourself, you can use Eclipse's right-click project menu to configure 
 */
module LearnJava {
	//exports main.java.com.cxwudi;//so others project that import this project can use class in this folder
	requires java.logging;
	requires javafx.base; //currently unavaliable by using User Library, don't know the reason
}