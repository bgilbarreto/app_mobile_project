package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Preguntas extends AppCompatActivity {
    TextView messagesTextView;
    EditText inputEditText;
    Button sendButton;
    private Button btn1;
    private Button boton1, boton2;
    private ImageView boton3;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        context = this;
        messagesTextView = findViewById(R.id.messagesTextView);
        inputEditText = findViewById(R.id.inputEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String input = inputEditText.getText().toString();
                messagesTextView.append(Html.fromHtml("<p><b>Tu:</b> " + input + "</p>"));
                inputEditText.setText("");

                getResponse(input);
            }
        });



        nextWindow();
        navegation();
        cart();
        profile();
    }

    private void getResponse(String input) {

        String urlAssistant = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/a4b4f8b6-7364-423c-a044-5147a6b49f20/v2/assistants/8e6bc4bd-03c9-4a42-ba1a-21dcc84aba39/message?version=2020-04-01";
        String authentication = "YXBpa2V5OkljTEo1eGhHcHBrNm9JUUdJNGRqUW4zUlJKRVhfSHVmVVpocjZ6NWhaU1hS";


        JSONObject inputJsonObject = new JSONObject();
        try {
            inputJsonObject.put("text",input);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonBody = new JSONObject();
        try {

            jsonBody.put("input", inputJsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(urlAssistant)
                .addHeaders("Content-Type","application/json")
                .addHeaders("Authorization","Basic " + authentication)
                .addJSONObjectBody(jsonBody)
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String outputAssistant = "";

                        //parseo la respuesta del json
                        try {

                            String outputJsonObject = response.getJSONObject("output").getJSONArray("generic").getString(0).substring(30);
                            //.getJSONArray("text").getString(0);


                            messagesTextView.append(Html.fromHtml("<p><b>Chatbot:</b> " + outputJsonObject + "</p>"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context,"Error de conexión", Toast.LENGTH_LONG).show();
                    }
                });





    }

    public void nextWindow() {
        btn1 = (Button) findViewById(R.id.button5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Preguntas.this,navegacion.class));
            }
        });
    }

    public void navegation () {
        boton1 = (Button) findViewById(R.id.button5);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Preguntas.this, navegacion.class));
            }
        });
    }

    public void cart () {
        boton2 = (Button) findViewById(R.id.carrito);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Preguntas.this, CarroDeCompras.class));
            }
        });
    }

    public void profile () {
        boton3 = (ImageView) findViewById(R.id.imageView5);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Preguntas.this, perfil.class));
            }
        });
    }

}
