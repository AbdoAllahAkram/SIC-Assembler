import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;


public class CreatFile {

    static String file_path = "files/location.txt";

    /* ---Create Directory Method--- */
    public static void createDirectory() {
        File creatDir = new File("files");

        if (!creatDir.exists()) {
            creatDir.mkdir();
            System.out.println(creatDir.getPath() + " is created");
         } else
            System.out.println(creatDir.getPath() + " is exist");
    }

    /* ---Create File Method--- */
    public static void createFile() {
        File newFile = new File(file_path);
        try {
            if(newFile.createNewFile())
                System.out.println("File Is Created!");
            else
                System.out.println("File Is Already Existed");
        } catch(IOException e) {
            System.out.println("Error! " + e);
        }

    }
    // *********************************************************** \\

    /* ---Write To File Method */
    public static void writeToFile() {
        String one_line = "This line printed from java program";

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file_path, true)); // true: not delete old contents

            writer.append(one_line, 0,one_line.length());
            writer.close();
            System.out.println("text is printed Successfully");
        } catch (IOException IOE) {
            System.out.println("Error! " + IOE);
        }
    }
    // *********************************************************** \\

    /* ---Read File Method---*/
    public static void readFile() {
        BufferedReader reader ;
        String line;

        try {
            reader = new BufferedReader(new FileReader("files/prog_file.txt"));

            while((line = reader.readLine()) != null) {

                String[] line_array = line.split("\\s+");
                for(int i=0; i < line_array.length; i++) {
                    if(line_array[i].contains("START"))System.out.println(line_array[i-1]);
                }
            }

            reader.close();

        } catch(IOException ioEx) {
            System.err.println("Cant\'t Read file" + ioEx);
        }
    }
    // *********************************************************** \\


    /* ---Main Method---*/
    public static void main(String[] args) {
            createDirectory();
            createFile();
//        writeToFile();
//        readFile();

        BufferedReader reader ;
        String line;
        String location;
        int coun = 0x0000;
        boolean start_flag = false;
        boolean frist_ime = true;

        try {
            reader = new BufferedReader(new FileReader("files/prog_file.txt"));

            while((line = reader.readLine()) != null) {

                String[] line_array = line.split("\\s+");
                for(int i=0; i < line_array.length; i++) {
                    //System.out.println(line_array[i]);
                    if(line_array[i].contains("START")) start_flag = true;
                    if(line_array[i].contains("+")) coun += 1;
                }

                try {
                    PrintWriter writer = new PrintWriter(new FileWriter(file_path, true)); // true: not delete old contents

                    if(start_flag) {
                        location = Integer.toHexString(coun);

                        coun +=3;
                        if(frist_ime) {
                            coun = 0;
                            frist_ime = false;
                        }

                        writer.println("000" + location + "      " + line);
                        writer.close();
                        System.out.println("text is printed Successfully");
                    }

                } catch (IOException IOE) {
                    System.out.println("Error! " + IOE);
                }
            }

            reader.close();

        } catch(IOException ioEx) {
            System.err.println("Cant\'t Read file" + ioEx);
        }




    }
}
