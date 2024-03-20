package com.example.polynomial.model;

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
        this.coefficient = 0;
        this.degree = 0;
        if(m.find()) {
            this.coefficient = (m.group(1) == "" || m.group(1) == null) ? 1 : (m.group(1).equals("-") ? -1 : Integer.parseInt(m.group(1)));
            this.degree = (m.group(2) != null && !m.group(2).equals("^")) ? Integer.parseInt(string.substring(m.start(2) + 1, m.end(2))) : 1;
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
        if(this.coefficient.floatValue() < 0) result.append("- ").append(this.coefficient.floatValue() * -1.0);
        else if (this.coefficient.floatValue() == 0) return "";
        else if (this.coefficient.floatValue() > 0) result.append("+ ").append(this.coefficient.floatValue());

        if (this.degree == 1) result.append("x ");
        else if (this.degree > 1) result.append("x^").append(this.degree).append(" ");

        //if(this.coefficient.floatValue() == 0) result = "0.0";
        //else if (this.degree == 0) result = this.coefficient.toString();
        //else if (this.degree == 1) result = this.coefficient + "x";
        //else result = this.coefficient + "x^" + this.degree;
        return result.toString();
    }
}
