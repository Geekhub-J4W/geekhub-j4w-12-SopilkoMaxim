const form = document.getElementById('register-form');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm-password');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    if (password.value !== confirmPassword.value) {
        alert('Passwords do not match. Please try again.');
        return;
    }

    const formData = new FormData(form);
    const data = {};
    for (let [key, value] of formData.entries()) {
        data[key] = value;
    }

    try {
        const response = await fetch('/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error('An error occurred while processing your request.');
        }

        const responseData = await response.json();
        console.log(responseData);
    } catch (error) {
        console.error(error);
    }
});
