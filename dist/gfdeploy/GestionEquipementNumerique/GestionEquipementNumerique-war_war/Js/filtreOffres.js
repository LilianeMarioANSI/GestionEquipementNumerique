/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function() {
                // Sélectionnez les éléments nécessaires
                const filterDon = document.getElementById("filterDon");
                const filterPret = document.getElementById("filterPret");
                const etatEquipement = document.getElementById("etatEquipement");
                const categorie = document.getElementById("categorie");
                const applyFilterBtn = document.getElementById("applyFilterBtn");
                const resetFilterBtn = document.getElementById("resetFilterBtn");
                const offreItems = document.querySelectorAll(".offre-item");

                // Fonction pour appliquer le filtre
                function appliquerFiltre() {
                    const etatSelectionne = etatEquipement.value;
                    const categorieSelectionnee = categorie.value;
                    const montrerDon = filterDon.checked;
                    const montrerPret = filterPret.checked;

                    offreItems.forEach(function(offreItem) {
                        const etatOffre = offreItem.dataset.etat;
                        const categorieOffre = offreItem.dataset.categorie;
                        const estDon = offreItem.dataset.offreType === "DON";
                        const estPret = offreItem.dataset.offreType === "PRET";

                        if ((montrerDon && estDon) || (montrerPret && estPret)) {
                            if (etatSelectionne === "Tous" || etatSelectionne === etatOffre) {
                                if (categorieSelectionnee === "Toutes" || categorieSelectionnee === categorieOffre) {
                                    offreItem.style.display = "table-row";
                                    return;
                                }
                            }
                        }
                        offreItem.style.display = "none";
                    });
                }

                // Fonction pour réinitialiser le filtre
                function reinitialiserFiltre() {
                    offreItems.forEach(function(offreItem) {
                        offreItem.style.display = "table-row";
                    });
                    filterDon.checked = false;
                    filterPret.checked = false;
                    etatEquipement.value = "Tous";
                    categorie.value = "Toutes";
                }

                // Ajouter un gestionnaire d'événements pour le bouton "Appliquer"
                applyFilterBtn.addEventListener("click", appliquerFiltre);

                // Ajouter un gestionnaire d'événements pour le bouton "Réinitialiser"
                resetFilterBtn.addEventListener("click", reinitialiserFiltre);
            });


