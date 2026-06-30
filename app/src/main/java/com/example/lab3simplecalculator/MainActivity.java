package com.example.lab3simplecalculator;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private enum Operator { none, add, minus, multiply, divide, eq }
    private double data1 = 0, data2 = 0;
    private Operator optr = Operator.none;
    private boolean requiresCleaning = false;
    private boolean hasDot = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void btn00Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "0");
    }
    public void btn01Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "1");
    }
    public void btn02Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "2");

    }public void btn03Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "3");
    }
    public void btn04Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "4");
    }
    public void btn05Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "5");
    }
    public void btn06Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "6");
    }
    public void btn07Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "7");
    }
    public void btn08Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "8");
    }
    public void btn09Click(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + "9");
    }
    public void btnDotClick(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText(eText.getText() + ".");
    }
    public void btnAddClick(View view) {
        optr = Operator.add;
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        data1 = Double.parseDouble(eText.getText().toString());
        eText.setText("");
    }
    public void btnMinusClick(View view) {
        optr = Operator.minus;
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        data1 = Double.parseDouble(eText.getText().toString());
        eText.setText("");
    }
    public void btnMultClick(View view) {
        optr = Operator.multiply;
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        data1 = Double.parseDouble(eText.getText().toString());
        eText.setText("");
    }
    public void btnDivClick(View view) {
        optr = Operator.divide;
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        data1 = Double.parseDouble(eText.getText().toString());
        eText.setText("");
    }
    public void btnClearClick(View view) {
        TextView eText = (TextView) findViewById(R.id.resultEdit);
        eText.setText("");
    }

    public void btnResultClick(View view) {
        if (optr != Operator.none) {
            TextView eText = (TextView) findViewById(R.id.resultEdit);
            data2 = Double.parseDouble(eText.getText().toString());
            double result = 0;
            if (optr == Operator.add) result = data1 + data2;
            else if (optr == Operator.minus) result = data1 - data2;
            else if (optr == Operator.multiply) result = data1 * data2;
            else if (optr == Operator.divide) result = data1 / data2;
            optr = Operator.none;
            data1 = result;
            if ((result - (int) result) != 0) {
                eText.setText(String.valueOf(result));
            } else {
                eText.setText(String.valueOf((int) result));
            }
        }
    }

    public void onClickNumericalButton(View view) {
        int pressID = view.getId();
        TextView curText = (TextView) findViewById(R.id.resultEdit);
// If the previous press was =, start a fresh expression
        if (optr == Operator.eq) {
            optr = Operator.none;
            curText.setText("");
        }
        if (requiresCleaning) {
            requiresCleaning = false;
            curText.setText("");
        }

        if (pressID == R.id.button00) curText.append("0");
        else if (pressID == R.id.button01) curText.append("1");
        else if (pressID == R.id.button02) curText.append("2");
        else if (pressID == R.id.button03) curText.append("3");
        else if (pressID == R.id.button04) curText.append("4");
        else if (pressID == R.id.button05) curText.append("5");
        else if (pressID == R.id.button06) curText.append("6");
        else if (pressID == R.id.button07) curText.append("7");
        else if (pressID == R.id.button08) curText.append("8");
        else if (pressID == R.id.button09) curText.append("9");
        else if (pressID == R.id.buttonDot) {
            if (!hasDot) {
                curText.append(".");
                hasDot = true;
            }
        }
    }
    public void onClickFunctionButton(View view) {
        int pressID = view.getId();
        TextView curText = (TextView) findViewById(R.id.resultEdit);
            // CE always clears everything
        if (pressID == R.id.buttonCE) {
            optr = Operator.none; curText.setText("");
            data1 = 0; data2 = 0; requiresCleaning = false;
            return;
        }
        String dataText = curText.getText().toString();
        double numberVal = dataText.length() > 0 ? Double.parseDouble(dataText) : 0;
        if (optr == Operator.none) {
            data1 = numberVal; requiresCleaning = true;
            if (pressID == R.id.buttonAdd) {
                optr = Operator.add;
            }
            else if (pressID == R.id.buttonSub) {
                optr = Operator.minus;
            }
            else if (pressID == R.id.buttonMult) {
                optr = Operator.multiply;
            }
            else if (pressID == R.id.buttonDiv) {
                optr = Operator.divide;
            }
        }
        else {
            data2 = numberVal;
            double result = 0;
            if (optr == Operator.add) {
                result = data1 + data2;
            }
            else if (optr == Operator.minus) {
                result = data1 - data2;
            }
            else if (optr == Operator.multiply) {
                result = data1 * data2;
            }
            else if (optr == Operator.divide) {
                result = data1 / data2;
            }
            data1= result; optr = Operator.none;
            curText.setText(String.valueOf(result));
        }
    }

}