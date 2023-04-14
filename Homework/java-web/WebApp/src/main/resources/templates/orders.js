const ordersTable = document.querySelector('#orders-table tbody');
const addOrderButton = document.querySelector('#add-order-button');
const orderDialog = document.querySelector('#order-dialog');
const orderForm = document.querySelector('#order-form');
const saveOrderButton = document.querySelector('#save-order-button');
const cancelOrderButton = document.querySelector('#cancel-order-button');

let orders = [];

// Get all orders from backend and display them in the table
async function getOrders() {
    const response = await fetch('/orders');
    if (!response.ok) {
        console.error(`Failed to get orders: ${response.status} ${response.statusText}`);
        return;
    }
    orders = await response.json();
    renderOrders();
}

// Create a new order
async function createOrder(order) {
    const response = await fetch('/orders', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(order)
    });
    if (!response.ok) {
        console.error(`Failed to create order: ${response.status} ${response.statusText}`);
        return;
    }
    const newOrder = await response.json();
    orders.push(newOrder);
    renderOrders();
}

// Update an existing order
async function updateOrder(order) {
    const response = await fetch(`/orders/${order.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(order)
    });
    if (!response.ok) {
        console.error(`Failed to update order: ${response.status} ${response.statusText}`);
        return;
    }
    const updatedOrder = await response.json();
    orders = orders.map(o => o.id === updatedOrder.id ? updatedOrder : o);
    renderOrders();
}

// Delete an order
async function deleteOrder(id) {
    const response = await fetch(`/orders/${id}`, { method: 'DELETE' });
    if (!response.ok) {
        console.error(`Failed to delete order: ${response.status} ${response.statusText}`);
        return;
    }
    orders = orders.filter(o => o.id !== id);
    renderOrders();
}

// Populate the order form with the given order
function populateOrderForm(order) {
    orderForm.reset();
    orderForm.elements['id'].value = order.id;
    orderForm.elements['quantity'].value = order.quantity;
    orderForm.elements['date'].value = order.date;
    orderForm.elements['productId'].value = order.productId;
    orderForm.elements['customerId'].value = order.customerId;
}

// Show the order dialog and populate the form if given an order
function showOrderDialog(order) {
    populateOrderForm(order || { id: '', quantity: '', date: '', productId: '', customerId: '' });
    orderDialog.showModal();
}

// Hide the order dialog
function hideOrderDialog() {
    orderDialog.close();
}

// Render the orders in the table
function renderOrders() {
    fetch('/orders')
        .then(response => response.json())
        .then(orders => {
            const ordersTable = document.querySelector('#orders-table tbody');
            ordersTable.innerHTML = '';

            orders.forEach(order => {
                const row = document.createElement('tr');
                row.innerHTML = `
          <td>${order.id}</td>
          <td>${order.quantity}</td>
          <td>${order.date}</td>
          <td>${order.productId}</td>
          <td>${order.customerId}</td>
          <td>
            <button class="edit-button" data-id="${order.id}">Edit</button>
            <button class="delete-button" data-id="${order.id}">Delete</button>
          </td>
        `;
                ordersTable.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching orders:', error);
        });
}


