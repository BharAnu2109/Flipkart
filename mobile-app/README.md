# Flipkart Mobile App

React Native mobile application for Flipkart e-commerce platform.

## Prerequisites

- Node.js >= 16
- React Native CLI
- Android Studio (for Android development)
- Xcode (for iOS development, macOS only)

## Installation

1. Install dependencies:
```bash
npm install
```

2. Install iOS dependencies (macOS only):
```bash
cd ios && pod install && cd ..
```

## Running the App

### Android
```bash
npm run android
```

### iOS (macOS only)
```bash
npm run ios
```

## Features

- Product listing with images
- Product search
- Shopping cart management
- Order placement
- User authentication

## Project Structure

```
mobile-app/
├── src/
│   ├── components/    # Reusable UI components
│   ├── screens/       # Screen components
│   ├── services/      # API services
│   └── types/         # TypeScript type definitions
├── App.tsx            # Main app component
└── package.json       # Project dependencies
```

## API Configuration

Update the API URL in `src/services/productService.ts` to point to your backend server.

## Development

To start the Metro bundler:
```bash
npm start
```
