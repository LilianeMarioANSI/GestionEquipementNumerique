/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

/**
 *
 * @author loulo
 */
public enum StatutDemande {
    EN_COURS("En cours"),
    TERMINEE("Terminée");
    
    public final String label;

    private StatutDemande(String label) {
        this.label = label;
    }
}
