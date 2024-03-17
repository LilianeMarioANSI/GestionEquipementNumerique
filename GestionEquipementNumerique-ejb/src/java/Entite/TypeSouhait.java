/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

/**
 *
 * @author loulo
 */
public enum TypeSouhait {
    DON("Don"),
    PRET("Prêt");
    
    public final String label;

    private TypeSouhait(String label) {
        this.label = label;
    }
    
    //Trouve la valeur à partir du label
    public static TypeSouhait valueOfLabel(String label) {
        for (TypeSouhait e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
