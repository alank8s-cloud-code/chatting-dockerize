import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { store } from './Redux/store';
import App from './App';

test('renders the signin page by default when not authenticated', () => {
  render(
    <Provider store={store}>
      <MemoryRouter initialEntries={['/signin']}>
        <App />
      </MemoryRouter>
    </Provider>
  );
  const signInButton = screen.getByText(/sign in/i);
  expect(signInButton).toBeInTheDocument();
});
