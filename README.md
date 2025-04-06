# 📚 JavaDoc Generator v2.1

A sleek, user-friendly desktop application for generating JavaDoc documentation with HTML5 support. Skip the command line and generate professional documentation for your Java projects with just a few clicks!

![JavaDoc Generator Screenshot](https://github.com/user-attachments/assets/7b92d18d-0e6a-4f79-a4e0-141fa693eb7d)

## ✨ Features

- 🎨 Modern, aesthetically pleasing UI
- 📁 Simple folder-based processing
- 🔄 HTML5 documentation support
- 🔊 Audio feedback on successful generation
- 📝 Real-time console output
- 👍 Visual status indicators
- 🏠 No command-line knowledge required!

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java project with properly formatted JavaDoc comments

### Installation

1. Download the latest JAR file from the [Releases](https://github.com/yourusername/javadoc-generator/releases) page
2. No additional installation required - it's ready to run!

### Running the Application

Simply double-click the JAR file, or run it from the command line:

```bash
java -jar JavadocGenerator.jar
```

## 📖 How to Use

1. **Launch the application**
   - Double-click the JAR file or run it from the command line

2. **Select Source Folder**
   - Click the "Browse" button next to "Source Folder"
   - Navigate to and select the root directory of your Java source files
   - This should be the folder containing your .java files or package structure

3. **Choose Output Folder**
   - Click the "Browse" button next to "Output Folder"
   - Select where you want the JavaDoc documentation to be generated
   - By default, this is set to "./javadoc" in your project folder

4. **Configure Options**
   - Check or uncheck "Use HTML5 format (as Brian likes)" depending on your preference
   - HTML5 format produces more modern-looking documentation

5. **Generate Documentation**
   - Click the "Generate JDoc" button
   - The application will:
     - Find all Java files in the selected source directory
     - Generate JavaDoc documentation based on your code's comments
     - Display progress and any errors in the console area
     - Play a success sound when complete
     - Show a success or error message in the status bar

6. **View Your Documentation**
   - Once generation is complete, navigate to the output folder
   - Open "index.html" in any web browser to view your documentation

## 🧩 How It Works

The application provides a graphical interface to the standard JavaDoc tool that comes with the JDK. Behind the scenes, it:

1. Scans your selected source directory to find all Java files
2. Builds a proper javadoc command with your selected options
3. Executes the command and captures the output
4. Monitors the process for completion or errors
5. Provides visual and audio feedback on completion

The application handles all command-line parameters for you, making it easy to generate documentation without remembering complex command syntax.

## 🤔 Why I Made This

I created this application for several reasons:

- **Avoid Command Line Complexity**: JavaDoc is powerful but requires remembering numerous command-line options. This GUI simplifies the process.
  
- **Practice UI Design**: This project gave me an opportunity to practice creating an aesthetically pleasing UI with modern design elements, custom borders, icons, and color schemes.

- **Enhance User Experience**: I wanted to create a tool that provides immediate feedback through status messages, console output, and audio cues.

- **Learn Swing Components**: Developing this application helped deepen my understanding of Java Swing components and custom UI elements.

- **Help Fellow Developers**: Many Java developers, especially beginners, struggle with documentation generation. This tool makes it more accessible.

## 🛠️ Technology Stack

- Java Swing for the UI components
- Java Sound API for audio feedback
- Standard Java libraries for file operations and process execution

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Oracle's JavaDoc tool for making documentation generation possible
- The Java Swing library for providing flexible UI components
- Brian, for his enthusiastic support of HTML5 format 😉
