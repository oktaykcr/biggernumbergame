package com.example.biggernumbergame.services;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class StorageService {

    public StringBuilder readFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if(file.exists()) {
            StringBuilder builder = new StringBuilder();
            Scanner scanner = null;
            try {
                scanner = new Scanner(context.openFileInput(fileName));
                while (scanner.hasNext()) {
                    builder.append(scanner.nextLine());
                }
                scanner.close();
                Log.i("FILE", fileName + " reading successful");
            } catch (FileNotFoundException e) {
                Log.e("FILE", fileName + " could not read");
                e.printStackTrace();
            }

            return builder;
        } else {
            writeFile(context, fileName, "0,0");
        }
        return new StringBuilder().append("0,0");
    }

    public void writeFile(Context context, String fileName, String fileContent) {
        try {
            PrintStream ps = new PrintStream(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            ps.print(fileContent);
            ps.close();
            Log.i("FILE", fileName + " is saved");
        } catch (FileNotFoundException e) {
            Log.e("FILE", fileName + " could not saved");
            e.printStackTrace();
        }
    }
}
