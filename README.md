# Health App

## Description
A comprehensive health management system built using a microservice architecture. This app enables seamless management of healthcare services

##Project Architecture & Tools
![Project Architecture](https://drive.google.com/uc?export=view&id=1sWI50G4oUa8chEkfhvzJcretGLrstsEO)

## Installation
1. Clone the repository:
    ```bash
    git clone <repository-url>
    ```
2. Navigate to the project directory:
    ```bash
    cd healthcare-microservices
    ```
3. Follow any setup instructions (e.g., installing dependencies, environment setup, etc.)

## Microservices
- Patients:
  Focuses on the patient's privileges which mainly are:
    - Sign Up / in
    - Reviewing a list of doctors
    - Scheduling an appointment
    - Getting access to their medical history
- Doctors:
  Focuses on the doctor's privileges which mainly are:
    - Setting up their availabilities
    - Billing information
    - etc.
- Appointments:
  This microservice is built to facilitate the connection between doctor and patient when needing to schedule an appointment, mainly holds:
  - Simple CRUD actions
  - Advanced features for the communication between the required microservices to attain its goal.
- Medical records:
  It holds the medical record of a patient that is either attained by a patient's input or through multiple inputs by doctors on the plateforme.
- Billing:
  The billing procedure is based on the transparency between patient and doctor in terms of finances and ease of payment.

## Contributing
This project was brought to life thanks to the contribution of:
- [Yasmine Rajhi](https://github.com/yasmineRajhi) : Appointment MicroService
- [Malek Khelil](https://github.com/mkh-dev) : Patient MicroService
- [Zied Ghanem](https://github.com/zied-gh) : Doctor MicroService
- [Ons Hamzaoui](https://github.com/onshamzaoui) : Medical record MicroService
