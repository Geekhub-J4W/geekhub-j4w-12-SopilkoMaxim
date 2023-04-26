fetch('http://localhost:8080/superadmin/users')
    .then(response => response.json())
    .then(users => {
        const userTable = document.getElementById('user-table');
        users.forEach(user => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.fullName}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
                <td>
                    <button type="button" class="btn-edit" data-id="${user.id}">Edit</button>
                </td>
            `;
            userTable.appendChild(row);

        });
    });


const userForm = document.getElementById('user-form');
if (userForm) {
    userForm.addEventListener('submit', event => {
        event.preventDefault();
        const formData = new FormData(userForm);
        const user = {
            email: formData.get('email'),
            password: formData.get('password'),
            fullName: formData.get('full-name'),
            role: formData.get('role'),
            status: formData.get('status')
        };
        fetch('/superadmin/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(response => response.json())
            .then(newUser => {
                const userTable = document.getElementById('user-table');
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${newUser.id}</td>
                <td>${newUser.email}</td>
                <td>${newUser.fullName}</td>
                <td>${newUser.role}</td>
                <td>${newUser.status}</td>
            `;
                userTable.appendChild(row);
                userForm.reset();
                location.reload();
            });
    });
}


async function deleteUser() {
    const id = document.getElementById('id').value;
    await fetch(`http://localhost:8080/superadmin/users/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                console.log(`Deleted user with ID ${id}`);
            } else {
                console.error(`Failed to delete user with ID ${id}`);
            }
        })
        .catch(error => console.error(error));
}


document.querySelector("#user-table").addEventListener('click', (event) => {
    if (!event.target.getAttribute("data-id")) {
        return;
    }
    const button = event.target;
    const userId = button.getAttribute('data-id');

    fetch(`/superadmin/users/${userId}`)
        .then(response => response.json())
        .then(user => {
            const dialog = window.open('', 'Edit User', 'width=400,height=300');

            const formHtml = `
                <form id="edit-form" method="POST">
                    <input type="hidden" name="_method" value="PUT">
                    <label for="email">Email:</label>
                    <input type="email" name="email" value="${user.email}" required>
                    <br>
                    <label for="password">Password:</label>
                    <input type="password" name="password" value="${user.password}" required>
                    <br>
                    <label for="full-name">Full Name:</label>
                    <input type="text" name="full-name" value="${user.full_name}" required>
                    <br>
                    <label for="role">Role:</label>
                    <select name="role" required>
                        <option value="USER" ${user.role === "USER" ? 'selected' : ''}>User</option>
                        <option value="ADMIN" ${user.role === "ADMIN" ? 'selected' : ''}>Admin</option>
                        <option value="SUPERADMIN" ${user.role === "SUPERADMIN" ? 'selected' : ''}>Superadmin</option>
                    </select>
                    <br>
                    <label for="status">Status:</label>
                    <select name="status" required>
                        <option value="ACTIVE" ${user.status === "ACTIVE" ? 'selected' : ''}>Active</option>
                        <option value="BANNED" ${user.status === "BANNED" ? 'selected' : ''}>Banned</option>
                    </select>
                    <br>
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit">Save Changes</button>
                </form>
            `;
            dialog.document.body.innerHTML = formHtml;

            const editForm = dialog.document.getElementById('edit-form');
            editForm.addEventListener('submit', event => {
                event.preventDefault();

                const formData = new FormData(editForm);
                const user = {
                    id: formData.get('id'),
                    email: formData.get('email'),
                    password: formData.get('password'),
                    full_name: formData.get('full-name'),
                    role: formData.get('role'),
                    status: formData.get('status')
                };
                fetch(`/superadmin/users/${userId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(user)
                })
                    .then(response => response.json())
                    .then(response => {
                        if (response.ok) {
                            dialog.close();
                            location.reload();
                        } else {
                            throw new Error('Failed to update user data!');
                        }
                    })
                    .catch(error => {
                        alert(error.message);
                    });
            });
        })
        .catch(error => {
            alert(error.message);
        });
});
