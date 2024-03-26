/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entite;

/**
 *
 * @author loulo
 */
public enum Agence {
    AIX_EN_PROVENCE("Aix-en-provence"),
    AMIENS("Amiens"),
    BORDEAUX("Bordeaux"),
    BREST("Brest"),
    CLERMONT_FERRAND("Clermond Ferrand"),
    COLMAR("Colmar"),
    GRENOBLE("Grenoble"),
    LAXOU("Laxou"),
    LE_MANS("Le Mans"),
    LILLE("Lille"),
    LIMOGES("Limoges"),
    LORIENT("Lorient"),
    LYON("Lyon"),
    MONTBELIARD("Montbeliard"),
    MONTPELLIER("Montpellier"),
    NANTES("Nantes"),
    NIORT("Niort"),
    ORLEANS("Orléans"),
    PAU("Pau"),
    PUTEAUX("Puteaux"),
    RENNES("Rennes"),
    SAINT_AVERTIN("Saint-Avertin"),
    SOPHIA_ANTIPOLIS("Sophia-Antipolis"),
    STRASBOURG("Strasbourg"),
    TARNOS("Tarnos"),
    TOULOUSE("Toulouse"),
    TOURS("Tours");
    
    public final String label;

    private Agence(String label) {
        this.label = label;
    }
    
    //Trouve la valeur à partir du label
    public static Agence valueOfLabel(String label) {
        for (Agence e : values()) {
            if (e.label.equalsIgnoreCase(label)) {
                return e;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return label;
    }
    
}
