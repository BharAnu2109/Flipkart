export interface Product {
  id?: number;
  name: string;
  description: string;
  price: number;
  discountPrice?: number;
  stockQuantity: number;
  category: string;
  brand: string;
  imageUrl: string;
  rating?: number;
  reviewCount?: number;
}

export interface CartItem {
  id?: number;
  productId: number;
  quantity: number;
}

export interface Cart {
  id?: number;
  userId: number;
  cartItems: CartItem[];
}

export interface OrderItem {
  productId: number;
  quantity: number;
  price: number;
}

export interface Order {
  id?: number;
  orderNumber?: string;
  userId: number;
  totalAmount: number;
  status?: string;
  paymentStatus?: string;
  shippingAddress: string;
  orderItems: OrderItem[];
}
