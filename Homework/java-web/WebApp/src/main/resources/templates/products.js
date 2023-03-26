const productTable = document.getElementById('product-table');
const productForm = document.getElementById('product-form');
const createProductButton = document.getElementById('create-product-button');
const productDialog = document.getElementById('product-dialog');
const cancelProductButton = document.getElementById('cancel-product-button');
const saveProductButton = document.getElementById('save-product-button');
const imageDialog = document.getElementById('image-dialog');
const imagePreview = document.getElementById('image-preview');

// Fetch all products from the backend and add them to the table
fetch('http://localhost:8080/products')
    .then(response => response.json())
    .then(products => {
        products.forEach(product => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.rating}</td>
                <td>${product.quantityOnStock}</td>
                <td>
                    <button class="btn-edit" data-id="${product.id}">Edit</button>
                    <button class="btn-picture" data-id="${product.id}">Picture</button>
                </td>
            `;
            productTable.appendChild(row);
        });
    })
    .catch(error => console.error('Error fetching products:', error));

// Show the product dialog when the "Create Product" button is clicked
createProductButton.addEventListener('click', () => {
    productForm.reset();
    productDialog.showModal();
});

// Hide the product dialog when the "Cancel" button is clicked
cancelProductButton.addEventListener('click', () => {
    productDialog.close();
});

// Show a preview of the selected image when it is uploaded
// image.addEventListener('change', () => {
//     const file = image.files[0];
//     const reader = new FileReader();
//     reader.addEventListener('load', () => {
//         imagePreview.src = reader.result;
//         imageDialog.showModal();
//     });
//     reader.readAsDataURL(file);
// });

// Save the new product or updated product when the "Save" button is clicked
saveProductButton.addEventListener('click', () => {
    const productData = new FormData(productForm);
    const imageInput = document.getElementById('product-image');
    let imageFile;
    if (imageInput) {
        imageFile = imageInput.files[0];
        productData.append('file', imageFile);
    }
    fetch('http://localhost:8080/products', {
        method: 'POST',
        body: productData
    })
        .then(response => response.json())
        .then(product => {
            const row = document.createElement('tr');
            row.innerHTML = `
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.rating}</td>
        <td>${product.quantityOnStock}</td>
        <td>
          <button class="btn-edit" data-id="${product.id}">Edit</button>
          <button class="btn-picture" data-id="${product.id}">Picture</button>
        </td>
      `;
            productTable.appendChild(row);
            productDialog.close();
        })
        .catch(error => console.error('Error saving product:', error));
});

// Handle the "Edit" button clicks by showing the product dialog with the current data
productTable.addEventListener('click', event => {
    const target = event.target;

    if (target.matches('.btn-edit')) {
        const id = target.dataset.id;
        fetch(`http://localhost:8080/products/${id}`)
            .then(response => response.json())
            .then(product => {
                // Open the dialog
                productDialog.showModal();

                // Set the form values
                productForm.name.value = product.name;
                productForm.price.value = product.price;
                productForm.rating.value = product.rating;
                productForm.quantityOnStock.value = product.quantityOnStock;

                // Add an event listener for the form submit
                productForm.addEventListener('submit', event => {
                    event.preventDefault();

                    // Get the form values
                    const name = productForm.name.value;
                    const price = productForm.price.value;
                    const rating = productForm.rating.value;
                    const quantityOnStock = productForm.quantityOnStock.value;

                    // Update the product on the server
                    fetch(`http://localhost:8080/products/${id}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            name,
                            price,
                            rating,
                            quantityOnStock
                        })
                    })
                        .then(response => response.json())
                        .then(product => {
                            // Close the dialog
                            productDialog.close();

                            // Update the product in the table
                            // const productRow = document.querySelector(`tr[data-id="${product.id}"]`);
                            // productRow.querySelector('.product-name').textContent = product.name;
                            // productRow.querySelector('.product-price').textContent = product.price;
                            // productRow.querySelector('.product-rating').textContent = product.rating;
                            // productRow.querySelector('.product-quantity').textContent = product.quantity;
                        })
                        .catch(error => {
                            console.error(error);
                        });
                });
            })
            .catch(error => {
                console.error(error);
            });
    }
});

