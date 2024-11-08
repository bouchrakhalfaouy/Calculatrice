package com.example.seance2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class maCalculatrice extends AppCompatActivity {
    private TextView textView;
    private String currentInput = "";
    private String operation = "";
    private double firstNumber = 0;
    private EditText editText;
    private double memory = 0;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ma_calculatrice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextText);
        setupButtons();
        setupMemoryButtons();
        linearLayout=findViewById(R.id.linearLayout);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemScientifique) {
            // Crée une Intent pour ouvrir une URL de calculatrice scientifique
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.desmos.com/scientific"));
            startActivity(intent);
            return true;
        } else if (id == R.id.itemStandard) {

            setContentView(R.layout.activity_ma_calculatrice);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupButtons() {
        Button button1 = findViewById(R.id.button7);
        button1.setOnClickListener(v -> updateInput("1"));
        Button buttonDecimal = findViewById(R.id.button18);
        buttonDecimal.setOnClickListener(v -> addDecimalPoint());

        Button buttonPlus = findViewById(R.id.button6);
        buttonPlus.setOnClickListener(v -> setOperation("+"));

        Button buttonMoin= findViewById(R.id.button11);
        buttonMoin.setOnClickListener(v -> setOperation("-"));

        Button buttonMultiplication = findViewById(R.id.button20);
        buttonMultiplication.setOnClickListener(v -> setOperation("*"));

        Button buttonDivision = findViewById(R.id.button21);
        buttonDivision.setOnClickListener(v -> setOperation("/"));

        Button buttonEquals = findViewById(R.id.button19);
        buttonEquals.setOnClickListener(v -> calculateResult());

        Button button2 = findViewById(R.id.button8);
        button2.setOnClickListener(v -> updateInput("2"));

        Button button3 = findViewById(R.id.button9);
        button3.setOnClickListener(v -> updateInput("3"));

        Button button4 = findViewById(R.id.button10);
        button4.setOnClickListener(v -> updateInput("4"));

        Button button5= findViewById(R.id.button12);
        button5.setOnClickListener(v -> updateInput("5"));

        Button button6 = findViewById(R.id.button13);
        button6.setOnClickListener(v -> updateInput("6"));

        Button button7 = findViewById(R.id.button14);
        button7.setOnClickListener(v -> updateInput("7"));

        Button button8 = findViewById(R.id.button15);
        button8.setOnClickListener(v -> updateInput("8"));

        Button button9 = findViewById(R.id.button16);
        button9.setOnClickListener(v -> updateInput("9"));

        Button button0 = findViewById(R.id.button17);
        button0.setOnClickListener(v -> updateInput("0"));

        Button button24=findViewById(R.id.button24);
        button24.setOnClickListener(v -> Clear());
        Button button25=findViewById(R.id.button25);
        button25.setOnClickListener(v -> backSpace());
        Button button22=findViewById(R.id.button22);
        button22.setOnClickListener(v -> Pourcentage());
        Button button23=findViewById(R.id.button23);
        button23.setOnClickListener(v -> ClearEntry());
        Button button27=findViewById(R.id.button27);
        button27.setOnClickListener(v -> inverse());
        Button button28=findViewById(R.id.button28);
        button28.setOnClickListener(v -> puissanceDeux());
        Button button29=findViewById(R.id.button29);
        button29.setOnClickListener(v -> racineCarre());
        Button button30=findViewById(R.id.button30);
        button30.setOnClickListener(v -> operations());
    }
    private void setupMemoryButtons() {
        Button buttonMS = findViewById(R.id.button5);
        buttonMS.setOnClickListener(v -> memoryStore());

        Button buttonMC = findViewById(R.id.button);
        buttonMC.setOnClickListener(v -> memoryClear());

        Button buttonMPlus = findViewById(R.id.button3);
        buttonMPlus.setOnClickListener(v -> memoryAdd());

        Button buttonMMinus = findViewById(R.id.button4);
        buttonMMinus.setOnClickListener(v -> memorySubtract());

        Button buttonMR = findViewById(R.id.button2);
        buttonMR.setOnClickListener(v -> memoryRecall());
    }
    private void memoryStore() {
        if (!editText.getText().toString().isEmpty()) {
            memory = Double.parseDouble(editText.getText().toString());
        }
    }
    private void memoryClear() {
        memory = 0;
        textView.setText("");
    }
    private void memoryAdd() {
        if (!editText.getText().toString().isEmpty()) {
            memory += Double.parseDouble(editText.getText().toString());
        }
    }
    private void memorySubtract() {
        if (!editText.getText().toString().isEmpty()) {
            memory -= Double.parseDouble(editText.getText().toString());
        }
    }
    private void memoryRecall() {
        editText.setText(String.valueOf(memory));
    }
    private void updateInput(String input) {
        currentInput += input;
        if (operation.isEmpty()) {
            textView.setText(currentInput);
        } else {
            textView.setText(firstNumber + " " + operation + " " + currentInput);
        }
    }
    private void setOperation(String op) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operation = op;
            currentInput = ""; // Réinitialise l'entrée actuelle
            textView.setText(firstNumber + " " + operation);  // Affiche l'opération
        }
    }
    private void addDecimalPoint() {
        if (!currentInput.contains(".")) { // Assurez-vous qu'il n'y a pas déjà un point
            if (currentInput.isEmpty()) {
                currentInput = "0."; // Ajoute "0." si aucun chiffre n'est encore saisi
            } else {
                currentInput += ".";
            }
            updateInput("");
        }
    }
    private void calculateResult() {
        if (!currentInput.isEmpty() && !operation.isEmpty()) {
            Double secondNumber = Double.parseDouble(currentInput);
            double result = 0;
            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        editText.setText("Error"); // Gérer la division par zéro
                        return;
                    }
                    break;
            }

            editText.setText(String.valueOf(result)); // Affiche le résultat
            currentInput = ""; // Réinitialise l'entrée actuelle
            operation = ""; // Réinitialise l'opération
        }
    }
     private void Clear(){
         currentInput="";
         editText.setText("0");
     }
     private void backSpace(){
        if(!currentInput.isEmpty()){
            currentInput=currentInput.substring(0,currentInput.length()-1);
            editText.setText(currentInput.isEmpty()? "0" : currentInput);
        }
     }
    private void Pourcentage()
    {
        Double value=Double.parseDouble(textView.getText().toString());
        editText.setText(String.valueOf(value/100));
    }
    private void ClearEntry(){
        currentInput="";
        editText.setText("0");
    }
    private void inverse() {
        if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                double result = 1 / value;
            editText.setText(String.valueOf(result));
        }
    }
    private void puissanceDeux()
    {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            double result = Math.pow(value,2);
            editText.setText(String.valueOf(result));
        }
    }
    private void racineCarre()
    {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            if(value>=0) {
                double result = Math.sqrt(value);
                editText.setText(String.valueOf(result));
            }
        }
    }
    private void operations()
    {

    }
}