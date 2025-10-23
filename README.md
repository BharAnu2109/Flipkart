# Flipkart E-Commerce Platform

A comprehensive full-stack e-commerce application similar to Flipkart, built with Spring Boot, Angular, React, React Native, and AWS integration.

## 🚀 Features

### Backend (Spring Boot)
- **RESTful API** with comprehensive endpoints
- **JPA/Hibernate** for database operations
- **Spring Security** for authentication and authorization
- **AWS Integration**:
  - S3 for product image storage
  - SQS for asynchronous order processing
  - SNS for notifications
- **Complex E-commerce Scenarios**:
  - User management with roles (Customer, Seller, Admin)
  - Product catalog with categories and search
  - Shopping cart management
  - Order processing and tracking
  - Inventory management
  - Payment status tracking

### Frontend - Angular
- Responsive product listing with grid layout
- Product search and filtering
- Shopping cart functionality
- Order management
- Modern UI with Flipkart-inspired design

### Frontend - React
- TypeScript-based implementation
- Product catalog with detailed views
- Cart management
- Order placement
- Reusable component architecture

### Mobile App (React Native)
- Native mobile experience for iOS and Android
- Product browsing and search
- Add to cart functionality
- Order management
- Cross-platform compatibility

## 📁 Project Structure

```
Flipkart/
├── backend/                    # Spring Boot Backend
│   ├── src/main/java/
│   │   └── com/flipkart/backend/
│   │       ├── model/         # Domain entities (User, Product, Order, Cart)
│   │       ├── repository/    # JPA repositories
│   │       ├── service/       # Business logic layer
│   │       ├── controller/    # REST API endpoints
│   │       ├── config/        # Configuration classes
│   │       ├── security/      # Security configuration
│   │       └── aws/           # AWS service integrations
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── pom.xml
│   └── Dockerfile
│
├── frontend-angular/          # Angular Frontend
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/   # UI components
│   │   │   └── services/     # API services
│   │   └── assets/
│   ├── package.json
│   ├── Dockerfile
│   └── nginx.conf
│
├── frontend-react/            # React Frontend
│   ├── src/
│   │   ├── components/       # React components
│   │   ├── services/         # API services
│   │   └── types/            # TypeScript types
│   ├── package.json
│   ├── Dockerfile
│   └── nginx.conf
│
├── mobile-app/                # React Native Mobile App
│   ├── src/
│   │   ├── screens/          # App screens
│   │   ├── components/       # Reusable components
│   │   ├── services/         # API services
│   │   └── types/            # TypeScript types
│   ├── package.json
│   └── App.tsx
│
└── docker-compose.yml         # Docker orchestration
```

## 🛠️ Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (development)
- **MySQL** (production)
- **AWS SDK** (S3, SQS, SNS)
- **JWT** for authentication
- **Lombok**
- **Maven**

### Frontend - Angular
- **Angular 16**
- **TypeScript**
- **RxJS**
- **HttpClient**

### Frontend - React
- **React 18**
- **TypeScript**
- **Create React App**

### Mobile
- **React Native 0.72**
- **TypeScript**

### DevOps
- **Docker**
- **Docker Compose**
- **Nginx**

## 📋 Prerequisites

- **Java 17** or higher
- **Node.js 18** or higher
- **Maven 3.9+**
- **Docker** and **Docker Compose** (optional)
- **AWS Account** (for AWS services)
- **Android Studio** (for mobile development)
- **Xcode** (for iOS development, macOS only)

## 🚦 Getting Started

### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build the project:
```bash
mvn clean package
```

3. Run the application:
```bash
mvn spring-boot:run
```

The backend will be available at `http://localhost:8080`

### Frontend Setup (Angular)

1. Navigate to the Angular directory:
```bash
cd frontend-angular
```

2. Install dependencies:
```bash
npm install
```

3. Run the development server:
```bash
npm start
```

The Angular app will be available at `http://localhost:4200`

### Frontend Setup (React)

1. Navigate to the React directory:
```bash
cd frontend-react
```

2. Install dependencies:
```bash
npm install
```

3. Run the development server:
```bash
npm start
```

The React app will be available at `http://localhost:3000`

### Mobile App Setup

1. Navigate to the mobile app directory:
```bash
cd mobile-app
```

2. Install dependencies:
```bash
npm install
```

3. Run on Android:
```bash
npm run android
```

4. Run on iOS (macOS only):
```bash
npm run ios
```

## 🐳 Docker Deployment

Run all services using Docker Compose:

```bash
docker-compose up --build
```

This will start:
- Backend API: `http://localhost:8080`
- Angular Frontend: `http://localhost:4200`
- React Frontend: `http://localhost:3000`

## 🔧 Configuration

### Backend Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
# Database
spring.datasource.url=jdbc:h2:mem:flipkart
spring.datasource.username=sa
spring.datasource.password=

# AWS
aws.region=us-east-1
aws.access-key-id=${AWS_ACCESS_KEY_ID}
aws.secret-access-key=${AWS_SECRET_ACCESS_KEY}
aws.s3.bucket-name=${AWS_S3_BUCKET}
aws.sqs.queue-url=${AWS_SQS_QUEUE_URL}
aws.sns.topic-arn=${AWS_SNS_TOPIC_ARN}
```

### Environment Variables

Set the following environment variables for AWS integration:

```bash
export AWS_ACCESS_KEY_ID=your_access_key
export AWS_SECRET_ACCESS_KEY=your_secret_key
export AWS_REGION=us-east-1
export AWS_S3_BUCKET=flipkart-images
export AWS_SQS_QUEUE_URL=your_sqs_queue_url
export AWS_SNS_TOPIC_ARN=your_sns_topic_arn
```

## 📚 API Documentation

### Products API

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/category/{category}` - Get products by category
- `GET /api/products/search?keyword={keyword}` - Search products
- `GET /api/products/top-rated` - Get top-rated products
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Cart API

- `GET /api/carts/user/{userId}` - Get user's cart
- `POST /api/carts/user/{userId}/items` - Add item to cart
- `PUT /api/carts/user/{userId}/items/{productId}` - Update cart item
- `DELETE /api/carts/user/{userId}/items/{productId}` - Remove item from cart
- `DELETE /api/carts/user/{userId}` - Clear cart

### Orders API

- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders/user/{userId}` - Get user's orders
- `POST /api/orders` - Create new order
- `PATCH /api/orders/{id}/status` - Update order status
- `PATCH /api/orders/{id}/payment-status` - Update payment status

### Users API

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Angular Tests
```bash
cd frontend-angular
npm test
```

### React Tests
```bash
cd frontend-react
npm test
```

## 🔒 Security

- Spring Security configured with JWT authentication
- Password encryption using BCrypt
- CORS enabled for frontend communication
- Environment-based configuration for sensitive data

## 📦 AWS Services Integration

### S3 (Simple Storage Service)
- Product image storage
- File upload and retrieval
- Public URL generation

### SQS (Simple Queue Service)
- Asynchronous order processing
- Message queuing for background tasks
- Decoupled architecture

### SNS (Simple Notification Service)
- Order status notifications
- User registration emails
- Real-time alerts

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License.

## 👥 Authors

- Your Name - Initial work

## 🙏 Acknowledgments

- Inspired by Flipkart's e-commerce platform
- Spring Boot community
- React and Angular communities
- AWS documentation 
