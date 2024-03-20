package com.example.polynomial.model;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monomial {
    public Integer degree;
    public Number coefficient;
    public Monomial(Integer degree, Number coefficient){
        this.degree = degree;
        this.coefficient = coefficient;
    }
    public Monomial(String string){
        Pattern p = Pattern.compile("(-?\\d*)x(\\^\\d+)?");
        Matcher m = p.matcher(string);
        Pattern p2 = Pattern.compile("-?\\d+$");
        Matcher m2 = p2.matcher(string);
        this.coefficient = 0;
        this.degree = 0;
        if(m.find()) {
            this.coefficient = (m.group(1) == "" || m.group(1) == null) ? 1 : (m.group(1).equals("-") ? -1 : Integer.parseInt(m.group(1)));
            this.degree = (m.group(2) != null && !m.group(2).equals("^")) ? Integer.parseInt(string.substring(m.start(2) + 1, m.end(2))) : 1;
        }else if(m2.find()) {
            this.coefficient = Integer.parseInt(m2.group());
        }
    }
    public Monomial add(Monomial second) {
        return second != null && degree.equals(second.degree) ? new Monomial(degree, coefficient.floatValue() + second.coefficient.floatValue()) : new Monomial(degree, coefficient);
    }
    public Monomial invert() {
        return new Monomial(degree, -coefficient.floatValue());
    }

    public Monomial subtract(Monomial second) {
        return this.add(second.invert());
    }

    public Monomial product(Monomial second) {
        return second != null  ? new Monomial(this.degree + second.degree, this.coefficient.floatValue() * second.coefficient.floatValue()) : new Monomial(degree, coefficient);
    }
    public Monomial div(Monomial second) {
        return second != null  ? new Monomial(this.degree - second.degree, this.coefficient.floatValue() / second.coefficient.floatValue()) : new Monomial(degree, coefficient);
    }
    public Monomial derivate() {
        return new Monomial(this.degree - 1,this.coefficient.floatValue() * this.degree);
    }
    public Monomial integrate() {
        return new Monomial(this.degree + 1,this.coefficient.floatValue()/(this.degree + 1));
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");
        if(this.coefficient.floatValue() == 0) return "";
        else if (this.coefficient.floatValue() == -1) result.append("- ");
        else if (this.coefficient.floatValue() == 1) result.append("+ ");
        else if(this.coefficient.floatValue() < 0) result.append("- ").append(df.format(this.coefficient.floatValue() * -1.0));
        else if (this.coefficient.floatValue() > 0) result.append("+ ").append(df.format(this.coefficient.floatValue()));

        if (this.degree == 1) result.append("x ");
        else if (this.degree > 1) result.append("x^").append(this.degree).append(" ");

        return result.toString();
    }
}
