import { Link, NavLink, useNavigate } from 'react-router-dom';
import { ShoppingCart, LogOut, Store } from 'lucide-react';

type HeaderProps = {
  cartCount: number;
  authenticated: boolean;
  onLogout: () => void;
};

export function Header({ cartCount, authenticated, onLogout }: HeaderProps) {
  const navigate = useNavigate();

  function handleLogout() {
    onLogout();
    navigate('/login');
  }

  return (
    <header className="header">
      <Link to="/" className="brand">
        <Store size={24} />
        <span>E-commerce Enterprise</span>
      </Link>

      <nav className="nav">
        <NavLink to="/products">Produtos</NavLink>
        <NavLink to="/cart" className="cart-link">
          <ShoppingCart size={18} />
          Carrinho
          <span className="badge">{cartCount}</span>
        </NavLink>

        {authenticated ? (
          <button className="ghost-button" onClick={handleLogout}>
            <LogOut size={16} />
            Sair
          </button>
        ) : (
          <NavLink to="/login">Login</NavLink>
        )}
      </nav>
    </header>
  );
}
