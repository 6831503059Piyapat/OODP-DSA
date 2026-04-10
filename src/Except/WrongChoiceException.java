package Except;

public class WrongChoiceException extends Exception{
   public WrongChoiceException(String message){super(message);}
   public WrongChoiceException(Throwable cause){super(cause);}
   public WrongChoiceException(String message,Throwable cause){super(message,cause);}
}
