# LearnRoom

Room Database
The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

Room provides the following benefits:

Compile-time verification of SQL queries.
Convenience annotations that minimize repetitive and error-prone boilerplate code.
Streamlined database migration paths.


There are three major components in Room:

The “database class” that holds the database and serves as the main access point for the underlying connection to our app's persisted data.

“Data entities” that represent tables in our app's database.

“Data access objects (DAOs)” that provide methods that our app can use to query, update, insert, and delete data in the database.
	



To prevent queries from blocking the UI, Room does not allow database access on the main thread. This restriction means that you must make your DAO queries asynchronous.

DAO queries fall into three categories:

One-shot write queries that insert, update, or delete data in the database.

One-shot read queries that read data from our database only once and return a result with the snapshot of the database at that time.

Observable read queries that read data from our database every time the underlying database tables change and emit new values to reflect those changes.
