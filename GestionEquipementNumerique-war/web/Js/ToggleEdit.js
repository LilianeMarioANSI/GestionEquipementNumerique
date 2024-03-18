/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let toggleEditBtn = document.getElementById("toggleEdit");
let editableInputs = document.querySelectorAll(".editable");
let formWithEditableInput = document.getElementById("editForm");

toggleEditBtn.addEventListener("click", () => {
    editableInputs.forEach((input) => {
        
        if(input.tagName.toLowerCase() === "input"){
            input.readOnly = !input.readOnly;
        }else if(input.tagName.toLowerCase() === "select"){
            input.disabled = !input.disabled;
        }
    });
    
    if (toggleEditBtn.dataset.state === "edit") {
        toggleEditBtn.dataset.state = "reset";
        formWithEditableInput.reset();
        toggleEditBtn.innerText = "Modifier";
        toggleEditBtn.classList.remove("delete");
        toggleEditBtn.classList.add("edit");
    } else {
        toggleEditBtn.dataset.state = "edit";
        toggleEditBtn.innerText = "Annuler";
        toggleEditBtn.classList.remove("edit");
        toggleEditBtn.classList.add("delete");
    }
});