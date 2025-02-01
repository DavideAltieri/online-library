OnlineLibrary



Si tratta di una piccola applicazione web (implementata con MVC e JPA) che funge da libreria online, in cui l'utente può aggiungere, modificare ed eliminare i libri presenti, oltre a visualizzare i libri precedentemente inseriti.

Per avviare l'applicazione, è necessario disporre di JDK 17 (o superiore) e di MySQL. È anche disponibile una build JAR, creata tramite Maven, che può essere eseguita direttamente (eseguendo "java -jar target/online_library-0.0.1-SNAPSHOT.jar" all'interno della directory "online_library"). Una volta avviata l'applicazione, è sufficiente visitare su un browser "http://localhost:8080/", che effettuerà automaticamente un redirect verso "http://localhost:8080/libri".

L'utente sarà in grado di ordinare i libri per nome, per autore, per genere, o per anno, sia in ordine crescente che in ordine decrescente.
Per aggiungere un libro l'utente deve selezionare "Aggiungi un libro", che effettuerà un redirect verso un form per aggiungere un libro (con i relativi campi, l'utente deve riempire tutti i campi e rispettare determinati criteri).
Per modificare un libro l'utente deve selezionare "Modifica" nella riga del libro interessato, che effettuerà un redirect verso un form simile a quello di aggiunta, che permetterà all'utente di modificare i campi che desidera.
Per eliminare un libro l'utente deve selezionare "Elimina" nella riga del libro interessato, verrà mostrato un messaggio di conferma.

Per quanto riguarda l'applicazione, è stato utilizzato un pattern MVC (con Spring):
	-Model: rappresenta la logica dei dati dell'applicazione. In questo caso, la classe "Libro" rientra in questa categoria, rappresenta i dati relativi ai libri (ad esempio: titolo, autore, genere e anno di rilascio). Si tratta di una classe @Entity mappata con JPA.
	-View: rappresenta la parte dell'applicazione che l'utente vede e con cui interagisce. In questo caso, le pagine HTML utilizzano Thymeleaf. Nello specifico ci sono "libri.html", che rappresenta la home dell'applicazione, "aggiungi_libro.html", che rappresenta il form visualizzato quando si aggiunge un libro, e "modifica_libro.html", visualizzato quando si modifica un libro.
	-Controller: gestisce le richieste dell'utente e coordina l'interazione tra il Model e la View. In questo caso, la classe "LibroController" è quella che gestisce le richieste HTTP. Nello specifico ci sono la richiesta di aggiunta gestita da "showAddLibroForm" (per il getMapping) e "addLibro" (per il postMapping), la richiesta di modifica gestita da "showUpdateLibroForm" (per il getMapping) e "updateLibro" (per il postMapping), la richiesta di eliminazione di un libro gestita da "deleteLibro" (che richiede il solo getMapping), ed infine la richiesta iniziale, gestita da "getLibri", che si occupa tra le altre cose di ordinare i libri in base a come viene richiesto dall'utente.

È presente anche un semplice "HomeController", che si occupa di effettuare automaticamente un redirect verso "libri", la home dell'applicazione.
Per quanto riguarda il repository JPA l'interfaccia che se ne occupa è "LibroRepository", che estende JpaRepository. Le implementazioni d'accesso vengono gestite automaticamente e non è necessario indicarle esplicitamente, l'unico metodo indicato esplicitamente è "findByTitoloAndAutore", che viene utilizzato per verificare se esiste nel DB un libro con un determinato titolo ed un determinato autore.

Sono stati implementati anche dei test di unità per verificare il corretto funzionamento di ogni classe implementata nell'applicazione.
