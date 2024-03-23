/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

/**
 *
 * @author loulo
 */
public enum TypeOffre {
    DON("Don"),
    PRET("Pret");
    
    public final String label;

    private TypeOffre(String label) {
        this.label = label;
    }

    //Trouve la valeur à partir du label
    public static TypeOffre valueOfLabel(String label) {
        for (TypeOffre e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
