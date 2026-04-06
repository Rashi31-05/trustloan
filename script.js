function goToLogin(role) {
    localStorage.setItem("userRole", role);
    window.location.href = "login.html";
}

function goToForm() {
    window.location.href = "form.html";
}

if(document.getElementById("loanForm")){
    document.getElementById("loanForm").addEventListener("submit", function(e) {
        e.preventDefault();

        const formData = new FormData(this);
        const data = Object.fromEntries(formData.entries());

        localStorage.setItem("loanData", JSON.stringify(data));

        window.location.href = "success.html";
    });
}