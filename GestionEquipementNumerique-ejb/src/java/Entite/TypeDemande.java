/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

/**
 *
 * @author loulo
 */
public enum TypeDemande {
    DON("Don"),
    PRET("¨Prêt");
    
    public final String label;

    private TypeDemande(String label) {
        this.label = label;
    }

    //Trouve la valeur à partir du label
    public static TypeDemande valueOfLabel(String label) {
        for (TypeDemande e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
