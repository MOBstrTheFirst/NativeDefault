package com.example.nativedefault;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManipulator {

    private static final String FILE_NAME = "numbers.txt";
    File file;
    Context context;

    public FileManipulator(Context context) {
        this.context = context;
    }

    public void createFile() throws IOException {
        file = File.createTempFile(FILE_NAME, null, context.getCacheDir());
        if (file.canRead() && file.canWrite()){
            Toast.makeText(context, "File is readable and writable", Toast.LENGTH_SHORT).show();
        }
    }

    public void write(ArrayList<Integer> numbers) {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            for (int i = 0; i < numbers.size(); i++){
                writer.writeInt(numbers.get(i));
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            for (int i = 0; i < 5; i++){
                writer.writeInt(i);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
