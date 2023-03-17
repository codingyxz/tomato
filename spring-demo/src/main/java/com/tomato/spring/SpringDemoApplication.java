package com.tomato.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;

@SpringBootApplication
//@PropertySource(value = "classpath:test.properties")
public class SpringDemoApplication {

    public static void main(String[] args) {


//        AnnotationMetadata introspect = AnnotationMetadata.introspect(SpringDemoApplication.class);
//
//        System.out.println("ClassName:  " + introspect.getClassName());
//        System.out.println("SuperClassName:  " + introspect.getSuperClassName());
//        System.out.println("EnclosingClassName:  " + introspect.getEnclosingClassName());
//        System.out.println("Class:  " + introspect.getClass());
//
//        for (String annotationType : introspect.getAnnotationTypes()) {
//            System.out.println("AnnotationType: " + annotationType);
//        }
//
//        for (String interfaceName : introspect.getInterfaceNames()) {
//            System.out.println("InterfaceName:  " + interfaceName);
//        }
//
//        for (MergedAnnotation<Annotation> annotation : introspect.getAnnotations()) {
//            System.out.println("MergedAnnotation:" + annotation);
//        }

        SpringApplication application = new SpringApplication(SpringDemoApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

}
