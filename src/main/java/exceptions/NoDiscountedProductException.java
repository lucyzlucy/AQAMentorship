package exceptions;

public class NoDiscountedProductException extends Exception {
    @Override
    public String getMessage() {
        return "There is no discounted product";
    }
}
