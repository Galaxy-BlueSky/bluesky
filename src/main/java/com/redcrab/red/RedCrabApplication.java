package com.redcrab.red;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RedCrabApplication {

public static void main(String[] args) {

  SpringApplication.run(RedCrabApplication.class, args);

  System.out.println("Hello World");

  List<String> booklist = initializeBookList();

  printBookList(booklist);

  // 구구단 계산식

}

private static List<String> initializeBookList() {

  List<String> books = new ArrayList<>();

  books.add("book1");
  books.add("book2");
  books.add("book3");
  books.add("book4");
  books.add("book5");
  books.add("book6");

  return books;

}

private static void printBookList(List<String> list) {

  System.out.println(list);

}

}
