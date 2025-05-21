<<<<<<< HEAD
# E-commerce Order Processing System

A full-stack application for managing customer orders in an E-commerce scenario, featuring a Spring Boot backend and a React frontend. Supports order creation, status tracking, listing, updating, and cancellation, with a scheduled job for automatic status updates.

---

## Features
- Create an order with multiple items (via UI or API)
- Retrieve order details by ID
- Update order status (PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED)
- List all orders, optionally filtered by status
- Cancel an order (if still PENDING)
- Scheduled job: automatically updates PENDING orders to PROCESSING every 5 minutes
- Modern React frontend with Material-UI
- CORS enabled for local development

---

## Backend (Spring Boot)

### API Endpoints

#### Create Order
- **POST** `/orders`
- **Request Body:**
```json
{
  "items": [
    { "productId": "prod-1", "quantity": 2, "price": 10.0 },
    { "productId": "prod-2", "quantity": 1, "price": 20.0 }
  ]
}
```
- **Response:**
```json
{
  "id": "...",
  "items": [...],
  "status": "PENDING",
  "createdAt": "...",
  "updatedAt": "..."
}
```

#### Get Order by ID
- **GET** `/orders/{id}`
- **Response:** Order object or 404 if not found

#### Update Order Status
- **PUT** `/orders/{id}/status`
- **Request Body:**
```json
{
  "status": "SHIPPED"
}
```
- **Response:** Updated Order object or 404 if not found

#### List Orders
- **GET** `/orders?status=PENDING`
- **Response:** Array of Order objects

#### Cancel Order
- **POST** `/orders/{id}/cancel`
- **Response:** Cancelled Order object or 400 if not allowed

### CORS Configuration
CORS is enabled for `http://localhost:3000` (the frontend) via `WebConfig.java`.

### How to Run Backend
1. Ensure you have Java 17+ and Maven installed.
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
4. The API will be available at `http://localhost:8080/orders`

### How to Run Backend Tests
Run all tests with:
```sh
mvn test
```

---

## Frontend (React)

### Features
- View all orders in a sortable/filterable table
- Create new orders by adding multiple items (Product ID, Quantity)
- View order details, including items and status
- Update order status (including to CANCELLED)
- Cancel pending orders
- Responsive, modern UI with Material-UI

### How to Run Frontend
1. Ensure you have Node.js and npm installed.
2. In a new terminal, go to the `frontend` directory:
   ```sh
   cd frontend
   ```
3. Install dependencies:
   ```sh
   npm install
   ```
4. Start the development server:
   ```sh
   npm start
   ```
5. Open [http://localhost:3000](http://localhost:3000) in your browser.

### Notes
- The frontend currently collects Product ID and Quantity for each item. Price is not collected in the UI, so the backend will use a default or zero price unless you modify the frontend to include it.
- The backend uses in-memory storage (no database). All data will be lost when the backend restarts.
- CORS is enabled for local development.

---

## Project Structure

- `src/main/java/com/example/orders/` - Spring Boot backend
- `frontend/` - React frontend

---

## Demo Walkthrough
1. **Start the backend** (`mvn spring-boot:run`)
2. **Start the frontend** (`npm start` in `frontend`)
3. **Create an order** via the UI (add at least one item)
4. **View, update, or cancel orders** from the UI
5. **API can also be tested with Postman or curl (see above)**

---

## Interviewer Notes
- The project demonstrates a full-stack order management system with RESTful API and a modern React UI.
- CORS, error handling, and basic validation are implemented.
- The backend is stateless and easy to extend for database support.
- Please see `API_EXAMPLES.md` for more sample requests and responses. 
=======
# orderManagement
>>>>>>> b0aed2110144b53def8047048825f24cb676b9d2
