# Mortgage Calculator RESTful Service

This project implements a mortgage calculator as a RESTful service using Spring Boot. It allows users to calculate mortgage-related information such as monthly payments and payment schedules.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your machine
- Apache Maven for building the project
- An Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or Spring Tool Suite

### Installation

1. Clone the repository to your local machine:


2. Open the project in your preferred IDE.

3. Build the project using Maven:


4. Run the application:


5. Once the application is running, you can access the endpoints using tools like cURL, Postman, or any web browser.

## Usage

### Endpoints

- **POST** `/api/v1/mortgage`: Calculate mortgage information by providing loan amount, interest rate, loan term.

#### Request Body Example:

```json
{
  "loan_amount": 90000,
  "interest_Rate": 3.92,
  "loan_term_years": 10
}
