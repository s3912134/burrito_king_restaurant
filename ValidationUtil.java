package util;

public class ValidationUtil {
    public static boolean isValidCreditCard(String cardNumber) {
        return cardNumber.matches("\\d{16}");
    }

    public static boolean isFutureDate(String expiryDate) {
      
        return true; 
    }

    public static boolean isValidCVV(String cvv) {
        return cvv.matches("\\d{3}");
    }
}
