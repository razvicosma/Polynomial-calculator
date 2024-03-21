package com.example.polynomial.model;

import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;

public class Polynomial {
    private TreeMap<Integer, Monomial> map;

    public Polynomial(TreeMap<Integer, Monomial> map) {
        this.setMap(map);
    }
    public Polynomial(String string) {
        string= string.replaceAll("-\\s+","-");
        string = string.replaceAll("\\+\\s+","+");
        String[] monomials = string.split("\\s+");
        this.setMap(new TreeMap<>(Collections.reverseOrder()));
        for(String monomialString : monomials){
            Monomial monomial = new Monomial(monomialString);
            this.getMap().put(monomial.getDegree(),monomial);
        }
    }
    public Polynomial() {
        this.setMap(new TreeMap<>(Collections.reverseOrder()));
    }
    public Polynomial(Monomial monomial) {
        this.setMap(new TreeMap<>(Collections.reverseOrder()));
        this.getMap().put(monomial.getDegree(),monomial);
    }

    public Polynomial add(Polynomial second){
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer,Monomial> entry : this.getMap().entrySet()){
                Monomial monomial = entry.getValue().add(second.getMap().get(entry.getKey()));
                result.getMap().put(monomial.getDegree(),monomial);
        }
        for(Map.Entry<Integer,Monomial> entry : second.getMap().entrySet()){
            if(!this.getMap().containsKey(entry.getKey())) {
                result.getMap().put(entry.getKey(),entry.getValue());
            }
        }
        return result;
    }
    public Polynomial invert() {
        Polynomial inverted = new Polynomial();
        for (Map.Entry<Integer,Monomial> entry : this.getMap().entrySet()) {
            inverted.getMap().put(entry.getKey(), entry.getValue().invert());
        }
        return inverted;
    }
    public Polynomial subtract(Polynomial second){
        return this.add(second.invert());
    }
    public Polynomial product(Polynomial second){
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer,Monomial> entry : this.getMap().entrySet()){
            for(Map.Entry<Integer,Monomial> entry2 : second.getMap().entrySet()) {
                Monomial monomial = entry.getValue().product(entry2.getValue());
                result = result.add(new Polynomial(monomial));
            }
        }
        return result;
    }
    public Polynomial[] div(Polynomial divisor) throws ArithmeticException{
        Polynomial dividend = this;
        Polynomial quotient = new Polynomial();
        if(divisor.getMap().firstKey() > dividend.getMap().firstKey()) throw new ArithmeticException("Second polynomial is greater than the first");
        while (!dividend.getMap().isEmpty()  && dividend.getMap().firstKey() >= divisor.getMap().firstKey()) {
            int currentDegree = dividend.getMap().firstKey() - divisor.getMap().firstKey();
            Monomial leadingDividend = dividend.getMap().firstEntry().getValue();
            Monomial leadingDivisor = divisor.getMap().firstEntry().getValue();
            Monomial termQuotient = leadingDividend.div(leadingDivisor);
            Polynomial product = new Polynomial(termQuotient).product(divisor);
            dividend = dividend.subtract(product);
            dividend.getMap().entrySet().removeIf(entry -> entry.getValue().getCoefficient().floatValue() == 0.0);
            quotient.getMap().put(currentDegree, termQuotient);
        }
        return new Polynomial[]{quotient, dividend};
    }



    public Polynomial derivate() {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer,Monomial> entry : this.getMap().entrySet()) {
            result.getMap().put(entry.getKey(), entry.getValue().derivate());
        }
        return result;
    }
    public Polynomial integrate() {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer,Monomial> entry : this.getMap().entrySet()) {
            result.getMap().put(entry.getKey(), entry.getValue().integrate());
        }
        return result;
    }
@Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        int first = 0;
    for(Map.Entry<Integer, Monomial> entry : this.getMap().entrySet()){
        if(first == 0) {
            stringBuilder.append(entry.getValue().toString().replaceAll("\\+\\s+","").replaceAll("-\\s+","-"));
            first = 1;
        } else {
            stringBuilder.append(entry.getValue().toString());
        }
    }
    if (stringBuilder.toString().isEmpty()) return "0";
    else return stringBuilder.toString();
    }

    public TreeMap<Integer, Monomial> getMap() {
        return map;
    }

    public void setMap(TreeMap<Integer, Monomial> map) {
        this.map = map;
    }
}
