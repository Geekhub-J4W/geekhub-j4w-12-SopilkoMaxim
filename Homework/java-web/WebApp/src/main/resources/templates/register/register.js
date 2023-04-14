const form = document.getElementById('register-form');
const password = document.getElementById('password');
const passwordConfirm = document.getElementById('password-confirm');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    if (password.value !== passwordConfirm.value) {
        alert('Passwords do not match. Please try again.');
        return;
    }

    const email = document.getElementById('email').value;
    const name = document.getElementById('name').value;
    const age = document.getElementById('age').value;

    const data = {
        email,
        password: password.value,
        name,
        age
    };

    fetch('your_api_endpoint', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
});
