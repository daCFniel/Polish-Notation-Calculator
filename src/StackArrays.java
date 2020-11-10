import java.util.Scanner;
/**
 * Normal polish notation calculator.
 * Program reads from standard input an NPN formula
 * and performs a calculation.
 * Then outputs the answer as an integer.
 * Implemented using stack based on arrays.
 *
 * @author Daniel Bielech
 * @version 20.02.2020
 */
public class StackArrays
{
    /**
     * Own stack implemented using Array
     */
    public static class MyStack
    {
        int top;
        int[] stackArray;

        public MyStack(int capacity)
        {
            top = -1;
            stackArray = new int[capacity];
        }

        public void push(int number)
        {
            stackArray[++top] = number;
        }

        public int pop()
        {
            return stackArray[top--];
        }

        public boolean isEmpty()
        {
            return (top == -1);
        }

    }

    public static boolean isNumber(String word)
    {
        boolean number = true;
        number = word.matches("-?\\d+(\\.\\d+)?");
        if(number){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args)
    {
        long before = System.currentTimeMillis();

        Scanner input = new Scanner(System.in);
        StackArrays.MyStack stack = new StackArrays.MyStack(100000);

        String formula = input.nextLine();
        String[] words = formula.trim().split("[ ,.;:?]+");

        for(int i = words.length-1; i >= 0; i--){
            if (isNumber(words[i])){
                stack.push(Integer.parseInt(words[i]));
            }
            else if (words[i].equals("+")){
                stack.push(stack.pop() + stack.pop());
            }
            else if(words[i].equals("-")){
                stack.push(stack.pop() - stack.pop());
            }
            else if(words[i].equals("x")){
                stack.push(stack.pop() * stack.pop());
            }
        }

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        long after = System.currentTimeMillis();
        System.out.println("Code executed in: "+(after-before));
    }
}