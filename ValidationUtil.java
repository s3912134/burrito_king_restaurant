package util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("^[0-9]{16}$");
    private static final Pattern CVV_PATTERN = Pattern.compile("^[0-9]{3,4}$");

    public static boolean isValidCreditCard(String cardNumber) {
        return CREDIT_CARD_PATTERN.matcher(cardNumber).matches();
    }

    public static boolean isFutureDate(String expiryDate) {
        // Simple validation to check if expiry date is in the future
        // Assuming the format of expiryDate is "MM/YY"
        String[] parts = expiryDate.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]) + 2000;

        // Assuming current date is June 2024 for simplicity
        int currentMonth = 6;
        int currentYear = 2024;

        if (year > currentYear || (year == currentYear && month > currentMonth)) {
            return true;
        }
        return false;
    }

    public static boolean isValidCVV(String cvv) {
        return CVV_PATTERN.matcher(cvv).matches();
    }
}
