package persistence;

import models.Process;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PersistenceManager {

    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    public static void saveReport(String report, ArrayList<Process> reportList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./Reportes/"+report));
            bufferedWriter.write("|  NOMBRE  |  |  TIEMPO  |  |  BLOQUEA  |   ");
            bufferedWriter.newLine();
            bufferedWriter.write("__________________________________________");
            bufferedWriter.newLine();

            for (int i = 0; i < reportList.size(); i++) {
                bufferedWriter.write("|  ");
                bufferedWriter.write(reportList.get(i).getName());
                bufferedWriter.write("      |  |");
                bufferedWriter.write("  ");
                bufferedWriter.write(String.valueOf(reportList.get(i).getTime()));
                bufferedWriter.write("     |  |");
                bufferedWriter.write("  ");
                bufferedWriter.write(isBlock(reportList.get(i).isIsLock()));
                bufferedWriter.write("      |");
                bufferedWriter.write("  ");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("__________________________________________");
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {

        }
    }

    public static String isBlock(Boolean isBlock){
        String block = "";
        if (isBlock){
            block = "Si";
        }else{
            block = "No";
        }
        return block;
    }

    public static void showManual(){
        try {
            File path = new File("src/main/java/resources/Manual de usuariodocx.docx");
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
