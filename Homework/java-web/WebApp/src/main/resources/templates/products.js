const productTable = document.getElementById('product-table');
const productForm = document.getElementById('product-form');
const createProductButton = document.getElementById('create-product-button');
const productDialog = document.getElementById('product-dialog');
const cancelProductButton = document.getElementById('cancel-product-button');
const saveProductButton = document.getElementById('save-product-button');
const imageDialog = document.getElementById('image-dialog');
const imagePreview = document.getElementById('image-preview');

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
                    <button class="btn-delete" data-id="${product.id}">Delete</button>
                </td>
            `;
            productTable.appendChild(row);



            const pictureButton = row.querySelector('.btn-picture');
            pictureButton.addEventListener('click', () => {
                const id = pictureButton.dataset.id;
                fetch(`http://localhost:8080/products/${id}/picture`)
                    .then(response => response.arrayBuffer())
                    .then(buffer => {
                        const blob = new Blob([buffer]);
                        const imageUrl = URL.createObjectURL(blob);
                        imagePreview.src = imageUrl;
                        imageDialog.showModal();
                    })
                    .catch(error => {
                        console.error(error);
                    });
            });

            const deleteButton = row.querySelector('.btn-delete');
            deleteButton.addEventListener('click', () => {
                const id = deleteButton.dataset.id;
                fetch(`http://localhost:8080/products/${id}`, {
                    method: 'DELETE'
                })
                    .then(response => {
                        if (response.ok) {
                            row.remove();
                        } else {
                            throw new Error('Error deleting product');
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });
            });
        });
    })
    .catch(error => console.error('Error fetching products:', error));


createProductButton.addEventListener('click', () => {
    productForm.reset();
    productDialog.showModal();
});


cancelProductButton.addEventListener('click', () => {
    productDialog.close();
});

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

productTable.addEventListener('click', event => {
    const target = event.target;

    if (target.matches('.btn-edit')) {
        const id = target.dataset.id;
        fetch(`http://localhost:8080/products/${id}`)
            .then(response => response.json())
            .then(product => {

                productDialog.showModal();

                productForm.name.value = product.name;
                productForm.price.value = product.price;
                productForm.rating.value = product.rating;
                productForm.quantityOnStock.value = product.quantityOnStock;

                productForm.addEventListener('submit', event => {
                    event.preventDefault();

                    const name = productForm.name.value;
                    const price = productForm.price.value;
                    const rating = productForm.rating.value;
                    const quantityOnStock = productForm.quantityOnStock.value;

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
                            productDialog.close();

                            const productRow = document.querySelector(`tr[data-id="${product.id}"]`);
                            productRow.querySelector('.product-name').textContent = product.name;
                            productRow.querySelector('.product-price').textContent = product.price;
                            productRow.querySelector('.product-rating').textContent = product.rating;
                             productRow.querySelector('.product-quantity').textContent = product.quantity;
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

