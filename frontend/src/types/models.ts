export type Product = {
  id: string;
  sku?: string;
  name: string;
  price: number | { amount: number };
  active?: boolean;
};

export type CartItem = {
  product: Product;
  quantity: number;
};

export type LoginResponse = {
  accessToken: string;
};

export type OrderResponse = {
  id: string;
  customerId: string;
  status: string;
  subtotal: number;
  discount: number;
  shipping: number;
  total: number;
};
