import type { Product } from '../types/models';
import { formatMoney, getPriceValue } from '../utils/money';

type ProductCardProps = {
  product: Product;
  onAdd: (product: Product) => void;
};

export function ProductCard({ product, onAdd }: ProductCardProps) {
  return (
    <article className="card product-card">
      <div>
        <span className="eyebrow">{product.sku || 'SKU'}</span>
        <h3>{product.name}</h3>
        <p className="muted">Produto disponível no catálogo enterprise.</p>
      </div>
      <div className="product-footer">
        <strong>{formatMoney(getPriceValue(product.price))}</strong>
        <button onClick={() => onAdd(product)}>Adicionar</button>
      </div>
    </article>
  );
}
