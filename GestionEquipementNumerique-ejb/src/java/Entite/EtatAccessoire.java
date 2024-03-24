/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

/**
 *
 * @author loulo
 */
public enum EtatAccessoire {
    NEUF("Neuf"),
    TRES_BON_ETAT("Tres bon etat"),
    BON_ETAT("Bon etat"),
    CORRECT("correct"),
    MAUVAIS("Mauvais");
    
    
    public final String label;

    private EtatAccessoire(String label) {
        this.label = label;
    }

    public static EtatAccessoire valueOfLabel(String label) {
        for (EtatAccessoire e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
