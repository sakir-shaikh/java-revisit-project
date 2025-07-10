package util;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= 2;
    }

    public static boolean isValidJobTitle(String title) {
        return title != null && title.trim().length() >= 3;
    }

    public static boolean isValidJobDescription(String description) {
        return description != null && description.trim().length() >= 10;
    }

    public static boolean isValidRating(int rating) {
        return rating >= 1 && rating <= 5;
    }

    public static boolean isValidId(Long id) {
        return id != null && id > 0;
    }

    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        return input.trim();
    }
} 