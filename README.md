# Proiecte
python_and_c
//open in raw
Aplicatii in Python:
  -Numere - o aplicatie ce simuleaza un calculator cu operatii elementare pt numere complexe
  -Catalog - un catalog pentru studenti
  -Avioane - o aplicatie ce gestioneaza zborurile unei firme dintr-un fisier dat
  -Inchirieri filme - simuleaza procesul de inchiriere si returnare a unor filme
            Secventa din main ce exemplifica operatiile efectuate de aplicatie:
                   /*def Main(self):
                        """
                            Reads the command that the user gives and returns the command as a string
                        """
                        print("Type 'x' to exit.")
                        print("Choose a menu: Movie, Client, Contracts")
                        return input("Option:").strip()

                    def Movie_Menu(self):
                        """
                            Prints the commands for movies and returns the command that the client picked as a string
                        """
                        print(" ")
                        print("---------------------------------------------------------------")
                        print(" Write 'back' to go back to the main menu")
                        print(" Write 'add' to add a new movie in the list")
                        print("Write 'remove' to delete a movie from the list")
                        print("Write 'show' if you want to see all the movies in the list")
                        print("Write 'search' to search a movie after his name")
                        print("Write 'undo' to undo one action in the movie list")
                        print("Write 'update' to update the dates of a movie")
                        print("Write 'random' to generate random a movie")
                        print("----------------------------------------------------------------")

                    def Client_Menu(self):
                        """
                            Prints the commands for client meniu and returns the command that the client picked as a string
                        """
                        print(" ")
                        print("------------------------------------------------------------------")
                        print(" Write 'back' to go back to the main menu")
                        print(" Write 'add' to add a new client in the list")
                        print("Write 'remove' to delete a client from the list")
                        print("Write 'show' if you want to see all the clients in the list")
                        print("Write 'search' to search a client after his first and last name")
                        print("Write 'undo' to undo one action made in the client list")
                        print("Write 'update' to update the dates of a client")
                        print("Write 'random' to generate random a client")
                        print("-------------------------------------------------------------------")

                    def Contract_Menu(self):
                        """
                            Prints the commands for contracts and returns the command that the client picked as a string
                        """
                        print(" ")
                        print("---------------------------------------------------------------")
                        print(" Write 'back' to go back to the main menu")
                        print(" Write 'add' (a.k.a rent a movie) to add a new contract in the list")
                        print("Write 'remove' (a.k.a return a movie) to delete a contract from the list")
                        print("Write 'search' to searhc a contract after id")
                        print("Write 'show' if you want to see all the contracts in the list")
                        print("Write 'show name' to see clients with rented movies sorted after name")
                        print("Write 'show movie' to see the latest 10 movies ")
                        print("Write 'show rent' to see clients with rented movies sorted after the number of rents")
                        print("Write 'top' to see 5 most rented movies")
                        print("Write 'first' to see the first 30% clients")
                        print("----------------------------------------------------------------")*/
  -Laborator - o aplicatie de gestionare a problemelor si laboratoarelor pentru un student sau o grupa 
                  Operatii efectuate:
                  /*def start(self):
                        print("1.Students")
                        print("2.Search student after id")
                        print("3.Add lab")
                        print("4.Labs from a student")
                        print("5.Students afer lab")
                        print("x-Exit")*/
  -examen - o aplicatie pentru o echipa de fotbal. Importare jucatori se face dintr-un fisier exterior.
  /* def start(self):
        print('1.Afisare jucatori')
        print('2.Adaugare jucator')
        print('3.Modificare inaltime')
        print('4.Formare Echipa')
        print('5.Importare jucatori')
        print('x.Inchide aplicatia')*/
Aplicatii in C:
  -lab2-4(dinamic) iar lab2-4_static(static): o aplicatie care permite gestiunea ofertelor de la o agentie imobiliara.
Fiecare oferta are: tip (teren, casa, apartament), suprafata, adresa, pret
Aplicatia permite:
 a) Adaugarea de noi oferte. 
 b) Actualizare oferte
 c) Stergere oferta
 d) Vizualizare oferete ordonat dupa pret, tip (crescator/descrescator)
 e) Vizualizare oferta filtrate dupa un criteriu (suprafata, tip, pret)
