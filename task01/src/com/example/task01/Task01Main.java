package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "ffprobe -v error -of flat -show_format " + file.getAbsolutePath())
                .directory(new File("C:\\Users\\home\\ffmpeg-master-latest-win64-gpl\\ffmpeg-master-latest-win64-gpl\\bin"));
        Process process = processBuilder.start();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = bufferedReader.readLine();
            while (line!= null ) {
                //System.out.print(line);
                if (line.contains("title")) {
                    return line.split("\"")[1];
                }
                line = bufferedReader.readLine();
            }
        }
        return null;
    }
}
