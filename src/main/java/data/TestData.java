package data;

public class TestData {
    public static final String USERNAME = "tuteiko.liudmyla@gmail.com";
    public static final String PASSWORD = "12345";


    public static final String CATALOGUE_PAGE_URL = "http://automationpractice.com/index.php";
    public static final String SIGNIN_PAGE_URL = CATALOGUE_PAGE_URL + "?controller=authentication";
    public static final String MY_ACCOUNT_PAGE = CATALOGUE_PAGE_URL + "?controller=my-account";
    public static final String CART_PAGE = CATALOGUE_PAGE_URL + "?controller=order";


    public static final String ERROR_INVALID_PASS = "Invalid password.";
    public static final String ERROR_INVALID_EMAIL = "Invalid email address.";
    public static final String ERROR_EMPTY_EMAIL = "An email address required.";
    public static final String ERROR_EXISTING_EMAIL = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    public static final String EMPTY_CART_MESSAGE = "Your shopping cart is empty.";
    public static final String SUCCESS_ADDED_TO_CART_MESSAGE = "Product successfully added to your shopping cart";

}
