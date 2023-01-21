package com.example.recipe33.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class FirstController {

    @GetMapping
    public String launchApp() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String info() {
        return "Имя ученика: Рамиль "  + ", название проекта: " + getClass() +  ", дата создания проекта: " + LocalDate.of(2022, 12, 29) + "" + " ,описание проекта: название проекта - образец для Skypro.\n" +
                "функции проекта - сдача домашнего задания. Проект реализован с помощью фреймворка Spring и сборщика приложений Maven. Проект написан на языке Java. "  ;
    }



}
