import java.util.Scanner;

public class PasswordStrengthAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("   Password Strength Analyzer");
        System.out.println("=================================");
        System.out.println("This tool checks password strength locally.");
        System.out.println("Your password is not saved or stored.");
        System.out.println();

        System.out.print("Enter a password to analyze: ");
        String password = scanner.nextLine();

        analyzePassword(password);

        scanner.close();
    }

    public static void analyzePassword(String password) {
        int score = 0;

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSymbol = false;

        for (int i = 0; i < password.length(); i++) {
            char currentChar = password.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(currentChar)) {
                hasLowercase = true;
            } else if (Character.isDigit(currentChar)) {
                hasNumber = true;
            } else {
                hasSymbol = true;
            }
        }

        System.out.println();
        System.out.println("Password Analysis:");
        System.out.println("------------------");

        if (password.length() >= 12) {
            score += 25;
            System.out.println("[+] Good length");
        } else if (password.length() >= 8) {
            score += 15;
            System.out.println("[~] Decent length, but 12+ characters is better");
        } else {
            System.out.println("[-] Password is too short");
        }

        if (hasUppercase) {
            score += 15;
            System.out.println("[+] Contains uppercase letters");
        } else {
            System.out.println("[-] Missing uppercase letters");
        }

        if (hasLowercase) {
            score += 15;
            System.out.println("[+] Contains lowercase letters");
        } else {
            System.out.println("[-] Missing lowercase letters");
        }

        if (hasNumber) {
            score += 15;
            System.out.println("[+] Contains numbers");
        } else {
            System.out.println("[-] Missing numbers");
        }

        if (hasSymbol) {
            score += 20;
            System.out.println("[+] Contains symbols");
        } else {
            System.out.println("[-] Missing symbols");
        }

        if (isCommonPassword(password)) {
            score -= 30;
            System.out.println("[-] Password is commonly used and unsafe");
        }

        if (hasRepeatedCharacters(password)) {
            score -= 15;
            System.out.println("[-] Password contains repeated characters");
        }

        if (isNumbersOnly(password)) {
            score -= 20;
            System.out.println("[-] Password only contains numbers");
        }

        if (score < 0) {
            score = 0;
        }

        if (score > 100) {
            score = 100;
        }

        System.out.println();
        System.out.println("Final Score: " + score + "/100");
        System.out.println("Strength Rating: " + getStrengthRating(score));

        System.out.println();
        giveSuggestions(password, hasUppercase, hasLowercase, hasNumber, hasSymbol);
    }

    public static boolean isCommonPassword(String password) {
        String[] commonPasswords = {
                "password",
                "password123",
                "123456",
                "12345678",
                "qwerty",
                "abc123",
                "admin",
                "letmein",
                "welcome",
                "iloveyou"
        };

        String lowerPassword = password.toLowerCase();

        for (String common : commonPasswords) {
            if (lowerPassword.equals(common)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasRepeatedCharacters(String password) {
        int repeatCount = 1;

        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                repeatCount++;

                if (repeatCount >= 3) {
                    return true;
                }
            } else {
                repeatCount = 1;
            }
        }

        return false;
    }

    public static boolean isNumbersOnly(String password) {
        if (password.length() == 0) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            if (!Character.isDigit(password.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String getStrengthRating(int score) {
        if (score >= 85) {
            return "Very Strong";
        } else if (score >= 65) {
            return "Strong";
        } else if (score >= 40) {
            return "Medium";
        } else {
            return "Weak";
        }
    }

    public static void giveSuggestions(String password, boolean hasUppercase, boolean hasLowercase,
                                       boolean hasNumber, boolean hasSymbol) {
        System.out.println("Improvement Suggestions:");
        System.out.println("------------------------");

        boolean needsSuggestion = false;

        if (password.length() < 12) {
            System.out.println("- Use at least 12 characters.");
            needsSuggestion = true;
        }

        if (!hasUppercase) {
            System.out.println("- Add uppercase letters.");
            needsSuggestion = true;
        }

        if (!hasLowercase) {
            System.out.println("- Add lowercase letters.");
            needsSuggestion = true;
        }

        if (!hasNumber) {
            System.out.println("- Add numbers.");
            needsSuggestion = true;
        }

        if (!hasSymbol) {
            System.out.println("- Add symbols like !, @, #, $, or %.");
            needsSuggestion = true;
        }

        if (isCommonPassword(password)) {
            System.out.println("- Avoid common passwords like password123 or qwerty.");
            needsSuggestion = true;
        }

        if (hasRepeatedCharacters(password)) {
            System.out.println("- Avoid repeated characters like aaa or 111.");
            needsSuggestion = true;
        }

        if (!needsSuggestion) {
            System.out.println("- Great password structure. Keep using unique passwords for each account.");
        }
    }
}