package com.example.nativedefault;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nativedefault.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'nativedefault' library on application startup.
    static {
        System.loadLibrary("nativedefault");
    }

    private ActivityMainBinding binding;
    Button button;
    public int a = 0;
    FileManipulator fileManipulator;
    ArrayList<Integer> numbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
        button =findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileManipulator fileManipulator = new FileManipulator(MainActivity.this);
                try {
                    numbers = new ArrayList<>();
                    numbers.add(1);
                    numbers.add(2);
                    numbers.add(3);
                    numbers.add(4);
                    numbers.add(5);
                    fileManipulator.createFile();
                    fileManipulator.write();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tv.setText(averageFromFilE("numbers.txt"));
            }
        });
    }

    /**
     * A native method that is implemented by the 'nativedefault' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String stringFromJNISecond();

    public native String averageFromFilE(String fileName);

}