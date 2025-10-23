import { Product } from '../types';

const API_URL = 'http://localhost:8080/api/products';

export const productService = {
  getAllProducts: async (): Promise<Product[]> => {
    const response = await fetch(API_URL);
    if (!response.ok) throw new Error('Failed to fetch products');
    return response.json();
  },

  getProductById: async (id: number): Promise<Product> => {
    const response = await fetch(`${API_URL}/${id}`);
    if (!response.ok) throw new Error('Failed to fetch product');
    return response.json();
  },

  getProductsByCategory: async (category: string): Promise<Product[]> => {
    const response = await fetch(`${API_URL}/category/${category}`);
    if (!response.ok) throw new Error('Failed to fetch products by category');
    return response.json();
  },

  searchProducts: async (keyword: string): Promise<Product[]> => {
    const response = await fetch(`${API_URL}/search?keyword=${keyword}`);
    if (!response.ok) throw new Error('Failed to search products');
    return response.json();
  },

  getTopRatedProducts: async (): Promise<Product[]> => {
    const response = await fetch(`${API_URL}/top-rated`);
    if (!response.ok) throw new Error('Failed to fetch top-rated products');
    return response.json();
  },
};
