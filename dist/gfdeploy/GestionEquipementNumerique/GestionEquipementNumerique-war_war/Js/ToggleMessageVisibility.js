/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", () => {
    var messageElement = document.getElementById("message");
    if (messageElement) {
        messageElement.addEventListener("click", (event) => {
            event.target.style.opacity = "0";
            setTimeout(function() {
                event.target.remove();
            },200);
        });
    }
});
