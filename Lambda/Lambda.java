public class Lambda{
    public Lambda(){
        MyLambda firstLambda = () -> System.out.println("First Lambda"); //with one line you dont need return statement or brackets
        
        MyAdd addFunction = (int a, int b) -> a+b;

        addFunction.add(1, 2);

        firstLambda.foo();
    }

    public static void main(String[] args){
        Lambda app = new Lambda();
    }
}

interface MyLambda{     //tells what the return type is to the java compiler
    void foo();
}

interface MyAdd{
    int add(int a, int b);
}