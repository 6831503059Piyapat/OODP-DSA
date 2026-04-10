package required;
import java.util.*;
import java.util.Scanner;

import Except.WrongChoiceException;
 //Method Required
public interface recompo {
    public void showAllUi();
    public void searchUi(Scanner key);
    public void ReturnMethod();
    public void BorrowUi();
    public void addUi() throws WrongChoiceException;

}
