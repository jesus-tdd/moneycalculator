package es.ulpgc.software.moneycalculator.architecture.model;

public record Currency(String name, String code) {
    @Override
    public String toString() {
        return code;
    }
}
