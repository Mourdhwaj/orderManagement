import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const orderService = {
  createOrder: async (items) => {
    const response = await api.post('/orders', { items });
    return response.data;
  },

  getOrderById: async (id) => {
    const response = await api.get(`/orders/${id}`);
    return response.data;
  },

  updateOrderStatus: async (id, status) => {
    const response = await api.put(`/orders/${id}/status`, { status });
    return response.data;
  },

  listOrders: async (status = null) => {
    const params = status ? { status } : {};
    const response = await api.get('/orders', { params });
    return response.data;
  },

  cancelOrder: async (id) => {
    const response = await api.post(`/orders/${id}/cancel`);
    return response.data;
  },
};

export default api; 