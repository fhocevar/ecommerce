import { FormEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../api/ecommerceApi';

type LoginPageProps = {
  onLogin: (token: string) => void;
};

export function LoginPage({ onLogin }: LoginPageProps) {
  const navigate = useNavigate();
  const [email, setEmail] = useState('admin@portfolio.com');
  const [password, setPassword] = useState('admin123');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const response = await login(email, password);
      onLogin(response.accessToken);
      navigate('/products');
    } catch {
      setError('Falha no login. Confira usuário e senha.');
    } finally {
      setLoading(false);
    }
  }

  return (
    <main className="center-page">
      <section className="card login-card">
        <span className="eyebrow">JWT Authentication</span>
        <h1>Entrar no E-commerce</h1>
        <p className="muted">Use o usuário seedado no Flyway para testar o fluxo completo.</p>

        <form onSubmit={handleSubmit} className="form">
          <label>
            E-mail
            <input value={email} onChange={(e) => setEmail(e.target.value)} />
          </label>

          <label>
            Senha
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </label>

          {error && <p className="error">{error}</p>}

          <button type="submit" disabled={loading}>
            {loading ? 'Entrando...' : 'Entrar'}
          </button>
        </form>
      </section>
    </main>
  );
}
