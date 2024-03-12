/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

/**
 *
 * @author loulo
 */
public enum TypeAccessoire {
    ECRAN("Écran"),
    CLAVIER("Clavier"),
    SOURIS("Souris"),
    FILTRE_CONFIDENTIALITE("Filtre de confidentialité"),
    ADAPTATEUR_VGA("Adaptateur VGA"),
    CHARGEUR("Chargeur"),
    CASQUE("Casque"),
    CLE_USB("Clé USB");
    
    public final String label;

    private TypeAccessoire(String label) {
        this.label = label;
    }
}
