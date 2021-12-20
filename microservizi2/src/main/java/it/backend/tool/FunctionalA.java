package it.backend.tool;

@FunctionalInterface
public interface FunctionalA {

    private  void method4(){
        System.out.println("Method 4");
    }

    default void  method1(){
        System.out.println("Metodo 1");
    }

    static void method2(){
        System.out.println("Metodo 2") ;
    }

    void method3();
}
