package com.example.polynomial.model;

import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;

public class Polynomial {
    public TreeMap<Integer, Monomial> map;

    public Polynomial(TreeMap<Integer, Monomial> map) {
        this.map = map;
    }
    public Polynomial(String string) {
        string= string.replaceAll("-\\s+","-");
        string = string.replaceAll("\\+\\s+","+");
        String[] monomials = string.split("\\s+");
        this.map = new TreeMap<>(Collections.reverseOrder());
        for(String monomialString : monomials){
            Monomial monomial = new Monomial(monomialString);
            this.map.put(monomial.degree,monomial);
        }
    }
    public Polynomial() {
        this.map = new TreeMap<>(Collections.reverseOrder());
    }
    public Polynomial(Monomial monomial) {
        this.map = new TreeMap<>(Collections.reverseOrder());
        this.map.put(monomial.degree,monomial);
    }

    public Polynomial add(Polynomial second){
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer,Monomial> entry : this.map.entrySet()){
                Monomial monomial = entry.getValue().add(second.map.get(entry.getKey()));
                result.map.put(monomial.degree,monomial);
        }
        for(Map.Entry<Integer,Monomial> entry : second.map.entrySet()){
            if(!this.map.containsKey(entry.getKey())) {
                result.map.put(entry.getKey(),entry.getValue());
            }
        }
        return result;
    }
    public Polynomial invert() {
        Polynomial inverted = new Polynomial();
        for (Map.Entry<Integer,Monomial> entry : this.map.entrySet()) {
            inverted.map.put(entry.getKey(), entry.getValue().invert());
        }
        return inverted;
    }
    public Polynomial subtract(Polynomial second){
        return this.add(second.invert());
    }
    public Polynomial product(Polynomial second){
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer,Monomial> entry : this.map.entrySet()){
            for(Map.Entry<Integer,Monomial> entry2 : second.map.entrySet()) {
                Monomial monomial = entry.getValue().product(entry2.getValue());
                result = result.add(new Polynomial(monomial));
            }
        }
        return result;
    }
    public Polynomial[] div(Polynomial divisor) {
        Polynomial dividend = this;
        Polynomial quotient = new Polynomial();

        while (!dividend.map.isEmpty()  && dividend.map.firstKey() >= divisor.map.firstKey()) {
            int currentDegree = dividend.map.firstKey() - divisor.map.firstKey();
            Monomial leadingDividend = dividend.map.firstEntry().getValue();
            Monomial leadingDivisor = divisor.map.firstEntry().getValue();
            Monomial termQuotient = leadingDividend.div(leadingDivisor);
            Polynomial product = new Polynomial(termQuotient).product(divisor);
            dividend = dividend.subtract(product);
            dividend.map.entrySet().removeIf(entry -> entry.getValue().coefficient.floatValue() == 0.0);
            quotient.map.put(currentDegree, termQuotient);
        }
        return new Polynomial[]{quotient, dividend};
    }



    public Polynomial derivate() {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer,Monomial> entry : this.map.entrySet()) {
            result.map.put(entry.getKey(), entry.getValue().derivate());
        }
        return result;
    }
    public Polynomial integrate() {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer,Monomial> entry : this.map.entrySet()) {
            result.map.put(entry.getKey(), entry.getValue().integrate());
        }
        return result;
    }
@Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        int first = 0;
    for(Map.Entry<Integer, Monomial> entry : this.map.entrySet()){
        if(first == 0) {
            stringBuilder.append(entry.getValue().toString().replaceAll("\\+\\s+","").replaceAll("-\\s+","-"));
            first = 1;
        } else {
            stringBuilder.append(entry.getValue().toString());
        }
    }
    return stringBuilder.toString();
    }
}
