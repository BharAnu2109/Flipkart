# Flipkart E-Commerce Platform - Architecture Documentation

## System Overview

This is a full-stack e-commerce platform built with modern technologies, following microservices patterns and best practices for scalability and maintainability.

## Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         Client Layer                             │
├──────────────┬──────────────┬──────────────┬─────────────────────┤
│   Angular    │    React     │   Mobile     │   Other Clients    │
│   Frontend   │   Frontend   │   (React     │                    │
│              │              │   Native)    │                    │
└──────┬───────┴──────┬───────┴──────┬───────┴─────────────────────┘
       │              │              │
       │              │              │
       └──────────────┼──────────────┘
                      │
                      │ HTTP/REST
                      │
       ┌──────────────▼──────────────┐
       │   Backend API Gateway       │
       │   (Spring Boot)             │
       └──────────────┬──────────────┘
                      │
       ┌──────────────┴──────────────┐
       │                             │
┌──────▼────────┐            ┌───────▼──────┐
│  Controllers  │            │   Security   │
│  - User       │            │   - JWT      │
│  - Product    │            │   - BCrypt   │
│  - Cart       │            └──────────────┘
│  - Order      │
└──────┬────────┘
       │
┌──────▼────────┐
│   Services    │
│  - Business   │
│    Logic      │
│  - Validation │
└──────┬────────┘
       │
┌──────▼────────┐         ┌─────────────┐
│ Repositories  │────────▶│  Database   │
│  - JPA/       │         │  (H2/MySQL) │
│    Hibernate  │         └─────────────┘
└───────────────┘
       │
       │
┌──────▼────────────────────────────┐
│         AWS Services              │
├───────────┬──────────┬────────────┤
│    S3     │   SQS    │    SNS     │
│  (Images) │ (Queue)  │(Notifications)│
└───────────┴──────────┴────────────┘
```

## Component Breakdown

### 1. Backend (Spring Boot)

**Technology Stack:**
- Spring Boot 3.1.5
- Java 17
- Maven
- Spring Data JPA
- Spring Security
- AWS SDK

**Key Components:**

#### Models (Domain Entities)
- **User**: Customer, Seller, and Admin roles
- **Product**: Product catalog with categories
- **Cart**: Shopping cart management
- **Order**: Order processing and tracking
- **OrderItem**: Individual items in an order
- **CartItem**: Individual items in a cart

#### Repositories
- JPA-based data access layer
- Custom query methods for business logic
- Transactional operations

#### Services
- **UserService**: User management and authentication
- **ProductService**: Product CRUD and search
- **CartService**: Cart operations
- **OrderService**: Order processing and status updates

#### Controllers (REST API)
- RESTful endpoints for all entities
- CORS enabled for frontend access
- HTTP status code handling
- Request/Response validation

#### AWS Integration
- **S3Service**: Product image storage and retrieval
- **SQSService**: Asynchronous message processing
- **SNSService**: Email/SMS notifications

#### Security
- JWT-based authentication
- BCrypt password encryption
- Role-based access control (RBAC)

### 2. Frontend - Angular

**Technology Stack:**
- Angular 16
- TypeScript
- RxJS
- HttpClient

**Key Components:**
- **ProductListComponent**: Display products in a grid
- **ProductDetailComponent**: Detailed product view
- **CartComponent**: Shopping cart management
- **Services**: HTTP services for API communication

**Features:**
- Reactive programming with RxJS
- Component-based architecture
- Dependency injection
- Routing

### 3. Frontend - React

**Technology Stack:**
- React 18
- TypeScript
- Fetch API

**Key Components:**
- **ProductList**: Product catalog display
- **Services**: API service layer
- **Types**: TypeScript type definitions

**Features:**
- Functional components with hooks
- State management with useState/useEffect
- TypeScript for type safety
- CSS modules for styling

### 4. Mobile App (React Native)

**Technology Stack:**
- React Native 0.72
- TypeScript
- React Navigation (optional)

**Key Screens:**
- **ProductListScreen**: Browse products
- **Services**: Mobile API integration
- **Types**: Shared type definitions

**Features:**
- Cross-platform (iOS/Android)
- Native mobile components
- Touch-optimized UI
- Offline capability ready

## Data Flow

### Product Listing Flow
```
1. User opens app/website
2. Frontend sends GET /api/products
3. Backend queries database
4. Response sent with product list
5. Frontend renders products
```

### Add to Cart Flow
```
1. User clicks "Add to Cart"
2. Frontend sends POST /api/carts/user/{userId}/items
3. Backend validates product availability
4. Cart updated in database
5. Success response sent
6. Frontend updates cart UI
```

### Order Placement Flow
```
1. User proceeds to checkout
2. Frontend sends POST /api/orders
3. Backend creates order
4. Stock quantities updated
5. SQS message sent for processing
6. SNS notification sent to user
7. Order confirmation returned
```

## Security Architecture

### Authentication Flow
```
1. User submits credentials
2. Backend validates credentials
3. JWT token generated
4. Token sent to frontend
5. Token stored (localStorage/sessionStorage)
6. Subsequent requests include token
7. Backend validates token on each request
```

### Security Measures
- Password hashing with BCrypt
- JWT for stateless authentication
- HTTPS enforcement (production)
- CORS configuration
- Input validation
- SQL injection prevention (JPA)
- XSS protection

## Database Schema

### Users Table
```sql
- id (PK)
- email (unique)
- password (encrypted)
- first_name
- last_name
- phone
- address
- city
- state
- zip_code
- role (CUSTOMER/SELLER/ADMIN)
- active
- created_at
- updated_at
```

### Products Table
```sql
- id (PK)
- name
- description
- price
- discount_price
- stock_quantity
- category
- brand
- image_url
- rating
- review_count
- active
- created_at
- updated_at
```

### Orders Table
```sql
- id (PK)
- order_number (unique)
- user_id (FK)
- total_amount
- shipping_cost
- tax_amount
- status
- payment_status
- shipping_address
- tracking_number
- created_at
- updated_at
- delivered_at
```

### Order_Items Table
```sql
- id (PK)
- order_id (FK)
- product_id (FK)
- quantity
- price
- discount
```

### Carts Table
```sql
- id (PK)
- user_id (FK)
- created_at
- updated_at
```

### Cart_Items Table
```sql
- id (PK)
- cart_id (FK)
- product_id (FK)
- quantity
```

## AWS Services Integration

### S3 (Simple Storage Service)
**Purpose**: Store and serve product images
**Operations**:
- Upload product images
- Generate public URLs
- Delete images
- Retrieve images

### SQS (Simple Queue Service)
**Purpose**: Asynchronous order processing
**Use Cases**:
- Order confirmation emails
- Inventory updates
- Background tasks
- Event-driven processing

### SNS (Simple Notification Service)
**Purpose**: Real-time notifications
**Use Cases**:
- Order status updates
- Welcome emails
- Order confirmations
- Promotional messages

## Deployment Architecture

### Docker Deployment
```
┌─────────────────────────────────────┐
│         Docker Compose              │
├─────────────────────────────────────┤
│                                     │
│  ┌──────────────────────────────┐  │
│  │  Backend Container           │  │
│  │  - Spring Boot               │  │
│  │  - Port 8080                 │  │
│  └──────────────────────────────┘  │
│                                     │
│  ┌──────────────────────────────┐  │
│  │  Angular Container           │  │
│  │  - Nginx + Angular build     │  │
│  │  - Port 4200                 │  │
│  └──────────────────────────────┘  │
│                                     │
│  ┌──────────────────────────────┐  │
│  │  React Container             │  │
│  │  - Nginx + React build       │  │
│  │  - Port 3000                 │  │
│  └──────────────────────────────┘  │
│                                     │
└─────────────────────────────────────┘
```

### Production Deployment (AWS)
```
┌────────────────────────────────────────┐
│              Route 53                   │
│         (DNS Management)                │
└────────────┬───────────────────────────┘
             │
┌────────────▼───────────────────────────┐
│        CloudFront (CDN)                 │
│    - Static content caching             │
│    - HTTPS termination                  │
└────────────┬───────────────────────────┘
             │
┌────────────▼───────────────────────────┐
│    Application Load Balancer           │
└────────┬───────────────┬───────────────┘
         │               │
┌────────▼──────┐  ┌────▼──────────────┐
│  EC2/ECS      │  │   S3 Bucket       │
│  (Backend)    │  │   (Frontend)      │
└────────┬──────┘  └───────────────────┘
         │
┌────────▼──────────────────────────────┐
│          RDS (MySQL)                   │
│      - Multi-AZ deployment             │
│      - Automated backups               │
└────────────────────────────────────────┘
```

## Scalability Considerations

### Horizontal Scaling
- Stateless backend design
- Load balancer distribution
- Multiple backend instances
- Database read replicas

### Vertical Scaling
- Instance size optimization
- Database performance tuning
- Cache implementation
- Query optimization

### Caching Strategy
- Redis for session management
- CloudFront for static assets
- Application-level caching
- Database query caching

## Monitoring & Logging

### Application Monitoring
- Spring Boot Actuator endpoints
- Health checks
- Metrics collection
- Performance monitoring

### AWS Monitoring
- CloudWatch logs
- CloudWatch metrics
- X-Ray tracing
- Alerts and notifications

## Future Enhancements

1. **Payment Integration**
   - Stripe/PayPal integration
   - Payment gateway abstraction

2. **Search Enhancement**
   - Elasticsearch integration
   - Advanced filtering
   - Faceted search

3. **Recommendation Engine**
   - Machine learning models
   - Personalized recommendations
   - Collaborative filtering

4. **Real-time Features**
   - WebSocket integration
   - Real-time inventory updates
   - Live chat support

5. **Analytics**
   - User behavior tracking
   - Sales analytics
   - Performance metrics
   - Business intelligence

6. **Microservices Migration**
   - Service decomposition
   - API Gateway (Kong/Zuul)
   - Service discovery
   - Circuit breakers

## Best Practices Implemented

1. **Code Organization**
   - Clear separation of concerns
   - Layered architecture
   - DRY principle
   - SOLID principles

2. **Security**
   - JWT authentication
   - Password encryption
   - Input validation
   - SQL injection prevention

3. **Performance**
   - Lazy loading
   - Database indexing
   - Efficient queries
   - Connection pooling

4. **Testing**
   - Unit tests structure
   - Integration tests ready
   - Test coverage tracking

5. **Documentation**
   - API documentation
   - Code comments
   - README files
   - Architecture docs

## Conclusion

This architecture provides a robust, scalable foundation for an e-commerce platform. It follows industry best practices and is designed to handle complex scenarios while maintaining flexibility for future enhancements.
