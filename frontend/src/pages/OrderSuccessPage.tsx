import { useState } from 'react';
import { approvePayment } from '../api/ecommerceApi';
import type { OrderResponse } from '../types/models';
import { formatMoney } from '../utils/money';

type OrderSuccessPageProps = {
  order: OrderResponse | null;
};

export function OrderSuccessPage({ order }: OrderSuccessPageProps) {
  const [approved, setApproved] = useState(false);
  const [loading, setLoading] = useState(false);

  async function handleApprove() {
    if (!order) return;

    setLoading(true);
    try {
      await approvePayment(order.id);
      setApproved(true);
    } finally {
      setLoading(false);
    }
  }

  if (!order) {
    return (
      <main className="center-page">
        <section className="card login-card">
          <h1>Nenhum pedido recente</h1>
          <p className="muted">Crie um pedido pelo carrinho para visualizar esta página.</p>
        </section>
      </main>
    );
  }

  return (
    <main className="center-page">
      <section className="card success-card">
        <span className="eyebrow">Pedido criado</span>
        <h1>Checkout concluído</h1>
        <p className="muted">Pedido criado com status inicial de pagamento pendente.</p>

        <div className="details">
          <div><span>ID</span><strong>{order.id}</strong></div>
          <div><span>Status</span><strong>{approved ? 'PAYMENT_APPROVED' : order.status}</strong></div>
          <div><span>Total</span><strong>{formatMoney(order.total)}</strong></div>
        </div>

        <button onClick={handleApprove} disabled={loading || approved}>
          {approved ? 'Pagamento aprovado' : loading ? 'Aprovando...' : 'Aprovar pagamento'}
        </button>
      </section>
    </main>
  );
}
