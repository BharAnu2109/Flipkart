import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Cart {
  id?: number;
  userId: number;
  cartItems: CartItem[];
}

export interface CartItem {
  id?: number;
  productId: number;
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/carts';

  constructor(private http: HttpClient) { }

  getCart(userId: number): Observable<Cart> {
    return this.http.get<Cart>(`${this.apiUrl}/user/${userId}`);
  }

  addToCart(userId: number, productId: number, quantity: number): Observable<Cart> {
    return this.http.post<Cart>(
      `${this.apiUrl}/user/${userId}/items?productId=${productId}&quantity=${quantity}`,
      {}
    );
  }

  updateQuantity(userId: number, productId: number, quantity: number): Observable<Cart> {
    return this.http.put<Cart>(
      `${this.apiUrl}/user/${userId}/items/${productId}?quantity=${quantity}`,
      {}
    );
  }

  removeFromCart(userId: number, productId: number): Observable<Cart> {
    return this.http.delete<Cart>(`${this.apiUrl}/user/${userId}/items/${productId}`);
  }

  clearCart(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/user/${userId}`);
  }
}
