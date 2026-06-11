export function getPriceValue(price: number | { amount: number }): number {
  if (typeof price === 'number') return price;
  return price.amount;
}

export function formatMoney(value: number): string {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  }).format(value);
}
