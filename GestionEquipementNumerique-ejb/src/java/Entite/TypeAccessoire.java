/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

import static Entite.TypeSouhait.values;

/**
 *
 * @author loulo
 */
public enum TypeAccessoire {
    ECRAN("Ecran"),
    CLAVIER("Clavier"),
    SOURIS("Souris"),
    FILTRE_CONFIDENTIALITE("Filtre de confidentialite"),
    ADAPTATEUR_VGA("Adaptateur VGA"),
    CHARGEUR("Chargeur"),
    CASQUE("Casque"),
    CLE_USB("Cle USB");
    
    public final String label;

    private TypeAccessoire(String label) {
        this.label = label;
    }
    
    
    //Trouve la valeur à partir du label
    public static TypeAccessoire valueOfLabel(String label) {
        for (TypeAccessoire e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
