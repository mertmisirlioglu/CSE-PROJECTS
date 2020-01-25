package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Stack;

public class Controller implements Initializable {
    private Stack<Float> datas;
    private LinkedList<Integer> operators;
    private int operation = -1;
    private char[] intToChar;
    private boolean progresscont = false;
    private float data;
    private boolean inputMode = true;
    private boolean resultMode = false;
    private boolean isStarting = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datas = new Stack<>();
        operators = new LinkedList<>();

        intToChar = new char[5];
        intToChar[1] = '+';
        intToChar[2] = '-';
        intToChar[3] = '*';
        intToChar[4] = '/';
    }

    @FXML
    public Button one;

    @FXML
    public Button two;

    @FXML
    public Button three;

    @FXML
    public Button four;

    @FXML
    public Button five;

    @FXML
    public Button six;

    @FXML
    public Button seven;

    @FXML
    public Button eight;

    @FXML
    public Button nine;

    @FXML
    public Button plus;

    @FXML
    public Button minus;

    @FXML
    public Button multiplication;

    @FXML
    public Button zero;

    @FXML
    public Button dot;

    @FXML
    public Button back;

    @FXML
    public Button equals;

    @FXML
    public Button clear;

    @FXML
    public Button div;

    @FXML
    public Button signchange;

    @FXML
    public TextField display;

    @FXML
    public TextField screend;

    @FXML
    public void clickaction(ActionEvent event) {
        checkInputMode();

        if (event.getSource() == one) {
            display.setText(display.getText() + "1");
        } else if (event.getSource() == two) {
            display.setText(display.getText() + "2");
        } else if (event.getSource() == three) {
            display.setText(display.getText() + "3");
        } else if (event.getSource() == four) {
            display.setText(display.getText() + "4");
        } else if (event.getSource() == five) {
            display.setText(display.getText() + "5");
        } else if (event.getSource() == six) {
            display.setText(display.getText() + "6");
        } else if (event.getSource() == seven) {
            display.setText(display.getText() + "7");
        } else if (event.getSource() == eight) {
            display.setText(display.getText() + "8");
        } else if (event.getSource() == nine) {
            display.setText(display.getText() + "9");
        } else if (event.getSource() == zero) {
            display.setText(display.getText() + "0");
        } else if (event.getSource() == back) {
            display.setText(removeLastChar(display.getText()));
        } else if (event.getSource() == dot) {
            if (!isFloat(display.getText())) {
                display.setText(display.getText() + ".");
            }
        } else if (event.getSource() == clear) {
            clearMethod();
        } else if (event.getSource() == plus) {
            operatorMethod(1);
        } else if (event.getSource() == minus) {
            operatorMethod(2);
        } else if (event.getSource() == multiplication) {
            operatorMethod(3);
        } else if (event.getSource() == div) {
            operatorMethod(4);
        } else if (event.getSource() == equals) {
            clickEquals();
        } else if (event.getSource() == signchange) {
            signChangeMethod();
        }
    }

    public void clickEquals() {
        if (!resultMode)
            equalsMethod();
        else
            screend.setText("clear or make operation with result");
    }

    public void signChangeMethod() {
        if (!resultMode)
            display.setText("" + getData() * -1);
        else {
            datas.set(0, -1 * datas.get(0));
            screend.setText("" + datas.get(0));
        }
    }

    public void checkInputMode() {
        if (inputMode) {
            if (resultMode) {
                screend.setText(display.getText());
                display.setText("");
            } else {
                display.setText("");
            }
            inputMode = false;
        }
    }

    public void clearMethod() {
        display.setText("0");
        screend.setText("");
        datas.clear();
        operators.clear();
        progresscont = false;
        inputMode = true;
        isStarting = true;
    }

    public void operatorMethod(int opNum) {
        if (progresscont) {
            operators.addLast(opNum);
        } else {
            if (operators.size() != 0) {
                if (isStarting) {
                    operators.remove(0);
                    isStarting = false;
                } else {
                    data = getData();
                    datas.push(data);
                }

                operators.addLast(opNum);

                if (operators.getFirst() != operators.getLast())
                    screend.appendText("" + intToChar[operators.getFirst()] + getData());
                else
                    screend.appendText("" + intToChar[operators.getLast()] + getData());
            } else {
                data = getData();
                datas.push(data);
                screend.appendText("" + data);
                operators.addLast(opNum);
                operators.addLast(opNum);
            }
        }
        resultMode = false;

        if (!isStarting)
            opCounter();
        inputMode = true;
    }

    public void opCounter() {
        if (operators.size() >= 2) {
            int opCount = 0;
            Float secondnum = 0.0f;

            if (datas.size() < 2)
                secondnum = getData();
            else
                secondnum = datas.pop();

            Float firstnum = datas.pop();
            int opNum = operators.get(0);
            operators.remove(0);
            Float result = 0.0f;

            switch (opNum) {
                case 1:
                    result = firstnum + secondnum;
                    display(result);
                    break;
                case 2:
                    result = firstnum - secondnum;
                    display(result);
                    break;
                case 3:
                    result = firstnum * secondnum;
                    display(result);
                    break;
                case 4:
                    try {
                        result = firstnum / secondnum;
                        display(result);
                    } catch (Exception e) {
                        display.setText("Error");
                    }
                    break;
            }
            datas.push(result);
        }
    }

    public void display(Float result) {
        display.setText(result.toString());
        inputMode = true;
    }

    public Float getData() {
        Float data = 0.0f;
        try {
            data = Float.parseFloat(display.getText());
        } catch (Exception C) {
            display.setText("Error");
        }
        return data;
    }


    public Boolean isFloat(String z) {
        Float a1 = 1.0f;
        try {
            a1 = Float.parseFloat(z);
        } catch (Exception X) {
            return false;
        }
        if ((a1 % a1.intValue()) == 0.0f) {
            return false;
        }
        return true;
    }

    public String removeLastChar(String s) {
        if (s == null || s.length() <= 1) {
            return "0";
        }
        return s.substring(0, s.length() - 1);
    }

    public void equalsMethod() {
        Float result = 0.0f;
        Float firstnum = datas.pop();
        Float secondnum = 0.0f;

        if (datas.size() < 2)
            secondnum = getData();
        else
            secondnum = datas.pop();

        int opNum = operators.get(0);
        operators.remove(0);

        switch (opNum) {
            case 1:
                result = firstnum + secondnum;
                screend.appendText("+" + secondnum + "=" + result);
                display(result);
                break;
            case 2:
                result = firstnum - secondnum;
                screend.appendText("-" + secondnum + "=" + result);
                display(result);
                break;
            case 3:
                result = firstnum * secondnum;
                screend.appendText("*" + secondnum + "=" + result);
                display(result);
                break;
            case 4:
                try {
                    result = firstnum / secondnum;
                    screend.appendText("/" + secondnum + "=" + result);
                    display(result);
                } catch (Exception e) {
                    display.setText("Error");
                }
                break;
        }

        datas.push(result);

        inputMode = true;
        resultMode = true;
        isStarting = false;
        progresscont = true;
    }


}
