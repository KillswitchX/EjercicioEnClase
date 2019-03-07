package icesi.com.ejercicioenclase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mandar la solicitud de conexion
        //10.0.2.2 -> Direccio del mismo pc para depuracion

        new Thread(){

            @Override
            public void run() {

                try {

                    Socket socket = new Socket("10.0.2.2",5000);
                    Log.e(">>>","Conexion Acceptada");

                    BufferedReader receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    while (true){
                        final String line = receiver.readLine();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, line, Toast.LENGTH_LONG).show();
                            }
                        });

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();





    }
}
