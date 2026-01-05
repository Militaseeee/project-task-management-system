# 📖 API Reference & Examples

All requests must be sent with the header `Content-Type: application/json`. Protected routes require a `Bearer <token>` in the `Authorization` header.

## 📂 Project Structure (Hexagonal Architecture)

This project follows the **Hexagonal Architecture** (Ports and Adapters) pattern to ensure a strict separation between business logic, application orchestration, and infrastructure details.

```text
src/main/java/com/system/projectmanagement
│
├── domain/                         # Core Business Logic (No dependencies on frameworks)
│   ├── models/                     # Pure Domain Entities (Project, Task, User)
│   │   ├── Project.java
│   │   ├── Task.java
│   │   └── User.java
│   ├── ports/                      # Interfaces for communication
│   │   ├── in/                     # Inbound Ports (Use Case interfaces)
│   │   │   ├── ProjectServicePort.java
│   │   │   └── TaskServicePort.java
│   │   └── out/                    # Outbound Ports (Repository/External interfaces)
│   │       ├── ProjectRepositoryPort.java
│   │       ├── TaskRepositoryPort.java
│   │       └── NotificationPort.java
│   └── exceptions/                 # Custom Domain Exceptions
│       └── EntityNotFoundException.java
│
├── application/                    # Application Orchestration
│   ├── usecases/                   # Implementation of Inbound Ports
│   │   ├── ProjectUseCaseImpl.java
│   │   └── TaskUseCaseImpl.java
│   └── dto/                        # Data Transfer Objects (Request/Response)
│       ├── request/                # DTOs coming from the client
│       └── response/               # DTOs sent to the client
│
├── infrastructure/                 # External Tools & Frameworks (Adapters)
│   ├── adapters/
│   │   ├── inbound/                # Primary Adapters (REST Controllers)
│   │   │   └── controllers/
│   │   │       ├── AuthController.java
│   │   │       ├── ProjectController.java
│   │   │       └── TaskController.java
│   │   └── outbound/               # Secondary Adapters (Persistence, APIs)
│   │       ├── persistence/        # Database implementation details
│   │       │   ├── entities/       # JPA Entities (Database Table Mapping)
│   │       │   ├── repositories/   # Spring Data JPA Interfaces
│   │       │   └── PostgreSqlAdapter.java # Implementation of Repository Port
│   │       └── external/           # Integration with external services
│   │
│   ├── mappers/                    # Converters (Entity <-> Domain <-> DTO)
│   │   ├── ProjectMapper.java
│   │   └── TaskMapper.java
│   │
│   ├── config/                     # Framework Configuration
│   │   ├── BeanConfiguration.java  # Manual Bean injection for Hexagonal logic
│   │   ├── SecurityConfig.java     # Spring Security & JWT Config
│   │   └── OpenApiConfig.java      # Swagger/OpenAPI Config
│   │
│   └── security/                   # JWT & Auth implementation details

## 🔐 Authentication

| Method | Endpoint | Description | Auth |
| --- | --- | --- | --- |
| `POST` | `/api/v1/auth/register` | Register a new user | No |
| `POST` | `/api/v1/auth/login` | Login and receive JWT | No |

### Example Login Request

```json
{
  "username": "admin",
  "password": "password123"
}

```

---

## 📂 Projects

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/v1/projects?page=0&size=10` | Get all projects (Paginated) |
| `GET` | `/api/v1/projects/{id}` | Get project by ID |
| `POST` | `/api/v1/projects` | Create a new project |
| `PUT` | `/api/v1/projects/{id}` | Update an existing project |
| `DELETE` | `/api/v1/projects/{id}` | Soft delete a project |

### Create Project Body

```json
{
  "name": "E-commerce Platform",
  "description": "Redesigning the main shopping cart flow",
  "startDate": "2026-01-10",
  "status": "ACTIVE"
}

```

---

## 📝 Tasks

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/v1/tasks?projectId=1` | Get tasks by Project ID |
| `POST` | `/api/v1/tasks` | Assign a task to a project |
| `PUT` | `/api/v1/tasks/{id}` | Update task status/details |
| `DELETE` | `/api/v1/tasks/{id}` | Remove/Archive a task |

### Create Task Body

```json
{
  "projectId": 1,
  "title": "Fix API Authentication",
  "description": "Resolve the JWT expiration bug",
  "assignedTo": "dev_user_01",
  "priority": "HIGH",
  "dueDate": "2026-01-15"
}

```

---

## 🔍 Advanced Features

### 1. Pagination and Sorting

For lists of projects or tasks, you can use the following query parameters:

* `page`: Results page number (starting from 0).
* `size`: Number of records per page.
* `sort`: Attribute to sort by (e.g., `name,asc` or `createdAt,desc`).

**Example:**
`GET /api/v1/projects?page=0&size=5&sort=name,asc`

### 2. Soft Delete Logic

When a `DELETE` request is sent, the system does not remove the row from the database. It updates the `is_active` or `deleted_at` field.

* **Success Response:** `204 No Content`

### 3. Auditing Fields

The following fields are automatically managed by the system and returned in every GET response:

```json
{
  "createdAt": "2026-01-05T10:00:00",
  "createdBy": "system_admin",
  "updatedAt": "2026-01-05T11:45:00",
  "updatedBy": "system_admin"
}

```

---

## 🛠️ Testing with cURL

If you want to test via terminal:

**Create Project:**

```bash
curl -X POST http://localhost:8080/api/v1/projects \
-H "Authorization: Bearer YOUR_TOKEN_HERE" \
-H "Content-Type: application/json" \
-d '{"name": "New Project", "description": "Testing endpoints"}'

```

