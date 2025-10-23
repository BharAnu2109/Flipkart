# Quick Start Guide

Get the Flipkart e-commerce platform up and running in minutes!

## Prerequisites Checklist

- [ ] Java 17 or higher installed
- [ ] Node.js 18 or higher installed
- [ ] Maven 3.9+ installed
- [ ] Git installed
- [ ] Docker (optional, for containerized deployment)

## Quick Start - Backend Only

### Step 1: Clone the Repository
```bash
git clone https://github.com/BharAnu2109/Flipkart.git
cd Flipkart
```

### Step 2: Run Backend
```bash
cd backend
mvn spring-boot:run
```

Backend will be available at: **http://localhost:8080**

### Step 3: Test the API
```bash
# Get all products
curl http://localhost:8080/api/products

# Get product by ID
curl http://localhost:8080/api/products/1
```

## Quick Start - With Angular Frontend

### Step 1: Start Backend (Terminal 1)
```bash
cd backend
mvn spring-boot:run
```

### Step 2: Start Angular Frontend (Terminal 2)
```bash
cd frontend-angular
npm install
npm start
```

Angular app will be available at: **http://localhost:4200**

## Quick Start - With React Frontend

### Step 1: Start Backend (Terminal 1)
```bash
cd backend
mvn spring-boot:run
```

### Step 2: Start React Frontend (Terminal 2)
```bash
cd frontend-react
npm install
npm start
```

React app will be available at: **http://localhost:3000**

## Quick Start - Docker (All Services)

### Single Command Setup
```bash
docker-compose up --build
```

This will start:
- **Backend**: http://localhost:8080
- **Angular**: http://localhost:4200
- **React**: http://localhost:3000

### Stop All Services
```bash
docker-compose down
```

## Testing the Application

### 1. View Products
Open your browser to:
- Angular: http://localhost:4200
- React: http://localhost:3000

### 2. Test Backend API Endpoints

#### Get All Products
```bash
curl -X GET http://localhost:8080/api/products
```

#### Create a User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe",
    "role": "CUSTOMER"
  }'
```

#### Create a Product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "iPhone 15",
    "description": "Latest iPhone model",
    "price": 999.99,
    "stockQuantity": 50,
    "category": "Electronics",
    "brand": "Apple",
    "imageUrl": "https://example.com/iphone15.jpg"
  }'
```

#### Search Products
```bash
curl -X GET "http://localhost:8080/api/products/search?keyword=phone"
```

#### Get Products by Category
```bash
curl -X GET http://localhost:8080/api/products/category/Electronics
```

### 3. Access H2 Database Console

URL: http://localhost:8080/h2-console

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:flipkart`
- Username: `sa`
- Password: (leave empty)

## Mobile App Setup

### Android
```bash
cd mobile-app
npm install
npm run android
```

### iOS (macOS only)
```bash
cd mobile-app
npm install
npm run ios
```

## Common Issues & Solutions

### Issue: Port Already in Use

**Backend (8080)**
```bash
# Find process using port 8080
lsof -i :8080
# Kill the process
kill -9 <PID>
```

**Angular (4200)**
```bash
# Find process using port 4200
lsof -i :4200
# Kill the process
kill -9 <PID>
```

**React (3000)**
```bash
# Find process using port 3000
lsof -i :3000
# Kill the process
kill -9 <PID>
```

### Issue: Maven Build Fails

**Solution 1: Clean and rebuild**
```bash
cd backend
mvn clean install
```

**Solution 2: Update dependencies**
```bash
mvn clean install -U
```

### Issue: Node Modules Issues

**Solution: Clear cache and reinstall**
```bash
rm -rf node_modules package-lock.json
npm cache clean --force
npm install
```

### Issue: Docker Build Fails

**Solution: Clean Docker cache**
```bash
docker system prune -a
docker-compose build --no-cache
```

## Default Database Data

The application uses H2 in-memory database. Data is reset on restart.

To persist data, switch to MySQL:

1. Install MySQL
2. Create database: `CREATE DATABASE flipkart;`
3. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flipkart
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## AWS Configuration (Optional)

To enable AWS features, set environment variables:

```bash
export AWS_ACCESS_KEY_ID=your_access_key
export AWS_SECRET_ACCESS_KEY=your_secret_key
export AWS_REGION=us-east-1
export AWS_S3_BUCKET=your-bucket-name
export AWS_SQS_QUEUE_URL=your-queue-url
export AWS_SNS_TOPIC_ARN=your-topic-arn
```

Then restart the backend.

## Development Tips

### Hot Reload

**Backend (Spring Boot DevTools)**
- Already configured
- Changes auto-reload on save

**Angular**
- `ng serve` watches for changes
- Browser auto-refreshes

**React**
- `npm start` has hot reload
- Changes appear immediately

### Debugging

**Backend**
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

**Frontend**
- Use browser DevTools
- React DevTools extension
- Angular DevTools extension

## Next Steps

1. **Add Sample Data**: Use H2 console or API to add products
2. **Explore API**: Check all endpoints in README.md
3. **Customize**: Modify code for your needs
4. **Deploy**: Follow deployment guide for production

## Getting Help

- Check [README.md](README.md) for detailed documentation
- Review [ARCHITECTURE.md](ARCHITECTURE.md) for system design
- Open an issue on GitHub for bugs
- Refer to Spring Boot and Angular/React documentation

## Production Deployment Checklist

Before deploying to production:

- [ ] Change default passwords
- [ ] Configure MySQL database
- [ ] Set up AWS services
- [ ] Enable HTTPS
- [ ] Configure proper CORS
- [ ] Set up monitoring
- [ ] Configure backup strategy
- [ ] Review security settings
- [ ] Set production environment variables
- [ ] Test thoroughly

## Summary of Available URLs

After starting all services:

| Service | URL | Purpose |
|---------|-----|---------|
| Backend API | http://localhost:8080 | REST API |
| H2 Console | http://localhost:8080/h2-console | Database admin |
| Angular App | http://localhost:4200 | Frontend (Angular) |
| React App | http://localhost:3000 | Frontend (React) |
| API Docs | http://localhost:8080/api/* | API endpoints |

Happy coding! 🚀
