/*
//  String Constant Pool, Heap Memory

public class StringOps {
    public static void main(String s[]) {
        String s1 = "Hello World";
        System.out.println(s1);
        String s2 = new String("Hello World");
        System.out.println(s2);
        String s3 = "Hello World";

        System.out.println("s1 and s2 comparison "+ (s1 == s2));

        System.out.println("s2 and s3 comparison "+ (s2 == s3));

        System.out.println("s1 and s3 comparison "+ (s1 == s3));
    }
}


//  Find Length of the string and access characters

public class StringOps {
    public static void main(String s[]) {
        String s1 = "The quick brown fox jumped over the lazy dog";

        System.out.println(s1.length());

        char[] strAsArr = s1.toCharArray();
        System.out.println(strAsArr.length);

        System.out.println(strAsArr);

        System.out.println("The first char of the string is " + strAsArr[0]);
        System.out.println("The last char of the string is " + strAsArr[strAsArr.length-1]);

        System.out.println("The index of T is " + s1.indexOf('T'));
        System.out.println("The index of g is " + s1.indexOf('g'));
    }
}


//  String Comparison

public class StringOps {
    public static void main(String s[]) {
        String s1 = "Washington";
        String s2 = new String("Washington");
        String s3 = "WASHINGTON";

        System.out.println("Equality check s1 and s2 - "+s1.equals(s2));
        System.out.println("Equality check s1 and s3 - "+s1.equals(s3));
        System.out.println("Equality check s1 and s3 ignoring case - "+s1.equalsIgnoreCase(s3));

        System.out.println("s1 in lowercase - "+s1.toLowerCase());
        System.out.println("s3 in lowercase - "+s3.toLowerCase());

        System.out.println("s1 and s3 lowercase equality check - " +
                            s1.toLowerCase().equals((s3.toLowerCase())));

        System.out.println("s1 in uppercase - "+s1.toUpperCase());
        System.out.println("s3 in uppercase - "+s3.toUpperCase());

        String s4 = "50F1A";
        System.out.println("s4 in lowercase - "+s4.toLowerCase());

        String regexStr = "^W.*";
        System.out.println("s1 matches regex ^W.* - "+s1.matches(regexStr));
        System.out.println("s3 matches regex ^W.* - "+s3.matches(regexStr));

        String s5 = "     WASHINGTON          ";
        System.out.println("Equality check s3 and s5 - "+s3.equals(s5));
        s5 = s5.strip();
        System.out.println("Equality check after stripping s3 and s5 - "+s3.equals(s5));
    }

}


//  Concatenation and substringing


*/


public class StringOps {
    public static void main(String s[]) {
        String s1 = "Washington";
        String s2 = " DC";
        s1 = s1.concat(s2);
        System.out.println("s1 " + s1);
        s1 = s1.substring(0,10);
        System.out.println("s1 " + s1);

        System.out.println("s1.substring(7,10) " + s1.substring(7,10));
        System.out.println("s1.substring(7) " + s1.substring(7));
    }
}