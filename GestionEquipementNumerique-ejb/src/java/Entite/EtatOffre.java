/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

/**
 *
 * @author loulo
 */
public enum EtatOffre {
    DISPONIBLE("Disponible"),
    EN_COURS("En cours"),
    TERMINEE("Terminée");
    
    public final String label;

    private EtatOffre(String label) {
        this.label = label;
    }

    public static EtatOffre valueOfLabel(String label) {
        for (EtatOffre e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
    
}
