# spring-mini-projects
Multiple small Spring Boot projects for learning and demonstration. 


## Projects

### Book Catalog REST API
  A simple RESTful API to manage a collection of books. The API supports basic operations like:
  * Listing all Books
  * Getting a Book by ID
  * Adding a new Book
  * Updating a Book
  * Deleting a Book

  | Method | Endpoint          | Description       |
  | ------ | ----------------- | ----------------- |
  | GET    | `/api/books`      | Get all books     |
  | GET    | `/api/books/{id}` | Get book by ID    |
  | POST   | `/api/books`      | Add new book      |
  | PUT    | `/api/books/{id}` | Update book by ID |
  | DELETE | `/api/books/{id}` | Delete book by ID |

### Student Course Enrollment System

  | Feature                   | Endpoint                         | Method | Description                |
  | ------------------------- | -------------------------------- | ------ | -------------------------- |
  | List all students         | `/api/students`                  | GET    | Fetch all                  |
  | Create student            | `/api/students`                  | POST   | Add new                    |
  | Enroll student            | `/api/enrollments`               | POST   | Add enrollment             |
  | Get student's enrollments | `/api/students/{id}/enrollments` | GET    | View courses for a student |
  | Unenroll student          | `/api/enrollments/{id}`          | DELETE | Remove enrollment          |
  | List courses              | `/api/courses`                   | GET    | Fetch all                  |
  | Add new course            | `/api/courses`                   | POST   | Add new course             |

