package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"controllers"})
public class Application {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Application.class, args);
        System.out.println("Lets inspect the beans provided by Spring Boot");

        String[] beanDefinitionNames = app.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames);

        for (String bean : beanDefinitionNames) {
            System.out.println(bean);
        }
    }
}
