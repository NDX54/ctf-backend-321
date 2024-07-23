# CTF Project Setup Guide

Welcome to the Capture the Future Capstone project! This guide is designed to help you set up and run a Spring Boot backend with a React frontend on your machine. We'll walk you through every step, ensuring you have all the necessary tools installed and configured properly.

# Setting up Environment

## Installing a Specific Version of Java 22

To ensure compatibility and consistency across development environments, it's crucial to use the specific version of Java required for the project. This guide will walk you through downloading and installing Java 22, specifically targeting version `22.0.x` (replace `x` with the minor version number you need, if applicable).

### For Windows Users

1. **Download Java 22:**
   - Visit the [Oracle JDK Download Page](https://www.oracle.com/java/technologies/downloads/#jdk22).
   - Look for the specific version of Java 22 you need (We are using, 22.0.1)
   - Click on the download link for Windows (.exe installer).

2. **Install Java 22:**
   - Locate the downloaded `.exe` file in your Downloads folder and double-click it to start the installation process.
   - Follow the on-screen instructions. When prompted, make sure to set the installation path to a known directory for easier configuration of the JAVA_HOME environment variable.

3. **Set JAVA_HOME Environment Variable:**
   - Search for "Edit the system environment variables" in the Start menu and open it.
   - Click on the "Environment Variables" button.
   - Under "System variables," click "New" to create a new variable.
   - Name the variable `JAVA_HOME` and set its value to the path where you installed JDK 17 (e.g., `C:\Program Files\Java\jdk-22.0.x`).
   - Click "OK" to save your changes.

4. **Update the System PATH:**
   - In the same "Environment Variables" window, find the "Path" variable under "System variables" and select "Edit."
   - Click "New" and add `%JAVA_HOME%\bin`.
   - Click "OK" to save your changes and close all remaining windows.

5. **Verify the Installation:**
   - Open a new Command Prompt window and type `java -version` and `javac -version`.
   - If Java is correctly installed, you should see the version number you installed displayed.

### For macOS Users

1. **Download Java 22:**
   - Visit the [Oracle JDK Download Page](https://www.oracle.com/java/technologies/downloads/#jdk22).
   - Find the specific version of Java 22 you need (We are using, 22.0.1).
   - Click on the download link for macOS (.dmg installer).

2. **Install Java 22:**
   - Open the downloaded `.dmg` file and follow the installation instructions.

3. **Set JAVA_HOME Environment Variable:**
   - Open Terminal.
   - Run the following command to edit your shell profile (replace `nano` with your preferred text editor if necessary):
     ```bash
     nano ~/.zshrc
     ```
     If you are using Bash instead of Zsh, you might need to edit `~/.bash_profile` or `~/.bashrc` instead.
   - Add the following line to set the `JAVA_HOME` variable (adjust the JDK path as necessary):
     ```bash
     export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.0.x.jdk/Contents/Home
     ```
   - Save the file and exit the text editor.
   - Apply the changes by running `source ~/.zshrc` or reopen Terminal.

4. **Verify the Installation:**
   - In Terminal, type `java -version` and `javac -version`.
   - You should see the installed version of Java displayed.
## Install Maven

1. **Download Maven:**
   - Go to the [Official Apache Maven Project Page](https://maven.apache.org/install.html).
   - Download the latest version of Maven.

2. **Install Maven:**
   - Extract the archive to your desired location.
   - Add the `bin` directory from the extracted folder to your PATH environment variable.
   - Verify the installation by opening a terminal and running `mvn -version`.

## Node.js Setup with NVM (Node Version Manager)

To ensure compatibility and ease of managing different Node.js versions, we recommend using NVM (Node Version Manager). This allows you to work with multiple Node.js versions and easily switch between them. Follow the instructions below to install a specific version of Node.js, which is `21.7.1` for the ShopNShelf project.

### Installing NVM

#### Windows Users

1. **Download NVM for Windows:**
   - Visit the [NVM for Windows Releases Page](https://github.com/coreybutler/nvm-windows/releases).
   - Download the latest `nvm-setup.zip` file.

2. **Install NVM for Windows:**
   - Extract the downloaded zip file.
   - Run the extracted installer, `nvm-setup.exe`, and follow the installation prompts.

#### macOS Users

1. **Open Terminal:**
   - Open the Terminal application found in `/Applications/Utilities/`.

2. **Install NVM:**
   - Copy and paste the following command into Terminal and press Enter:
     ```sh
     curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
     ```
   - After the installation script runs, close and reopen Terminal.

3. **Verify NVM Installation:**
   - In Terminal, type `nvm --version` and press Enter. If the installation was successful, you should see the version number of NVM displayed.

### Installing Node.js Version 20.11.1

Once NVM is installed, you can easily install and use the specific Node.js version required for the project.

1. **Install Node.js Version 20.11.1:**
   - Open your Terminal (macOS) or Command Prompt (Windows).
   - Run the following command:
     ```sh
     nvm install 21.7.1
     ```

2. **Set Node.js Version 20.11.1 as Default:**
   - After installation, set this version as your default Node version by running:
     ```sh
     nvm use 21.7.1
     ```
   - To ensure this version is automatically selected in new terminal sessions, run:
     ```sh
     nvm alias default 21.7.1
     ```

3. **Verify Node.js Installation:**
   - To confirm that the correct version of Node.js is being used, type:
     ```sh
     node -v
     ```
   - This command should display `v21.7.1`, indicating that the correct version of Node.js is installed and active.

## Next Steps

With NVM installed and the specific version of Node.js set up, you're ready to proceed with the rest of the project setup. Remember to switch to the correct Node.js version with `nvm use 21.7.1` whenever you're working on this project to maintain compatibility.

For more information on NVM and managing multiple Node.js versions, visit the [NVM GitHub Page](https://github.com/nvm-sh/nvm).

### Choose and Install an IDE

#### For Visual Studio Code:

1. **Download VSCode:**
   - Visit the [Official Visual Studio Code Download Page](https://code.visualstudio.com/Download).
   - Download the version for your operating system.

2. **Install VSCode:**
   - Run the downloaded installer.
   - Follow the installation instructions, opting to add VSCode to your PATH for easy terminal access.

#### For IntelliJ IDEA:

1. **Download IntelliJ IDEA:**
   - Go to the [Official IntelliJ IDEA Download Page](https://www.jetbrains.com/idea/download/).
   - Choose the Community or Ultimate version and download it for your operating system.

2. **Install IntelliJ IDEA:**
   - Run the downloaded installer.
   - Follow the installation prompts, choosing your preferred settings.

## Project Setup

1. **Clone the Repository:**
   - Open a terminal or command prompt.
   - Navigate to the folder where you want to clone the project.
   - Run `git clone <repository-url>`. Replace `<repository-url>` with the URL of the project repository.

2. **Open the Project in Your IDE:**
   - **VSCode:** Open VSCode, go to `File` > `Open Folder`, and select the cloned project folder.
   - **IntelliJ IDEA:** Open IntelliJ IDEA, click on `Open`, and select the cloned project folder. Import the project as a Maven project if prompted.

# Running the Application (Frontend and Backend)

### Spring Boot Application

1. **Open a Terminal for the Backend:**
   - Navigate to the `ctf-backend` folder inside your project directory.

2. **Run the Spring Boot Application:**
   - Execute `mvn spring-boot:run`.
   - The backend will start and be accessible at `http://localhost:8080`.

### React Application

1. **Open a Separate Terminal for the Frontend:**
   - Navigate to the `ctf-client` folder inside your project directory.

2. **Install Dependencies:**
   - Run `npm install` to install all required npm packages.

3. **Start the React Application:**
   - Execute `npm start`.
   - The frontend will start and automatically open in your default web browser at `http://localhost:3000`.

## Accessing the Application

- **Frontend:** The React application is now running on `http://localhost:3000`.
- **Backend:** The Spring Boot application is accessible via API calls at `http://localhost:8080`.

## Troubleshooting

- If you encounter any issues with `mvn spring-boot:run`, ensure Maven is correctly installed and your JAVA_HOME environment variable is set.
- For npm-related issues, verify that Node.js and npm are correctly installed and accessible from your terminal or command prompt.

## Additional Resources

- [Official Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Official React Documentation](https://reactjs.org/docs/getting-started.html)

Congratulations! You have successfully set up and run the Capture the Future project on your machine. Happy coding!
