import React from 'react';
import ProductList from './components/ProductList';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="app-header">
        <div className="header-content">
          <h1>Flipkart</h1>
          <nav>
            <a href="/">Home</a>
            <a href="/products">Products</a>
            <a href="/cart">Cart</a>
          </nav>
        </div>
      </header>
      
      <main className="app-main">
        <ProductList />
      </main>
      
      <footer className="app-footer">
        <p>&copy; 2024 Flipkart. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default App;
