import { Navigate, Route, Routes } from 'react-router-dom';
import { useMemo, useState } from 'react';
import { Header } from './components/Header';
import { LoginPage } from './pages/LoginPage';
import { ProductsPage } from './pages/ProductsPage';
import { CartPage } from './pages/CartPage';
import { OrderSuccessPage } from './pages/OrderSuccessPage';
import type { CartItem, OrderResponse, Product } from './types/models';

export function App() {
  const [accessToken, setAccessToken] = useState(() => localStorage.getItem('accessToken'));
  const [cartItems, setCartItems] = useState<CartItem[]>([]);
  const [lastOrder, setLastOrder] = useState<OrderResponse | null>(null);

  const cartCount = useMemo(
    () => cartItems.reduce((acc, item) => acc + item.quantity, 0),
    [cartItems]
  );

  function handleLogin(token: string) {
    localStorage.setItem('accessToken', token);
    setAccessToken(token);
  }

  function handleLogout() {
    localStorage.removeItem('accessToken');
    setAccessToken(null);
  }

  function handleAddToCart(product: Product) {
    setCartItems((current) => {
      const existing = current.find((item) => item.product.id === product.id);

      if (existing) {
        return current.map((item) =>
          item.product.id === product.id
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      }

      return [...current, { product, quantity: 1 }];
    });
  }

  function handleUpdateQuantity(productId: string, quantity: number) {
    if (quantity <= 0) {
      setCartItems((current) => current.filter((item) => item.product.id !== productId));
      return;
    }

    setCartItems((current) =>
      current.map((item) =>
        item.product.id === productId ? { ...item, quantity } : item
      )
    );
  }

  return (
    <>
      <Header cartCount={cartCount} authenticated={Boolean(accessToken)} onLogout={handleLogout} />

      <Routes>
        <Route path="/" element={<Navigate to="/products" />} />
        <Route path="/login" element={<LoginPage onLogin={handleLogin} />} />
        <Route path="/products" element={<ProductsPage onAddToCart={handleAddToCart} />} />
        <Route
          path="/cart"
          element={
            <CartPage
              items={cartItems}
              onUpdateQuantity={handleUpdateQuantity}
              onClear={() => setCartItems([])}
              onOrderCreated={setLastOrder}
            />
          }
        />
        <Route path="/order-success" element={<OrderSuccessPage order={lastOrder} />} />
      </Routes>
    </>
  );
}
