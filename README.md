# Patient Management System

A modern healthcare application that helps medical staff manage patient information securely and efficiently.

## Features

- **Secure Login**: Simple and secure way for staff to log in and access the system
- **Patient Records**: Easily create, view, update, and delete patient information
- **Central Access Point**: Single entry point to access all system features with built-in security
- **Smart Service Connection**: Automatic connection between different parts of the system
- **Data Insights**: Tools to analyze and understand patient data
- **Billing Management**: Easy tracking and management of patient billing information

## How It Works

The system is built using a modern approach that divides functionality into separate, specialized services:

- **Gateway**: Acts as the front door to the system, directing requests to the right place
- **Login Service**: Handles user logins and security checks
- **Patient Service**: Manages all patient information and records
- **Billing Service**: Takes care of all billing-related tasks
- **Analytics Service**: Provides helpful insights from patient data
- **Service Connector**: Helps all services find and talk to each other

## Technologies Used

The system is built with modern, industry-standard tools:

- **Spring Boot**: Makes building robust applications easier
- **Security Tokens**: Ensures only authorized users access the system
- **Modern APIs**: Allows different parts of the system to communicate
- **Automated Testing**: Ensures the system works correctly
- **Documentation**: Makes the system easier to understand and use

## Getting Started

1. Download the project to your computer
2. Install Java and Maven if you don't have them
3. Start the Service Connector first
4. Start the other services (Login, Patient, etc.)
5. Access the system through your web browser at http://localhost:4004

## Main System Functions

- **Login**: 
  - Log in with your username and password
  - System verifies your identity for security

- **Patient Management**:
  - View all patients in the system
  - Add a new patient to the system
  - Update existing patient information
  - Remove a patient from the system