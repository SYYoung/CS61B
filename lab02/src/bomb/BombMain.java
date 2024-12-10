package bomb;

import common.IntList;

import java.util.Arrays;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            //b.phase0("Figure this out. I wonder where the phases are defined...");
            b.phase0("39291226");
        }
        if (phase >= 1) {
            IntList phase1List = IntList.of(0,9,3,0,8);
            //b.phase1(null); // Figure this out too
            b.phase1(phase1List);
        }
        if (phase >= 2) {
            // construct the password string
            String[] testStrArr = new String[]{"1","2","3"};
            String testStr = Arrays.toString(testStrArr);

            String repeatStr = "-81201430 ";
            int passwdLength = 1340;
            String passwd = repeatStr.repeat(passwdLength);
            //b.phase2("Figure this out. I wonder where the phases are defined...");
            b.phase2(passwd);
        }
    }
}
