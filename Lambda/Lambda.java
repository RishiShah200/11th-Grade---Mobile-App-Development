public class Lambda{
    public Lambda(){
        firstLambda = () -> System.out.println("First Lambda");
        
        doubleNumberFunction = (int a) -> a*2;  //with one line you dont need return statement or brackets

        addFunction = (int a, int b) -> a+b;

        safeDivideFcuntion = (int a, int b) -> {
            if(b == 0){
                return 0;
            }
            return a/b;
        };

        stringLengthCountFunction = (String s) -> s.length();
    }

    public static void main(String[] args){
        Lambda app = new Lambda();
    }
}