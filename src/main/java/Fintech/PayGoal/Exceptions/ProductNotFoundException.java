package Fintech.PayGoal.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
