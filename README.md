# Online Library

This project is a small web application (implemented using **Spring MVC** and **JPA**) that functions as an online library, where users can **add, edit, delete, and view books** stored in the system.

To run the application, the following requirements must be met:

- **JDK 17** (or higher)
- **MySQL**

A **JAR build**, created using Maven, is also available and can be executed directly.

From inside the `online_library` directory, run:

java -jar target/online_library-0.0.1-SNAPSHOT.jar

Once the application starts, open a browser and visit:

http://localhost:8080/

This will automatically redirect to:

http://localhost:8080/libri

The application allows users to:

- View all previously inserted books
- Add new books
- Edit existing books
- Delete books

Books can also be **sorted** by:

- Title
- Author
- Genre
- Release year

Sorting can be performed in **ascending** or **descending order**.

To add a book, the user selects **"Add a book"**, which redirects to a form where the required fields must be filled in.
All fields must be completed and must satisfy the specified validation constraints.

To edit a book, the user selects **"Edit"** in the row corresponding to the desired book.
This redirects to a form similar to the add form, allowing the user to modify the desired fields.

To delete a book, the user selects **"Delete"** in the row corresponding to the desired book.
A confirmation message is displayed before the deletion is completed.

The application follows the **MVC (Model-View-Controller) pattern** using Spring.

The **Model** represents the application's data logic.

In this project the following class belongs to this layer:

- `Libro`

It represents the data associated with books, including:

- title
- author
- genre
- release year

The class is annotated as a **JPA `@Entity`**, meaning it is mapped to a database table.

The **View** represents the user interface built with **Thymeleaf**.

The following templates are implemented:

- `libri.html`  
  Main page displaying the list of books.

- `aggiungi_libro.html`  
  Form used to add a new book.

- `modifica_libro.html`  
  Form used to edit an existing book.

The **Controller** handles HTTP requests and coordinates the interaction between the Model and the View.

The main controller is:

- `LibroController`

It manages the following operations:

- Display the list of books and handle sorting (`getLibri`)
- Show the form to add a book (`showAddLibroForm`)
- Insert a new book (`addLibro`)
- Show the form to update a book (`showUpdateLibroForm`)
- Update a book (`updateLibro`)
- Delete a book (`deleteLibro`)

Another controller is also present:

- `HomeController`

This controller automatically redirects the root path `/` to `/libri`, which represents the application's home page.

Database access is handled through:

- `LibroRepository`

This interface extends **`JpaRepository`**, allowing Spring to automatically generate the database access implementation.

A custom method is also defined:

`findByTitoloAndAutore`

This method checks whether a book with a specific **title** and **author** already exists in the database.

Unit tests were implemented to verify the correct behavior of each class in the application.
