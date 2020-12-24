package com.andavid.java.demo;

public class Demo {
  public static void main(String[] args) {
    System.out.println("Aa".hashCode() == "BB".hashCode());
    System.out.println(System.identityHashCode("Aa") == System.identityHashCode("BB"));
  }
}
