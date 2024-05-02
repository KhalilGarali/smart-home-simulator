# Smart Home Simulator

Welcome to our Smart Home Simulator repository! This project is a Java-based software system designed to simulate and experiment with smart home automation, offering a user-friendly graphical interface for homeowners, researchers, and stakeholders.

## Problem Statement

Navigating complex smart home systems poses challenges for users, researchers, and stakeholders. This simulator aims to address these difficulties by providing an intuitive interface and experimentation tools.

## Key Features

- **Simulation Panel:** Central hub for managing smart home simulation, featuring user profile control, time settings, and external data integration.
  
- **Module Panel:** Comprehensive control center with modules for user profiles, house peripherals (lights, doors, windows), security settings, and heating/cooling zones.

- **Console Panel:** Displays system events and notifications in a clear, organized format, allowing users to monitor and troubleshoot smart home operations.

- **House Layout Panel:** Visual representation of the smart home layout, providing real-time insights into room conditions and appliance control.

## Architecture and Design  

### Context Diagram  

The context diagram illustrates the architecture of a Smart Home Simulator system. At its core is the simulator itself, serving as the central hub for interactions. Users log in to create scenarios and manage settings, simulating real-world interactions with a smart home. The Virtual Person, representing a human occupant, is managed within the system. The Smart Home Module, controlled by the simulator, integrates smart devices and systems, acting as the functional brain of the smart home. The Sensor API connects the system to virtual sensors, providing data like temperature or motion, and relaying alerts back to trigger events. The Simulation Environment is the virtual space where these interactions occur, allowing users to model and analyze smart home behaviors under various conditions, all without physical device interaction.  

![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/2e6027ab-b687-496f-8bc2-06c2e2a24b7c)  

### Domain Model  

The Domain Model outlines the object-oriented design of a simulated smart home, centered around the SHS (Smart Home Simulator) module. The model includes Room classes that aggregate into a House class, which in turn aggregates into the SHS class. Each Room can host multiple User instances and is equipped with Opening and Fixtures classes controllable by the SHH (Smart Home Heating), SHC (Smart Home Control), and SHP (Smart Home Security) modules. This modular system integrates various control modules that interact with simulated sensors, such as HVAC for room temperature control and an external Temperature class representing outdoor conditions. Overall, the diagram illustrates the relationships and interactions among users, rooms, and home automation modules within the simulated smart home environment.  

![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/ff9c93cf-0c91-4fa9-91b8-9e5a2b00a387)  

### System Architecture  

The System Architecture of the Smart Home Simulator is structured into three layers: UI Layer, Business Logic Layer, and Model Layer, ensuring modular, scalable, and maintainable design.

**UI Layer**: Responsible for presenting information to users and receiving inputs.
- Dashboard Module: Manages the overall user interface, displaying tabs for smart modules, simulation parameters, and a graphical house layout.
- Module Tabs: Separate tabs for each smart home module (e.g., SHC, Motion Sensor), showing relevant information and controls.
- Simulation Controls: Buttons for controlling simulation parameters like start/stop and time speed.
- House View Component: Real-time graphical representation of the house layout.

**Business Logic Layer**: Contains core logic of the simulator, handling commands and module interactions.
- Smart Home Controller (SHC): Centralized controller for house actions, coordinating with module managers.
- Module Managers: Control specific smart home modules (e.g., Motion Sensor Manager, Lighting Manager) and interactions with SHC.
- Permission Handler: Manages user permissions for executing commands.

**Model Layer**: Represents data and entities within the simulator.
- Simulation Model: Manages simulation state, including environmental conditions, user positions, and device statuses.
- User Profile Model: Stores user profiles with roles and permissions.
- House Layout Model: Represents house layout data (rooms, windows, lights), used for visualization and command processing.

This layered architecture separates concerns, making the system cohesive and facilitating scalability and maintenance of the Smart Home Simulator (SHS).  

![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/1dfe0d8e-9742-45b7-9ca2-3e4ff14ff24a)  
![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/dace6b9e-efb3-4554-8525-cb304f82218d)  

### Design Patterns  

#### Command Design Pattern

The Command design pattern encapsulates requests within objects, enabling modular and concurrent operations in our system. Modules issue commands to rooms based on user input or environmental changes, leveraging this pattern for flexibility. The SHC class serves as the "Invoker," executing commands by invoking methods in ConcreteCommand classes that interact with Room methods to fulfill requests. This design promotes modularity and flexibility, managing operations triggered by user interactions or simulation modules within the Smart Home Simulator architecture.  

![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/93a88a82-41ce-4279-b726-5b5173fa3d3b)  

#### Singleton Design Pattern  

The power of the singleton pattern lies in its simplicity and effectiveness, we employed the pattern for all the modules to allow the simulation modules to have global access through the application.   

![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/8d471271-c5bf-451c-b67e-27a4d07e608d)

#### Observer Design Pattern  

Our team utilized the Observer pattern extensively, providing significant benefits to our system. We implemented the Observer Pattern by making the HVAC class (responsible for regulating room temperatures) an observable class observed by the GUI class HouseLayoutPanel, allowing real-time temperature updates through notifications defined in our Observer interface. Additionally, we demonstrated a unique application where the HVAC class acts as both an observable and an observer. Specifically, the HVAC class observes the Zone class to ensure that rooms automatically respond to changes in zone temperature. This dual role highlights the flexibility and effectiveness of the Observer pattern in facilitating dynamic interactions within our Smart Home Simulator.  

![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/7d9d6700-00bc-4146-807f-a501d6e774cd)  

#### Mediator Design Pattern  

In the Mediator pattern implementation within our system, the Smart Home Simulation serves as the concrete mediator, while the SHP (security) and SHH (heating) modules act as components. An illustrative scenario of this pattern occurs when the user sets the simulation to "away mode" from the SHP module, updating the variable "isAway" and notifying the SHS mediator. The SHS then mediates by instructing the SHC to close/open all windows and doors. This demonstrates the Mediator pattern's role in centralizing communication and coordination between different modules within our Smart Home Simulator, enhancing system flexibility and maintainability.  

![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/813bcc08-84e2-422f-b231-3726e0939129)  

## User Interface  

Our user interface is designed with four key panels to provide a comprehensive experience: the Simulation Panel, Module Panel, Console Panel, and House Layout Panel. Each panel serves a distinct purpose, from visualizing your home's automated processes to configuring individual modules and monitoring system feedback.  

![image](https://github.com/KhalilGarali/smart-home-simulator/assets/112202385/e879652a-386a-44d4-9823-3299684b5948)  


## Product Overview  
- Language: Java
- Framework: Swing
- Number of Lines of Code: 7888
- Number of classes: 72
    - Gui: 10 classes
    - Logic: 40 classes
    - Model: 22 classes

## Conclusion

The development of our Smart Home Simulator project has been a transformative learning experience, allowing our team to deeply apply and understand key software architecture and design principles. Through iterative sprints, we successfully implemented design patterns like Singleton, Observer, and Command, enhancing system modularity and code reusability. This practical application reinforced our skills in system integration, user-centric development, and agile methodologies. Additionally, exploring user interface design and implementing robust security features provided valuable insights into real-world system challenges. Overall, this project has been instrumental in bridging theoretical knowledge with practical application, preparing us for future industry endeavors and emphasizing the continuous evolution required in our careers as software engineers.  


# Contributors:
- Khalil Garaali - [KhalilGr](https://github.com/KhalilGarali)
- Youssef Alsheghri - [yousfino](https://github.com/yousfino)
- Wadeh Hamati - [wade3hamati](https://github.com/wade3hamati)
- Joud Babik - [JRB958](https://github.com/JRB958)
- Abdelhamid Saoud Messaoudi - [saoudmessaoudi](https://github.com/saoudmessaoudi)
- Minh Duc Vu - [MinhDuc1711](https://github.com/MinhDuc1711)
  
# Running the Program

To run the Smart Home Simulator on your local machine, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/KhalilGarali/smart-home-simulator.git
   ```
2. Navigate to the specified directory:
   ```bash
   cd smart-home-simulator
   ```
3. Compile the java source files:
   ```bash
   javac src/*.java
   ```
4. Run The Simulator:
   ```bash
   java src.Main
   ```
5. Dummy login Credentials:
   - Username: username
   - Password: password
6. Choose a house layout file under the resources folder



