package ru.abenefic.spring.context;

import lombok.Cleanup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean("productService", ProductService.class);

        System.out.println("""
                SUPPORTED COMMAND FORMAT:
                /create {title} {cost}
                /get {id}
                /get_all
                /update {id} {title} {cost}
                /delete {id}
                /count
                /avg
                /exit""");

        @Cleanup Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        String[] command = input.split(" ");
        String cmd = command[0];
        while (!cmd.equals("/exit")) {
            try {
                switch (cmd) {
                    case "/create" -> {
                        if (command.length != 3) {
                            System.out.println("Incorrect input data");
                            continue;
                        }
                        productService.create(command[1], Float.parseFloat(command[2]));
                    }
                    case "/get" -> {
                        if (command.length != 2) {
                            System.out.println("Incorrect input data");
                            continue;
                        }
                        System.out.println(productService.get(Integer.parseInt(command[1])));
                    }
                    case "/get_all" -> {
                        for (Product product : productService.getAll()) {
                            System.out.println(product);
                        }
                    }
                    case "/update" -> {
                        if (command.length != 4) {
                            System.out.println("Incorrect input data");
                            continue;
                        }
                        productService.update(Integer.parseInt(command[1]), command[2], Float.parseFloat(command[3]));
                    }
                    case "/delete" -> {
                        if (command.length != 2) {
                            System.out.println("Incorrect input data");
                            continue;
                        }
                        productService.delete(Integer.parseInt(command[1]));
                    }
                    case "/count" -> System.out.println(productService.count());
                    case "/avg" -> System.out.printf("%.2f%n", productService.averageCost());
                    default -> System.out.println("Incorrect input data");
                }
                input = sc.nextLine();
                command = input.split(" ");
                cmd = command[0];
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        context.close();
    }
}
