import java.util.Scanner;
/**
 * Normal polish notation calculator.
 * Program reads from standard input an NPN formula
 * and performs a calculation.
 * Then outputs the answer as an integer.
 * Implemented using recursive stack.
 *
 * @author Daniel Bielech
 * @version 20.02.2020
 */
public class Npn
{
    public static class MyStack
    {

        private Node top; // Top of the stack.

        public MyStack()
        {
            top = null;
        }

        public void push(int value)
        {
            top = new Node(value, top);
        }

        public int pop()
        {
            if(top != null) {
                int value = top.getValue();
                top = top.getNext();
                return value;
            }
            else {
                throw new IllegalStateException("the stack is empty");
            }
        }

        public boolean isEmpty()
        {
            return top == null;
        }

        private class Node
        {
            private int value;
            private Node next;

            public Node(int value, Node oldTop)
            {
                this.value = value;
                this.next = oldTop;
            }

            int getValue()
            {
                return value;
            }

            Node getNext()
            {
                return next;
            }
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
        Scanner input = new Scanner(System.in);
        Npn.MyStack stack = new Npn.MyStack();

        String formula = input.nextLine();
        String[] words = formula.trim().split("[ ,.;:?]+");

        for(int i = words.length-1; i >= 0; i--){
            if (isNumber(words[i])){
                stack.push(Integer.parseInt(words[i]));
            }
            else{
                switch(words[i]){
                    case "+": stack.push(stack.pop() + stack.pop());
                        break;
                    case "-": stack.push(stack.pop() - stack.pop());
                        break;
                    case "x": stack.push(stack.pop() * stack.pop());
                        break;
                    default: System.out.println("Unknown operator");
                }
            }
        }

        System.out.println(stack.pop());
    }
}