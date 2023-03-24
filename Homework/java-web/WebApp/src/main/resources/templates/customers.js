
fetch('http://localhost:8080/customers')
    .then(response => response.json())
    .then(customers => {
        const customerTable = document.getElementById('customer-table');
        customers.forEach(customer => {
            const row = document.createElement('tr');
            row.innerHTML = `
						<td>${customer.id}</td>
						<td>${customer.name}</td>
						<td>${customer.age}</td>
						<td>
                         <button type="button" class="btn-edit" data-id="${ customer.id }">Edit</button>
                        </td>
					`;
            customerTable.appendChild(row);
        });
    });


const customerForm = document.getElementById('customer-form');
customerForm.addEventListener('submit', event => {
    event.preventDefault();
    const formData = new FormData(customerForm);
    const customer = {
        name: formData.get('name'),
        age: formData.get('age')
    };
    fetch('/customers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(customer)
    })
        .then(response => response.json())
        .then(newCustomer => {
            const customerTable = document.getElementById('customer-table');
            const row = document.createElement('tr');
            row.innerHTML = `
	                <td>${newCustomer.name}</td>
					<td>${newCustomer.age}</td>
				`;
            customerTable.appendChild(row);
            customerForm.reset();
            location.reload();
        });
});

async function deleteCustomer() {
    const id = document.getElementById('id').value;
    await fetch(`http://localhost:8080/customers/${id}`, {
        method: 'DELETE'})
        .then(response => {
            if (response.ok) {
                console.log(`Deleted customer with ID ${id}`);
            } else {
                console.error(`Failed to delete customer with ID ${id}`);
            }
        })
        .catch(error => console.error(error));
}


document.querySelector("#customer-table").addEventListener('click', (event) => {
    if(!event.target.getAttribute("data-id")){return;}
    const button = event.target;
    const customerId = button.getAttribute('data-id');

    fetch(`/customers/customers/${customerId}`)
        .then(response => response.json())
        .then(customer => {
            const editWindow = window.open('', 'Edit Customer', 'width=400,height=300');

            const formHtml = `
          <form id="edit-form" method="PUT">
            <label for="name">Name:</label>
            <input type="text" name="name" value="${customer.name}" required>
            <br>
            <label for="age">Age:</label>
            <input type="number" name="age" value="${customer.age}" required>
            <br>
            <input type="hidden" name="id" value="${customer.id}">
            <button type="submit">Save Changes</button>
          </form>
        `;
            editWindow.document.body.innerHTML = formHtml;

            const editForm = editWindow.document.getElementById('edit-form');
            editForm.addEventListener('submit', event => {
                event.preventDefault();

                const formData = new FormData(editForm);
                const customer = {
                    id: formData.get('id'),
                    name: formData.get('name'),
                    age: formData.get('age')
                };
                fetch(`/customers/customers/${id}`, {
                    method: 'PUT',
                    //body: formData
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(customer)
                })  .then(response => response.json())
                    .then(response => {
                        if (response.ok) {
                            editWindow.close();
                            location.reload();
                        } else {
                            throw new Error('Failed to update customer data!');
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
