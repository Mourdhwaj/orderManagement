# API Usage Examples

## Create Order
**Request:**
```sh
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "items": [
      { "productId": "prod-1", "quantity": 2, "price": 10.0 },
      { "productId": "prod-2", "quantity": 1, "price": 20.0 }
    ]
  }'
```
**Response:**
```json
{
  "id": "a1b2c3d4-...",
  "items": [
    { "productId": "prod-1", "quantity": 2, "price": 10.0 },
    { "productId": "prod-2", "quantity": 1, "price": 20.0 }
  ],
  "status": "PENDING",
  "createdAt": "2024-05-01T12:00:00",
  "updatedAt": "2024-05-01T12:00:00"
}
```

---

## Get Order by ID
**Request:**
```sh
curl http://localhost:8080/orders/{orderId}
```
**Response:**
```json
{
  "id": "a1b2c3d4-...",
  "items": [...],
  "status": "PENDING",
  "createdAt": "2024-05-01T12:00:00",
  "updatedAt": "2024-05-01T12:00:00"
}
```

---

## Update Order Status
**Request:**
```sh
curl -X PUT http://localhost:8080/orders/{orderId}/status \
  -H "Content-Type: application/json" \
  -d '{ "status": "SHIPPED" }'
```
**Response:**
```json
{
  "id": "a1b2c3d4-...",
  "items": [...],
  "status": "SHIPPED",
  "createdAt": "2024-05-01T12:00:00",
  "updatedAt": "2024-05-01T12:05:00"
}
```

---

## List Orders (all or by status)
**Request:**
```sh
curl http://localhost:8080/orders
curl http://localhost:8080/orders?status=PROCESSING
```
**Response:**
```json
[
  {
    "id": "a1b2c3d4-...",
    "items": [...],
    "status": "PROCESSING",
    "createdAt": "2024-05-01T12:00:00",
    "updatedAt": "2024-05-01T12:05:00"
  },
  ...
]
```

---

## Cancel Order
**Request:**
```sh
curl -X POST http://localhost:8080/orders/{orderId}/cancel
```
**Response:**
```json
{
  "id": "a1b2c3d4-...",
  "items": [...],
  "status": "CANCELLED",
  "createdAt": "2024-05-01T12:00:00",
  "updatedAt": "2024-05-01T12:10:00"
}
``` 