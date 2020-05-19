#include <GL/glew.h>
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <GLFW/glfw3.h>
#define ASSERT(x) if (!(x)) __debugbreak();
#define GLCall(x) GLCLearError(); x; ASSERT(GLLogCall(#x, __FILE__, __LINE__))
static void GLCLearError() 
	{ while (glGetError() != GL_NO_ERROR); }
static bool GLLogCall(const char* function, const char* file, int line)
{
	while (GLenum error = glGetError())
	{
		std::cout << "[OpenGL Error] (" << error << "): " << function << " " << file << ": " << line << std::endl;
		return false;
	}
	return true;
}
static std::string ConvertShader(const std::string& shaderFilestream)
{
	std::ifstream stream;
	stream.open(shaderFilestream);
	std::stringstream fileContents;
	fileContents << stream.rdbuf();
	return fileContents.str();
}
static unsigned int CompileShader(unsigned int type, const std::string source)
{
	unsigned int id = glCreateShader(type);
	const char* src = source.c_str();
	glShaderSource(id, 1, &src, nullptr);
	glCompileShader(id);
	int result;
	glGetShaderiv(id, GL_COMPILE_STATUS, &result);
	// ---ERROR HANDLING---
	if (result == GL_FALSE)
	{
		int length;
		glGetShaderiv(id, GL_INFO_LOG_LENGTH, &length);
		char* message = (char*)_malloca(length * sizeof(char));
		glGetShaderInfoLog(id, length, &length, message);
		std::cout << "Failed to compile " << (type == GL_VERTEX_SHADER ? "vertex" : "fragment") << "shader!" << std::endl;
		std::cout << message << std::endl;
		glDeleteShader(id);
		return 0;
	}
	// --------------------
	return id;
}
static unsigned int CreateShader(const std::string & vShade, const std::string& fShader)
{
	unsigned int program = glCreateProgram();
	unsigned int vs = CompileShader(GL_VERTEX_SHADER, vShade);
	unsigned int fs = CompileShader(GL_FRAGMENT_SHADER, fShader);
	glAttachShader(program, vs);
	glAttachShader(program, fs);
	glLinkProgram(program);
	glValidateProgram(program);
	glDeleteShader(vs);
	glDeleteShader(fs);
	return program;
}
int main(void)
{
	GLFWwindow* window;
	if (!glfwInit())
	{
		std::cout << "GLFW Initialization Failed" << std::endl;
		return -1;
	}
	else
		std::cout << "GLFW Initializated Successfully!" << std::endl;
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);							// Setting GLFW major version to be 3	// Version 3.?
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);							// Setting GLFW minor version to be 3	// Version 3.3
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);			// Setting OpenGL profile to be core
	window = glfwCreateWindow(720, 720, "Hello World", NULL, NULL);
	if (!window)
	{
		glfwTerminate();
		return -1;
	}
	glfwMakeContextCurrent(window);
	glfwSwapInterval(1);
	if (glewInit() != GLEW_OK)
	{
		std::cout << "GLEW Initialization Failed!" << std::endl;
		return -1;
	}
	else
	{
		std::cout << "GLEW Initializated Successfully!" << std::endl;
		std::cout << "GLEW Version: " << glGetString(GL_VERSION) << std::endl;
	}
	float pos[] =		// Vertex Position
	{
		-0.5f, -0.5f,	// 0
		 0.5f, -0.5f,	// 1
		 0.5f,	0.5f,	// 2
		-0.5f,  0.5f	// 3
	};
	unsigned int indicies[]
	{
		0, 1, 2,		// Triangle One
		2, 3, 0			// Triangle Two
	};
	// ---VAO---								// ---VAO---
	unsigned int vao;							// vao holds vertex array id
	GLCall(glGenVertexArrays(1, &vao));			// Generate 1 array, store id in vertex array
	GLCall(glBindVertexArray(vao));				// Bind vertex array as vao id
	// ---------								// ---------
	// ---BUFFER---																		// ---BUFFER---
	unsigned int buffer;																// Buffer Variable, holds buffer id
	GLCall(glGenBuffers(1, &buffer));													// Generate buffer
	GLCall(glBindBuffer(GL_ARRAY_BUFFER, buffer)); 										// Bind(select) buffer
	GLCall(glBufferData(GL_ARRAY_BUFFER, 4 * 2 * sizeof(float), pos, GL_STATIC_DRAW)); 	// Buffer data
	GLCall(glEnableVertexAttribArray(0)); 												// Enable Attribute
	GLCall(glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, sizeof(float) * 2, 0));		// Vertex Attribute for our position		// Also links vertex array with buffer
	// ------------																		// ------------
	// ---IBO---																						// ---IBO---
	unsigned int ibo;																					// Buffer int
	GLCall(glGenBuffers(1, &ibo));																		// Generate buffer
	GLCall(glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo));													// Bind(select) buffer
	GLCall(glBufferData(GL_ELEMENT_ARRAY_BUFFER, 6 * sizeof(unsigned int), indicies, GL_STATIC_DRAW));	// Buffer data
	// ---------																						// ---------
	std::string vertexShader = ConvertShader("res/Shaders/DefaultVertex.shader");		// Find and convert the default vertex shader to a string to use in the program
	std::string fragmentShader = ConvertShader("res/Shaders/DefaultFragment.shader");	// Find and convert the default fragment shader to a string to use in the program
	unsigned int shader = CreateShader(vertexShader, fragmentShader);					// Create a shader using the vertex and fragment shader
	GLCall(glUseProgram(shader));														// Use shader variable as the shaders in the program
	GLCall(int location = glGetUniformLocation(shader, "uColor"));						// Get uColor and store the value in location
	
	GLCall(glBindVertexArray(0));
	GLCall(glUseProgram(0));
	GLCall(glBindBuffer(GL_ARRAY_BUFFER, 0));
	GLCall(glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0));
	
	float r = 0.0f;							// Red value
	float increment = 0.005f;				// Increment red by 0.005
	while (!glfwWindowShouldClose(window))	// Loop Whilst window is open
	{
		GLCall(glClear(GL_COLOR_BUFFER_BIT));
		GLCall(glUseProgram(shader));
		GLCall(glUniform4f(location, r, 0.3f, 0.8f, 1.0f));
		GLCall(glBindVertexArray(vao));
		GLCall(glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo));
		GLCall(glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, nullptr));				// Drawing Triangles with data provided
		if (r > 1.0f)					// If r is greater than one
			increment = -0.005f;		// Start incrementing by negative 0.005
		else if (r < 0.0f)				// If r is less than zero
			increment = 0.005f;			// Start incrementing by positive 0.005
		r += increment;					// add increment to r
		glfwSwapBuffers(window);		// Swap front and back buffers
		glfwPollEvents();				// Poll for and process events
	}
	GLCall(glDeleteProgram(shader));	// Delete Program
	glfwTerminate();					// Terminate GLFW
}
