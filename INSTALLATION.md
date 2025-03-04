# Installation Guide 🚀

![Java](https://img.shields.io/badge/Java-Swing-orange?logo=java)
![Java SE](https://img.shields.io/badge/Java%20SE-17-blue?logo=openjdk)
![JUnit](https://img.shields.io/badge/JUnit-5-green?logo=junit5)

This guide will walk you through the steps to set up and run the Gantt Chart Application on your local machine.

---

## Prerequisites 📋

Before you begin, ensure you have the following installed:

- **Java Swing**  
- **Java SE 17**  
- **JUnit 5**  

---

## Steps to Install 🛠️

### 1. Clone the Repository

Clone the repository to your local machine using the following command:

```bash
git clone https://github.com/yourusername/gantt-chart-application.git
cd gantt-chart-application
```

### 2. Open the Project in Your IDE
Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

**IntelliJ IDEA:**  
File → Open → Select the project folder

**Eclipse:**  
File → Import → Existing Projects into Workspace

### 4. Add JUnit 5 Dependencies
Ensure JUnit 5 is added to your project dependencies. If you're using:

**Maven:** Add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.0</version>
    <scope>test</scope>
</dependency>
```

### Running the Application 🚀
Once the setup is complete, you're ready to start the application. Refer to the Running the Application section for more details.

### Troubleshooting 🛑
If you encounter any issues during installation, please check the following:

- Ensure all prerequisites are installed and configured correctly.
- Check the project's README.md for additional setup instructions.
