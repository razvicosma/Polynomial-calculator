package com.example.polynomial.model;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monomial {
    private Integer degree;
    private Number coefficient;
    public Monomial(Integer degree, Number coefficient){
        this.setDegree(degree);
        this.setCoefficient(coefficient);
    }
    public Monomial(String string){
        Pattern p = Pattern.compile("(-?\\d*)x(\\^\\d+)?");
        Matcher m = p.matcher(string);
        Pattern p2 = Pattern.compile("-?\\d+$");
        Matcher m2 = p2.matcher(string);
        this.setCoefficient(0);
        this.setDegree(0);
        if(m.find()) {
            this.setCoefficient((m.group(1) == null || Objects.equals(m.group(1), "")) ? 1 : (m.group(1).equals("-") ? -1 : Integer.parseInt(m.group(1))));
            this.setDegree((m.group(2) != null && !m.group(2).equals("^")) ? Integer.parseInt(string.substring(m.start(2) + 1, m.end(2))) : 1);
        }else if(m2.find()) {
            this.setCoefficient(Integer.parseInt(m2.group()));
        }
    }
    public Monomial add(Monomial second) {
        return second != null && getDegree().equals(second.getDegree()) ? new Monomial(getDegree(), getCoefficient().floatValue() + second.getCoefficient().floatValue()) : new Monomial(getDegree(), getCoefficient());
    }
    public Monomial invert() {
        return new Monomial(getDegree(), -getCoefficient().floatValue());
    }

    public Monomial subtract(Monomial second) { return this.add(second.invert()); }

    public Monomial product(Monomial second) {
        return second != null  ? new Monomial(this.getDegree() + second.getDegree(), this.getCoefficient().floatValue() * second.getCoefficient().floatValue()) : new Monomial(0, 0);
    }
    public Monomial div(Monomial second) throws ArithmeticException{
        if(second == null || second.getCoefficient().floatValue() == 0) throw new ArithmeticException("Division by 0");
        else return new Monomial(this.getDegree() - second.getDegree(), this.getCoefficient().floatValue() / second.getCoefficient().floatValue());
    }
    public Monomial derivate() {
        return new Monomial(this.getDegree() - 1, this.getCoefficient().floatValue() * this.getDegree());
    }
    public Monomial integrate() {
        return new Monomial(this.getDegree() + 1, this.getCoefficient().floatValue()/(this.getDegree() + 1));
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");
        if(this.getCoefficient().floatValue() == 0) return "";
        else if (this.getCoefficient().floatValue() == -1 && this.getDegree() != 0) result.append("- ");
        else if (this.getCoefficient().floatValue() == 1 && this.getDegree() != 0) result.append("+ ");
        else if(this.getCoefficient().floatValue() < 0) result.append("- ").append(df.format(this.getCoefficient().floatValue() * -1.0));
        else if (this.getCoefficient().floatValue() > 0) result.append("+ ").append(df.format(this.getCoefficient().floatValue()));

        if (this.getDegree() == 1) result.append("x ");
        else if (this.getDegree() > 1) result.append("x^").append(this.getDegree()).append(" ");

        return result.toString();
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Number getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = coefficient;
    }
}
