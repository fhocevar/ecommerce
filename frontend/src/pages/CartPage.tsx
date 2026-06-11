import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { checkout } from '../api/ecommerceApi';
import type { CartItem, OrderResponse } from '../types/models';
import { formatMoney, getPriceValue } from '../utils/money';

type CartPageProps = {
  items: CartItem[];
  onUpdateQuantity: (productId: string, quantity: number) => void;
  onClear: () => void;
  onOrderCreated: (order: OrderResponse) => void;
};

const CUSTOMER_ID = '11111111-1111-1111-1111-111111111111';

export function CartPage({ items, onUpdateQuantity, onClear, onOrderCreated }: CartPageProps) {
  const navigate = useNavigate();
  const [shippingZipCode, setShippingZipCode] = useState('13000-000');
  const [couponCode, setCouponCode] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const subtotal = items.reduce(
    (acc, item) => acc + getPriceValue(item.product.price) * item.quantity,
    0
  );

  async function handleCheckout() {
    setLoading(true);
    setError(null);

    try {
      const order = await checkout({
        customerId: CUSTOMER_ID,
        shippingZipCode,
        couponCode: couponCode || null,
        items: items.map((item) => ({
          productId: item.product.id,
          quantity: item.quantity
        }))
      });

      onOrderCreated(order);
      onClear();
      navigate('/order-success');
    } catch {
      setError('Falha ao criar pedido. Confira estoque e dados enviados.');
    } finally {
      setLoading(false);
    }
  }

  return (
    <main className="page">
      <section className="hero compact">
        <span className="eyebrow">Checkout</span>
        <h1>Carrinho</h1>
        <p>Criação de pedido usando Idempotency-Key, reserva de estoque e Outbox.</p>
      </section>

      {items.length === 0 ? (
        <section className="card empty-state">
          <h2>Carrinho vazio</h2>
          <p className="muted">Adicione produtos para criar um pedido.</p>
        </section>
      ) : (
        <section className="cart-layout">
          <div className="card">
            {items.map((item) => (
              <div className="cart-row" key={item.product.id}>
                <div>
                  <strong>{item.product.name}</strong>
                  <p className="muted">{formatMoney(getPriceValue(item.product.price))}</p>
                </div>

                <input
                  type="number"
                  min={1}
                  value={item.quantity}
                  onChange={(e) => onUpdateQuantity(item.product.id, Number(e.target.value))}
                />
              </div>
            ))}
          </div>

          <aside className="card summary">
            <h2>Resumo</h2>
            <label>
              CEP
              <input value={shippingZipCode} onChange={(e) => setShippingZipCode(e.target.value)} />
            </label>
            <label>
              Cupom
              <input value={couponCode} onChange={(e) => setCouponCode(e.target.value)} placeholder="Opcional" />
            </label>
            <div className="summary-line">
              <span>Subtotal</span>
              <strong>{formatMoney(subtotal)}</strong>
            </div>
            {error && <p className="error">{error}</p>}
            <button onClick={handleCheckout} disabled={loading || items.length === 0}>
              {loading ? 'Finalizando...' : 'Finalizar pedido'}
            </button>
          </aside>
        </section>
      )}
    </main>
  );
}
