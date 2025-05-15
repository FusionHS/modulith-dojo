# Spring Modulith Pizza Delivery Demo

This project demonstrates the implementation of a modular monolith (modulith) architecture using Spring Modulith. It showcases how to structure a medium-sized
application into well-defined modules while maintaining loose coupling and proper encapsulation.

## 🍕 About Bob's Pizzas

Bob's Pizzas is a modern pizza delivery company that prides itself on its efficient delivery system and real-time order tracking. The company operates with:

- A team of skilled chefs who prepare pizzas
- Professional delivery drivers managing deliveries
- A real-time order tracking system
- Automated statistics tracking for popular pizzas
- Event-driven updates for order status changes

## 📦 Project Structure

The application is structured into several bounded contexts (modules):

```
com.fusionhs.modulithdojo
├── delivery/           # Handles delivery management and tracking
├── pizza/             # Pizza menu and order statistics
├── employee/          # Employee management and task assignment
└── common/            # Shared DTOs and utilities
```

### Module Responsibilities

- **Delivery Module**: Manages delivery lifecycle, from order creation to completion
- **Pizza Module**: Handles pizza menu, pricing, and order statistics
- **Employee Module**: Manages chef and delivery personnel assignments
- **Common**: Contains shared DTOs and utilities used across modules

## 🏗 Architecture Highlights

- **Event-Driven Communication**: Modules communicate through events using Spring Modulith's event system
- **Clear Boundaries**: Each module has its own internal implementation hidden behind public APIs
- **Loose Coupling**: Modules interact through well-defined interfaces and events
- **Data Autonomy**: Each module manages its own data, avoiding cross-module dependencies

## 🚀 Running Locally

### Prerequisites

- Java 24 or higher
- Gradle 8.5+
- Docker

### Running the Application

```bash
# Build the project
./gradlew build
```

```bash
# Run the application
./gradlew bootRun
```

The application will be available at `http://localhost:8080`

## 📚 Documentation

### Spring Modulith Documentation

- Generated Modulith documentation can be found at `build/spring-modulith-docs/all-docs.adoc` after running:

```bash
./gradlew test
```

- This documentation includes:
    * Module structure and dependencies
    * API boundaries
    * Event flows
    * Canvas diagrams

### API Documentation

Swagger UI is available at: `http://localhost:8080/swagger-ui.html`

## 🔄 Example Workflow

1. Customer places an order
2. System assigns available chef
3. Chef prepares the pizza
4. System assigns delivery driver
5. Driver delivers the order
6. System updates statistics

## 🛠 Technical Details

- Built with Spring Boot 3.4.5
- PostgreSQL for persistence
- Docker Compose support for development

## 📖 Further Reading

- [Spring Modulith Documentation](https://docs.spring.io/spring-modulith/reference/)
- [Modular Monoliths](https://www.kamilgrzybek.com/design/modular-monolith-primer)
- [Package by Feature vs Package by Layer](https://phauer.com/2020/package-by-feature/) 