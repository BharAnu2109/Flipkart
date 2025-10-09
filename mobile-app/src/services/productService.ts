import { Product } from '../types';

const API_URL = 'http://localhost:8080/api/products';

export const productService = {
  getAllProducts: async (): Promise<Product[]> => {
    try {
      const response = await fetch(API_URL);
      if (!response.ok) throw new Error('Failed to fetch products');
      return response.json();
    } catch (error) {
      console.error('Error fetching products:', error);
      return [];
    }
  },

  getProductById: async (id: number): Promise<Product | null> => {
    try {
      const response = await fetch(`${API_URL}/${id}`);
      if (!response.ok) throw new Error('Failed to fetch product');
      return response.json();
    } catch (error) {
      console.error('Error fetching product:', error);
      return null;
    }
  },

  searchProducts: async (keyword: string): Promise<Product[]> => {
    try {
      const response = await fetch(`${API_URL}/search?keyword=${keyword}`);
      if (!response.ok) throw new Error('Failed to search products');
      return response.json();
    } catch (error) {
      console.error('Error searching products:', error);
      return [];
    }
  },
};
