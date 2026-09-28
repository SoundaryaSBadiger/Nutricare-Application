document.addEventListener("DOMContentLoaded", function () {
    const dietitianSelect = document.getElementById("dietitianSelect");
    const dietitianName = document.getElementById("dietitianName");

    if (dietitianSelect && dietitianName) {
        dietitianSelect.addEventListener("change", function () {
            const option = this.options[this.selectedIndex];
            dietitianName.value = option.getAttribute("data-name") || "";
        });
    }

    const appointmentDate = document.getElementById("appointmentDate");
    if (appointmentDate) {
        const now = new Date();
        const localToday = new Date(now.getTime() - now.getTimezoneOffset() * 60000)
            .toISOString().split("T")[0];
        appointmentDate.min = localToday;
    }

    const registrationForm = document.getElementById("registrationForm");
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");

    if (registrationForm && password && confirmPassword) {
        registrationForm.addEventListener("submit", function (event) {
            if (password.value !== confirmPassword.value) {
                event.preventDefault();
                confirmPassword.setCustomValidity("Passwords do not match.");
                confirmPassword.reportValidity();
            } else {
                confirmPassword.setCustomValidity("");
            }
        });

        confirmPassword.addEventListener("input", function () {
            this.setCustomValidity(
                this.value !== password.value ? "Passwords do not match." : ""
            );
        });
    }
});
