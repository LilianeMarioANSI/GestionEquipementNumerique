/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


let mdp = document.getElementById("mdpRegister");
let confirm_mdp = document.getElementById("confirmMdp");

function validatePassword(){
    if(mdp.value === confirm_mdp.value) {
        confirm_mdp.setCustomValidity('');
    }else{
        confirm_mdp.setCustomValidity("Échec de la validation !");
    }
}

confirm_mdp.addEventListener("input", validatePassword);
mdp.addEventListener("input", validatePassword);
