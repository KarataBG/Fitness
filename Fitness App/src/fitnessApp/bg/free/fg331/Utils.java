package fitnessApp.bg.free.fg331;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Utils {

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
//            while((line = br.readLine()) != null) {
//                builder.append(line);
//                if (br.)
//            }

            Scanner scan = new Scanner(new File(path));

            if (!scan.toString().equals(" "))
                while (scan.hasNextLine()) {
                    builder.append(scan.nextLine());
                    if (scan.hasNextLine()) {
                        builder.append("\n");
                    } else
                        break;
                }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
