import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SubnetCalculator extends Object {
    static List<Integer> ipSequenzList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Willkommen beim Netzwerkrechner!");
        checkUserIPInput();
        checkUserSNMInput();
    }

    private static void checkUserIPInput() {
        System.out.println("Bitte gib eine IP im Format: 1.1.1.1 ein!");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        if (!checkDots(userInput)) {
            checkUserIPInput();
        } else if (!isLenghtRight(userInput)) {
            checkUserIPInput();
        } else if (!splitIP(userInput)) {
            checkUserIPInput();
        }
    }

    private static void checkUserSNMInput() {
        System.out.println("Bitte gib eine Subnetzmaske ein!");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        if (!checkDots(userInput)) {
            checkUserSNMInput();
        } else if (!isLenghtRight(userInput)) {
            checkUserSNMInput();
        } else if (!splitSNM(userInput)) {
            checkUserSNMInput();
        }

    }

    public static boolean splitIP(String ip) {
        List<String> sequentList = Arrays.asList(ip.split("\\."));
        for (int i = 0; i < 4; i++) {
            if (sequentList.get(i).isEmpty()) {
                System.out.println("Zuwenig Sequenz Inhalt!");
                return false;
            }
            if (sequentList.get(0).equals("0")) {
                System.out.println("Erste Sequenz darf nicht 0 sein!");
                return false;
            }
            if (!StringUtils.isNumeric(sequentList.get(i))) {
                System.out.println("Ungültige Eingabe! Gib bitte eine gültige IP ein!");
                return false;
            } else {
                int sequence = Integer.parseInt(sequentList.get(i));
                boolean testSequenzLength = ipSequentLength(sequence);
                if (!testSequenzLength) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean ipSequentLength(int sequent) {
        if (sequent < 256) {
            ipSequenzList.add(sequent);
        } else {
            System.out.println("Zahl zu groß!");
            return false;
        }
        return true;
    }

    public static boolean checkDots(String ip) {
        int count = StringUtils.countMatches(ip, ".");
        if (count == 3) {
            return true;
        }
        return false;
    }

    public static boolean isLenghtRight(String ip) {
        int iplength = ip.length();
        return iplength >= 7 && iplength <= 15;
    }

    public static boolean splitSNM(String snm) {
        List<Integer> snmSequenzList = new ArrayList<>();
        List<String> sequentList = Arrays.asList(snm.split("\\."));
        int sequentInt = 0;
        for (int i = 0; i < 4; i++) {
            if (sequentList.get(i).isEmpty()) {
                System.out.println("Zuwenig Sequent Inhalt!");
                return false;
            }
            if (!sequentList.get(0).equals("255")) {
                System.out.println("Erste Sequenz muss 255 sein!");
                return false;
            }
            if (!StringUtils.isNumeric(sequentList.get(i))) {
                System.out.println("Ungültige Eingabe! Gib bitte eine gültige IP ein!");
                return false;
            } else {
                snmSequenzList.add(Integer.parseInt(sequentList.get(i)));
            }

        }
        if (!snmValidation(snmSequenzList)) {
            return false;
        }

        return true;
    }

    public static boolean snmValidation(List<Integer> snmSequenzList) {
        for (int i = 0; i < 4; i++) {
            if (snmSequenzList.get(i) > 255) {
                System.out.println("Zahl zu groß!");
                return false;
            }
        }
        String binarySNM = "";
        for (int i = 0; i < 4; i++) {
            binarySNM += Integer.toBinaryString(snmSequenzList.get(i));
        }
        boolean isZeroFound = false;
        boolean isAfterZeroAOne = false;
        for (int i = 0; i < binarySNM.length(); i++) {
            char charr = binarySNM.charAt(i);
            if (charr == '0') {
                isZeroFound = true;
            }
            if (isZeroFound) {
                if (charr == '1') {
                    isAfterZeroAOne = true;
                }
            }
        }
        if (isZeroFound && isAfterZeroAOne) {
            System.out.println("Eingabe ungültig!");
            System.out.println(binarySNM);
            return false;
        }
        System.out.println(binarySNM);
        return true;
    }
}
