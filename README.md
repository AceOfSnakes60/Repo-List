
# Repo-List

**Repo-List** is a Java + Spring Boot application that fetches GitHub repositories and their branches for a given GitHub username using the GitHub REST API.




## Tech Stack

-  **Java 21**
-  **Spring Boot 3.4.4**
-  **Spring Boot Starter Web**
-  **Maven**


## Run Locally

Clone the project

```bash
  git clone https://github.com/yourusername/repo-list.git
    
```

Go to the project directory

```bash
  cd repo-list
```

Build & Run

```bash
  ./mvnw spring-boot:run
```

By default, the server runs at http://localhost:8080.


## API Reference

#### Get Repositories from username

```http
  GET /{username}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| username  | `string` | **Required**.              |

