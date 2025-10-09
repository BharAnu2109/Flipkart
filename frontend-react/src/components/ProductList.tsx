import React, { useEffect, useState } from 'react';
import { Product } from '../types';
import { productService } from '../services/productService';
import './ProductList.css';

const ProductList: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    try {
      setLoading(true);
      const data = await productService.getAllProducts();
      setProducts(data);
      setError(null);
    } catch (err) {
      setError('Failed to load products. Please try again later.');
      console.error('Error loading products:', err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="loading">Loading products...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="product-list-container">
      <h2>Products</h2>
      <div className="product-grid">
        {products.map((product) => (
          <div key={product.id} className="product-card">
            <img 
              src={product.imageUrl} 
              alt={product.name} 
              className="product-image"
            />
            <h3>{product.name}</h3>
            <p className="category">{product.category}</p>
            <p className="description">{product.description}</p>
            <div className="price-section">
              <span className="price">${product.price}</span>
              {product.discountPrice && (
                <span className="discount-price">${product.discountPrice}</span>
              )}
            </div>
            {product.rating && (
              <div className="rating">
                ⭐ {product.rating} ({product.reviewCount} reviews)
              </div>
            )}
            <button className="add-to-cart-btn">Add to Cart</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductList;
