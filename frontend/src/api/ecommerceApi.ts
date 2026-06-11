import { http } from './http';
import type { LoginResponse, OrderResponse, Product } from '../types/models';

export async function login(email: string, password: string): Promise<LoginResponse> {
  const response = await http.post<LoginResponse>('/api/v1/auth/login', { email, password });
  return response.data;
}

export async function listProducts(): Promise<Product[]> {
  const response = await http.get<Product[]>('/api/v1/products');
  return response.data;
}

export async function checkout(params: {
  customerId: string;
  shippingZipCode: string;
  couponCode?: string | null;
  items: Array<{ productId: string; quantity: number }>;
}): Promise<OrderResponse> {
  const response = await http.post<OrderResponse>('/api/v1/orders/checkout', params, {
    headers: {
      'Idempotency-Key': `frontend-${Date.now()}`
    }
  });

  return response.data;
}

export async function approvePayment(orderId: string): Promise<void> {
  await http.post(`/api/v1/payments/orders/${orderId}/approve`, null, {
    params: {
      externalReference: `PAY-${Date.now()}`
    }
  });
}
