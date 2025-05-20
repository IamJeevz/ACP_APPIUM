package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class shell_commands {

    public void delete_files() throws Exception {
        Process checkProcess = Runtime.getRuntime().exec(
                "adb shell ls \"/sdcard/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Audio/\""
        );
        BufferedReader reader = new BufferedReader(new InputStreamReader(checkProcess.getInputStream()));
        String line;
        boolean foundFiles = false;

        while ((line = reader.readLine()) != null) {
            System.out.println("Still exists: " + line);
            foundFiles = true;
        }

        if (!foundFiles) {
            System.out.println("Folder is now empty.");
        }


    }

    public String fetch_file_name() throws Exception
    {
        String fileName="";
        Process process = Runtime.getRuntime().exec(
                "adb shell ls /sdcard/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp\\ Audio/"
        );

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.endsWith(".opus")) {
                fileName=line;
            }
        }
        return fileName;
    }
}
