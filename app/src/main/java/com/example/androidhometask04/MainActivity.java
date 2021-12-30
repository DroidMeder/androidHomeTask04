package com.example.androidhometask04;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Double firstValue = 0.0, secondValue = 0.0, result = 0.0, percents = 0.0, percentsScreen = 0.0;
    private String operand;
    private boolean isOperationClicked = false, isPercentsClicked = false, isEqualRepeated = false;
    private boolean isPercentsOperand = false;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

        button=findViewById(R.id.btn_send_intent);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            String text = textView.getText().toString();
            intent.putExtra("Iaha", text);
            finish();
            startActivity(intent);
        });
    }
    @SuppressLint("NonConstantResourceId")
    public void numberClick(View v) {
        button.setVisibility(View.INVISIBLE);
        isEqualRepeated = false;
        switch (v.getId()) {
            case R.id.btn_1:
                numberClickMethod("1");
                break;
            case R.id.btn_2:
                numberClickMethod("2");
                //numberClick(findViewById(R.id.btn_5));
                break;
            case R.id.btn_3:
                numberClickMethod("3");
                break;
            case R.id.btn_4:
                numberClickMethod("4");
                break;
            case R.id.btn_5:
                numberClickMethod("5");
                break;
            case R.id.btn_6:
                numberClickMethod("6");
                break;
            case R.id.btn_7:
                numberClickMethod("7");
                break;
            case R.id.btn_8:
                numberClickMethod("8");
                break;
            case R.id.btn_9:
                numberClickMethod("9");
                break;
            case R.id.btn_0:
                numberClickMethod("0");
                break;
            case R.id.btn_floating_point:
                if (!textView.getText().toString().contains(".")) {
                    textView.append(".");
                }
                isOperationClicked = false;
                break;
        }

    }

    private void numberClickMethod(String s) {
        if (textView.getText().toString().equals("0")) {
            textView.setText(s);
        } else if (isOperationClicked) {
            textView.setText(s);
        } else {
            textView.append(s);
        }
        isOperationClicked = false;
    }

    @SuppressLint("NonConstantResourceId")
    public void operationClick(View v) {
        button.setVisibility(View.INVISIBLE);
        isEqualRepeated = false;
        isPercentsClicked = false;
        isPercentsOperand = false;
        switch (v.getId()) {
            case R.id.btn_add:
                operand = "+";
                firstValue = Double.valueOf(textView.getText().toString());
                isOperationClicked = true;
                break;
            case R.id.btn_subtract:
                operand = "-";
                firstValue = Double.valueOf(textView.getText().toString());
                isOperationClicked = true;
                break;
            case R.id.btn_multiply:
                operand = "*";
                firstValue = Double.valueOf(textView.getText().toString());
                isOperationClicked = true;
                break;
            case R.id.btn_division:
                operand = "/";
                firstValue = Double.valueOf(textView.getText().toString());
                isOperationClicked = true;
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void functionClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                button.setVisibility(View.INVISIBLE);
                operand = "";
                percents = (double) 0;
                isPercentsOperand = false;
                isEqualRepeated = false;
                isOperationClicked = false;
                isPercentsClicked = false;
                textView.setText("0");
                firstValue = (double) 0;
                secondValue = (double) 0;
                result = (double) 0;
                try {
                    onDestroy();
                } catch (Exception e) {
                    onRestart();
                }
                break;
            case R.id.btn_equal:
                try {
                    if (!isPercentsClicked) {
                        if (!isEqualRepeated) {
                            secondValue = Double.valueOf(textView.getText().toString());
                        }
                        switch (operand) {
                            case "+":
                                if (!isEqualRepeated) {
                                    result = firstValue + secondValue;
                                } else {
                                    result += secondValue;
                                }
                                break;
                            case "-":
                                if (!isEqualRepeated) {
                                    result = firstValue - secondValue;
                                } else {
                                    result -= secondValue;
                                }
                                break;
                            case "*":
                                if (!isEqualRepeated) {
                                    result = firstValue * secondValue;
                                } else {
                                    result *= secondValue;
                                }
                                break;
                            case "/":
                                if (!isEqualRepeated) {
                                    result = firstValue / secondValue;
                                } else {
                                    result /= secondValue;
                                }
                                break;
                        }
                    } else {
                        percentOperand();
                    }
                    isEqualRepeated = firstValue != 0 || secondValue != 0 || result != 0;
                    String str = new DecimalFormat("##.#########").format(result).length() > 10 ?
                            new DecimalFormat("##.######E0").format(result) : new DecimalFormat("##.##########").format(result);
                    textView.setText(str);
                    break;
                } catch (Exception e) {
                    textView.setText("1");
                }finally {
                    if (button.getVisibility()==View.INVISIBLE) {
                        button.setVisibility(View.VISIBLE);
                    }
                }
                       /*   ***** Инструкция как пользоваться процентом *****

            1. Вводим число, от которого считаем проценты
            2. Нажимаем на знак +, или -, или * или же /
            3. Вводим цифру процента
            4. Нажимаем на знак процента
            5. Получаем процент от исходного числа
            6. Нажимаем на знак «равно»
            7. Получаем итоговую сумму    */

            case R.id.btn_percent:
                button.setVisibility(View.INVISIBLE);
                try {
                    secondValue = Double.valueOf(textView.getText().toString());
                    switch (operand) {
                        case "/":
                            percents = secondValue / 100;
                            break;
                        case "*":
                            percents = secondValue / 100;
                            break;
                        case "+":
                            percents = firstValue * (secondValue / 100);
                            break;
                        case "-":
                            percents = firstValue * (secondValue / 100);
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value: " + operand);
                    }
                    String str = new DecimalFormat("#.######").format(percents).length() > 12 ?
                            new DecimalFormat("#.######E0").format(percents) : new DecimalFormat("#.######").format(percents);
                    textView.setText(str);
                    percentOperand();
                    isPercentsClicked = true;
                    break;
                } catch (Exception e) {
                    textView.setText("0");
                }
            case R.id.btn_minus_plus:
                button.setVisibility(View.INVISIBLE);
                double dtmp;
                try {
                    textView.setText(abs(Integer.parseInt(textView.getText().toString())));
                    dtmp = Double.parseDouble(textView.getText().toString());
                    result = dtmp;
                } catch (Exception e) {
                    textView.setText(abs(Double.parseDouble(textView.getText().toString())));
                    dtmp = Double.parseDouble(textView.getText().toString());
                    result = dtmp;
                }
        }
    }

    private void percentOperand() {
        if (!isPercentsOperand) {
            percentsScreen = Double.parseDouble(textView.getText().toString());
            isEqualRepeated = false;
        } else {
            switch (operand) {
                case "+":
                    if (!isEqualRepeated) {
                        result = firstValue + percentsScreen;
                    } else {
                        result += percentsScreen;
                    }
                    break;
                case "-":
                    if (!isEqualRepeated) {
                        result = firstValue - percentsScreen;
                    } else {
                        result -= percentsScreen;
                    }
                    break;
                case "*":
                    if (!isEqualRepeated) {
                        result = firstValue * percentsScreen;
                    } else {
                        result *= percentsScreen;
                    }
                    break;
                case "/":
                    if (!isEqualRepeated) {
                        result = firstValue / percentsScreen;
                    } else {
                        result /= percentsScreen;
                    }
                    break;
            }
            isEqualRepeated = firstValue != 0 || percentsScreen != 0 || result != 0;
        }
        isPercentsOperand = firstValue != 0 || percentsScreen != 0 || result != 0;
    }

    private String abs(Double a) {
        if (a > 0) {
            return String.valueOf(-a);
        } else if (a < 0) {
            return String.valueOf(-(a));
        } else return "0";
    }

    private String abs(Integer a) {
        if (a > 0) {
            return String.valueOf(-a);
        } else if (a < 0) {
            return String.valueOf(-(a));
        } else return "0";
    }
}