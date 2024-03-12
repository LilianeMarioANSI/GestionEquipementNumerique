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
    QUASI_NEUF("Quasi neuf"),
    USAGE("Usagé"),
    MAUVAIS("Mauvais");
    
    
    public final String label;

    private EtatAccessoire(String label) {
        this.label = label;
    }
}
