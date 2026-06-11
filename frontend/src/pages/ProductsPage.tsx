import { useEffect, useState } from 'react';
import { listProducts } from '../api/ecommerceApi';
import { ProductCard } from '../components/ProductCard';
import type { Product } from '../types/models';

type ProductsPageProps = {
  onAddToCart: (product: Product) => void;
};

export function ProductsPage({ onAddToCart }: ProductsPageProps) {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    listProducts()
      .then(setProducts)
      .catch(() => setError('Não foi possível carregar os produtos.'))
      .finally(() => setLoading(false));
  }, []);

  return (
    <main className="page">
      <section className="hero">
        <span className="eyebrow">Portfolio Backend + Frontend</span>
        <h1>Catálogo Enterprise</h1>
        <p>
          Interface React consumindo uma API Java com DDD, Clean Architecture, PostgreSQL,
          Redis, RabbitMQ, JWT, Outbox e Docker.
        </p>
      </section>

      {loading && <p>Carregando produtos...</p>}
      {error && <p className="error">{error}</p>}

      <section className="grid">
        {products.map((product) => (
          <ProductCard key={product.id} product={product} onAdd={onAddToCart} />
        ))}
      </section>
    </main>
  );
}
