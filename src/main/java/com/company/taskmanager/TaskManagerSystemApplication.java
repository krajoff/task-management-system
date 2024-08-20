package com.company.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска приложения Task Manager System.
 * Используется для инициализации и запуска Spring Boot приложения.
 */
@SpringBootApplication
public class TaskManagerSystemApplication {

    /**
     * Главный метод, запускающий приложение Task Manager System.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerSystemApplication.class, args);
    }

}
