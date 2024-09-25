import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        getMetodsOfString();


    }
    public static void getMetodsOfString(){
        Class<String> classOfString = String.class;
        Method[] methods = classOfString.getDeclaredMethods();
        for (Method item:methods) {
            System.out.println(item.getName());
        }
    }
}