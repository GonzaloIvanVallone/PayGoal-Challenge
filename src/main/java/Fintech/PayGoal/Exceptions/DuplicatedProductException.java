package Fintech.PayGoal.Exceptions;

public class DuplicatedProductException extends RuntimeException{
    public DuplicatedProductException(String msg) {
        super(msg);
    }
}
