# Features

- Authentication (JWT)
- Authorization
- Bidirectional Relationship
- DTO
- Validation
- Exception Handler
- Swagger openAPI("/swagger-ui.html")
- /me route


## Refactoring Steps
- hiding stacktrace on error responses 
- invalid post request shows details of the system
- in controllers, no need to specify ResponseEntity.ok(..

## Project Steps

- [x] Task Controller
- [x] Task Entity
- [x] Task CRUD
- [x] Task data initialization test
- [x] User Controller
- [x] User Entity
- [x] User CRUD
- [x] Task-User relationship
- [x] Authentication init (JWT Auth)
- [x] Authentication is completed
- [x] Authorization init (permits all routes for now)
- [x] Register endpoint
- [x] Auth has been simplyfied
- [x] Services has been simplyfied
- [x] Controllers has been simplyfied
- [x] Global ControllerAdvice for exceptions
- [x] Index controller
- [ ] Secret keys config 
